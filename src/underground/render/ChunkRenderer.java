package underground.render;

import engine.tools.ObjectCreator;
import org.lwjgl.util.vector.Vector3f;
import underground.Ref;
import underground.world.Block;
import underground.world.Chunk;

import java.util.ArrayList;
import java.util.List;

public class ChunkRenderer {

    public List<Block> createBlockListFromChunks(List<Chunk> chunks) {
        List<Block> blockList = new ArrayList<>();

        for(Chunk c: chunks) {
            blockList.addAll(createBlockListFromIDs(c.getBlocks(), c.getPosition()));
            //System.out.println("chunk");
        }

        return blockList;
    }

    public List<Block> createBlockListFromIDs(int[][][] ids, Vector3f chunkPos) {
        List<Block> blockList = new ArrayList<>();

        for (int x = 0; x < Ref.CHUNK_SIZE; x++) {
            for (int y = 0; y < Ref.CHUNK_SIZE; y++) {
                for (int z = 0; z < Ref.CHUNK_SIZE; z++) {
                    if(ids[x][y][z] > 0) {
                        Block newBlock = ObjectCreator.createBlock(new Vector3f(x + chunkPos.x, y + chunkPos.y, z + chunkPos.z), null, ids[x][y][z])
                                .setAllSides(true);

                        if(x > 0 && ids[x - 1][y][z] > 0) { // left of block
                            newBlock.setSide(Ref.LEFT, false);
                        }
                        if(x < Ref.CHUNK_SIZE - 1 && ids[x + 1][y][z] > 0) { // right
                            newBlock.setSide(Ref.RIGHT, false);
                        }
                        if(y > 0 && ids[x][y - 1][z] > 0) { // below of block
                            newBlock.setSide(Ref.BOTTOM, false);
                        }
                        if(y < Ref.CHUNK_SIZE - 1 && ids[x][y + 1][z] > 0) { // above
                            newBlock.setSide(Ref.TOP, false);
                        }
                        if(z > 0 && ids[x][y][z - 1] > 0) { // front of block
                            newBlock.setSide(Ref.BACK, false);
                        }
                        if(z < Ref.CHUNK_SIZE - 1 && ids[x][y][z + 1] > 0) { // back
                            newBlock.setSide(Ref.FRONT, false);
                        }

                        blockList.add(newBlock);
                    }
                }
            }
        }

        return blockList;
    }

}
