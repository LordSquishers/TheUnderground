package underground.block;

import engine.model.RawModel;
import engine.tools.ObjectCreator;
import org.lwjgl.util.vector.Vector3f;
import underground.textures.BlockTexture;
import underground.world.Block;

import java.util.ArrayList;
import java.util.List;

public class BlockIDs {

    public static final int AIR_BLOCK = 0;
    public static final int PINK_BLOCK = 1;
    public static final int GREEN_BLOCK = 2;
    public static final int BLUE_BLOCK = 3;
    public static final int WHITE_BLOCK = 4;

    public static final List<Block> BLOCK_INSTANCES = new ArrayList<>();

    static {
        BLOCK_INSTANCES.add(AIR_BLOCK, null);
        BLOCK_INSTANCES.add(PINK_BLOCK, createBlock(new Vector3f(255f / 255, 189f / 255, 202f / 255)));
        BLOCK_INSTANCES.add(GREEN_BLOCK, createBlock(new Vector3f(86f / 255, 184f / 255, 109f / 255)));
        BLOCK_INSTANCES.add(BLUE_BLOCK, createBlock(new Vector3f(57f / 255, 144f / 255, 250f / 255)));
        BLOCK_INSTANCES.add(WHITE_BLOCK, createBlock(new Vector3f(0.9f, 0.9f, 0.9f)));
    }

    public static RawModel getBlockModel(int id) {
        return BLOCK_INSTANCES.get(id).getModel();
    }

    private static Block createBlock(Vector3f colors) { //TODO- use textures
        return new Block(ObjectCreator.createBlockModel(colors), new BlockTexture(), new Vector3f(0, 0, 0));
    }

}
