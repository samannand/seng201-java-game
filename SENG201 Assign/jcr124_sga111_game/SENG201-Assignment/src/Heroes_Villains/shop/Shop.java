package Heroes_Villains.shop;

import Heroes_Villains.Game;
import Heroes_Villains.entities.items.Item;
import Heroes_Villains.graphics.Assets;
import Heroes_Villains.graphics.DrawText;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Class for bringing up the shop overlay in the Inn, allows for purchasing of items from the InnKeeper
 */
public class Shop {

    public boolean open;
    private Game game;
    private ArrayList<Item> shopItems;
    private int inventoryX = 420;
    private int inventoryY = 48;
    private int inventoryWidth = 800;
    private int inventoryHeight = 600;
    private int centreX = inventoryX + 267, centreY = inventoryY + inventoryHeight / 2 + 5;
    private int imageX = 1026, imageY = 101, imageWidth = 100, imageHeight = 100;
    private int countX = 1076, countY = 241;
    private int listSpacing = 47;
    private int currentIndex = 0;

    /**
     * Constructor for shop class, initializes the items in the shop to the passed array list
     * @param game game object, generic pass-through
     * @param shopItems the items that the shop will sell
     */
    public Shop(Game game, ArrayList<Item> shopItems) {
        this.game = game;
        this.shopItems = shopItems;
    }

    /**
     * Method for updating the functionality of the shop 60 times a second, mainly deals with navigation and items of zero count
     */
    public void update() {

        if(game.getKeyboardListener().keyJustPressed(KeyEvent.VK_UP)) {
        currentIndex--;
    }
        if(game.getKeyboardListener().keyJustPressed(KeyEvent.VK_DOWN)) {
        currentIndex++;
    }

        if(currentIndex < 0) {
        currentIndex = shopItems.size() - 1;
    }else if(currentIndex >= shopItems.size()) {
        currentIndex = 0;
    }

    }

    /**
     * Method for rendering the aesthetics of the shop 60 times a second and also handles purchasing as the render methods need to be placed in this method
     * @param graphics graphics object that the game draws to
     */
    public void render(Graphics graphics) {
        if(!open) {
            return;
        }
        if(game.getPlayer().getInventory().open) {
            return;
        }

        int length = shopItems.size();

        if(shopItems.size() == 0) {
            graphics.drawImage(Assets.inventory, inventoryX, inventoryY, inventoryWidth, inventoryHeight, null);
            return;
        }
        graphics.drawImage(Assets.inventory, inventoryX, inventoryY, inventoryWidth, inventoryHeight, null);
        for(int i=-5; i<6; i++) {
            if (currentIndex + i < 0 || currentIndex + i >= length) {
            continue;
        }
            if (currentIndex < 0 || currentIndex >= length) {
                continue;
            }
            if (i == 0) {
                DrawText.draw(graphics, "> " + shopItems.get(currentIndex+i).getName() + " <", centreX, centreY + i * listSpacing, true, Color.YELLOW, Assets.invFont);
            } else{
                DrawText.draw(graphics, shopItems.get(currentIndex+i).getName(), centreX, centreY + i * listSpacing, true, Color.WHITE, Assets.invFont);
            }
        }
        graphics.drawImage(shopItems.get(currentIndex).image, imageX, imageY, imageWidth, imageHeight, null);
        DrawText.draw(graphics, Integer.toString(shopItems.get(currentIndex).cost), countX, countY, true, Color.WHITE, Assets.invFont);
        DrawText.draw(graphics, "Cost: ", countX-75, countY, true, Color.WHITE, Assets.smallFont);
        DrawText.draw(graphics, "Coins: " + Integer.toString(game.getPlayer().money), countX, countY+200, true, Color.WHITE, Assets.invFont);
        if(shopItems.get(currentIndex).isBuyable() && game.getMouseListener().isHovering(inventoryX+583, inventoryY+258, 144, 64)) {
            DrawText.draw(graphics, ">BUY<", 655+inventoryX, 290+inventoryY,true, Color.WHITE, Assets.invFont);
            if(game.getMouseListener().isLeftClicked()) {
                game.getMouseListener().leftClicked = false;
                shopItems.get(currentIndex).buy();
            }
        }else if(shopItems.get(currentIndex).isBuyable()) {
            DrawText.draw(graphics, ">BUY<", 655+inventoryX, 290+inventoryY,true, Color.YELLOW, Assets.invFont);
        }else {
            DrawText.draw(graphics, ">BUY<", 655+inventoryX, 290+inventoryY,true, Color.GRAY, Assets.invFont);
        }


        DrawText.draw(graphics, shopItems.get(currentIndex).getDescription(), 30+inventoryX, 30+inventoryY,false, Color.GRAY, Assets.smallFont);

    }

}
