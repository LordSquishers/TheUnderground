package underground;

import engine.display.DisplayManager;
import engine.entities.Camera;
import engine.entities.Entity;
import engine.model.RawModel;
import engine.model.loader.Loader;
import engine.tools.Maths;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import underground.render.BlockRenderer;
import underground.shaders.block.BlockShader;
import underground.world.Block;

public class TestLoop {

    public static void main(String[] args) {

        DisplayManager.createDisplay();
        Loader loader = new Loader();
        BlockShader shader = new BlockShader();
        BlockRenderer renderer = new BlockRenderer(shader);

        float[] vertices = {
                0f, 0f, 0f
        };

        float[] baseColors = {
                0f, 1f, 1f
        };

        RawModel model = loader.loadToVAO(vertices, baseColors);
        Block block = new Block(model, null, new Vector3f(0, 0, -5));
        block.setRotation(new Vector3f(0.5f, 0.5f, 0.5f));

        Camera camera = new Camera();

        while(!Display.isCloseRequested()){
            camera.move();

            renderer.prepare();
            shader.start();
            shader.viewMatrix.load(Maths.createViewMatrix(camera));
            renderer.render(block, shader);
            shader.stop();

            DisplayManager.updateDisplay();
        }

        shader.clean();
        loader.clean();
        DisplayManager.closeDisplay();

    }


}
