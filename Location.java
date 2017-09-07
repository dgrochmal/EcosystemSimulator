/**
 * 
 */
package edu.ncsu.csc216.simulation.environment.utils;

/**
 * Location class that creates an object with a row component and a column component
 * 
 * @author Dan Grochmal
 *
 */
public class Location {

	/** The row that an Animal is in */
	public int row;
	/** the column that an Animal is in */
	public int column;
	
	/**
	 * Constructor of the Location of an Animal
	 * 
	 * @param row is the row that an Animal is in
	 * @param col is the column that an Animal is in
	 */
	public Location(int row, int col){
		this.row = row;
		this.column = col;
	}
	
	/**
	 * Gets the row that an Animal is in
	 * 
	 * @return The row that an Animal is in
	 */
	public int getRow(){
		return this.row;
	}
	
	/**
	 * Gets the column that an Animal is in
	 * 
	 * @return The column that an Animal is in
	 */
	public int getCol(){
		return this.column;
		
	}
}