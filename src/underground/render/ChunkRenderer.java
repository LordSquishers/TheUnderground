package underground.render;

import engine.tools.ObjectCreator;
import org.lwjgl.util.vector.Vector3f;
import underground.world.Block;
import underground.world.Chunk;

import java.util.ArrayList;
import java.util.List;

import static underground.Ref.*;

public class ChunkRenderer {

    public List<Block> createBlockListFromChunks(Vector3f mapPos, Chunk[][][] chunks) {
        List<Block> blockList = new ArrayList<>();

        for (int x = 0; x < MAP_SIZE; x++) {
            for (int y = 0; y < MAP_SIZE; y++) {
                for (int z = 0; z < MAP_SIZE; z++) {
                    if (chunks[x][y][z] != null) {
                        boolean[] sides = {true, true, true, true, true, true};

                        if(x > 0 && chunks[x - 1][y][z] != null) { // left of chunk
                            sides[LEFT] = false;
                        }
                        if(x < MAP_SIZE - 1 && chunks[x + 1][y][z] != null) { // right
                            sides[RIGHT] = false;
                        }
                        if(y > 0 && chunks[x][y - 1][z] != null) { // below of chunk
                            sides[BOTTOM] = false;
                        }
                        if(y < MAP_SIZE - 1 && chunks[x][y + 1][z] != null) { // above
                            sides[TOP] = false;
                        }
                        if(z > 0 && chunks[x][y][z - 1] != null) { // front of chunk
                            sides[BACK] = false;
                        }
                        if(z < MAP_SIZE - 1 && chunks[x][y][z + 1] != null) { // back
                            sides[FRONT] = false;
                        }
                        chunks[x][y][z].setEnabledSides(sides);

                        Vector3f chunkOffset = new Vector3f(x * CHUNK_SIZE, y * CHUNK_SIZE, z * CHUNK_SIZE);
                        blockList.addAll(createBlockListFromIDs(chunks[x][y][z].getPosition(), mapPos, chunkOffset, chunks[x][y][z].getBlocks(), chunks[x][y][z].getEnabledSides()));
                    }
                }
            }
        }

        return blockList;
    }

    public List<Block> createBlockListFromIDs(Vector3f chunkPos, Vector3f mapPos, Vector3f chunkOffset, int[][][] ids, boolean[] chunkSides) {
        List<Block> blockList = new ArrayList<>();

        for (int x = 0; x < CHUNK_SIZE; x++) {
            for (int y = 0; y < CHUNK_SIZE; y++) {
                for (int z = 0; z < CHUNK_SIZE; z++) {
                    if(ids[x][y][z] > 0) {
                        Block newBlock = ObjectCreator.createBlock(
                                new Vector3f(x + chunkPos.x + mapPos.x + chunkOffset.x, y + chunkPos.y + mapPos.y + chunkOffset.y, z + chunkPos.z + mapPos.z + chunkOffset.z),
                                ids[x][y][z]).setAllSides(true);
                        boolean[] sides = {true, true, true, true, true, true};

                        if(x > 0 && ids[x - 1][y][z] > 0) { // left of block
                            sides[LEFT] = false;
                        }
                        if(x < CHUNK_SIZE - 1 && ids[x + 1][y][z] > 0) { // right
                            sides[RIGHT] = false;
                        }
                        if(y > 0 && ids[x][y - 1][z] > 0) { // below of block
                            sides[BOTTOM] = false;
                        }
                        if(y < CHUNK_SIZE - 1 && ids[x][y + 1][z] > 0) { // above
                            sides[TOP] = false;
                        }
                        if(z > 0 && ids[x][y][z - 1] > 0) { // front of block
                            sides[BACK] = false;
                        }
                        if(z < CHUNK_SIZE - 1 && ids[x][y][z + 1] > 0) { // back
                            sides[FRONT] = false;
                        }

                        for (int i = 0; i < 6; i++) {
                            sides[i] = chunkSides[i] && sides[i];
                        }

                        newBlock.setSides(sides);
                        blockList.add(newBlock);
                    }
                }
            }
        }

        return blockList;
    }

}
