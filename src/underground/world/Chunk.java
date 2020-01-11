package underground.world;

import org.lwjgl.util.vector.Vector3f;
import underground.Ref;

public class Chunk {

    private int[][][] blocks;
    private Vector3f position;
    private boolean[] enabledSides = {true, true, true, true, true, true};
    private boolean isVisible = true;

    public Chunk(Vector3f position) {
        this.position = position;
        this.blocks = new int[Ref.CHUNK_SIZE][Ref.CHUNK_SIZE][Ref.CHUNK_SIZE];
    }

    public Chunk setAllBlocks(int id) {
        for (int x = 0; x < Ref.CHUNK_SIZE; x++) {
            for (int y = 0; y < Ref.CHUNK_SIZE; y++) {
                for (int z = 0; z < Ref.CHUNK_SIZE; z++) {
                    blocks[x][y][z] = id;
                }
            }
        }

        return this;
    }

    public void setEnabledSides(boolean[] enabledSides) {
        this.enabledSides = enabledSides;
    }

    public boolean[] getEnabledSides() {
        return enabledSides;
    }

    public void setChunkSide(int side, boolean isEnabled) {
        enabledSides[side] = isEnabled;
    }

    public void setBlocks(int[][][] blocks) {
        this.blocks = blocks;
    }

    public int[][][] getBlocks() {
        return this.blocks;
    }

    public void setBlockAt(int id, int x, int y, int z) {
        this.blocks[x][y][z] = id;
    }

    public int getBlockIDAt(int x, int y, int z) {
        return blocks[x][y][z];
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}
