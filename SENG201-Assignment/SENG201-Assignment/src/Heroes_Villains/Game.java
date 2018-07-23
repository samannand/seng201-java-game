package Heroes_Villains;

import Heroes_Villains.Listener.KeyboardListener;
import Heroes_Villains.Listener.MouseListener;
import Heroes_Villains.States.*;
import Heroes_Villains.display.Display;
import Heroes_Villains.entities.Player;
import Heroes_Villains.entities.heroes.Hero;
import Heroes_Villains.graphics.Assets;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

/**
 * Main game object class that is passed through most other objects
 */
public class Game implements Runnable{ //Runnable allows the class to use threads

    /**
     * Method for returning the display
     * @return displayObject
     */
    public Display getDisplay() {
        return display;
    }

    private Display display;
    private Thread gameThread;
    private String title;
    private boolean running = false;
    public int count;

    //State Variables
    public State menuState, pauseState, battleState, controlsState, setupState, adminState, teamBuilderState;
    public GameState gameState;
    public EndState endState;
    private StateHandler stateHandler = new StateHandler();

    //Keyboard Listener
    private KeyboardListener keyboardListener;
    private MouseListener mouseListener;

    //Display Dimensions
    public int width, height;

    public Player player;

    //Graphics variables
    private BufferStrategy buffer;
    public Graphics graphics;

    //Main Game Settings
    public int noOfCities;
    public int noOfHeros;
    public String teamName;

    /**
     * Method for returning the team list
     * @return team array list
     */
    public ArrayList<Hero> getTeam() {
        return team;
    }

    /**
     * Declaration of team array list
     */
    private ArrayList<Hero> team = new ArrayList<Hero>();
    
    public double delta;

    /**
     * Update method, checks state handler is not null, then updates the state, and checks for input that change the state
     */
    private void update(){
        keyboardListener.update();
        if(stateHandler.state != null) {
            stateHandler.state.update();
        }
        if(adminState != null){
        if(keyboardListener.keyJustPressed(KeyEvent.VK_CONTROL) && !((stateHandler.getState() instanceof MenuState) || (stateHandler.getState() instanceof SetupState))) {
            if (stateHandler.getState() instanceof AdminState) {
                stateHandler.setState(stateHandler.getLastState());
            } else {
                stateHandler.setState(adminState);
            }
        }
        }
    }


    /**
     * Render method, gets a buffer strategy and declares graphics object
     */
    private void render(){
        //Creating the buffer for the output frames
        buffer = display.getCanvas().getBufferStrategy();
        if (buffer == null){ //If not buffer exists create one
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        graphics = buffer.getDrawGraphics();
        //Graphics
        graphics.clearRect(0, 0, width, height);
        if(stateHandler.state != null) {
            stateHandler.state.render(graphics);
        }
        //Graphics end
        buffer.show();
        graphics.dispose();

    }

    /**
     * Method called when the game is run to set up the display and initialize the first states required
     */
    private void init(){ //Function run by run()
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyboardListener);
        display.getFrame().addMouseListener(mouseListener);
        display.getFrame().addMouseMotionListener(mouseListener);
        display.getCanvas().addMouseListener(mouseListener);
        display.getCanvas().addMouseMotionListener(mouseListener);
        Assets.init();
        //gameState = new GameState(this);
        //player = ((GameState) gameState).player;
        menuState = new MenuState(this);
        pauseState = new PauseState(this);
        //battleState = new BattleState(this);
        controlsState = new ControlsState(this);
        setupState = new SetupState(this);
        stateHandler.setState(menuState);
    }


