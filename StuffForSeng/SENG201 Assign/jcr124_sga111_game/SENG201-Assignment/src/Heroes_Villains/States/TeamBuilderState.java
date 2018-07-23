package Heroes_Villains.States;

import java.awt.*;
import java.nio.channels.AsynchronousServerSocketChannel;

import Heroes_Villains.Game;
import Heroes_Villains.SystemUI.RadioButtons;
import Heroes_Villains.SystemUI.UIButton;
import Heroes_Villains.SystemUI.UIElement;
import Heroes_Villains.entities.heroes.Hero;
import Heroes_Villains.graphics.Assets;
import Heroes_Villains.SystemUI.TextField;
import Heroes_Villains.graphics.DrawText;


/**
 * Class for the team builder state, renders the team and allows you to add and remove heros from it before eventually starting the game
 */
public class TeamBuilderState extends State {

    private UIElement backButton2;
    private UIElement startButton;

    private UIElement deleteButton;
    private RadioButtons teamDelete;

    private UIElement heroTypeA;
    private UIElement heroTypeB;
    private UIElement heroTypeC;
    private UIElement heroTypeD;
    private UIElement heroTypeE;
    private UIElement heroTypeF;

    private TextField nameinput;

    private boolean notFull;

    /**
     * Constructor method for team builder state, creates a serious of buttons and a text field used to add heros to the game team
     * @param game game object, as passed through everything in the game
     */
    public TeamBuilderState(Game game) {
        super(game);
        backButton2 = new UIButton(540, 650, game, Assets.backButton, 200, 35);
        startButton = new UIButton(540, 580, game, Assets.startButton, 200, 35);

        heroTypeA = new UIButton(11, 450, game, Assets.thiefButton, 200, 35);
        heroTypeB = new UIButton(222, 450, game, Assets.tankButton, 200, 35);
        heroTypeC = new UIButton(433, 450, game, Assets.nurseButton, 200, 35);
        heroTypeD = new UIButton(644, 450, game, Assets.psychicButton, 200, 35);
        heroTypeE = new UIButton(855, 450, game, Assets.scoutButton, 200, 35);
        heroTypeF = new UIButton(1066, 450, game, Assets.sacrificeButton, 200, 35);

        nameinput = new TextField(540, 250, 200, 25, game, Assets.textField, 10, 2);

        notFull = false;

        deleteButton = new UIButton(1000, 100, game, Assets.deleteButton, 200, 35);




    }

    @Override
    public void update() {
        backButton2.update();
        startButton.update();

        heroTypeA.update();
        heroTypeB.update();
        heroTypeC.update();
        heroTypeD.update();
        heroTypeE.update();
        heroTypeF.update();

        nameinput.update();


        deleteButton.update();





        if (game.getMouseListener().leftClicked && deleteButton.click()) {
            game.getMouseListener().leftClicked = false;
            if (game.getTeam().size() > 0) {
                game.getTeam().remove(game.getTeam().size()-1);
            }
        }


        if (game.getMouseListener().leftClicked && backButton2.click()) {
            game.getMouseListener().leftClicked = false;
            game.getStateHandler().setState(game.getSetupState());
            game.getTeam().clear();
        }

        if (game.getMouseListener().leftClicked && startButton.click()) {
            game.getMouseListener().leftClicked = false;
            if (game.getTeam().size() < game.noOfHeros) {
                notFull = true;
            } else {
                notFull = false;
                game.gameState = new GameState(game);
                game.player = ((GameState) game.gameState).player;
                game.battleState = new BattleState(game);
                game.endState = new EndState(game);
                if (game.teamName.equals("ADMIN")) {
                    game.adminState = new AdminState(game, ((GameState) game.gameState).masterCities, ((BattleState) game.battleState));
                }

                //game.getTeam().get(0).setHealth(50);
                //game.getTeam().get(1).setHealth(50);
                //game.getTeam().get(2).setHealth(50);

                game.getStateHandler().setState(game.getGameState());

            }

        }
    }

