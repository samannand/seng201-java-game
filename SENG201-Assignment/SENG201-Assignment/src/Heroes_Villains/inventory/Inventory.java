package Heroes_Villains.inventory;

import Heroes_Villains.Game;
import Heroes_Villains.SystemUI.RadioButtons;
import Heroes_Villains.entities.items.Item;
import Heroes_Villains.graphics.Assets;
import Heroes_Villains.graphics.DrawText;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Contains the items and the system to use them.
 */
public class Inventory {

    private Game game;
    public boolean open = false;
    public ArrayList<Item> items;
    private int inventoryX = 420;
    private int inventoryY = 48;
    private int inventoryWidth = 800;
    private int inventoryHeight = 600;
    private int centreX = inventoryX + 267, centreY = inventoryY + inventoryHeight / 2 + 5;
    private int imageX = 1026, imageY = 101, imageWidth = 100, imageHeight = 100;
    private int countX = 1076, countY = 241;
    private int listSpacing = 47;
    private int currentIndex = 0;
    private int totalWidth1, totalWidth2, totalWidth3, clicked;
    private RadioButtons heroSelector1, heroSelector2, heroSelector3;

    /**
     * Constructs the inventory and places objects.
     * @param game the object containing all the objects and variables in the game.
     */
    public Inventory(Game game) {
        this.game = game;
        items = new ArrayList<>();
        totalWidth1 = 50;
        totalWidth2 = 10 + 100;
        totalWidth3 = 20 + 150;
        heroSelector1 = new RadioButtons(countX-(totalWidth1/2), countY+150, game, Assets.invRadioButton, 1, 10, true, 50, 50);
        heroSelector2 = new RadioButtons(countX-(totalWidth2/2), countY+150, game, Assets.invRadioButton, 2, 10, true, 50, 50);
        heroSelector3 = new RadioButtons(countX-(totalWidth3/2), countY+150, game, Assets.invRadioButton, 3, 10, true, 50, 50);
    }

    /**
     * Adds an item to the inventory by checking if an item with the same id
     * already exists and if it does the count of the existing item increases else
     * the item is added to the inventory array list.
     * @param item the item to be added to the inventory.
     */
    public void addItem(Item item) {
        for(Iterator<Item> iterator = items.listIterator(); iterator.hasNext();){
            Item currItem = iterator.next();
            if(currItem.getId() == item.getId()) {
                currItem.setCount(currItem.getCount()+1);
                return;
            }
        }
        items.add(item);
    }


    /**
     * Updates all of the variables in and below the inventory.
     */
    public void update() {
        if(game.getTeam().size()==1) {
            heroSelector1.update();
            clicked = heroSelector1.currentlyClicked;
        }
        if(game.getTeam().size()==2) {
            heroSelector2.update();
            clicked = heroSelector2.currentlyClicked;
        }
        if(game.getTeam().size()==3) {
            heroSelector3.update();
            clicked = heroSelector3.currentlyClicked;
        }
        if(game.getKeyboardListener().keyJustPressed(KeyEvent.VK_E)) {
            heroSelector1.clicked(0);
            heroSelector2.clicked(0);
            heroSelector3.clicked(0);
            open = !open;
        }
        if(!open) {
            return;
        }
        for(Iterator<Item> iterator = items.listIterator(); iterator.hasNext();) {
            Item currItem = iterator.next();
            if (currItem.getCount() <= 0) {
                iterator.remove();
            }
        }
        if(game.getKeyboardListener().keyJustPressed(KeyEvent.VK_UP)) {
            currentIndex--;
        }
        if(game.getKeyboardListener().keyJustPressed(KeyEvent.VK_DOWN)) {
            currentIndex++;
        }

        if(currentIndex < 0) {
            currentIndex = items.size() - 1;
        }else if(currentIndex >= items.size()) {
            currentIndex = 0;
        }
    }

