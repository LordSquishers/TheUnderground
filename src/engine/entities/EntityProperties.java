package engine.entities;

public class EntityProperties {

    private float reflectivity = 0.0f, shineDamper = 1.0f;

    public float getReflectivity() {
        return reflectivity;
    }

    public EntityProperties setReflectivity(float reflectivity) {
        this.reflectivity = reflectivity;
        return this;
    }

    public float getShineDamper() {
        return shineDamper;
    }

    public EntityProperties setShineDamper(float shineDamper) {
        this.shineDamper = shineDamper;
        return this;
    }
}
