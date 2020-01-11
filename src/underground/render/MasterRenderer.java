package underground.render;

import engine.entities.Camera;
import engine.entities.Light;
import engine.tools.Maths;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;
import underground.shaders.block.BlockShader;
import underground.world.Block;
import underground.world.Chunk;
import underground.world.Map;

import java.util.ArrayList;
import java.util.List;

import static underground.Ref.*;

public class MasterRenderer {

    private BlockRenderer blockRenderer;
    private BlockShader blockShader = new BlockShader();

    private ChunkRenderer chunkRenderer;

    private Camera camera;

    private Matrix4f projectionMatrix;

    private List<Block> blocks = new ArrayList<>();
    private Map map;

    public MasterRenderer(Camera camera, Light mainLight) {
        createProjectionMatrix();

        this.camera = camera;

        blockRenderer = new BlockRenderer(blockShader);
        chunkRenderer = new ChunkRenderer();
    }

    public void addBlock(Block block) { this.blocks.add(block); }

    public void addBlocks(List<Block> blocks) { this.blocks.addAll(blocks); }

    public void setMap(Map map) { this.map = map; }

    private void prepare() {
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(SKY_COLOR_R, SKY_COLOR_G, SKY_COLOR_B, 1);
    }

    public void render() {
        prepare();

        blocks.addAll(chunkRenderer.createBlockListFromChunks(map.getPosition(), map.getChunks()));
        renderBlocks();
    }

    private void renderBlocks() {
        blockShader.start();

        blockShader.viewMatrix.load(Maths.createViewMatrix(camera));
        blockShader.projectionMatrix.load(projectionMatrix);
        blockShader.ambientLighting.load(AMBIENCE_LIGHT_LVL);

        blockRenderer.render(blocks);
        blockShader.stop();

        blocks.clear();
    }

    public void clean() {
        blockShader.clean();
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
