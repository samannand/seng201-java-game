package Heroes_Villains.minigames;

import Heroes_Villains.Game;
import Heroes_Villains.States.BattleState;

import java.awt.*;


/**
 * Class for abstract MiniGame object, used to define abstract methods for passing down to children games
 */
public abstract class MiniGame {

    public int trys;
    public int villainLives;
    protected int villainMove;
    protected Game game;
    public String gameName;
    public String villainMoveWords;
    public int playerTurns;
    public boolean playing;
    public boolean won, battleWon;
    public static int DAMAGE_TAKEN = 30;
    protected BattleState battleState;

    /**
     * MiniGame constructor
     * @param villainMove integer, the move the villain will play
     * @param game  game object, generic pass-through
     * @param gameName Srtring, the name of the game, e.g. PaperScissorsRock
     */
    public MiniGame(int villainMove, Game game, String gameName) {
        this.villainMove = villainMove;
        this.game = game;
        this.gameName = gameName;
        this.battleState = ((BattleState) game.getBattleState());
    }

    /**
     * Abstract method for updating the functionality of the minigame 60 times a second, does the logical calculations regarding who wins and loses
     */
    public abstract void update();

    /**
     * Abstract method for rendering the aesthetics of the minigame 60 times a second, draws the components of the mini game to the passed in graphics object
     * @param graphics graphics object, used to draw on
     */
    public abstract void render(Graphics graphics);
}
