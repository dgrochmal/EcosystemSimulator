/**
 * 
 */
package edu.ncsu.csc216.simulation.environment.utils;

import java.awt.Color;

/**
 * Location class that creates an object with a row component, a column component,
 * a color, and an Animal symbol
 * 
 * @author Dan Grochmal
 *
 */
public class PaintedLocation extends Location {

	/** The Color that a location in the grid will be */
	public Color tint;
	/** The symbol that will be displayed at a location on the grid */
	public char symbol;
	
	/**
	 * Constructor for the Painted Location object
	 * 
	 * @param row is the row value in the grid
	 * @param col is the column value in the grid
	 * @param color is the color of a grid element
	 * @param symbol is the symbol displayed at a grid element
	 */
	public PaintedLocation(int row, int col, Color color, char symbol){
    	super(row, col);
    	this.row = row;
    	this.column = col;
    	this.tint = color;
    	this.symbol = symbol;
	}
	
	/**
	 * Gets the Color of a grid element
	 * 
	 * @return The Color of a grid element
	 */
	public Color getColor(){
		return this.tint;
	}
	
	/**
	 * Gets the symbol of a grid element
	 * 
	 * @return The symbol of a grid element
	 */
	public char getSymbol(){
		return this.symbol;
	}
}