package Heroes_Villains.States;

import Heroes_Villains.Game;
import Heroes_Villains.SystemUI.RadioButtons;
import Heroes_Villains.SystemUI.UIButton;
import Heroes_Villains.graphics.Assets;
import Heroes_Villains.graphics.DrawText;
import Heroes_Villains.minigames.MiniGame;
import Heroes_Villains.minigames.MiniGameHandler;
import Heroes_Villains.utils.RandomNum;

import java.awt.*;

/**
 * Class for battle state of the game, manages battles and minigames against villains
 */
public class BattleState extends State {

    private UIButton battleButton, backButton, nextCity, okButton, okButton2, psychicButton;
    private RadioButtons heroSelect1, heroSelect2, heroSelect3;
    public MiniGame currMiniGame;
    public boolean battling;
    private MiniGameHandler miniGameHandler;
    private static final int HEALTH_LOST_ON_LOSS = 25;
    private static final int VILLAIN_LIVES = 3;
    private int currLives;
    private boolean won, lost, battleWon, taunting;
    private int currHero, currDead;
    private int radioTotalWidth1, radioTotalWidth2, radioTotalWidth3, taunt, reward;
    private String[] villainNames, villainTaunts;
    private String villainName;
    private boolean nurse, psychic, psychicUsed, sacrifice, abitiy, useAbilityHovering;

    /**
     * Constructor for battle state, defines buttons and villain taunts and hero selection
     * @param game game object, generic pass-through
     */
    public BattleState(Game game) {
        super(game);
        nextCity = new UIButton(640-Assets.buttonWidth/2, 500, game, Assets.battleStateNext, Assets.buttonWidth, Assets.buttonHeight);
        battleButton = new UIButton(640-Assets.buttonWidth/2, 350, game, Assets.battleStateBattle, Assets.buttonWidth, Assets.buttonHeight);
        okButton = new UIButton(640-Assets.buttonWidth/2, 250, game, Assets.battleStateOK, Assets.buttonWidth, Assets.buttonHeight);
        okButton2 = new UIButton(640-Assets.buttonWidth/2, 475, game, Assets.battleStateOK, Assets.buttonWidth, Assets.buttonHeight);
        backButton = new UIButton(640-Assets.buttonWidth/2, 400, game, Assets.battleStateBack, Assets.buttonWidth, Assets.buttonHeight);
        psychicButton = new UIButton(640-Assets.buttonWidth/2, 475, game, Assets.battleStateOK, Assets.buttonWidth, Assets.buttonHeight);
        radioTotalWidth1 = 50;
        radioTotalWidth2 = 10+100;
        radioTotalWidth3 = 20+150;
        heroSelect1 = new RadioButtons(1070-(radioTotalWidth1/2), 211, game, Assets.invRadioButton, 1, 10, true, 50, 50);
        heroSelect1.clicked(0);
        heroSelect2 = new RadioButtons(1070-(radioTotalWidth2/2), 211, game, Assets.invRadioButton, 2, 10, true, 50, 50);
        heroSelect2.clicked(0);
        heroSelect3 = new RadioButtons(1070-(radioTotalWidth3/2), 211, game, Assets.invRadioButton, 3, 10, true, 50, 50);
        heroSelect3.clicked(0);
        battling = false;
        miniGameHandler = new MiniGameHandler(game);
        villainNames = new String[6];
        villainNames[0] = "Clarence the Vile";
        villainNames[1] = "Snugget the cruel";
        villainNames[2] = "PotatoWhale the scavenger";
        villainNames[3] = "Maccas the temptress";
        villainNames[4] = "Smirnoff the foul";
        villainNames[5] = "Spaceman (Super Villain)";
        villainTaunts = new String[10];
        villainTaunts[0] = "'Just why do you suck?'";
        villainTaunts[1] = "'Wait who?'";
        villainTaunts[2] = "'You are going to be as easy as SENG201'";
        villainTaunts[3] = "'Wait why are you a bat?'";
        villainTaunts[4] = "'Am I just in a pokemon ripoff?'";
        villainTaunts[5] = "'I can just feel how inefficient your code is'";
        villainTaunts[6] = "'You deserve to fail with this'";
        villainTaunts[7] = "'You know software engineering is computer science'";
        villainTaunts[8] = "'I bet you use Mac OS, and you think you know computers'";
        villainTaunts[9] = "'Who uses potions in a hospital?'";
        currMiniGame = null;
        currDead = 0;
        taunting = false;
        won = false;
        lost = false;
        psychicUsed = false;
    }

