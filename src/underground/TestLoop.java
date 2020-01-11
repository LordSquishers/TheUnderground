package underground;

import engine.display.DisplayManager;
import engine.entities.Camera;
import engine.entities.Light;
import engine.model.loader.Loader;
import engine.tools.ObjectCreator;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import underground.render.MasterRenderer;
import underground.world.Chunk;
import underground.world.Map;

public class TestLoop {

    public static void main(String[] args) {

        DisplayManager.createDisplay();
        Loader loader = new Loader();
        ObjectCreator.setLoader(loader);

        Map map = new Map(new Vector3f(0, 0, -10));

        Camera camera = new Camera();
        Light light = new Light(new Vector3f(0, 20, 200), new Vector3f(1, 1, 1));
        MasterRenderer renderer = new MasterRenderer(camera, light);

        map.setChunkAt(new Chunk(new Vector3f(0, 0, 0)).setAllBlocks(1), 0, 0, 0);
        map.setChunkAt(new Chunk(new Vector3f(0, 0, 0)).setAllBlocks(3), 1, 0, 0);

        //TODO- properly implement rendering with rest of engine

        while(!Display.isCloseRequested()){
            camera.move();

            renderer.setMap(map);

            renderer.render();

            DisplayManager.updateDisplay();
        }

        renderer.clean();
        loader.clean();
        DisplayManager.closeDisplay();

    }


}
