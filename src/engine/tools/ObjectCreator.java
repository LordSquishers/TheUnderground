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
import underground.block.BlockIDs;
import underground.textures.BlockTexture;
import underground.world.Block;

public class ObjectCreator {

    private static Loader loader;
    public static void setLoader(Loader loader) { ObjectCreator.loader = loader; }

    private static RawModel createBlockModel(int id) {
        return BlockIDs.getBlockModel(id);
    }

    public static RawModel createBlockModel(Vector3f colors) {
        RawModel model;

        float[] vertex = {0f, 0f, 0f}, color = {colors.x, colors.y, colors.z};
        model = loader.loadToVAO(vertex, color);

        return model;
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

    public static Block createBlock(Vector3f position, int id) {
        RawModel model = createBlockModel(id);
        BlockTexture texture = new BlockTexture();

        /*if(textureFiles != null) {
            for(int i = 0; i < textureFiles.length; i++) {
                texture.setTexture(i, new ModelTexture(loader.loadTexture(textureFiles[i])));
            }
        }*/

        return new Block(model, texture, position);
    }

}