    @Override
    public void update() {
        if(game.getTeam().size()==1) {
            currHero = heroSelect1.currentlyClicked;
        }
        if(game.getTeam().size()==2) {
            currHero = heroSelect2.currentlyClicked;
        }
        if(game.getTeam().size()==3) {
            currHero = heroSelect3.currentlyClicked;
        }
        if(!battling) {
            battleButton.update();
            backButton.update();
            if (game.getMouseListener().leftClicked && backButton.click()) {
                game.getMouseListener().leftClicked = false;
                game.getStateHandler().setState(game.getGameState());
                return;
            }
            if (game.getMouseListener().leftClicked && battleButton.click()) {
                game.getMouseListener().leftClicked = false;
                won = false;
                lost = false;
                currLives = VILLAIN_LIVES;
                battleWon = false;
                currMiniGame = miniGameHandler.getGame();
                taunt = RandomNum.getNum(10);
                battling = true;
                taunting = true;
                if(game.getPlayer().getCurrentCity() + 1 < game.getNoOfCities()) {
                    villainName = villainNames[game.getPlayer().getCurrentCity()];
                }else {
                    villainName = villainNames[5];
                }
            }
            return;
        }
        if(taunting) {
            okButton2.update();
            if(okButton2.click() && game.getMouseListener().leftClicked) {
                game.getMouseListener().leftClicked = false;
                taunting = false;
                battling = true;
                return;
            }
        }
        abitiy = game.getTeam().get(currHero).isAbilityUsed();
        if(game.getTeam().get(currHero).getType() == "Psychic" && !abitiy) {
            psychic = true;
            nurse = false;
            sacrifice = false;
            if(game.getMouseListener().isHovering(990, 463, 144, 78) && game.getMouseListener().leftClicked) {
                game.getMouseListener().leftClicked = false;
                game.getTeam().get(currHero).setAbilityUsed(true);
                psychicUsed = true;
            }
        }else if(game.getTeam().get(currHero).getType() == "Nurse" && !abitiy) {
            nurse = true;
            psychic = false;
            sacrifice = false;
            if (game.getMouseListener().isHovering(990, 463, 144, 78) && game.getMouseListener().leftClicked) {
                game.getMouseListener().leftClicked = false;
                game.getTeam().get(currHero).setAbilityUsed(true);
                game.getTeam().get(currHero).setHealth(game.getTeam().get(currHero).getMaxHealth());
            }
        }else if(game.getTeam().get(currHero).getType() == "Sacrifice" && !abitiy) {
            sacrifice = true;
            nurse = false;
            psychic = false;
            if(game.getMouseListener().isHovering(990, 463, 144, 78) && game.getMouseListener().leftClicked) {
                game.getMouseListener().leftClicked = false;
                game.getTeam().get(currHero).setAbilityUsed(true);
                game.getTeam().remove(currHero);
                battleWon = true;
            }
        }else {
            sacrifice = false;
            nurse = false;
            psychic = false;
        }
        if(psychicUsed) {
            psychicButton.update();
            if(psychicButton.click() && game.getMouseListener().leftClicked) {
                game.getMouseListener().leftClicked = false;
                psychicUsed = false;
            }
        }
        if(battleWon) {
            nextCity.update();
            reward = 50;
            for(int i=0; i<game.getTeam().size(); i++) {
                if(game.getTeam().get(i).getType() == "Thief") {
                    reward += 25;
                }
            }
            if(nextCity.click() && game.getMouseListener().leftClicked) {
                if (game.getPlayer().getCurrentCity() < game.getNoOfCities() - 1) {
                    game.getMouseListener().leftClicked = false;
                    battling = false;
                    battleWon = false;
                    won = false;
                    lost = false;
                    currLives = VILLAIN_LIVES;
                    game.getPlayer().money += reward;
                    game.getPlayer().setCurrentRoom(4);
                    game.getPlayer().setCurrentCity(game.getPlayer().getCurrentCity() + 1);
                    game.getPlayer().setX(640 - (game.getPlayer().getWidth())/2);
                    game.getPlayer().setY(360 - (game.getPlayer().getHeight())/2);
                    game.getStateHandler().setState(game.getGameState());
                    return;
                } else {
                    game.endState.setWon(true);
                    game.endState.setTotalSecs(game.count);
                    game.getStateHandler().setState(game.getEndState());
                }
            }
            return;
        }
        currMiniGame.update();
        if(game.getTeam().size()==1) {
            heroSelect1.update();
        }
        if(game.getTeam().size()==2) {
            heroSelect2.update();
        }
        if(game.getTeam().size()==3) {
            heroSelect3.update();
        }
        if(won || lost) {
            okButton.update();
            if(okButton.click() && game.getMouseListener().leftClicked) {
                game.getMouseListener().leftClicked = false;
                this.nextGame();
                won = false;
                lost = false;
            }
        }
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Assets.battleState, 0, 0, null);
        if(game.getTeam().size()==1) {
            heroSelect1.render(graphics);
        }
        if(game.getTeam().size()==2) {
            heroSelect2.render(graphics);
        }
        if(game.getTeam().size()==3) {
            heroSelect3.render(graphics);
        }

