/**
 * 
 */
package edu.ncsu.csc216.simulation.simulator;

import edu.ncsu.csc216.simulation.environment.utils.PaintedLocation;

/**
 * Provides a streamlined way for the Simulator to interact with the GUI class
 * 
 * @author Dan Grochmal
 *
 */
public interface SimulatorInterface {

	/**
	 * Runs through a single step of simulation
	 */
	void step();
	
	/**
	 * Returns the view which is a 2D PaintedLocation array
	 * @return The view which is a 2D PaintedLocation array
	 */
	PaintedLocation[][] getView();
	
	/**
	 * Returns a String array of Animal names
	 * @return A String array of Animal names
	 */
	String[] getNames();
	
}