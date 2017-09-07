/**
 * 
 */
package edu.ncsu.csc216.simulation.actor;

import java.awt.Color;

import edu.ncsu.csc216.simulation.environment.EcoGrid;
import edu.ncsu.csc216.simulation.environment.utils.Location;

/**
 * PredatorPrey includes the unique abilities of an Animal in the middle of a food chain.
 * 
 * @author Dan Grochmal
 *
 */
public class PredatorPrey extends Animal {

	/**
	 * Constructor of the PredatorPrey object
	 * 
	 * @param symbol is the symbol of the Animal
	 */
	public PredatorPrey(char symbol){
	  super(symbol);
	}
	
	/**
	 * Gets the color of the Animal
	 * 
	 * @return the color of the Animal
	 */
	public Color getColor(){
		return Configs.getMiddleColor();
	}
	
	/**
	 * The animal commits an act
	 * 
	 * @param location is the X and Y coordinates of an Animal in the grid
	 * @param grid is the Animal array in which an Animal is located
	 */
	public void act(Location location, EcoGrid grid){
		boolean stop = false;
		if(canActThisStep){
			if(!stop && pastBreedTime(getTimeSinceLastBreed())){
				stop = breed(location, grid);
				if(stop){
					incrementTimeSinceLastMeal();
				}
			}
			if (!stop){
				stop = eat(location, grid);
				if(stop){
					incrementTimeSinceLastBreed();
				}
			}
			if (!stop){
				move(location, grid);
				incrementTimeSinceLastBreed();
				incrementTimeSinceLastMeal();
			}
			if(getTimeSinceLastMeal() >= Configs.getMiddleStarveTime()){
				die();
			}
			this.disable();
		}
	}
	
	/**
	 * Discerns if an Animal is able to breed again based on the amount of time since the last breeding
	 * 
	 * @param time is the amount of time since breeding last
	 * @return True if the Animal is past the amount of time needed to wait to breed (or breed again)
	 */
	protected boolean pastBreedTime(int time){
		return (time >= Configs.getMiddleBreedTime());
	}
	
	/**
	 * Animal object creates a new animal object
	 * 
	 * @return A new baby Animal object
	 */
	protected Animal makeNewBaby(){
		Animal a = new PredatorPrey(symbol);
		a.disable();
		return a;
	}
	
	/**
	 * Gets the food chain rank of an Animal
	 * 
	 * @return The food chain rank of an Animal
	 */
	protected int getFoodChainRank(){
		return Configs.getMiddleFoodChainRank();
	}
}