package underground.shaders.block;

import engine.shaders.ShaderProgram;
import engine.shaders.uniform.Uniform1F;
import engine.shaders.uniform.Uniform3F;
import engine.shaders.uniform.Uniform4F;
import engine.shaders.uniform.UniformBoolean;

import static underground.Ref.*;

public class BlockShader extends ShaderProgram {

    public Uniform4F projectionMatrix, viewMatrix, transformMatrix;
    public UniformBoolean[] enabledSides;

    public Uniform3F lightPosition, lightColor, skyColor;
    public Uniform1F ambienceLevel, reflectivity, shineDamper;

    public BlockShader() {
        super(createShaderPath(ShaderType.BLOCK, VERTEX_FILE, GAME_LOC), createShaderPath(ShaderType.BLOCK, FRAGMENT_FILE, GAME_LOC),
                createShaderPath(ShaderType.BLOCK, GEOMETRY_FILE, GAME_LOC));

    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "texCoords");
    }

    @Override
    protected void intializeUniforms() {
        projectionMatrix = new Uniform4F("projectionMat", programID);
        viewMatrix = new Uniform4F("viewMat", programID);
        transformMatrix = new Uniform4F("transformMat", programID);

        ambienceLevel = new Uniform1F("ambienceLevel", programID);
        lightPosition = new Uniform3F("lightPos", programID);
        lightColor = new Uniform3F("lightColor", programID);

        reflectivity = new Uniform1F("reflectivity", programID);
        shineDamper = new Uniform1F("shineDamper", programID);
        skyColor = new Uniform3F("skyColor", programID);

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
