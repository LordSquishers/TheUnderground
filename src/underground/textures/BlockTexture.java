package underground.textures;

import engine.model.ModelTexture;

public class BlockTexture {

    private ModelTexture[] textures;
    private boolean isMultiTextured = false;
    private boolean isTransparent = false;

    public BlockTexture(ModelTexture[] textures) {
        this.textures = textures;
    }

    public boolean isTransparent() {
        return isTransparent;
    }

    public void setTransparent(boolean transparent) {
        isTransparent = transparent;
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

    public ModelTexture getTexture() {
        return textures[0];
    }

    public void setTextures(ModelTexture[] textures) {
        this.textures = textures;
    }

    public boolean isMultiTextured() {
        return isMultiTextured;
    }

    public void setMultiTextured(boolean multiTextured) {
        isMultiTextured = multiTextured;
    }
}
