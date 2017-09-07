
package edu.ncsu.csc216.simulation.environment;

import edu.ncsu.csc216.simulation.actor.Animal;
import edu.ncsu.csc216.simulation.environment.utils.Location;

/**
 * Ecosystem creates and fills a 2D array of Animals to be used in the simulator class
 * 
 * @author Dan Grochmal
 *
 */
public class Ecosystem implements EcoGrid {

	/**	The maximum amount of rows that an Ecosystem can have */
	public int maxRows;
	/** The maximum amount of columns that an Ecosystem can have */
	public int maxCols;
	/** 2D array of Animal objects representing the Ecosystem */
	private Animal[][] map;
	/** The size of the Ecosystem */
	private final int size;
	
	/**
	 * The Constructor for the Ecosystem object
	 * 
	 * @param row is the amount of rows in a Ecosystem
	 * @param col is the amount of columns in a Ecosystem
	 */
	public Ecosystem(int row, int col){
		map = new Animal[row][col];
		size = row;
	}
	
	/**
	 * Discerns if a grid location is empty or not
	 * 
	 * @param location is a location in the Ecosystem grid
	 * @return True if the location is empty, false otherwise
	 */
	public boolean isEmpty(Location location){
		Animal a;
		a = getItemAt(location);
		if(a == null){
			return true;
		}
		return false;
	}
	
	/**
	 * Gets an Animal object from a location in the grid
	 * 
	 * @param location is a location in the Ecosystem grid
	 * @return The Animal object from the location
	 */
	public Animal getItemAt(Location location){
		Animal a;
		a = map[location.getRow()][location.getCol()];
		return a;
	}
	
	/**
	 * Removes an object from the location in the grid
	 * 
	 * @param location is a location in the Ecosystem grid
	 */
	public void remove(Location location){
		Animal a = null;
		map[location.getRow()][location.getCol()] = a;
	}
	
	/**
	 * Adds an Animal object to a location in the grid
	 * 
	 * @param animal Animal object to be added to the grid
	 * @param location is a location in the Ecosystem grid
	 */
	public void add(Animal animal, Location location){
		map[location.getRow()][location.getCol()] = animal;
	}
	
	/**
	 * Finds the first empty adjacent location in the cardinal directions
	 * 
	 * @param location is a location in the Ecosystem grid
	 * @param direction the direction to start the search in 
	 * @return The location of the first empty neighboring space
	 */
	public Location findFirstEmptyNeighbor(Location location, int direction){
		if(direction == 0){
			if(isEmpty(dueWest(location))){
				return dueWest(location);
			} else if(isEmpty(dueNorth(location))){
				return dueNorth(location);
			} else if(isEmpty(dueEast(location))){
				return dueEast(location);
			} else if(isEmpty(dueSouth(location))){
				return dueSouth(location);
			}
		} else if(direction == 1){
			if(isEmpty(dueNorth(location))){
				return dueNorth(location);
			} else if(isEmpty(dueEast(location))){
				return dueEast(location);
			} else if(isEmpty(dueSouth(location))){
				return dueSouth(location);
			} else if(isEmpty(dueWest(location))){
				return dueWest(location);
			}
		} else if(direction == 2){
			if(isEmpty(dueEast(location))){
				return dueEast(location);
			} else if(isEmpty(dueSouth(location))){
				return dueSouth(location);
			} else if(isEmpty(dueWest(location))){
				return dueWest(location);
			} else if(isEmpty(dueNorth(location))){
				return dueNorth(location);
			}
		} else if(direction == 3){
			if(isEmpty(dueSouth(location))){
				return dueSouth(location);
			} else if(isEmpty(dueWest(location))){
				return dueWest(location);
			} else if(isEmpty(dueNorth(location))){
				return dueNorth(location);
			} else if(isEmpty(dueEast(location))){
				return dueEast(location);
			}
		}
		return null;
	}
	
	/**
	 * Checks due north of location
	 * 
	 * @param location is a location in the Ecosystem grid
	 * @return The location of the space directly north of a location
	 */
	public Location dueNorth(Location location){
		int row = location.getRow();
		int col = location.getCol();
		row = row - 1;
		if(row == -1){
			row = size - 1;
		}
		Location loco = new Location(row, col);
		return loco;
	}
	
	/**
	 * Checks due south of location
	 * 
	 * @param location is a location in the Ecosystem grid
	 * @return The location of the space directly south of a location
	 */
	public Location dueSouth(Location location){
		int row = location.getRow();
		int col = location.getCol();
		row = row + 1;
		if(row == size){
			row = 0;
		}
		Location loco = new Location(row, col);
		return loco;
	}
	
	/**
	 * Checks due east of location
	 * 
	 * @param location is a location in the Ecosystem grid
	 * @return The location of the space directly east of a location
	 */
	public Location dueEast(Location location){
		int row = location.getRow();
		int col = location.getCol();
		col = col + 1;
		if(col == size){
			col = 0;
		}
		Location loco = new Location(row, col);
		return loco;
	}
	
	/**
	 * Checks due west of location
	 * 
	 * @param location is a location in the Ecosystem grid
	 * @return The location of the space directly west of a location
	 */
	public Location dueWest(Location location){
		int row = location.getRow();
		int col = location.getCol();
		col = col - 1;
		if(col == -1){
			col = size - 1;
		}
		Location loco = new Location(row, col);
		return loco;
	}
	
	/**
	 * Gets the map of the Ecosystem grid
	 * 
	 * @return A 2D Animal array that represents the Ecosystem
	 */
	public Animal[][] getMap(){
		return map;
	}
	
	/**
	 * Enables the living at the beginning of the simulation
	 */
	public void enableTheLiving(){
		Animal a;
		for(int j = 0; j < size; j++){
			for(int k = 0; k < size; k++){
				a = map[j][k];
				if(a != null && a.isAlive()){
					a.enable();
				}
			}
		}
	}
	
	/**
	 * Removes dead animals from the grid
	 */
	public void buryTheDead(){
		Animal a;
		for(int j = 0; j < size; j++){
			for(int k = 0; k < size; k++){
				Location l = new Location(j, k);
				a = map[j][k];
				if(a != null && !a.alive){
					remove(l);	
				}
			}
		}
	}
}