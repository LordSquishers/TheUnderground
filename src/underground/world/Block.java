package underground.world;

import engine.model.ModelTexture;
import org.lwjgl.util.vector.Vector3f;

public class Block {

    private Vector3f position;
    private ModelTexture[] textures;
    private boolean[] sides;

    private boolean isMultiTextured;
    private boolean isEnabled;

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
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

    public void setTexture(ModelTexture texture, int side) {
        this.textures[side] = texture;
    }

    public boolean[] getSides() {
        return sides;
    }

    public void checkEnabled() {
        boolean isDisabled = true;
        for(boolean side: sides) isDisabled = !side && isDisabled;
        if(isDisabled) isEnabled = false;
    }

    public void setSide(int side, boolean enabled) {
        this.sides[side] = enabled;
        checkEnabled();
    }

    public void setSides(boolean[] sides) {
        this.sides = sides;
        checkEnabled();
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public boolean isMultiTextured() {
        return isMultiTextured;
    }

    public void setMultiTextured(boolean multiTextured) {
        isMultiTextured = multiTextured;
    }
}
