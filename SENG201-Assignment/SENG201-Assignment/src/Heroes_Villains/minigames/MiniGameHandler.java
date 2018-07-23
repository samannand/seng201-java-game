package Heroes_Villains.minigames;

import Heroes_Villains.Game;
import Heroes_Villains.utils.RandomNum;

/**
 * Class for the minigame handler of the whole game, allows the generation of a random new minigame
 */
public class MiniGameHandler {

    public MiniGame[] miniGames;
    public Game game;
    private int pickedGame;
    private int villainMove;

    /**
     * Constructor method for mini game handler
     * @param game game object, generic pass-through
     */
    public MiniGameHandler(Game game) {
        this.game = game;

    }

    /**
     * Method that returns a random new minigame, either guess the number, paper scissors rock, or dice roll
     * @return MiniGame object, a random minigame
     */
    public MiniGame getGame() {
        MiniGame miniGame;
        RandomNum randomNum;
        randomNum = new RandomNum();
            pickedGame = randomNum.getNum(3); //Get new game
            if(pickedGame == 0) {
                villainMove = randomNum.getNum(3); //Set random villain move within the correct bound
                miniGame = new PaperScissorsRock(villainMove, game);
                return miniGame;
            }
            else if(pickedGame == 1) {
                villainMove = randomNum.getNum(6) + 1; //Set random villain move within the correct bound
                miniGame = new DiceRoll(villainMove, game);
                return miniGame;
            }
            else{
                villainMove = randomNum.getNum(10) + 1; //Set random villain move within the correct bound
                miniGame = new GuessTheNumber(villainMove, game);
                return miniGame;
            }
        }

    /**
     * Method for returing the currently played minigame in a city
      * @param cityNum integer, index of the current city in the main city array
     * @return Minigame object, current mini game being played
     */
}
