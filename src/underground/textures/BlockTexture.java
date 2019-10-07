package underground.textures;

import engine.model.ModelTexture;

public class BlockTexture {

    private ModelTexture[] textures;

    public BlockTexture(ModelTexture[] textures) {
        this.textures = textures;
    }

    public BlockTexture() {
        this.textures = new ModelTexture[6];
    }

    public void setTexture(int side, ModelTexture texture) {
        this.textures[side] = texture;
    }

    public ModelTexture getTexture(int side) {
        return textures[side];
    }

    public ModelTexture[] getTextures() {
        return textures;
    }

    public void setTextures(ModelTexture[] textures) {
        this.textures = textures;
    }
}
