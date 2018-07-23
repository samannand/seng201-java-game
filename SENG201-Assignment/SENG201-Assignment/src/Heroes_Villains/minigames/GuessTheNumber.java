package Heroes_Villains.minigames;

import Heroes_Villains.Game;
import Heroes_Villains.SystemUI.RadioButtons;
import Heroes_Villains.SystemUI.UIElement;
import Heroes_Villains.graphics.Assets;
import Heroes_Villains.graphics.DrawText;

import java.awt.*;

/**
 * Class for the guess the number minigame, player can pick a number, and will then be told whether their guess was too high or too low, and has a final guess
 */
public class GuessTheNumber extends MiniGame {

    private UIElement numberSelector, playButton;
    public int numMoves;
    private int currHero, currClicked;
    private boolean lower, higher, playing;

    /**
     * Constructor for GuessTheNumber
     * @param villainMove integer, number of the villains move
     * @param game game object, generic pass-through
     */
    public GuessTheNumber(int villainMove, Game game) {
        super(villainMove, game, "Guess the number");
        numberSelector = new RadioButtons(235, 517, game, Assets.invRadioButton, 10, 7, true, 50, 50);
        ((RadioButtons) numberSelector).currentlyClicked = 0;
        ((RadioButtons) numberSelector).clicked(0);
        currClicked = ((RadioButtons) numberSelector).currentlyClicked;
        this.villainMoveWords = Integer.toString(villainMove);
        numMoves = 2;
        currHero = battleState.getCurrHero();
        playing = true;
    }

    @Override
    public void update() {
        currHero = battleState.getCurrHero();
        numberSelector.update();
        currClicked = ((RadioButtons) numberSelector).currentlyClicked;
        if((game.getMouseListener().isHovering(55, 520, 150, 66) && game.getMouseListener().leftClicked) && numMoves > 0 && playing) { //If the go button is clicked and the player still has turns and the game is still playing
            numMoves--;
            game.getMouseListener().leftClicked = false;
            if ((((RadioButtons) numberSelector).currentlyClicked + 1) == villainMove) {
                playing = false;
                battleState.won(currHero);
            } else if ((((RadioButtons) numberSelector).currentlyClicked + 1) > villainMove) {
                if (numMoves >= 1) {
                    lower = true;
                }
                playing = true; //Continue playing once
            } else if ((((RadioButtons) numberSelector).currentlyClicked + 1) < villainMove) {
                if (numMoves >= 1) {
                    higher = true;
                }
                playing = true; //Continue playing once
            }
            if(numMoves <= 0 && (((RadioButtons) numberSelector).currentlyClicked + 1 != villainMove)) {
                numMoves = 2;
                playing = false;
                battleState.lost(currHero);
            }
        }
    }

    @Override
    public void render(Graphics graphics) {
        numberSelector.render(graphics);
        DrawText.draw(graphics, "Go", 130, 552, true, Color.WHITE, Assets.invFont);
        if(game.getMouseListener().isHovering(55, 520, 150, 66)) {
            DrawText.draw(graphics, "Go", 130, 552, true, Color.YELLOW, Assets.invFont);
        }
        if(lower) {
            DrawText.draw(graphics, "To high" ,592, 262, true, Color.WHITE, Assets.battleFont);
        }else if(higher) {
            DrawText.draw(graphics, "To low" ,592, 262, true, Color.WHITE, Assets.battleFont);
        }
        for(int i=0; i<10; i++) {
            int x = i*50 + i*7 + 260;
            DrawText.draw(graphics, Integer.toString(i+1), x, 542, true, Color.WHITE, Assets.smallFont);
        }
    }
}
