package underground.shaders.block;

import engine.shaders.ShaderProgram;
import engine.shaders.uniform.Uniform4F;
import engine.shaders.uniform.UniformBoolean;

import static underground.Ref.*;

public class BlockShader extends ShaderProgram {

    public Uniform4F projectionMatrix, viewMatrix, transformMatrix;
    public UniformBoolean[] enabledSides;

    public BlockShader() {
        super(createShaderPath(ShaderType.BLOCK, VERTEX_FILE, GAME_LOC), createShaderPath(ShaderType.BLOCK, FRAGMENT_FILE, GAME_LOC),
                createShaderPath(ShaderType.BLOCK, GEOMETRY_FILE, GAME_LOC));

    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "color");
    }

    @Override
    protected void intializeUniforms() {
        projectionMatrix = new Uniform4F("projectionMat", programID);
        viewMatrix = new Uniform4F("viewMat", programID);
        transformMatrix = new Uniform4F("transformMat", programID);

        enabledSides = new UniformBoolean[6];

        for(int i = 0; i < 6; i++) {
            enabledSides[i] = new UniformBoolean("sides[" + i + "]", programID);
        }
    }

    public void loadSides(boolean[] sides) {
        for(int i = 0; i < 6; i++) {
            enabledSides[i].load(sides[i]);
        }
    }
}
