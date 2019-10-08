package underground.render;

import engine.entities.Entity;
import engine.model.RawModel;
import engine.tools.Maths;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import underground.Ref;
import underground.shaders.block.BlockShader;
import underground.world.Block;

import static underground.Ref.*;

public class BlockRenderer {

    private Matrix4f projectionMatrix;

    public BlockRenderer(BlockShader shader){
        createProjectionMatrix();
        shader.start();
        shader.projectionMatrix.load(projectionMatrix);
        shader.stop();
    }

    public void prepare() {
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(SKY_COLOR_R, SKY_COLOR_G, SKY_COLOR_B, 1);
    }

    public void render(Block block, BlockShader shader) {
        RawModel rawModel = block.getModel();
        GL30.glBindVertexArray(rawModel.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        Matrix4f transformationMatrix = Maths.createTransformationMatrix(block.getPosition(),
                block.getRotation(), block.getScale());
        shader.transformMatrix.load(transformationMatrix);

        shader.loadSides(block.getSides());
        GL11.glDrawArrays(GL11.GL_POINTS, 0, rawModel.getVertexCount());
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL30.glBindVertexArray(0);
    }

    private void createProjectionMatrix(){
        projectionMatrix = new Matrix4f();
        float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
        float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))));
        float x_scale = y_scale / aspectRatio;
        float frustum_length = FAR_PLANE - NEAR_PLANE;

        projectionMatrix.m00 = x_scale;
        projectionMatrix.m11 = y_scale;
        projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
        projectionMatrix.m23 = -1;
        projectionMatrix.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustum_length);
        projectionMatrix.m33 = 0;
    }

}
