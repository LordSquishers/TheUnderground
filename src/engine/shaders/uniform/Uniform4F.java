package engine.shaders.uniform;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;

import java.nio.FloatBuffer;

public class Uniform4F extends Uniform<Matrix4f> {

    private static FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);

    public Uniform4F(String name, int programID) {
        super(name, programID);
    }

    @Override
    public void load(Matrix4f value) {
        value.store(matrixBuffer);
        matrixBuffer.flip();

        GL20.glUniformMatrix4(location, false, matrixBuffer);
    }
}