    @Override
    public void run() { //Following function is run when the thread is started
        init();

        //System.out.println(System.currentTimeMillis() / 1000);

        long lastTime = System.nanoTime();
        final int  FPS = 60;
        final long OPTIMAL_TIME = 1000000000 / FPS ;
        int lastFpsTime = 0;
        int fps = 0;
        count = 0;

        while(running){
            long timeNow = System.nanoTime();
            long updateLength = timeNow - lastTime;
            lastTime = timeNow;
            
            delta = updateLength / ((double)OPTIMAL_TIME);
            
            lastFpsTime += updateLength;
            fps ++;
            
            if (lastFpsTime >= 1000000000) {
            	//System.out.println("(FPS: "+fps+")");
            	//System.out.println(delta);
                count ++;
                //System.out.println(count);
            	lastFpsTime = 0;
            	fps = 0;
            }
            
            //Running game updates
            update();
            render();
            try {
            	
            	if ((lastTime - System.nanoTime() + OPTIMAL_TIME) >= 0) {

            		Thread.sleep((lastTime - System.nanoTime() + OPTIMAL_TIME)/1000000);
            	
            	}

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        stop();

    }

    /**
     * Method for starting the game thread
     */
    public synchronized void start(){
        if (running){
            return;
        }
        running = true;
        gameThread = new Thread(this);
        gameThread.start();


    }

    /**
     * Method for stopping the game thread
     */
    public synchronized void stop(){
        if (!running){
            return;
        }
        running = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * Constructer method that takes a game title for the window, and a size for the window
     * @param title String title of the game
     * @param width int width of the window
     * @param height int height of the window
     */
    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        keyboardListener = new KeyboardListener();
        mouseListener = new MouseListener();
        }

    /**
     * Method for getting the mouse listener
      * @return MouseListener object
     */
    public MouseListener getMouseListener() {
        return mouseListener;
    }

    /**
     * Method for getting the keyboard listener
     * @return KeyboardListener object
     */
    public KeyboardListener getKeyboardListener() {
        return keyboardListener;
    }

    /**
     * Method for getting the state handler object
     * @return StateHandler object
     */
    public StateHandler getStateHandler() {
        return stateHandler;
    }

    /**
     * Method for getting the game state
     * @return GameState object
     */
    public State getGameState() {
        return gameState;
    }

    /**
     * method for getting the menu state
     * @return MenuState object
     */
    public State getMenuState() {
        return menuState;
    }

    /**
     * Method for getting the games graphics for use in rendering
     * @return Graphics object
     */
    public Graphics getGraphics() {
        return graphics;
    }

    /**
     * Method for getting the pause state
     * @return PauseState object
     */
    public State getPauseState() {
        return pauseState;
    }

    /**
     * Method for getting the battle state
     * @return BattleState object
     */
    public State getBattleState() {
        return battleState;
    }

    /**
     * Method for getting the controls state
     * @return ControlsState object
     */
    public State getControlsState() { return controlsState; }

    /**
     * Method for getting the setup state
     * @return SetupState object
     */
    public State getSetupState() { return setupState; }

    /**
     * Method for getting the team builder state
     * @return TeamBuilder object
     */
    public State getTeamBuilderState() { return teamBuilderState; }

    /**
     * Method for gettting the end state
     * @return EndState object
     */
    public State getEndState() { return endState; }

    //Main Game settings Getters and Setters

    /**
     * Method for getting the number of cities in the game
     * @return integer, number of cities
     */
    public int getNoOfCities() {
        return noOfCities;
    }

    /**
     * Method for setting the number of cities to the integer parameter
     * @param noOfCities integer parameter number of cities
     */
    public void setNoOfCities(int noOfCities) {
        this.noOfCities = noOfCities;
    }

    /**
     * Method for getting the number of heros in the game iniitially
     * @return integer, number of heros
     */
    public int getNoOfHeros() {
        return noOfHeros;
    }

    /**
     * Method for setting the number of heros
     * @param noOfHeros integer, number of heros
     */
    public void setNoOfHeros(int noOfHeros) {
        this.noOfHeros = noOfHeros;
    }

    /**
     * Method for returning the player of the game
     * @return Player object: player
     */
    public Player getPlayer() {
        return player;
    }

}
