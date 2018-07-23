package Heroes_Villains.minigames;

import Heroes_Villains.Game;
import Heroes_Villains.SystemUI.UIButton;
import Heroes_Villains.SystemUI.UIElement;
import Heroes_Villains.graphics.Assets;
import Heroes_Villains.graphics.DrawText;
import Heroes_Villains.utils.RandomNum;

import java.awt.*;

/**
 * Class for the dice roll game, player rolls and villains rolls, highest roll wins
 */
public class DiceRoll extends MiniGame {

    public int playerRoll, currHero;
    private boolean draw;
    private UIElement okButton;

    /**
     * Constructor for DiceRoll
     * @param villainMove integer of the villains roll
     * @param game game object, generic pass-through
     */
    public DiceRoll(int villainMove, Game game) {
        super(villainMove, game, "Dice Roll");
        this.villainMoveWords = Integer.toString(villainMove);
        okButton = new UIButton(640-Assets.buttonWidth/2, 450, game, Assets.battleStateOK, Assets.buttonWidth, Assets.buttonHeight);
        playerRoll = RandomNum.getNum(6)+1;
        currHero = battleState.getCurrHero();
        draw = false;
    }

    @Override
    public void update() {
        currHero = battleState.getCurrHero();
        if (game.getMouseListener().isHovering(55, 520, 150, 66) && game.getMouseListener().leftClicked) {
            game.getMouseListener().leftClicked = false;
            if (villainMove > playerRoll) {
                battleState.lost(currHero);
            } else if (villainMove == playerRoll) {
                draw = true;
                return;
            } else if (playerRoll > villainMove) {
                battleState.won(currHero);
            }

        }
        if (draw) { //If a draw create a popup and make player acknowledge
            okButton.update();
            if (okButton.click() && game.getMouseListener().leftClicked) {
                game.getMouseListener().leftClicked = false;
                battleState.currMiniGame = new DiceRoll(RandomNum.getNum(6), game);
            }
        }
    }

    @Override
    public void render(Graphics graphics) {

        DrawText.draw(graphics, "Your roll", 594, 250, true, Color.WHITE, Assets.tinyFont);
        DrawText.draw(graphics, Integer.toString(playerRoll), 594, 286, true, Color.WHITE, Assets.titleFont);
        DrawText.draw(graphics, "Go", 130, 552, true, Color.WHITE, Assets.invFont);
        if(game.getMouseListener().isHovering(55, 520, 150, 66)) {
            DrawText.draw(graphics, "Go", 130, 552, true, Color.YELLOW, Assets.invFont);
        }
        if(draw) {
            //Render Draw
            graphics.drawImage(Assets.battlePopup, 384, 168, null);
            DrawText.draw(graphics, "You Both rolled:", 640, 250, true, Color.WHITE, Assets.invFont);
            DrawText.draw(graphics, villainMoveWords, 640, 300, true, Color.WHITE, Assets.invFont);
            DrawText.draw(graphics, "Try again", 640, 350, true, Color.WHITE, Assets.invFont);
            okButton.render(graphics);
        }
    }
}
