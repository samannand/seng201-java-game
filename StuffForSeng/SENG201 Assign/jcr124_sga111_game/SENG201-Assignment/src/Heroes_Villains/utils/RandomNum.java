package Heroes_Villains.utils;

import java.util.Random;

/**
 * A class for generating a random number within a given bound
 */
public class RandomNum {

    /**
     * Method that returns a number between 0 and the bound exclusively
     * @param bound integer parameter for the bound
     * @return integer value between 0 and the bound
     */
    public static int getNum(int bound) {
        int output;
        Random number = new Random();
        output = number.nextInt(bound);
        return output;
    }
}
