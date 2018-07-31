package Heroes_Villains.display;

import javax.swing.*;
import java.awt.*;

/**
 * Class that creates the window, JFrame and canvas that the
 * game is rendered to.
 */
public class Display {

    private JFrame frame;
    private Canvas canvas;

    private String title;
    int width, height;

    /**
     * Constructor that takes variables to create the window.
     *
     * @param title the title to be displayed on the window.
     * @param width the width in pixels of the window and canvas.
     * @param height the height in pixels of the window and the canvas.
     */
    public Display(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        createDisplay();
    }

    /**
     * Creates the JFrame and canvas and sets the given width and heights.
     * Adds the canvas to the JFrame and packs the Frame.
     */
    private void createDisplay(){

        //JFrame creation and init
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Made the game shut down on close
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //Canvas creations and init
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false);

        //Adding canvas to frame
        frame.add(canvas);
        frame.pack();

    }

    /**
     * Returns the JFrame object.
     * @return JFrame object.
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Returns the canvas object.
     * @return Canvas object.
     */
    public Canvas getCanvas() {
        return canvas;
    }
}
