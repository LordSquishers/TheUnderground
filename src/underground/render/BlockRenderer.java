package underground.render;

import engine.model.RawModel;
import engine.tools.Maths;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import underground.shaders.block.BlockShader;
import underground.world.Block;

import java.util.List;

public class BlockRenderer {

    private BlockShader blockShader;

    public BlockRenderer(BlockShader blockShader){
        this.blockShader = blockShader;
    }

    public void render(List<Block> blocks) {
        for(Block block: blocks) {
            RawModel rawModel = block.getModel();
            GL30.glBindVertexArray(rawModel.getVaoID());
            GL20.glEnableVertexAttribArray(0);
            GL20.glEnableVertexAttribArray(1);

            Matrix4f transformationMatrix = Maths.createTransformationMatrix(block.getPosition(),
                    block.getRotation(), block.getScale());
            blockShader.transformMatrix.load(transformationMatrix);

            blockShader.loadSides(block.getSides());
            GL11.glDrawArrays(GL11.GL_POINTS, 0, rawModel.getVertexCount());

            GL20.glDisableVertexAttribArray(0);
            GL20.glDisableVertexAttribArray(1);
            GL30.glBindVertexArray(0);
        }
    }

}
