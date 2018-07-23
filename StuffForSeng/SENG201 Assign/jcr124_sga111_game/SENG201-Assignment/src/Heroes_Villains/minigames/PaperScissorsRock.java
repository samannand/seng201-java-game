package Heroes_Villains.minigames;

import Heroes_Villains.Game;
import Heroes_Villains.States.BattleState;
import Heroes_Villains.SystemUI.RadioButtons;
import Heroes_Villains.SystemUI.UIButton;
import Heroes_Villains.SystemUI.UIElement;
import Heroes_Villains.graphics.Assets;
import Heroes_Villains.graphics.DrawText;
import Heroes_Villains.utils.RandomNum;

import java.awt.*;

/**
 * Class for the PaperScissorsRock mini game that gets played in the battle state of the game
 */
public class PaperScissorsRock extends MiniGame {

    private UIElement buttons, okButton;
    private int playerChoice;
    private boolean draw, played;
    private int currHero, radioTotalWidth;

    /**
     * PaperScissorsRock constructor method, defines a radio button cluster for selecting the choice
     * @param villainMove integer, the number value of the move that the villain will play
     * @param game game object, generic pass-through
     */
    public PaperScissorsRock(int villainMove, Game game) {
        super(villainMove, game, "Paper, Scissors, Rock");
        radioTotalWidth = (2)*25 + 100*3;
        okButton = new UIButton(640-Assets.buttonWidth/2, 450, game, Assets.battleStateOK, Assets.buttonWidth, Assets.buttonHeight);
        buttons = new RadioButtons(516-(radioTotalWidth/2), 509, game, Assets.invRadioButton, 3, 25, true, 100, 100);
        ((RadioButtons) buttons).clicked(0);
        villainLives = 3;
        currHero = battleState.getCurrHero();
        battleWon = false;
        draw = false;
        played = false;
        if(villainMove == 0) {
            this.villainMoveWords = "Paper";
        }else if(villainMove == 1) {
            this.villainMoveWords = "Scissors";
        }else {
            this.villainMoveWords = "Rock";
        }
    }

    @Override
    public void update() {
        currHero = battleState.getCurrHero();
        buttons.update();
        //goButton.update();
        if(played) { //If the player has selected a move
            played = false;
            playerChoice = ((RadioButtons) buttons).currentlyClicked;
            if(villainMove == playerChoice) {
                draw = true;
                ((RadioButtons) buttons).clicked(0);
            }else if(villainMove == 0 && playerChoice == 1) {
                ((BattleState) game.getBattleState()).won(currHero);
            }else if(villainMove == 1 && playerChoice == 2) {
                ((BattleState) game.getBattleState()).won(currHero);
            }else if(villainMove == 2 && playerChoice == 0) {
                ((BattleState) game.getBattleState()).won(currHero);
            }else {
                ((BattleState) game.getBattleState()).lost(currHero);
            }
            played = false;
        }
        if(game.getMouseListener().isHovering(55, 520, 150, 66)) {
            if(game.getMouseListener().leftClicked) {
                game.getMouseListener().leftClicked = false;
                played = true;
            }
        }
        if(draw) {
            okButton.update();
            played = false;
            if(okButton.click() && game.getMouseListener().leftClicked) {
                game.getMouseListener().leftClicked = false;
                battleState.currMiniGame = new PaperScissorsRock(RandomNum.getNum(3), game);
            }
        }
    }

    @Override
    public void render(Graphics graphics) {
        buttons.render(graphics);
        DrawText.draw(graphics, "Paper", 391, 559, true, Color.WHITE, Assets.tinyFont);
        DrawText.draw(graphics, "Scissors", 516, 559, true, Color.WHITE, Assets.tinyFont);
        DrawText.draw(graphics, "Rock", 641, 559, true, Color.WHITE, Assets.tinyFont);
        DrawText.draw(graphics, "Go", 130, 552, true, Color.WHITE, Assets.invFont);
        if(game.getMouseListener().isHovering(55, 520, 150, 66)) {
            DrawText.draw(graphics, "Go", 130, 552, true, Color.YELLOW, Assets.invFont);
        }
        if(draw) {
            graphics.drawImage(Assets.battlePopup, 384, 168, null);
            DrawText.draw(graphics, "You Both played:", 640, 250, true, Color.WHITE, Assets.invFont);
            DrawText.draw(graphics, villainMoveWords, 640, 300, true, Color.WHITE, Assets.invFont);
            DrawText.draw(graphics, "Try again", 640, 350, true, Color.WHITE, Assets.invFont);
            okButton.render(graphics);
        }
    }
}
