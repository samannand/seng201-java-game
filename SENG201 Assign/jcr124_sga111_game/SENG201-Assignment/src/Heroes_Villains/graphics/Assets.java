package Heroes_Villains.graphics;


import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Loads all of the assests for the game, fonts, images, and image arrays.
 */
public class Assets {

    public static BufferedImage black, purple, playerUp, playerDown, playerLeft, playerRight, villain, innkeep, inn, innFloor, inventory, red, basicPotion, advancedPotion, masterPotion, coin, radio, radioHover, radioClicked, battlePopup, battleState, paper, scissors, rock, map, greenScroll, blueScroll, redScroll, stolenPopup;
    public static Font titleFont, invFont, smallFont, battleFont, tinyFont;
    private static int widthSheet1 = 16;
    private static int heightSheet1 = 16;
    private static int widthPlayerSheet = 32;
    private static int heightPlayerSheet = 32;
    public static int batDim = 32;
    public static int buttonHeight, buttonWidth;

    //Buffered image arrays for animations
    public static BufferedImage[] walkingUp, startButton, menuButton, backButton, batUp, batDown, batLeft, batRight, battleButton, testRadioButton, exitButton, controlsButton, doorWay, doorWayH, textField, teamButton, blankButton, nurseButton, psychicButton, sacrificeButton, scoutButton, tankButton, thiefButton, invRadioButton, deleteButton, battleStateBattle, battleStateBack, battleStatePlay, battleStateNext, battleStateOK, playAgainButton;

