package Heroes_Villains.entities.items;

import Heroes_Villains.Game;
import Heroes_Villains.cities.Citys;
import Heroes_Villains.cities.rooms.Hospital;
import Heroes_Villains.entities.heroes.Hero;

import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Represents an Item used to heal a hero.
 * Extends Item.
 */
public class HealingItem extends Item {

    private int healthAmount;
    private int applyTime;

    /**
     * Constructor sets the default class variables.
     *
     * @param id the id numble of the item.
     * @param name the name of the item.
     * @param image the image of the item.
     * @param game the game object containing all objects and variables in the game.
     * @param cityClass the object containing the array of City objects.
     * @param healthAmount the amount of health that potion will heal to a hero.
     * @param cost the amount that the item costs to buy.
     * @param applyTime the amount of time is take for the item to full be
     *                  used on the player.
     * @param description the written description of the item.
     */
    public HealingItem(int id, String name, BufferedImage image, Game game, Citys cityClass, int healthAmount, int cost, int applyTime, String description) {
        super(id, name, image, game, cityClass, description);
        this.healthAmount = healthAmount;
        this.cost = cost;
        this.applyTime = applyTime;
    }

    @Override
    public void use(Hero hero) {

        if ((hero.getHealth() < hero.getMaxHealth()) && !hero.getUsingPotion()) {
            count --;
            Timer timer = new Timer();
            hero.setUsingPotion(true); //Make the hero be currently using the potion
            int loops = 0;
            boolean done = false;
            int testHealth = hero.getHealth();
            int testMax = hero.getHealth() + healthAmount;
            if (testMax > hero.getMaxHealth()) { //If the healing effect would be increasing the hero to more that max health set to max health
                testMax = hero.getMaxHealth();
            }

            while (!done) { //While the potion iss still applying
                if (testHealth + healthAmount/4 <= testMax) {
                    testHealth += healthAmount/4;
                    loops += 1;
                } else {
                    done = true;
                }
            }
            game.gameState.masterCities.cities[game.getPlayer().getCurrentCity()].hospital.getTimerList().get(game.getTeam().indexOf(hero)).setTimeRemaining((applyTime/4)*loops); //Changes the time remaining a a heal in the hospital in the city the hero is currently in
            TimerTask task = new TimerTask() {
                int timerCounter = 0;
                @Override
                public void run() {
                    if (timerCounter >= 3) {
                        hero.setUsingPotion(false);
                        timer.cancel();
                    }
                    if (hero.getHealth() + healthAmount/4 < hero.getMaxHealth()) {
                        hero.setHealth(hero.getHealth() + healthAmount/4);
                        System.out.println("Used " + name);
                        System.out.println("first");
                        System.out.println(hero.getName() + ": " +hero.getHealth());
                        timerCounter ++;
                    } else {
                        hero.setHealth(hero.getMaxHealth());
                        System.out.println(hero.getName() + ": " +hero.getHealth());
                        System.out.println("second");
                        hero.setUsingPotion(false);
                        timer.cancel();
                    }
                }
            };

            timer.schedule(task, ((int)((applyTime * 1000)/4)), ((int)((applyTime * 1000)/4)));

        }

    }

    /**
     * Returns a boolean if the item is able to be used.
     * @return
     */
    public boolean isUseable() {
        try {

            if (cityClass.cities[game.getPlayer().getCurrentCity()].getRooms()[game.getPlayer().getCurrentRoom()] instanceof Hospital) { //Checks if the player is in the hospital and if they are allow the player to use the item
                return true;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return false;
    }
}
