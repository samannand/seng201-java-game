package Heroes_Villains.States;

import Heroes_Villains.Game;

import java.awt.*;

/**
 * Abstract parent class for all states of the game used to define required methods for children
 */
public abstract class State {

    protected Game game;

    /**
     * Contructer for state, makes a new state
     * @param game game object, standard pass-through
     */
    public State(Game game) {
        this.game = game;
    }

    /**
     * Abstract update method, this method gets defined properly in the children of state and gets ran 60 times per second. This method is used to provide the functionality to the states of the game.
     */
    public abstract void update();

    /**
     * Abstract render method, this method gets defined properly in the children of state and gets ran 60 times per second. This method is used to create the look of the states of the game.
     * @param graphics Graphics object, required to be able to use render methods of objects defined in the current state
     */
    public abstract void render(Graphics graphics);

}