    /**
     * Initiates the assets by loading them from their respective
     * files into variables.
     */
    public static void init() {
        SpriteSheet sheet1 = new SpriteSheet("/textures/sheets/tiles.png");
        SpriteSheet playerSheet = new SpriteSheet("/textures/sheets/PlayerSheet.png");
        SpriteSheet batSheet = new SpriteSheet("/textures/sheets/32x32-bat-sprite.png");
        SpriteSheet villainSheet = new SpriteSheet("/textures/sheets/4.png");
        walkingUp = new BufferedImage[3];
        startButton = new BufferedImage[2];
        menuButton = new BufferedImage[2];
        backButton = new BufferedImage[2];
        battleButton = new BufferedImage[2];
        exitButton = new BufferedImage[2];
        controlsButton = new BufferedImage[2];
        batDown = new BufferedImage[3];
        batUp = new BufferedImage[3];
        batLeft = new BufferedImage[3];
        batRight = new BufferedImage[3];
        testRadioButton = new BufferedImage[6];
        invRadioButton = new BufferedImage[3];
        doorWay = new BufferedImage[2];
        doorWayH = new BufferedImage[2];
        textField = new BufferedImage[2];
        teamButton = new BufferedImage[2];
        blankButton = new BufferedImage[1];
        nurseButton = new BufferedImage[2];
        psychicButton = new BufferedImage[2];
        sacrificeButton = new BufferedImage[2];
        scoutButton = new BufferedImage[2];
        tankButton = new BufferedImage[2];
        thiefButton = new BufferedImage[2];
        battleStateBattle = new BufferedImage[2];
        battleStateBack = new BufferedImage[2];
        battleStatePlay = new BufferedImage[2];
        battleStateNext = new BufferedImage[2];
        deleteButton = new BufferedImage[2];
        battleStateOK = new BufferedImage[2];
        playAgainButton = new BufferedImage[2];


        //Setting default button width and height
        buttonHeight = 35;
        buttonWidth = 200;

        //Button Frames
        startButton[1] = ImageHandler.loadImage("/textures/NewStartButton1.png");
        startButton[0] = ImageHandler.loadImage("/textures/NewStartButton2.png");

        menuButton[1] = ImageHandler.loadImage("/textures/MenuButton1.png");
        menuButton[0] = ImageHandler.loadImage("/textures/MenuButton2.png");

        backButton[1] = ImageHandler.loadImage("/textures/BackButton1.png");
        backButton[0] = ImageHandler.loadImage("/textures/BackButton2.png");

        battleButton[1] = ImageHandler.loadImage("/textures/BattleButton1.png");
        battleButton[0] = ImageHandler.loadImage("/textures/BattleButton2.png");

        exitButton[1] = ImageHandler.loadImage("/textures/ExitButton1.png");
        exitButton[0] = ImageHandler.loadImage("/textures/ExitButton2.png");

        controlsButton[1] = ImageHandler.loadImage("/textures/ControlsButton1.png");
        controlsButton[0] = ImageHandler.loadImage("/textures/ControlsButton2.png");

        blankButton[0] = ImageHandler.loadImage("/textures/BlankButton.png");

        radio = ImageHandler.loadImage("/textures/RadioUnclicked.png");
        radioHover = ImageHandler.loadImage("/textures/RadioHover.png");
        radioClicked = ImageHandler.loadImage("/textures/RadioClicked.png");

        teamButton[1] = ImageHandler.loadImage("/textures/TeamButton1.png");
        teamButton[0] = ImageHandler.loadImage("/textures/TeamButton2.png");

        nurseButton[1] = ImageHandler.loadImage("/textures/NurseButton1.png");
        nurseButton[0] = ImageHandler.loadImage("/textures/NurseButton2.png");

        psychicButton[1] = ImageHandler.loadImage("/textures/PsychicButton1.png");
        psychicButton[0] = ImageHandler.loadImage("/textures/PsychicButton2.png");

        sacrificeButton[1] = ImageHandler.loadImage("/textures/SacrificeButton1.png");
        sacrificeButton[0] = ImageHandler.loadImage("/textures/SacrificeButton2.png");

        scoutButton[1] = ImageHandler.loadImage("/textures/ScoutButton1.png");
        scoutButton[0] = ImageHandler.loadImage("/textures/ScoutButton2.png");

        tankButton[1] = ImageHandler.loadImage("/textures/TankButton1.png");
        tankButton[0] = ImageHandler.loadImage("/textures/TankButton2.png");

        thiefButton[1] = ImageHandler.loadImage("/textures/ThiefButton1.png");
        thiefButton[0] = ImageHandler.loadImage("/textures/ThiefButton2.png");

        battleStateBattle[0] = ImageHandler.loadImage("/textures/BattleStateBattle2.png");
        battleStateBattle[1] = ImageHandler.loadImage("/textures/BattleStateBattle1.png");

        battleStateBack[0] = ImageHandler.loadImage("/textures/BattleStateBack2.png");
        battleStateBack[1] = ImageHandler.loadImage("/textures/BattleStateBack1.png");

        battleStatePlay[0] = ImageHandler.loadImage("/textures/BattleStatePlay2.png");
        battleStatePlay[1] = ImageHandler.loadImage("/textures/BattleStatePlay1.png");

        battleStateNext[0] = ImageHandler.loadImage("/textures/BattleStateNext2.png");
        battleStateNext[1] = ImageHandler.loadImage("/textures/BattleStateNext1.png");

        battleStateOK[0] = ImageHandler.loadImage("/textures/BattleStateOK2.png");
        battleStateOK[1] = ImageHandler.loadImage("/textures/BattleStateOK1.png");

        deleteButton[1] = ImageHandler.loadImage("/textures/DeleteButton1.png");
        deleteButton[0] = ImageHandler.loadImage("/textures/DeleteButton2.png");

        playAgainButton[1] = ImageHandler.loadImage("/textures/PlayAgainButton1.png");
        playAgainButton[0] = ImageHandler.loadImage("/textures/PlayAgainButton2.png");

        //Player animation frame to crop
        walkingUp[0] = playerSheet.getImage(0, 0, widthPlayerSheet, heightPlayerSheet);
        walkingUp[1] = playerSheet.getImage(widthPlayerSheet, 0, widthPlayerSheet, heightPlayerSheet);
        walkingUp[2] = playerSheet.getImage(widthPlayerSheet * 2, 0, widthPlayerSheet, heightPlayerSheet);

        //batDown[0] = batSheet.getImage(0, 0, batDim, batDim);
        batDown[0] = batSheet.getImage(batDim, 0, batDim, batDim);
        batDown[1] = batSheet.getImage(batDim * 2, 0, batDim, batDim);
        batDown[2] = batSheet.getImage(batDim * 3, 0, batDim, batDim);

        //batUp[0] = batSheet.getImage(0, batDim * 2, batDim, batDim);
        batUp[0] = batSheet.getImage(batDim, batDim * 2, batDim, batDim);
        batUp[1] = batSheet.getImage(batDim * 2, batDim * 2, batDim, batDim);
        batUp[2] = batSheet.getImage(batDim * 3, batDim * 2, batDim, batDim);

        //batRight[0] = batSheet.getImage(0, batDim, batDim, batDim);
        batRight[0] = batSheet.getImage(batDim, batDim, batDim, batDim);
        batRight[1] = batSheet.getImage(batDim * 2, batDim, batDim, batDim);
        batRight[2] = batSheet.getImage(batDim * 3, batDim, batDim, batDim);

        //batLeft[0] = batSheet.getImage(0, batDim * 3, batDim, batDim);
        batLeft[0] = batSheet.getImage(batDim, batDim * 3, batDim, batDim);
        batLeft[1] = batSheet.getImage(batDim * 2, batDim * 3, batDim, batDim);
        batLeft[2] = batSheet.getImage(batDim * 3, batDim * 3, batDim, batDim);

        //Items
        basicPotion = ImageHandler.loadImage("/textures/pt1.png");
        advancedPotion = ImageHandler.loadImage("/textures/pt2.png");
        masterPotion = ImageHandler.loadImage("/textures/pt3.png");
        coin = ImageHandler.loadImage("/textures/coin.png");
        map = ImageHandler.loadImage("/textures/Map.png");
        blueScroll = ImageHandler.loadImage("/textures/BlueScroll.png");
        redScroll = ImageHandler.loadImage("/textures/RedScroll.png");
        greenScroll = ImageHandler.loadImage("/textures/GreenScroll.png");

        //System PopUp
        stolenPopup = ImageHandler.loadImage("/textures/ItemStolenPopup.png");

        //NPCs
        villain = villainSheet.getImage(0, 0, 64, 64);
        innkeep = ImageHandler.loadImage("/textures/15.png");

        //Inventory
        inventory = ImageHandler.loadImage("/textures/inventoryScreen.png");

        //Buildings
        inn = ImageHandler.loadImage("/textures/Inn.png");
        innFloor = ImageHandler.loadImage("/textures/InnFloor.png");

        doorWay[0] = ImageHandler.loadImage("/textures/Door1.png");
        doorWay[1] = ImageHandler.loadImage("/textures/Door2.png");

        doorWayH[0] = ImageHandler.loadImage("/textures/Door1H.png");
        doorWayH[1] = ImageHandler.loadImage("/textures/Door2H.png");

        black = sheet1.getImage(0,0, widthSheet1, heightSheet1);
        purple = sheet1.getImage(widthSheet1, 0, widthSheet1, heightSheet1);
        red = sheet1.getImage(2*widthSheet1, 0, widthSheet1, heightSheet1);
        playerUp = ImageHandler.loadImage("/textures/PlayerUp.png");
        playerDown = ImageHandler.loadImage("/textures/PlayerDown.png");
        playerLeft = ImageHandler.loadImage("/textures/PlayerLeft.png");
        playerRight = ImageHandler.loadImage("/textures/PlayerRight.png");

        rock = ImageHandler.loadImage("/textures/Rock.png");
        paper = ImageHandler.loadImage("/textures/Paper.png");
        scissors = ImageHandler.loadImage("/textures/Scissors.png");

        //Battle state
        battleState = ImageHandler.loadImage("/textures/BattleState.png");
        battlePopup = ImageHandler.loadImage("/textures/BattlePopup.png");

        //Fonts

        //titleFont = FontLoader.load("SENG201-Assignment/res/fonts/Symtext.ttf", 70);

        titleFont = FontLoader.load("/textures/Symtext.ttf", 70);
        invFont = FontLoader.load("/textures/Symtext.ttf", 36);
        smallFont = FontLoader.load("/textures/Symtext.ttf", 20);
        battleFont = FontLoader.load("/textures/Symtext.ttf", 24);
        tinyFont = FontLoader.load("/textures/Symtext.ttf", 16);




        testRadioButton[0] = radio;
        testRadioButton[1] = radioHover;
        testRadioButton[2] = radioClicked;

        invRadioButton[0] = ImageHandler.loadImage("/textures/InvRadioUnclicked.png");
        invRadioButton[1] = ImageHandler.loadImage("/textures/InvRadioClicked.png");
        invRadioButton[2] = ImageHandler.loadImage("/textures/InvRadioHover.png");

        textField[0] = ImageHandler.loadImage("/textures/TextField.png");
        textField[1] = ImageHandler.loadImage("/textures/TextField2.png");

    }


}
