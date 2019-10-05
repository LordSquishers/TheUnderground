package engine;

public class Ref {

    public static final int WINDOW_WIDTH = 1280;
    public static final int WINDOW_HEIGHT = 720;
    public static final String WINDOW_TITLE = "The Underground";
    public static final int WINDOW_FPS = 120;

    public static float SKY_COLOR_R = 0.8f;
    public static float SKY_COLOR_G = 0.8f;
    public static float SKY_COLOR_B = 0.8f;

    // Rendering
    public static final float FOV = 70;
    public static final float NEAR_PLANE = 0.1f;
    public static final float FAR_PLANE = 1000f;

    public static final float CAMERA_MOVE_SPEED = 0.05f;
    public static final float MOVE_MULTIPLIER = 3f;
    public static final float AMBIENCE_LIGHT_LVL = 0.15f;

    // Terrain
    public static final float SIZE = 800;
    public static final int VERTEX_COUNT = 128;

    // Shaders
    public static final int VERTEX_FILE = 0;
    public static final int FRAGMENT_FILE = 1;
    public static final String SHADER_LOC = "src/engine/shaders/";
    public static final String VERTEX_EXT = "VS.glsl";
    public static final String FRAGMENT_EXT = "FS.glsl";

    public enum ShaderType {
        ENTITY("entity"), TERRAIN("terrain");

        private String name;
        ShaderType(String n) { this.name = n; }
        String getName() { return name; }
    }

    public static String createShaderPath(ShaderType type, int shader) {
        if(shader == VERTEX_FILE) return SHADER_LOC + type.getName() + "/" + type.getName() + VERTEX_EXT;
        if(shader == FRAGMENT_FILE) return SHADER_LOC + type.getName() + "/" + type.getName() + FRAGMENT_EXT;

        return null;
    }

    // Assets
    public static final String RES_LOC = "res/";

    public enum ResourceType {
        TEXTURES("textures"), MODELS("models");

        private String name;
        ResourceType(String n) { this.name = n; }
        String getName() { return name; }
    }

    public enum ResourceExt {
        TEXTURES("png"), MODELS("obj");

        private String name;
        ResourceExt(String n) { this.name = n; }
        String getName() { return name; }
    }

    public static String createResourcePath(ResourceType type, ResourceExt ext, String fileName) {
        return RES_LOC + type.getName() + "/" + fileName + "." + ext.getName();
    }
}
