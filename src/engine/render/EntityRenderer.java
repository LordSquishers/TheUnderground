package engine.render;

import engine.entities.Entity;
import engine.model.TexturedModel;
import engine.shaders.entity.EntityShader;
import engine.tools.Maths;
import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.Matrix4f;

import java.util.List;
import java.util.Map;

import static engine.Ref.*;

public class EntityRenderer {

    private EntityShader entityShader;

    public EntityRenderer(EntityShader shader, Matrix4f projectionMatrix) {
        this.entityShader = shader;
        shader.start();
        shader.projectionMatrix.load(projectionMatrix);
        shader.ambienceLevel.load(AMBIENCE_LIGHT_LVL);
        shader.stop();
    }

    public void render(Map<TexturedModel, List<Entity>> entities) {
        for(TexturedModel texturedModel: entities.keySet()) {
            prepareTexturedModel(texturedModel);
            List<Entity> batch = entities.get(texturedModel);

            for(Entity entity: batch) {
                prepareInstance(entity);
                GL11.glDrawElements(GL11.GL_TRIANGLES, texturedModel.getRawModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
            }

            unbindTexturedModel();
        }
    }

    private void prepareTexturedModel(TexturedModel texturedModel) {
        GL30.glBindVertexArray(texturedModel.getRawModel().getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL20.glEnableVertexAttribArray(2);

        entityShader.shineDamper.load(texturedModel.getModelTexture().getShineDamper());
        entityShader.reflectivity.load(texturedModel.getModelTexture().getReflectivity());

        if(texturedModel.getModelTexture().isTransparent()) MasterRenderer.disableCulling();
        entityShader.useFakeLighting.load(texturedModel.getModelTexture().isUseFakeLighting() ? 1f : 0f);

        GL13.glActiveTexture(texturedModel.getModelTexture().getTextureID());
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texturedModel.getModelTexture().getTextureID());
    }

    private void unbindTexturedModel() {
        MasterRenderer.enableCulling();
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(2);
        GL30.glBindVertexArray(0);
    }

    private void prepareInstance(Entity entity) {
        Matrix4f transformationMatrix = Maths.createTransformationMatrix(entity.getPosition(), entity.getRotation(), entity.getScale());
        entityShader.transformationMatrix.load(transformationMatrix);
    }

}
