package underground;

import engine.display.DisplayManager;
import engine.entities.Camera;
import engine.entities.Entity;
import engine.model.RawModel;
import engine.model.loader.Loader;
import engine.tools.Maths;
import engine.tools.ObjectCreator;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import underground.render.BlockRenderer;
import underground.shaders.block.BlockShader;
import underground.world.Block;

public class TestLoop {

    public static void main(String[] args) {

        DisplayManager.createDisplay();
        Loader loader = new Loader();
        ObjectCreator.setLoader(loader);

        BlockShader shader = new BlockShader();
        BlockRenderer renderer = new BlockRenderer(shader);

        Block test = ObjectCreator.createBlock(new Vector3f(-1.5f, 0, -5), null);
        Block test1 = ObjectCreator.createBlock(new Vector3f(0, 0, -5), null);
        Block test2 = ObjectCreator.createBlock(new Vector3f(1.5f, 0, -5), null);

        Camera camera = new Camera();

        //TODO- properly implement rendering with rest of engine

        while(!Display.isCloseRequested()){
            camera.move();

            renderer.prepare();
            shader.start();
            shader.viewMatrix.load(Maths.createViewMatrix(camera));
            renderer.render(test1, shader);
            renderer.render(test2, shader);
            renderer.render(test, shader);
            shader.stop();

            DisplayManager.updateDisplay();
        }

        shader.clean();
        loader.clean();
        DisplayManager.closeDisplay();

    }


}