        if(!battling){
            graphics.drawImage(Assets.battlePopup, 384, 168, null);
            battleButton.render(graphics);
            backButton.render(graphics);
            DrawText.draw(graphics, "Do you want to battle?", 640, 300, true, Color.WHITE, Assets.smallFont);
            return;
        }
        if(battleWon) {
            graphics.drawImage(Assets.battlePopup, 384, 168, null);
            DrawText.draw(graphics, "You won " + Integer.toString(reward) + " Coins", 640, 350, true, Color.WHITE, Assets.smallFont);
            DrawText.draw(graphics,villainName + " was defeated", 640, 300, true, Color.WHITE, Assets.smallFont);
            nextCity.render(graphics);
            return;
        }
        if(taunting) {
            graphics.drawImage(Assets.battlePopup, 384, 168, null);
            okButton2.render(graphics);
            DrawText.draw(graphics, "Battling: " + villainName, 640, 350, true, Color.WHITE, Assets.tinyFont);
            DrawText.draw(graphics, villainTaunts[taunt], 640, 400, true, Color.WHITE, Assets.tinyFont);
            return;


        }
        DrawText.draw(graphics, "Ability", 1066, 500, true, Color.GRAY, Assets.battleFont);
        if((psychic || nurse || sacrifice) && !abitiy) {
            DrawText.draw(graphics, "Ability", 1066, 500, true, Color.WHITE, Assets.battleFont);
            if(game.getMouseListener().isHovering(990, 463, 144, 78) && !abitiy) {
                DrawText.draw(graphics, "Ability", 1066, 500, true, Color.YELLOW, Assets.battleFont);
            }
        }
        DrawText.draw(graphics, currMiniGame.gameName, 1070, 80, true, Color.WHITE, Assets.battleFont);
        DrawText.draw(graphics, "Hero: " + game.getTeam().get(currHero).getName(), 1065, 347, true, Color.WHITE, Assets.smallFont);
        DrawText.draw(graphics, "Health: " + Integer.toString(game.getTeam().get(currHero).getHealth()), 1065, 407, true, Color.WHITE, Assets.smallFont);
        currMiniGame.render(graphics);
        if(won) {
            graphics.drawImage(Assets.battlePopup, 384, 168, null);
            okButton.render(graphics);

            DrawText.draw(graphics, villainName, 640, 350,true, Color.WHITE, Assets.invFont);
            DrawText.draw(graphics, "Played: " + currMiniGame.villainMoveWords, 640, 400,true, Color.WHITE, Assets.invFont);
            DrawText.draw(graphics, " and you beat him", 640, 450,true, Color.WHITE, Assets.invFont);
        }
        if(lost) {

            graphics.drawImage(Assets.battlePopup, 384, 168, null);
            okButton.render(graphics);

            DrawText.draw(graphics, villainName, 640, 350,true, Color.WHITE, Assets.smallFont);
            DrawText.draw(graphics, "Played: " + currMiniGame.villainMoveWords, 640, 400,true, Color.WHITE, Assets.invFont);
            DrawText.draw(graphics, " and you lost", 640, 450,true, Color.WHITE, Assets.invFont);
        }
        DrawText.draw(graphics, "Villain lives remaining: " + Integer.toString(currLives), 376, 97, true, Color.WHITE, Assets.smallFont);
        if(psychicUsed) {
            graphics.drawImage(Assets.battlePopup, 384, 168, null);
            psychicButton.render(graphics);
            DrawText.draw(graphics, "The villain will play:", 640, 350, true, Color.WHITE, Assets.smallFont);
            DrawText.draw(graphics, currMiniGame.villainMoveWords, 640, 420, true, Color.WHITE, Assets.invFont);
        }
    }

    /**
     * Method that gets called when a particular minigame gets won, reduced the current lives of the villain by 1 and sets won to true
     * @param hero hero object that won the minigame
     */
    public void won(int hero) {
        currLives -= 1;
        if(currLives <= 0) {
            currLives = 0;
            battleWon = true;
            return;
        }
        won = true;
    }

    /**
     * Method that gets called when a particular minigame gets lost by a particular hero
     * @param hero hero object, hero that loses the game
     */
    public void lost(int hero) {
        game.getTeam().get(hero).setHealth(game.getTeam().get(hero).getHealth()-HEALTH_LOST_ON_LOSS);
        battleWon = false;
        lost = true;
        if(game.getTeam().get(hero).getHealth() <= 0) {
            game.getTeam().get(currHero).setHealth(0);
            game.getTeam().get(hero).setDead(true);
            game.getTeam().remove(currHero);
            currHero = 0;
            heroSelect1.clicked(0);
            heroSelect2.clicked(0);
            heroSelect3.clicked(0);
            currDead++;
            if(game.getTeam().size() == 0) {
                game.endState.setWon(false);
                game.getStateHandler().setState(game.getEndState());
            }
        }

    }

    /**
     * Method for generating a random new minigame after the current game is won or lost
     */
    public void nextGame() {
        MiniGameHandler tempHandler = new MiniGameHandler(game);
        currMiniGame = tempHandler.getGame();
    }

    /**
     * Method for getting the current minigame being played
     * @return MiniGame object, current game being played
     */
    public MiniGame getCurrMiniGame() {
        return currMiniGame;
    }

    /**
     * Method for getting the index of the current hero selected
     * @return int, index of current hero
     */
    public int getCurrHero() {
        return currHero;
    }
}
