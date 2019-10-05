package underground.world;

import org.lwjgl.util.vector.Vector3f;

public class Chunk {

    private Block[][][] blocks;
    private Vector3f position;
    private boolean isVisible;

    public void setBlocks(Block[][][] blocks) {
        this.blocks = blocks;
    }

    public Block[][][] getBlocks() {
        return this.blocks;
    }

    public void setBlockAt(Block block, int x, int y, int z) {
        this.blocks[x][y][z] = block;
    }

    public Block getBlockAt(int x, int y, int z) {
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
