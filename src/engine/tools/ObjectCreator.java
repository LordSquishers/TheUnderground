package engine.tools;

import engine.entities.Entity;
import engine.entities.EntityProperties;
import engine.model.ModelTexture;
import engine.model.RawModel;
import engine.model.TexturedModel;
import engine.model.loader.Loader;
import engine.model.loader.ModelData;
import engine.model.loader.OBJFileLoader;
import engine.terrain.Terrain;
import org.lwjgl.util.vector.Vector3f;
import underground.Ref;
import underground.textures.BlockTexture;
import underground.world.Block;

public class ObjectCreator {

    private static Loader loader;

    public static RawModel blockModel = null;

    public static void setLoader(Loader loader) { ObjectCreator.loader = loader; }

    private static void createBlockModel() {
        if(blockModel == null) {
            float[] vertex = {0f, 0f, 0f}, color = {1, .82f, .86f};
            blockModel = loader.loadToVAO(vertex, color);
        }
    }

    public static Entity createEntity(String modelFile, String textureFile, Vector3f pos, Vector3f rot, float scale, EntityProperties entityProperties) {
        ModelData mdata = OBJFileLoader.loadOBJ(modelFile);
        ModelTexture texture = new ModelTexture(loader.loadTexture(textureFile));
        texture.setShineDamper(entityProperties.getShineDamper());
        texture.setReflectivity(entityProperties.getReflectivity());

        RawModel rawModel = loader.loadToVAO(mdata.getVertices(), mdata.getIndices(), mdata.getTextureCoords(), mdata.getNormals());
        TexturedModel tmodel = new TexturedModel(rawModel, texture);

        return new Entity(tmodel, pos, rot, scale);
    }

    public static Terrain createTerrain(int gridX, int gridZ, String texturePath) {
        return new Terrain(gridX, gridZ, loader, new ModelTexture(loader.loadTexture(texturePath)));
    }

    public static Block createBlock(Vector3f position, String[] textureFiles) {
        createBlockModel();
        BlockTexture texture = new BlockTexture();

        if(textureFiles != null) {
            for(int i = 0; i < textureFiles.length; i++) {
                texture.setTexture(i, new ModelTexture(loader.loadTexture(textureFiles[i])));
            }
        }

        return new Block(blockModel, texture, position);
    }

}
