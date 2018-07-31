package Heroes_Villains.Listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

/**
 * Listens for keyboard inputs and updates the appropriate variables.
 * Implements KEyListener.
 */
public class KeyboardListener implements KeyListener {

    public boolean[] keys, justPressed, cantPress;

    public Boolean up, down, left, right;
    public Boolean arrowUp, arrowDown, arrowLeft, arrowRight, f, enter, delete, control, p;
    public Boolean esc;
    public Boolean invOpen;

    /**
     * Constructs the keyboard listener with an array of keys
     * with the index for the array to correspond to the key code of the key.
     */
    public KeyboardListener() {
        keys = new boolean[256];
        Arrays.fill(keys, false);
        invOpen = false;
        justPressed = new boolean[keys.length];
        cantPress = new boolean[keys.length];
    }

    /**
     * Checks which key were pressed last tick and which keys can no longer be clicked
     * the variables corresponding with the required key inputs are assigned to the current their key code
     * values in the keys array.
     */
    public void update() {
        for(int i=0; i<keys.length; i++) {
            if(cantPress[i] && !keys[i]) {//if the key is not being help down and the key cant be press
                cantPress[i] = false; //Key can be pressed
            }else if(justPressed[i]) { //If key just pressed
                cantPress[i] = true; //Key cant be pressed
                justPressed[i] = false; //Key is no longer just pressed
            }
            if(!cantPress[i] && keys[i]) { //If can press and is being pressed
                justPressed[i] = true; //KEy is just pressed
            }
        }
        //Setting key variables to keys array at index key
        esc = keys[KeyEvent.VK_ESCAPE];

        up = keys[KeyEvent.VK_W];
        left = keys[KeyEvent.VK_A];
        down = keys[KeyEvent.VK_S];
        right = keys[KeyEvent.VK_D];

        arrowUp = keys[KeyEvent.VK_UP];
        arrowDown = keys[KeyEvent.VK_DOWN];
        arrowLeft = keys[KeyEvent.VK_LEFT];
        arrowRight = keys[KeyEvent.VK_RIGHT];

        f = keys[KeyEvent.VK_F];

        enter = keys[KeyEvent.VK_ENTER];
        delete = keys[KeyEvent.VK_DELETE] || keys[KeyEvent.VK_BACK_SPACE];

        control = keys[KeyEvent.VK_CONTROL];
        p = keys[KeyEvent.VK_P];
    }

    /**
     * Checks if a key for a given key code was pressed last update call.
     * @param keyCode the code to check for the key.
     * @return if the key has just been pressed
     */
    public boolean keyJustPressed(int keyCode){
        if(keyCode < 0 || keyCode >= keys.length)
            return false;
        return justPressed[keyCode];
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //Unused
    }

    /**
     * Takes a key event and set that event to its corresponding index for pressed in the keys array.
     * @param e the key event of the key just pressed.
     */
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length) {
            return;
        }
        keys[e.getKeyCode()] = true;
    }

    /**
     * Takes a key event and set that event to its corresponding index for released in the keys array.
     * @param e the key event of the key just released.
     */
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length) {
            return;
        }
        keys[e.getKeyCode()] = false;
        if(e.getKeyCode() == KeyEvent.VK_UP) {

        }
    }
}
