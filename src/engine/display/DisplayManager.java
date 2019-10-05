package engine.display;

import engine.Ref;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;

public class DisplayManager {

    public static void createDisplay() {
        ContextAttribs attribs = new ContextAttribs(3,2)
                .withForwardCompatible(true)
                .withProfileCore(true);

        try {
            Display.setDisplayMode(new DisplayMode(Ref.WINDOW_WIDTH, Ref.WINDOW_HEIGHT));
            Display.create(new PixelFormat(), attribs);
            Display.setTitle("Our First Display!");
        } catch (LWJGLException e) {
            e.printStackTrace();
        }

        GL11.glViewport(0,0, Ref.WINDOW_WIDTH, Ref.WINDOW_HEIGHT);
    }

    public static void updateDisplay() {
        Display.sync(Ref.WINDOW_FPS);
        Display.update();
    }

    public static void closeDisplay() {
        Display.destroy();
    }

}
