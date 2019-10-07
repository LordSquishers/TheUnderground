package underground;

import engine.display.DisplayManager;
import engine.entities.Camera;
import engine.entities.Entity;
import engine.entities.EntityProperties;
import engine.entities.Light;
import engine.model.loader.Loader;
import engine.render.MasterRenderer;
import engine.terrain.Terrain;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import java.util.ArrayList;
import java.util.List;

import static engine.tools.ObjectCreator.*;

public class GameLoop {

    public static void main(String[] args) {
        DisplayManager.createDisplay();

        // Rendering
        Loader loader = new Loader();
        setLoader(loader);
        List<Entity> entities = new ArrayList<>();
        List<Terrain> terrains = new ArrayList<>();

        Light light = new Light(new Vector3f(0, 500, 2000), new Vector3f(1, 1, 1));

        Entity entity = createEntity("dragon", "white", new Vector3f(0, 0, -20), new Vector3f(0, 0, 0), 1f,
                new EntityProperties().setReflectivity(0.0f).setShineDamper(10.0f));
        entities.add(entity);
        Entity entity2 = createEntity("dragon", "white", new Vector3f(0, 0, -100), new Vector3f(0, 0, 0), 1f,
                new EntityProperties().setReflectivity(0.0f).setShineDamper(10.0f));
        entities.add(entity2);
        Entity entity3 = createEntity("dragon", "white", new Vector3f(0, 0, -200), new Vector3f(0, 0, 0), 1f,
                new EntityProperties().setReflectivity(0.0f).setShineDamper(10.0f));
        entities.add(entity3);
        Entity entity4 = createEntity("dragon", "white", new Vector3f(0, 0, -300), new Vector3f(0, 0, 0), 1f,
                new EntityProperties().setReflectivity(0.0f).setShineDamper(10.0f));
        entities.add(entity4);

        terrains.add(createTerrain(-1, -1, "white"));
        terrains.add(createTerrain(0, -1, "white"));

        Camera camera = new Camera();
        camera.setPosition(new Vector3f(0, 5, 0));

        MasterRenderer renderer = new MasterRenderer(light, camera);
        while(!Display.isCloseRequested()) {

            // LOGIC
            entity.increaseRotation(new Vector3f(0, 0.1f, 0));
            camera.move();

            renderer.processEntities(entities);
            renderer.processTerrains(terrains);
            // END LOGIC


            // RENDERING
            renderer.render();

            DisplayManager.updateDisplay();
            // END RENDERING
        }

        renderer.clean();
        loader.clean();
        DisplayManager.closeDisplay();
    }

}
