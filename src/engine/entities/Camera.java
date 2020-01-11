package engine.entities;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;
import underground.Ref;

import static org.lwjgl.input.Keyboard.*;
import static underground.Ref.*;

public class Camera {

    private Vector3f position;
    private float pitch, yaw, roll;

    public Camera() {
        this.position = new Vector3f(0, 0, 0);
        this.pitch = 0;
        this.yaw = 0;
        this.roll = 0;
    }

    public void move() {
        float moveMultiplier = isKeyDown(KEY_LSHIFT) ? MOVE_MULTIPLIER : 1;

        if(isKeyDown(KEY_W)) {
            position.z -= CAMERA_MOVE_SPEED * moveMultiplier;
        } if(isKeyDown(KEY_S)) {
            position.z += CAMERA_MOVE_SPEED * moveMultiplier;
        } if(isKeyDown(KEY_A)) {
            position.x -= CAMERA_MOVE_SPEED * moveMultiplier;
        } if(isKeyDown(KEY_D)) {
            position.x += CAMERA_MOVE_SPEED * moveMultiplier;
        } if(isKeyDown(KEY_Q)) {
            position.y -= CAMERA_MOVE_SPEED * moveMultiplier;
        } if(isKeyDown(KEY_E)) {
            position.y += CAMERA_MOVE_SPEED * moveMultiplier;
        }
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public float getRoll() {
        return roll;
    }

    public void setRoll(float roll) {
        this.roll = roll;
    }
}