    /**
     * Draw all inventory object and all objects below it on to the
     * graphics objects.
     * @param graphics the object everything is draw to.
     */
    public void render(Graphics graphics) {
        if(!open) {
            return;
        }
        int length = items.size();

        if(items.size() == 0) {
            graphics.drawImage(Assets.inventory, inventoryX, inventoryY, inventoryWidth, inventoryHeight, null);
            return;
        }
        graphics.drawImage(Assets.inventory, inventoryX, inventoryY, inventoryWidth, inventoryHeight, null);
        for(int i=-5; i<6; i++) {
            if (currentIndex + i < 0 || currentIndex + i >= length) {
                continue;
            }
            if (i == 0) {
                DrawText.draw(graphics, "> " + items.get(currentIndex + i).getName() + " <", centreX, centreY + i * listSpacing, true, Color.YELLOW, Assets.invFont);
            } else {
                DrawText.draw(graphics, items.get(currentIndex + i).getName(), centreX, centreY + i * listSpacing, true, Color.WHITE, Assets.invFont);
            }
        }
        graphics.drawImage(items.get(currentIndex).image, imageX, imageY, imageWidth, imageHeight, null);
        DrawText.draw(graphics, Integer.toString(items.get(currentIndex).getCount()), countX, countY, true, Color.WHITE, Assets.invFont);
        DrawText.draw(graphics, "Coins: " + Integer.toString(game.getPlayer().money), countX, countY+300, true, Color.WHITE, Assets.invFont);

        if(items.get(currentIndex).isUseable() && game.getMouseListener().isHovering(inventoryX+583, inventoryY+258, 144, 64)) { //If the item is usable and the mouse is hovering over the use button
            DrawText.draw(graphics, ">USE<", 655+inventoryX, 290+inventoryY,true, Color.WHITE, Assets.invFont);
            if(game.getMouseListener().isLeftClicked()) {
                game.getMouseListener().leftClicked = false;
                if(game.getTeam().size()==1) {
                    System.out.println(game.getTeam().get(heroSelector1.currentlyClicked).getName() + ": " +game.getTeam().get(heroSelector1.currentlyClicked).getHealth());
                    items.get(currentIndex).use(game.getTeam().get(heroSelector1.currentlyClicked));
                }
                if(game.getTeam().size()==2) {
                    System.out.println(game.getTeam().get(heroSelector2.currentlyClicked).getName() + ": " +game.getTeam().get(heroSelector2.currentlyClicked).getHealth());
                    items.get(currentIndex).use(game.getTeam().get(heroSelector2.currentlyClicked));
                }
                if(game.getTeam().size()==3) {
                    System.out.println(game.getTeam().get(heroSelector3.currentlyClicked).getName() + ": " +game.getTeam().get(heroSelector3.currentlyClicked).getHealth());
                    items.get(currentIndex).use(game.getTeam().get(heroSelector3.currentlyClicked));
                }
            }
        }else if(items.get(currentIndex).isUseable()) { //If the selected item is usable
            DrawText.draw(graphics, ">USE<", 655+inventoryX, 290+inventoryY,true, Color.YELLOW, Assets.invFont);
        }else {
            DrawText.draw(graphics, ">USE<", 655+inventoryX, 290+inventoryY,true, Color.GRAY, Assets.invFont);
        }

        if (game.getMouseListener().isHovering(imageX, imageY, imageWidth, imageHeight)) {
            DrawText.draw(graphics, items.get(currentIndex).getDescription(), 30+inventoryX, 30+inventoryY,false, Color.GRAY, Assets.smallFont);
        }


        if(game.getTeam().size()==1) {
            heroSelector1.render(graphics);
        }
        if(game.getTeam().size()==2) {
            heroSelector2.render(graphics);
        }
        if(game.getTeam().size()==3) {
            heroSelector3.render(graphics);
        }
        if(game.getTeam().size()==1) {
            DrawText.draw(graphics, game.getTeam().get(heroSelector1.currentlyClicked).getName(), countX, countY+240, true, Color.WHITE, Assets.smallFont);
            DrawText.draw(graphics, "Health: "+Integer.toString(game.getTeam().get(heroSelector1.currentlyClicked).getHealth()), countX, countY+270, true, Color.WHITE, Assets.smallFont);
        }
        if(game.getTeam().size()==2) {
            DrawText.draw(graphics, game.getTeam().get(heroSelector2.currentlyClicked).getName(), countX, countY+240, true, Color.WHITE, Assets.smallFont);
            DrawText.draw(graphics, "Health: "+Integer.toString(game.getTeam().get(heroSelector2.currentlyClicked).getHealth()), countX, countY+270, true, Color.WHITE, Assets.smallFont);
        }
        if(game.getTeam().size()==3) {
            DrawText.draw(graphics, game.getTeam().get(heroSelector3.currentlyClicked).getName(), countX, countY+240, true, Color.WHITE, Assets.smallFont);
            DrawText.draw(graphics, "Health: "+Integer.toString(game.getTeam().get(heroSelector3.currentlyClicked).getHealth()), countX, countY+270, true, Color.WHITE, Assets.smallFont);
        }

    }

    /**
     * Returns index of the hero selector radio button in the inventory currently
     * clicked.
     * @return the index of the currently clicked hero selector radio buttons.
     */
    public int getClicked() {
        return clicked;
    }
}
