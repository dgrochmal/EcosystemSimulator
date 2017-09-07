/**
 * 
 */
package edu.ncsu.csc216.simulation.actor;

import java.awt.Color;

/**
 * Contains all information for each type of Animal(Predator, Middle, and Prey)
 * Includes both default values and values passed in from a configuration file.
 * 
 * @author Dan Grochmal
 *
 */
public class Configs {

	/** Integer array containing the default rank for each animal */
	public static final int[] DEFAULT_FOOD_CHAIN_RANK = {0, 10, 20};
	/** Color array containing the default color for each animal */
	public static final Color[] DEFAULT_COLORS = {Color.green, Color.orange, Color.red};
	/** Color array containing the colors from the configuration file */
	public static Color[] playerColors = new Color[3];
	/** Integer array containing the default amount of time that will cause an animal to starve */
	public static final int[] DEFAULT_STARVE_TIME = {10, 6, 5};
	/** Integer array containing the amount of time that will cause an animal to starve,
	 *  from the configuration file */
	public static int[] starveTime = new int[3];
	/** Integer array containing the default amount of time that needs to elapse before an animal can breed again */
	public static final int[] DEFAULT_BREED_TIME = {1, 7, 15};
	/** Integer array containing the amount of time that needs to elapse before an animal can breed,
	 * from the configuration file*/
	public static int[] breedTime = new int[3];
	
	/**
	 * The initial configuration numbers
	 * 
	 * @param c is the default color array
	 * @param s is the default starve number array
	 * @param b is the default breed array
	 */
	public static void initConfigs(Color[] c, int[] s, int[] b){
		playerColors = c;
		starveTime = s;
		breedTime = b;
	}
	
	/**
	 * Sets the Configs to the defaults
	 */
	public static void setToDefaults(){
		playerColors = DEFAULT_COLORS;
		starveTime = DEFAULT_STARVE_TIME;
		breedTime = DEFAULT_BREED_TIME;
	}
	
	/**
	 * Gets the color of the prey Animal
	 * 
	 * @return The color of the prey Animal
	 */
	public static Color getPreyColor(){
		return playerColors[0];
	}
	
	/**
	 * Gets the color of the middle Animal
	 * 
	 * @return The color of the middle Animal
	 */
	public static Color getMiddleColor(){
		return playerColors[1];
	}
	
	/**
	 * Gets the color of the predator Animal
	 * 
	 * @return The color of the predator Animal
	 */
	public static Color getPredatorColor(){
		return playerColors[2];
	}
	
	/**
	 * Gets the food chain rank of the prey Animal
	 * 
	 * @return The food chain rank of the prey Animal
	 */
	public static int getPreyFoodChainRank(){
		return DEFAULT_FOOD_CHAIN_RANK[0];
	}
	
	/**
	 * Gets the food chain rank of the middle Animal
	 * 
	 * @return The food chain rank of the middle Animal
	 */
	public static int getMiddleFoodChainRank(){
		return DEFAULT_FOOD_CHAIN_RANK[1];
	}
	
	/**
	 * Gets the food chain rank of the predator Animal
	 * 
	 * @return The food chain rank of the predator Animal
	 */
	public static int getPredatorFoodChainRank(){
		return DEFAULT_FOOD_CHAIN_RANK[2];
	}
	
	/**
	 * Gets the starve time of the prey Animal
	 * 
	 * @return The starve time of the prey Animal
	 */
	public static int getPreyStarveTime(){
		return starveTime[0];
	}
	
	/**
	 * Gets the starve time of the middle Animal
	 * 
	 * @return The starve time of the middle Animal
	 */
	public static int getMiddleStarveTime(){
		return starveTime[1];
	}
	
	/**
	 * Gets the starve time of the predator Animal
	 * 
	 * @return The starve time of the predator Animal
	 */
	public static int getPredatorStarveTime(){
		return starveTime[2];
	}
	
	/**
	 * Gets the breed time of the prey Animal
	 * 
	 * @return The breed time of the prey Animal
	 */
	public static int getPreyBreedTime(){
		return breedTime[0];
	}
	
	/**
	 * Gets the breed time of the middle Animal
	 * 
	 * @return The breed time of the middle Animal
	 */
	public static int getMiddleBreedTime(){
		return breedTime[1];
	}
	
	/**
	 * Gets the breed time of the predator Animal
	 * 
	 * @return The breed time of the predator Animal
	 */
	public static int getPredatorBreedTime(){
		return breedTime[2];
	}
}