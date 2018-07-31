package Heroes_Villains.States;

import Heroes_Villains.Game;
import Heroes_Villains.SystemUI.RadioButtons;
import Heroes_Villains.SystemUI.TextField;
import Heroes_Villains.SystemUI.UIButton;
import Heroes_Villains.SystemUI.UIElement;
import Heroes_Villains.cities.Citys;
import Heroes_Villains.cities.rooms.Rooms;
import Heroes_Villains.graphics.Assets;
import Heroes_Villains.graphics.DrawText;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Class of admin state of the game, used to bring up an admin control pane when the team is named 'ADMIN'
 */
public class AdminState extends State{

    public int buttonClicked;
    private Rooms[] currRooms;
    private UIElement up, down, left, right;
    private Color colour;
    private Citys citysClass;
    private BattleState battleState;
    private Heroes_Villains.SystemUI.TextField healthField;
    private RadioButtons heroSelector;

    /**
     * Contructor for admie state, generates radio buttons for selecting heros to add health to using a text field, and buttons for teleporting and seeing villain moves
     * @param game game object, generic pass-through
     * @param citysClass Citys object
     * @param battleState the current battlestate of the game
     */
    public AdminState(Game game, Citys citysClass, BattleState battleState) {
        super(game);
        this.citysClass = citysClass;
        this.battleState = battleState;
        heroSelector = new RadioButtons(600, 500, game, Assets.testRadioButton, 3, 10, true, 50, 50);
        healthField = new TextField(600, 600, 200, 25, game, Assets.textField, 3, 1);
        up = new UIButton(200, 550, game, Assets.blankButton, 200, 35);
        down = new UIButton(200, 650, game, Assets.blankButton, 200, 35);
        left = new UIButton(50, 600, game, Assets.blankButton, 200, 35);
        right = new UIButton(350, 600, game, Assets.blankButton, 200, 35);
        currRooms = new Rooms[5];
        for(int i=0; i<4; i++) {
            currRooms[i] = citysClass.cities[game.getPlayer().getCurrentCity()].rooms[i];
        }
        colour = Color.WHITE;

    }


    @Override
    public void update() {
        heroSelector.update();
        if(game.getKeyboardListener().keyJustPressed(KeyEvent.VK_ENTER) && healthField.isEditing()) {
            game.getTeam().get(heroSelector.currentlyClicked).setHealth(Integer.parseInt(healthField.getInput()));
        }
        healthField.update();
        for(int i=0; i<4; i++) {
        currRooms[i] = citysClass.cities[game.getPlayer().getCurrentCity()].rooms[i];
    }
        up.update();
        down.update();
        left.update();
        right.update();
        if(((UIButton) up).isHovering() && game.getMouseListener().leftClicked) {
            game.getMouseListener().leftClicked = false;
            game.getPlayer().setX(640);
            game.getPlayer().setY(360);
            game.getPlayer().setCurrentRoom(1);
        }
        if(((UIButton) right).isHovering() && game.getMouseListener().leftClicked) {
            game.getMouseListener().leftClicked = false;
            game.getPlayer().setX(640);
            game.getPlayer().setY(360);
            game.getPlayer().setCurrentRoom(2);
        }
        if(((UIButton) down).isHovering() && game.getMouseListener().leftClicked) {
            game.getMouseListener().leftClicked = false;
            game.getPlayer().setX(640);
            game.getPlayer().setY(360);
            game.getPlayer().setCurrentRoom(3);
        }
        if(((UIButton) left).isHovering() && game.getMouseListener().leftClicked) {
            game.getMouseListener().leftClicked = false;
            game.getPlayer().setX(640);
            game.getPlayer().setY(360);
            game.getPlayer().setCurrentRoom(0);
        }
    }

    @Override
    public void render(Graphics graphics) {
        healthField.render(graphics);
        heroSelector.render(graphics);
        up.render(graphics);
        down.render(graphics);
        left.render(graphics);
        right.render(graphics);
        DrawText.draw(graphics, currRooms[1].roomName, 300, 568, true, colour, Assets.smallFont);
        DrawText.draw(graphics, currRooms[3].roomName, 300, 668, true, colour, Assets.smallFont);
        DrawText.draw(graphics, currRooms[0].roomName, 150, 618, true, colour, Assets.smallFont);
        DrawText.draw(graphics, currRooms[2].roomName, 450, 618, true, colour, Assets.smallFont);
        if(((UIButton) up).isHovering()) {
            DrawText.draw(graphics, currRooms[1].roomName, 300, 568, true, Color.CYAN, Assets.smallFont);
        }
        if(((UIButton) down).isHovering()) {
            DrawText.draw(graphics, currRooms[3].roomName, 300, 668, true, Color.CYAN, Assets.smallFont);
        }
        if(((UIButton) left).isHovering()) {
            DrawText.draw(graphics, currRooms[0].roomName, 150, 618, true, Color.CYAN, Assets.smallFont);
        }
        if(((UIButton) right).isHovering()) {
            DrawText.draw(graphics, currRooms[2].roomName, 450, 618, true, Color.CYAN, Assets.smallFont);
        }
        if(battleState.currMiniGame != null) {
            DrawText.draw(graphics, "Current Mini Game: " + battleState.currMiniGame.gameName, 640, 100, true, Color.BLACK, Assets.invFont);
            DrawText.draw(graphics, "Villains Move: " + battleState.currMiniGame.villainMoveWords, 640, 150, true, Color.BLACK, Assets.invFont);
        }

    }
}
