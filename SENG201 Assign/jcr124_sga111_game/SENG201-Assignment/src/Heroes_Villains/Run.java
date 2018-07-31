package Heroes_Villains;

/**
 * Class called when the game is run
 */
public class Run {


    /**
     * Main method for running the game
     * @param args
     */
    public static void main(String[] args) {
        Game heroes_villains = new Game("Heroes and Villains", 1280, 720);
        heroes_villains.start();
    }
}
