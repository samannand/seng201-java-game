package Heroes_Villains.Listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Checks variables and events around mouse movement.
 */
public class MouseListener implements java.awt.event.MouseListener, MouseMotionListener {

    public boolean leftClicked, rightClicked;
    public int mousePosX, mousePosY;

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * Checks if a mouse event is left or right press and if it is
     * then set the corresponding variable to true.
     * @param e the input mouse event mouse pressed.
     */
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            leftClicked = true;
        }
        else if(e.getButton() == MouseEvent.BUTTON3) {
            rightClicked = true;
        }
    }

    /**
     * Checks if the mouse is hovering over a certain rectangle area.
     * @param x the x position of the rectangle corner.
     * @param y the y position of the rectangle corner.
     * @param width the width of the retangle.
     * @param height the height of the rectangle.
     * @return if the mouse is hovering over a rectangle area.
     */
    public boolean isHovering(int x, int y, int width, int height) {
        if(mousePosX >= x && mousePosX <= x + width) {
            if(mousePosY >= y && mousePosY <= y + height) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a mouse event is left or right release and if it is
     * then set the corresponding variable to true.
     * @param e the input mouse event mouse released.
     */
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            leftClicked = false;
        }
        else if(e.getButton() == MouseEvent.BUTTON3) {
            rightClicked = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    /**
     * Updates the x and y positions of the mouse every time the mouse is moved.
     * @param e the mouse moved event.
     */
    public void mouseMoved(MouseEvent e) {
        mousePosX = e.getX();
        mousePosY = e.getY();
    }
    //Getters

    /**
     * Returns if the left mouse buttion is currently clicked.
     * @return if the left buttion is clicked
     */
    public boolean isLeftClicked() {
        return leftClicked;
    }
}
