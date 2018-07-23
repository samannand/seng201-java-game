package Heroes_Villains.States;

import Heroes_Villains.Game;
import Heroes_Villains.SystemUI.RadioButton;
import Heroes_Villains.SystemUI.RadioButtons;
import Heroes_Villains.SystemUI.TextField;
import Heroes_Villains.SystemUI.UIButton;
import Heroes_Villains.SystemUI.UIElement;
import Heroes_Villains.entities.Player;
import Heroes_Villains.graphics.Assets;
import Heroes_Villains.graphics.DrawText;
import com.sun.corba.se.spi.servicecontext.UEInfoServiceContext;

import javax.swing.*;
import java.awt.*;

/**
 * Class for creating the games setup state, this allows the user to set a team name, the number of cities to explore, and the number of heros that will be on there team. From this state they progress to the team builder state
 */
public class SetupState extends State{

    private UIElement backButton;
    private RadioButtons citySelect;
    private RadioButtons heroSelect;
    private UIElement teamButton;
    private TextField entry;
    private boolean noTeam;

    //private UIElement testButton;

    /**
     * Constructor method for setup state, renders various radio buttons and buttons to obtain the values set by the class
     * @param game game object, generic pass-through of all game variables
     */
    public SetupState(Game game) {
        super(game);
        backButton = new UIButton(540, 553, game, Assets.backButton, 200, 35);
        teamButton = new UIButton(540, 483, game, Assets.teamButton, 200, 35);
        citySelect = new RadioButtons(900, 268, game, Assets.testRadioButton, 4, 25, true, 32, 32);
        heroSelect = new RadioButtons(900, 368, game, Assets.testRadioButton, 3, 25, true, 32, 32);
        entry = new TextField(900, 175, 200, 25, game, Assets.textField, 10, 2);
        noTeam = false;
        citySelect.clicked(0);
        heroSelect.clicked(0);

    }

    @Override
    public void update() {
        backButton.update();
        citySelect.update();
        heroSelect.update();
        entry.update();
        teamButton.update();

        if(game.getMouseListener().isLeftClicked() && backButton.click()){
            game.getMouseListener().leftClicked = false;
            game.getStateHandler().setState(game.getMenuState());
        }

        if(game.getMouseListener().isLeftClicked() && teamButton.click()){
            game.getMouseListener().leftClicked = false;
            String teamname = entry.getInput();
            int numC = citySelect.currentlyClicked + 3;
            int numH = heroSelect.currentlyClicked + 1;
            System.out.println(teamname + " " + numC + " " + numH);
            game.teamName = teamname;
            game.setNoOfCities(numC);
            game.setNoOfHeros(numH);
            if (teamname.length() > 1) {
                noTeam = false;

                game.teamBuilderState = new TeamBuilderState(game);

                game.getStateHandler().setState(game.getTeamBuilderState());


            } else {
                noTeam = true;
            }

        }
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setFont(Assets.smallFont);
        graphics.drawString("Enter Team Name: " , 200, 200);
        graphics.drawString("Pick the number of cities:" , 200, 300);
        graphics.drawString("Pick the amount of heros on your team:", 200, 400);

        for(int i=0; i < citySelect.getButtons().length; i++) {
            graphics.drawString(""+(i+3), citySelect.getButtons()[i].x + citySelect.getButtons()[i].getWidth()/2, citySelect.getButtons()[i].y - citySelect.getButtons()[i].getHeight()/2);
        }

        for(int i=0; i < heroSelect.getButtons().length; i++) {
            graphics.drawString("" + (i + 1), heroSelect.getButtons()[i].x + heroSelect.getButtons()[i].getWidth() / 2, heroSelect.getButtons()[i].y - heroSelect.getButtons()[i].getHeight() / 2);
        }

        if (noTeam) {
            DrawText.draw(graphics,"Please enter a team name...", game.width/2,  625, true, Color.RED, Assets.smallFont);
        }

        backButton.render(graphics);
        citySelect.render(graphics);
        heroSelect.render(graphics);
        teamButton.render(graphics);
        entry.render(graphics);
    }
    //pass
}
