package underground;

public class Ref {

    public static final int WINDOW_WIDTH = 1280;
    public static final int WINDOW_HEIGHT = 720;
    public static final String WINDOW_TITLE = "The Underground";
    public static final int WINDOW_FPS = 120;

    public static final float SKY_COLOR_R = 0.8f;
    public static final float SKY_COLOR_G = 0.8f;
    public static final float SKY_COLOR_B = 0.8f;

    // Rendering
    public static final float FOV = 70;
    public static final float NEAR_PLANE = 0.1f;
    public static final float FAR_PLANE = 1000f;

    public static final float CAMERA_MOVE_SPEED = 0.05f;
    public static final float MOVE_MULTIPLIER = 3f;
    public static final float AMBIENCE_LIGHT_LVL = 0.15f;

    // Blocks
    public static final int TOP = 0;
    public static final int BOTTOM = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    public static final int FRONT = 4;
    public static final int BACK = 5;

    // Terrain
    public static final float SIZE = 800;
    public static final int VERTEX_COUNT = 128;

    // Chunks
    public static final int CHUNK_SIZE = 8;
    public static final int MAP_SIZE = 10;

    // Shaders
    public static final int VERTEX_FILE = 0;
    public static final int FRAGMENT_FILE = 1;
    public static final int GEOMETRY_FILE = 2;

    public static final int ENGINE_LOC = 1;
    public static final int GAME_LOC = 2;

    private static final String VERTEX_EXT = "VS.glsl";
    private static final String FRAGMENT_EXT = "FS.glsl";
    private static final String GEOMETRY_EXT = "GS.glsl";

    public enum ShaderType {
        ENTITY("entity"), TERRAIN("terrain"), BLOCK("block");

        private String name;
        ShaderType(String n) { this.name = n; }
        String getName() { return name; }
    }

    public static String createShaderPath(ShaderType type, int shader, int dir) {
        if(shader == VERTEX_FILE) return "src/" +  (dir == 1 ? "engine" : "underground") + "/shaders/" + type.getName() + "/" + type.getName() + VERTEX_EXT;
        if(shader == FRAGMENT_FILE) return "src/" +  (dir == 1 ? "engine" : "underground") + "/shaders/" + type.getName() + "/" + type.getName() + FRAGMENT_EXT;
        if(shader == GEOMETRY_FILE) return "src/" +  (dir == 1 ? "engine" : "underground") + "/shaders/" + type.getName() + "/" + type.getName() + GEOMETRY_EXT;

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