    @Override
    public void render(Graphics graphics) {
        backButton2.render(graphics);
        startButton.render(graphics);

        heroTypeA.render(graphics);
        heroTypeB.render(graphics);
        heroTypeC.render(graphics);
        heroTypeD.render(graphics);
        heroTypeE.render(graphics);
        heroTypeF.render(graphics);

        if (game.getTeam().size() > 0) {
            deleteButton.render(graphics);
        }



        graphics.setFont(Assets.smallFont);

        if (heroTypeA.click()) {
            DrawText.draw(game.getGraphics(), "Hero Type: Thief", game.width/2, (game.height/2)-20, true, Color.BLACK, Assets.smallFont);
            DrawText.draw(game.getGraphics(), "Max Health: 100", game.width/2, (game.height/2), true, Color.BLACK, Assets.smallFont);
            DrawText.draw(game.getGraphics(), "Ability (Passive): Steals 50% extra gold from villains", game.width/2, (game.height/2)+20, true, Color.BLACK, Assets.smallFont);
            boolean dontAdd = false;
            if (game.getMouseListener().leftClicked) {
                game.getMouseListener().leftClicked = false;
                if (game.getTeam().size() < game.noOfHeros && nameinput.getInput().length() >= 2) {
                    for (Hero h : game.getTeam()) {
                        if (h.getName().equals(nameinput.getInput())) {
                            dontAdd = true;
                        }
                    }
                    if (!dontAdd) {
                        game.getTeam().add(new Hero(100, "Thief", nameinput.getInput()));
                        nameinput.setInput("");
                    }

                }

            }
        }

        if (heroTypeB.click()) {
            DrawText.draw(game.getGraphics(), "Hero Type: Tank", game.width/2, (game.height/2)-20, true, Color.BLACK, Assets.smallFont);
            DrawText.draw(game.getGraphics(), "Max Health: 200", game.width/2, (game.height/2), true, Color.BLACK, Assets.smallFont);
            DrawText.draw(game.getGraphics(), "Ability (Passive): Has an increased max health", game.width/2, (game.height/2)+20, true, Color.BLACK, Assets.smallFont);
            boolean dontAdd = false;
            if (game.getMouseListener().leftClicked) {
                game.getMouseListener().leftClicked = false;
                if (game.getTeam().size() < game.noOfHeros && nameinput.getInput().length() >= 2) {
                    for (Hero h : game.getTeam()) {
                        if (h.getName().equals(nameinput.getInput())) {
                            dontAdd = true;
                        }
                    }
                    if (!dontAdd) {
                        game.getTeam().add(new Hero(200, "Tank", nameinput.getInput()));

                        nameinput.setInput("");
                    }

                }

            }
        }

        if (heroTypeC.click()) {
            DrawText.draw(game.getGraphics(), "Hero Type: Nurse", game.width/2, (game.height/2)-20, true, Color.BLACK, Assets.smallFont);
            DrawText.draw(game.getGraphics(), "Max Health: 80", game.width/2, (game.height/2), true, Color.BLACK, Assets.smallFont);
            DrawText.draw(game.getGraphics(), "Ability (Active): Can replenish her health completely during a battle", game.width/2, (game.height/2)+20, true, Color.BLACK, Assets.smallFont);
            boolean dontAdd = false;
            if (game.getMouseListener().leftClicked) {
                game.getMouseListener().leftClicked = false;
                if (game.getTeam().size() < game.noOfHeros && nameinput.getInput().length() >= 2) {
                    for (Hero h : game.getTeam()) {
                        if (h.getName().equals(nameinput.getInput())) {
                            dontAdd = true;
                        }
                    }
                    if (!dontAdd) {
                        game.getTeam().add(new Hero(80, "Nurse", nameinput.getInput()));
                        nameinput.setInput("");
                    }

                }

            }
        }

        if (heroTypeD.click()) {
            DrawText.draw(game.getGraphics(), "Hero Type: Psychic", game.width/2, (game.height/2)-20, true, Color.BLACK, Assets.smallFont);
            DrawText.draw(game.getGraphics(), "Max Health: 100", game.width/2, (game.height/2), true, Color.BLACK, Assets.smallFont);
            DrawText.draw(game.getGraphics(), "Ability (Active): Can see what the villains next move will be", game.width/2, (game.height/2)+20, true, Color.BLACK, Assets.smallFont);
            boolean dontAdd = false;
            if (game.getMouseListener().leftClicked) {
                game.getMouseListener().leftClicked = false;
                if (game.getTeam().size() < game.noOfHeros && nameinput.getInput().length() >= 2) {
                    for (Hero h : game.getTeam()) {
                        if (h.getName().equals(nameinput.getInput())) {
                            dontAdd = true;
                        }
                    }
                    if (!dontAdd) {
                        game.getTeam().add(new Hero(100, "Psychic", nameinput.getInput()));
                        nameinput.setInput("");
                    }

                }

            }
        }

        if (heroTypeE.click()) {
            DrawText.draw(game.getGraphics(), "Hero Type: Scout", game.width/2, (game.height/2)-20, true, Color.BLACK, Assets.smallFont);
            DrawText.draw(game.getGraphics(), "Max Health: 60", game.width/2, (game.height/2), true, Color.BLACK, Assets.smallFont);
            DrawText.draw(game.getGraphics(), "Ability (Passive): Knows where everything is in the game", game.width/2, (game.height/2)+20, true, Color.BLACK, Assets.smallFont);
            boolean dontAdd = false;
            if (game.getMouseListener().leftClicked) {
                game.getMouseListener().leftClicked = false;
                if (game.getTeam().size() < game.noOfHeros && nameinput.getInput().length() >= 2) {
                    for (Hero h : game.getTeam()) {
                        if (h.getName().equals(nameinput.getInput())) {
                            dontAdd = true;
                        }
                    }
                    if (!dontAdd) {
                        game.getTeam().add(new Hero(60, "Scout", nameinput.getInput()));
                        nameinput.setInput("");
                    }

                }

            }
        }

        if (heroTypeF.click()) {
            DrawText.draw(game.getGraphics(), "Hero Type: Sacrifice", game.width/2, (game.height/2)-20, true, Color.BLACK, Assets.smallFont);
            DrawText.draw(game.getGraphics(), "Max Health: 10", game.width/2, (game.height/2), true, Color.BLACK, Assets.smallFont);
            DrawText.draw(game.getGraphics(), "Ability (Active): Can instantly defeat a villain but will die, warning: has very low max health", game.width/2, (game.height/2)+20, true, Color.BLACK, Assets.smallFont);
            boolean dontAdd = false;
            if (game.getMouseListener().leftClicked) {
                game.getMouseListener().leftClicked = false;
                if (game.getTeam().size() < game.noOfHeros && nameinput.getInput().length() >= 2) {
                    for (Hero h : game.getTeam()) {
                        if (h.getName().equals(nameinput.getInput())) {
                            dontAdd = true;
                        }
                    }
                    if (!dontAdd) {
                        game.getTeam().add(new Hero(100, "Sacrifice", nameinput.getInput()));
                        nameinput.setInput("");
                    }

                }

            }
        }


        DrawText.draw(game.getGraphics(), "Current Team: " + game.teamName, 640, 50, true, Color.BLACK, Assets.invFont);
        graphics.setFont(Assets.smallFont);

        int i = 0;
        for (Hero hero : game.getTeam()) {
            DrawText.draw(game.getGraphics(), hero.getName() + ": " + hero.getType(), 640, 100 + i, true, Color.BLACK, Assets.smallFont);
            i += 40;
        }



        if (notFull) {
            DrawText.draw(graphics,"Team not full...", game.width/2,  625, true, Color.RED, Assets.smallFont);
        }
        DrawText.draw(game.getGraphics(), "Enter a hero name in the text field and then click on the hero type to add hero", game.width/2, 545, true, Color.BLACK, Assets.smallFont);
        nameinput.render(graphics);


    }
}
