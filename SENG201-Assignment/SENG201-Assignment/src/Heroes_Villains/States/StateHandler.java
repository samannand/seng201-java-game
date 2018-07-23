package Heroes_Villains.States;


/**
 * Class for state handler that has the ability to change the state or get the last state of the game for navigation and progression throughout the game
 */
public class StateHandler {

    public static State state;
    public static State lastState;

    /**
     * Method for setting the game into a new state
     * @param newState State object that becomes the new state of the game
     */
    public void setState(State newState) {
        lastState = this.state;
        this.state = newState;
    }

    /**
     * Method for getting the state of the game
     * @return state object, current state of the game
     */
    public static State getState() {
        return state;
    }

    /**
     * Method for getting the previous state of the game
     * @return state object, previous state of the game
     */
    public static State getLastState() {
        return lastState;
    }
}
