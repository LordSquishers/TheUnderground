package underground.world;

import engine.model.ModelTexture;
import engine.model.RawModel;
import org.lwjgl.util.vector.Vector3f;
import underground.textures.BlockTexture;

public class Block {

    private Vector3f position, rotation;
    private float scale;

    private RawModel model;
    private BlockTexture texture;
    private boolean[] sides;

    private boolean isMultiTextured;
    private boolean isEnabled;

    public Block(RawModel model, BlockTexture texture, Vector3f position) {
        this.position = position;
        this.model = model;
        this.texture = texture;

        this.sides = new boolean[6];

        this.rotation = new Vector3f(0, 0, 0);
        this.scale = 1.0f;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public RawModel getModel() {
        return model;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public BlockTexture getTexture() {
        return texture;
    }

    public void setTexture(BlockTexture texture) {
        this.texture = texture;
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

    public Block setAllSides(boolean value) {
        for(int i = 0; i < 6; i++) {
            sides[i] = value;
        }

        return this;
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
