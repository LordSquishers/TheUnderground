package underground.world;

public class Map {

    private Chunk[][][] chunks;

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
