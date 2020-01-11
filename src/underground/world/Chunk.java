package underground.world;

import org.lwjgl.util.vector.Vector3f;
import underground.Ref;

public class Chunk {

    private int[][][] blocks;
    private Vector3f position;
    private boolean isVisible = true;

    public Chunk(Vector3f position) {
        this.position = position;
        this.blocks = new int[Ref.CHUNK_SIZE][Ref.CHUNK_SIZE][Ref.CHUNK_SIZE];
    }

    public void setAllBlocks(int id) {
        for (int x = 0; x < Ref.CHUNK_SIZE; x++) {
            for (int y = 0; y < Ref.CHUNK_SIZE; y++) {
                for (int z = 0; z < Ref.CHUNK_SIZE; z++) {
                    blocks[x][y][z] = id;
                }
            }
        }
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
