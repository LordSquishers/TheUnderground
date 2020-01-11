package underground.world;

import org.lwjgl.util.vector.Vector3f;
import underground.Ref;

public class Map {

    private Chunk[][][] chunks;
    private Vector3f position;

    public Map(Vector3f position) {
        this.chunks = new Chunk[Ref.MAP_SIZE][Ref.MAP_SIZE][Ref.MAP_SIZE];
        this.position = position;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public void setChunks(Chunk[][][] blocks) {
        this.chunks = blocks;
    }

    public Chunk[][][] getChunks() {
        return this.chunks;
    }

    public void setChunkAt(Chunk chunk, int x, int y, int z) {
        this.chunks[x][y][z] = chunk;
    }

    public Chunk getChunkAt(int x, int y, int z) {
        return chunks[x][y][z];
    }

}
