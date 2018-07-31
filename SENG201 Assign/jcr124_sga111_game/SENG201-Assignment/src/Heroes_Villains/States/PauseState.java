package Heroes_Villains.States;

import Heroes_Villains.Game;
import Heroes_Villains.SystemUI.RadioButtons;
import Heroes_Villains.SystemUI.UIButton;
import Heroes_Villains.SystemUI.UIElement;
import Heroes_Villains.graphics.Assets;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Class for the pause state of the game, entered when the escape key is pressed, allows user to go back to menu or controls state
 */
public class PauseState extends State{

    private UIElement returnButton, menuButton, controlsButton;

    /**
     * Constructor for pause state of the game, initializes three buttons for switching between states of the game
     * @param game game object, generic pass-through
     */
    public PauseState(Game game) {
        super(game);
        returnButton = new UIButton(540, 413, game, Assets.backButton, Assets.buttonWidth, Assets.buttonHeight);
        menuButton = new UIButton(540, 343, game, Assets.menuButton, Assets.buttonWidth, Assets.buttonHeight);
        controlsButton = new UIButton(540, 273, game, Assets.controlsButton, Assets.buttonWidth, Assets.buttonHeight);
    }

    @Override
    public void update() {
        returnButton.update();
        menuButton.update();
        controlsButton.update();
        if(game.getMouseListener().isLeftClicked() && menuButton.click()){
            game.getMouseListener().leftClicked = false;
            game.getStateHandler().setState(game.getMenuState());
        }
        if(game.getMouseListener().isLeftClicked() && returnButton.click()) {
            game.getMouseListener().leftClicked = false;
            game.getStateHandler().setState(game.getGameState());
        }
        if(game.getKeyboardListener().keyJustPressed(KeyEvent.VK_ESCAPE)) {
            game.getStateHandler().setState(game.getGameState());
        }
        if(game.getMouseListener().leftClicked && controlsButton.click()) {
            game.getMouseListener().leftClicked = false;
            game.getStateHandler().setState(game.getControlsState());
        }
    }

    @Override
    public void render(Graphics graphics) {
        returnButton.render(graphics);
        menuButton.render(graphics);
        controlsButton.render(graphics);
    }
}
