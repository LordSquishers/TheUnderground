package engine.shaders.entity;

import static underground.Ref.*;
import engine.shaders.ShaderProgram;
import engine.shaders.uniform.Uniform1F;
import engine.shaders.uniform.Uniform3F;
import engine.shaders.uniform.Uniform4F;

public class EntityShader extends ShaderProgram {

    public Uniform4F transformationMatrix, projectionMatrix, viewMatrix;
    public Uniform3F lightPosition, lightColor, skyColor;
    public Uniform1F ambienceLevel, reflectivity, shineDamper, useFakeLighting;

    public EntityShader() {
        super(createShaderPath(ShaderType.ENTITY, VERTEX_FILE, ENGINE_LOC), createShaderPath(ShaderType.ENTITY, FRAGMENT_FILE, ENGINE_LOC));
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "texCoords");
        super.bindAttribute(2, "normal");
    }

    @Override
    protected void intializeUniforms() {
        transformationMatrix = new Uniform4F("transformMat", programID);
        projectionMatrix = new Uniform4F("projectionMat", programID);
        viewMatrix = new Uniform4F("viewMat", programID);

        ambienceLevel = new Uniform1F("ambienceLevel", programID);
        lightPosition = new Uniform3F("lightPos", programID);
        lightColor = new Uniform3F("lightColor", programID);
        useFakeLighting = new Uniform1F("useFakeLighting", programID);

        reflectivity = new Uniform1F("reflectivity", programID);
        shineDamper = new Uniform1F("shineDamper", programID);
        skyColor = new Uniform3F("skyColor", programID);
    }

}
