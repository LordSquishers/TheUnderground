package underground.block;

import engine.model.RawModel;
import engine.tools.ObjectCreator;
import org.lwjgl.util.vector.Vector3f;
import underground.textures.BlockTexture;
import underground.world.Block;
import underground.world.Chunk;

import java.util.ArrayList;
import java.util.List;

public class BlockIDs {

    public static final int AIR_BLOCK = 0;
    public static final int PINK_BLOCK = 1;
    public static final int GREEN_BLOCK = 2;

    public static final List<Block> BLOCK_INSTANCES = new ArrayList<>();

    static {
        BLOCK_INSTANCES.add(AIR_BLOCK, null);
        BLOCK_INSTANCES.add(PINK_BLOCK, createBlock(new Vector3f(1, .82f, .86f)));
        BLOCK_INSTANCES.add(GREEN_BLOCK, createBlock(new Vector3f(0.196f, 0.658f, 0.312f)));
    }

    public static RawModel getBlockModel(int id) {
        return BLOCK_INSTANCES.get(id).getModel();
    }

    private static Block createBlock(Vector3f colors) { //TODO- use textures
        return new Block(ObjectCreator.createBlockModel(colors), new BlockTexture(), new Vector3f(0, 0, 0));
    }

}
