package underground;

import engine.display.DisplayManager;
import engine.entities.Camera;
import engine.entities.Entity;
import engine.entities.Light;
import engine.model.RawModel;
import engine.model.loader.Loader;
import engine.tools.Maths;
import engine.tools.ObjectCreator;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import underground.block.BlockIDs;
import underground.render.BlockRenderer;
import underground.render.MasterRenderer;
import underground.shaders.block.BlockShader;
import underground.world.Block;
import underground.world.Chunk;

import java.util.ArrayList;
import java.util.List;

public class TestLoop {

    public static void main(String[] args) {

        DisplayManager.createDisplay();
        Loader loader = new Loader();
        ObjectCreator.setLoader(loader);

        List<Block> blocks = new ArrayList<>();
        List<Chunk> chunks = new ArrayList<>();

        Block test = ObjectCreator.createBlock(new Vector3f(-1.5f, 0, -5), 1);
        Block test1 = ObjectCreator.createBlock(new Vector3f(0, 0, -5), 1);
        Block test2 = ObjectCreator.createBlock(new Vector3f(1.5f, 0, -5), 1);

        test.setAllSides(true);
        test1.setAllSides(true);
        test2.setAllSides(true);

        blocks.add(test);
        blocks.add(test1);
        blocks.add(test2);

        Camera camera = new Camera();
        Light light = new Light(new Vector3f(0, 20, 200), new Vector3f(1, 1, 1));
        MasterRenderer renderer = new MasterRenderer(camera, light);

        Chunk chunk1 = new Chunk(new Vector3f(0, 0, -10));
        chunk1.setAllBlocks(1);
        chunk1.setBlockAt(2, 0, 0, 7);
        chunk1.setBlockAt(2, 1, 0, 7);
        chunk1.setBlockAt(2, 2, 0, 7);
        chunk1.setBlockAt(2, 3, 0, 7);
        chunk1.setBlockAt(0, 2, 4, 7);
        chunk1.setBlockAt(0, 3, 4, 7);
        chunk1.setBlockAt(0, 4, 4, 7);
        chunk1.setBlockAt(0, 5, 4, 7);
        chunks.add(chunk1);

        //TODO- properly implement rendering with rest of engine

        while(!Display.isCloseRequested()){
            camera.move();

            //renderer.addBlocks(blocks);
            renderer.addChunks(chunks);

            renderer.render();

            DisplayManager.updateDisplay();
        }

        renderer.clean();
        loader.clean();
        DisplayManager.closeDisplay();

    }


}
