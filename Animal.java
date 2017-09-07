/**
 * 
 */
package edu.ncsu.csc216.simulation.actor;

import java.awt.Color;
import java.util.Random;

import edu.ncsu.csc216.simulation.environment.EcoGrid;
import edu.ncsu.csc216.simulation.environment.utils.Location;

/**
 * Animal object that serves as a parent class to all species on the food chain
 * 
 * @author Dan Grochmal
 *
 */
public abstract class Animal {

	/** Amount of steps since the last meal */
	public int timeSinceLastMeal;
	/** Amount of steps since the last breeding */
	public int timeSinceLastBreed;
	/** True if Animal is able to move, false otherwise */
	public boolean canActThisStep;
	/** Symbol representing each different type of Animal */
	public char symbol;
	/** True if an Animal is alive, false otherwise */
	public boolean alive;
	/** The level of each type of animal in a food chain */
	public static int seed = 500;
	/** Random number generator to assign a random seed */
	public static Random randomGenerator = new Random(seed);
	
	/**
	 * Constructor for the Animal object
	 * 
	 * @param symbol is the character that represents an Animal
	 */
	public Animal(char symbol){
		super();
		this.symbol = symbol;
		this.disable();
		alive = true;
		timeSinceLastBreed = 0;
		timeSinceLastMeal = 0;
	}
	
	/**
	 * Sets the seed to a random number
	 * 
	 * @param s is the unaltered number to be randomized
	 */
	public static void setRandomSeed(int s){
		seed = s;
		randomGenerator = new Random(seed);
	}
	
	/**
	 * Gets the symbol of an Animal
	 * 
	 * @return The symbol of an Animal
	 */
	public char getSymbol(){
		return this.symbol;
	}
	
	/**
	 * Gets if an Animal is alive or not
	 * 
	 * @return True if alive, false otherwise
	 */
	public boolean isAlive(){
		return alive;
	}
	
	/**
	 * Enables an Animal
	 */
	public void enable(){
		canActThisStep = true;
	}
	
	/**
	 * Disables an Animal
	 */
	public void disable(){
		canActThisStep = false;
	}
	
	/**
	 * Kills an Animal
	 */
	protected void die(){
			alive = false;
	}
	
	/**
	 * Gets if an Animal can act or not
	 * 
	 * @return True if the Animal can act, false otherwise
	 */
	protected boolean canAct(){
		return canActThisStep;
	}
	
	/**
	 * Gets the amount of steps since the animal last bred
	 * 
	 * @return The amount of steps since the Animal last bred
	 */
	protected int getTimeSinceLastBreed(){
		return timeSinceLastBreed;
	}
	
	
	/**
	 * Gets the amount of steps since the Animal last ate
	 * 
	 * @return The amount of steps since the Animal last ate
	 */
	protected int getTimeSinceLastMeal(){
		return timeSinceLastMeal;
	}
	
	/**
	 * 
	 * Increments the amount of steps since the last meal by one
	 */
	protected void incrementTimeSinceLastMeal(){
		timeSinceLastMeal++;
	}
	
	/**
	 * 
	 * Increments the amount of steps since the last breeding by one
	 */
	protected void incrementTimeSinceLastBreed(){
		timeSinceLastBreed++;
	}
	
	/**
	 * Breeds and creates a new Animal
	 * 
	 * @return True if the animal bred, false otherwise
	 * @param location is the X and Y coordinates of an Animal
	 * @param grid is the array of Animal objects
	 */
	protected boolean breed(Location location, EcoGrid grid){
		Location l = null;
		Animal a;
		l = grid.findFirstEmptyNeighbor(location, 0);
		if(!pastBreedTime(this.timeSinceLastBreed)){
			return false;
		}
		if(l == null){
			return false;
		} else {
			a = makeNewBaby();
			a.disable();
			grid.add(a, l);
			timeSinceLastBreed = 0;
			return true;
		}
	}
	
	/**
	 * Moves an Animal to an adjacent location
	 * 
	 * @param location is the X and Y coordinates of an Animal
	 * @param grid is the array of Animal objects
	 */
	protected void move(Location location, EcoGrid grid){
		int direction = randomGenerator.nextInt(4);
		Animal a = null;
		Location l = null;
		a = grid.getItemAt(location);
		l = grid.findFirstEmptyNeighbor(location, direction);
		if(l != null){
			a.disable();
			grid.remove(location);
			grid.add(a, l);
		}
//		incrementTimeSinceLastBreed();
//		incrementTimeSinceLastMeal();
	}
	
	/**
	 * Animal eats another and moves to the eaten Animal's spot
	 * 
	 * @return True if an Animal is successful in eating another
	 * @param location is the X and Y coordinates of an Animal
	 * @param grid is the array of Animal objects
	 */
	protected boolean eat(Location location, EcoGrid grid){
		Location w = grid.dueWest(location);
		Location n = grid.dueNorth(location);
		Location e = grid.dueEast(location);
		Location s = grid.dueSouth(location);
		Animal eater;
		Animal eatee;
		eater = grid.getItemAt(location);
		eatee = grid.getItemAt(w);
		if((eatee != null) && eater.getFoodChainRank() > eatee.getFoodChainRank()){
			grid.remove(location);
			grid.remove(w);
			grid.add(eater, w);
//			incrementTimeSinceLastBreed();
//			incrementTimeSinceLastMeal();
			timeSinceLastMeal = 0;
			eater.disable();
			return true;
		} 
		eatee = grid.getItemAt(n);
		if((eatee != null) && eater.getFoodChainRank() > eatee.getFoodChainRank()){
			grid.remove(location);
			grid.remove(n);
			grid.add(eater, n);
//			incrementTimeSinceLastBreed();
//			incrementTimeSinceLastMeal();
			timeSinceLastMeal = 0;
			eater.disable();
			return true;
		}
		eatee = grid.getItemAt(e);
		if((eatee != null) && eater.getFoodChainRank() > eatee.getFoodChainRank()){
			grid.remove(location);
			grid.remove(e);
			grid.add(eater, e);
//			incrementTimeSinceLastBreed();
//			incrementTimeSinceLastMeal();
			timeSinceLastMeal = 0;
			eater.disable();
			return true;
		}
		eatee = grid.getItemAt(s);
		if((eatee != null) && eater.getFoodChainRank() > eatee.getFoodChainRank()){
			grid.remove(location);
			grid.remove(s);
			grid.add(eater, s);
//			incrementTimeSinceLastBreed();
//			incrementTimeSinceLastMeal();
			timeSinceLastMeal = 0;
			eater.disable();
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Gets the color of an Animal
	 * 
	 * @return The color of an Animal
	 */
	public abstract Color getColor();
	
	/**
	 * Allows an Animal to act
	 * 
	 * @param location is the X and Y coordinates of an Animal
	 * @param grid is the array of Animal objects
	 */
	public abstract void act(Location location, EcoGrid grid);
	
	/**
	 * Discerns if an Animal is able to breed again based on the amount of time since the last breeding
	 * 
	 * @param time is the amount of time since breeding last
	 * @return True if the Animal is past the amount of time needed to wait to breed (or breed again)
	 */
	protected abstract boolean pastBreedTime(int time);
	
	/**
	 * Animal object creates a new animal object
	 * 
	 * @return A new baby Animal object
	 */
	protected abstract Animal makeNewBaby();
	
	/**
	 * Gets the food chain rank of an Animal
	 * 
	 * @return The food chain rank of an Animal
	 */
	protected abstract int getFoodChainRank();
}