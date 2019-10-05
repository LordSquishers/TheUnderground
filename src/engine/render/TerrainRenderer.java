package engine.render;

import engine.entities.Entity;
import engine.model.TexturedModel;
import engine.shaders.terrain.TerrainShader;
import engine.terrain.Terrain;
import engine.tools.Maths;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import java.util.List;

import static engine.Ref.*;

public class TerrainRenderer {

    private TerrainShader terrainShader;
    private Matrix4f projectionMatrix;

    public TerrainRenderer(TerrainShader terrainShader, Matrix4f projectionMatrix) {
        this.terrainShader = terrainShader;
        this.projectionMatrix = projectionMatrix;

        terrainShader.start();
        terrainShader.projectionMatrix.load(projectionMatrix);
        terrainShader.ambienceLevel.load(AMBIENCE_LIGHT_LVL);
        terrainShader.stop();
    }

    public void render(List<Terrain> terrains) {
        for(Terrain terrain: terrains) {
            prepareTerrain(terrain);
            loadModelMatrix(terrain);

            GL11.glDrawElements(GL11.GL_TRIANGLES, terrain.getModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);

            unbindTexturedModel();
        }
    }

    private void prepareTerrain(Terrain terrain) {
        GL30.glBindVertexArray(terrain.getModel().getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL20.glEnableVertexAttribArray(2);

        terrainShader.shineDamper.load(terrain.getTexture().getShineDamper());
        terrainShader.reflectivity.load(terrain.getTexture().getReflectivity());

        GL13.glActiveTexture(terrain.getTexture().getTextureID());
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, terrain.getTexture().getTextureID());
    }

    private void unbindTexturedModel() {
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(2);
        GL30.glBindVertexArray(0);
    }

    private void loadModelMatrix(Terrain terrain) {
        Matrix4f transformationMatrix = Maths.createTransformationMatrix(new Vector3f(terrain.getX(), 0, terrain.getZ()), new Vector3f(0, 0, 0), 1);
        terrainShader.transformationMatrix.load(transformationMatrix);
    }

}
