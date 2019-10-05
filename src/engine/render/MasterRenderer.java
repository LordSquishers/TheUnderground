package engine.render;

import engine.entities.Camera;
import engine.entities.Entity;
import engine.entities.Light;
import engine.model.TexturedModel;
import engine.shaders.entity.EntityShader;
import engine.shaders.terrain.TerrainShader;
import engine.terrain.Terrain;
import engine.tools.Maths;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static underground.Ref.*;

public class MasterRenderer {

    private EntityShader entityShader = new EntityShader();
    private EntityRenderer entityRenderer;

    private TerrainShader terrainShader = new TerrainShader();
    private TerrainRenderer terrainRenderer;

    private Map<TexturedModel, List<Entity>> entities = new HashMap<>();
    private List<Terrain> terrains = new ArrayList<>();

    private Light mainLight;
    private Camera camera;
    private Matrix4f projectionMatrix;

    public MasterRenderer(Light mainLight, Camera camera) {
        enableCulling();

        this.camera = camera;
        this.mainLight = mainLight;
        createProjectionMatrix();

        entityRenderer = new EntityRenderer(entityShader, projectionMatrix);
        terrainRenderer = new TerrainRenderer(terrainShader, projectionMatrix);
    }

    private void prepare() {
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(SKY_COLOR_R, SKY_COLOR_G, SKY_COLOR_B, 1);
    }

    public void render() {
        prepare();

        renderEntities();
        renderTerrains();
    }

    private void renderEntities() {
        entityShader.start();

        entityShader.lightColor.load(mainLight.getColor());
        entityShader.lightPosition.load(mainLight.getPosition());
        entityShader.viewMatrix.load(Maths.createViewMatrix(camera));
        entityShader.skyColor.load(new Vector3f(SKY_COLOR_R, SKY_COLOR_G, SKY_COLOR_B));

        entityRenderer.render(entities);

        entityShader.stop();
        entities.clear();
    }

    private void renderTerrains() {
        terrainShader.start();

        terrainShader.lightColor.load(mainLight.getColor());
        terrainShader.lightPosition.load(mainLight.getPosition());
        terrainShader.viewMatrix.load(Maths.createViewMatrix(camera));
        terrainShader.skyColor.load(new Vector3f(SKY_COLOR_R, SKY_COLOR_G, SKY_COLOR_B));

        terrainRenderer.render(terrains);

        terrainShader.stop();
        terrains.clear();
    }

    public static void enableCulling() {
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glCullFace(GL11.GL_BACK);
    }

    public static void disableCulling() {
        GL11.glDisable(GL11.GL_CULL_FACE);
    }

    public void processTerrain(Terrain terrain) {
        terrains.add(terrain);
    }

    public void processTerrains(List<Terrain> terrains) {
        this.terrains.addAll(terrains);
    }

    public void processEntities(List<Entity> es) {
        for(Entity e: es) processEntity(e);
    }

    public void processEntity(Entity entity) {
        TexturedModel entityModel = entity.getModel();
        List<Entity> batch = entities.get(entityModel);

        if(batch != null) {
            batch.add(entity);
        } else {
            List<Entity> newBatch = new ArrayList<>();
            newBatch.add(entity);
            entities.put(entityModel, newBatch);
        }
    }

    public void clean() {
        entityShader.clean();
        terrainShader.clean();
    }

    private void createProjectionMatrix() {
        float aspectRatio = (float) WINDOW_WIDTH / (float) WINDOW_HEIGHT;
        float yScale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))) * aspectRatio);
        float xScale = yScale / aspectRatio;
        float frustumLength = FAR_PLANE - NEAR_PLANE;

        projectionMatrix = new Matrix4f();
        projectionMatrix.m00 = xScale;
        projectionMatrix.m11 = yScale;
        projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustumLength);
        projectionMatrix.m23 = -1;
        projectionMatrix.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustumLength);
        projectionMatrix.m33 = 0;
    }

}
