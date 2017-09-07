/**
 * 
 */
package edu.ncsu.csc216.simulation.simulator;

import java.awt.Color;
import java.io.File;
import java.util.Scanner;

import edu.ncsu.csc216.simulation.actor.Animal;
import edu.ncsu.csc216.simulation.actor.Configs;
import edu.ncsu.csc216.simulation.actor.PredatorPrey;
import edu.ncsu.csc216.simulation.actor.PurePredator;
import edu.ncsu.csc216.simulation.actor.PurePrey;
import edu.ncsu.csc216.simulation.environment.EcoGrid;
import edu.ncsu.csc216.simulation.environment.Ecosystem;
import edu.ncsu.csc216.simulation.environment.utils.Location;
import edu.ncsu.csc216.simulation.environment.utils.PaintedLocation;

/**
 * AutomataSimulator gets all info from the input file(s) and interacts with the GUI
 * to run each step
 * 
 * @author Dan Grochmal
 *
 */
public class AutomataSimulator implements SimulatorInterface {

	/** Size is the amount of rows and columns in the Ecosystem */
	public static final int SIZE = 20;
	/** Threshold is the minimum amount of Animals needed to run the program */
	public static final int THRESHOLD = 2;
	/** Error to be displayed if the size of the grid is too big */
	public static final String SIZE_ERROR_MESSAGE = "The size is too big!";
	/** Error to be displayed if there are not enough animals */
	public static final String THRESHOLD_ERROR_MESSAGE = "The threshold is too big";
	/** String array of Animal names */
	public String[] names;
	/** The number of names from the configuration file */
	public int numberOfNames;
	/** Char array of Animal symbols */
	public char[] symbol;
	/** Empty char character */
	public static final char EMPTY = ' ';
	/** Initializes a blank EcoGrid of the correct size */
	private EcoGrid simpleSystem = new Ecosystem(SIZE, SIZE);
	
	/**
	 * Constructor for the Simulator when only the required file is entered
	 * as a command line argument
	 * 
	 * @param filename is the name of the file containing the required information
	 */
	@SuppressWarnings("resource")
	public AutomataSimulator(String filename) {
		try{
			Animal a = null;
			Animal b = null;
			char c;
			char n;
			boolean isValidSymbol = false;
			int count = 0;
			String line;
			File file = new File(filename);
			Scanner scan = new Scanner(file);
			numberOfNames = scan.nextInt();
			if(numberOfNames < THRESHOLD){
				throw new IllegalArgumentException(THRESHOLD_ERROR_MESSAGE);
			}
			Configs.setToDefaults();
			symbol = new char[numberOfNames];
			names = new String[numberOfNames];
			for(int i = 0; i < numberOfNames; i++){
				n = scan.next().charAt(0);
				if(!Character.isAlphabetic(n)){
					throw new IllegalArgumentException("Invalid character in file");
				} 
				symbol[i] = n;
				names[i] = scan.nextLine().trim();
			}
			while(scan.hasNextLine()){
				count++;
				if(count > SIZE){
					throw new IllegalArgumentException(SIZE_ERROR_MESSAGE);
				}
				line = scan.next();
				if(line.length() != SIZE){
					throw new IllegalArgumentException(SIZE_ERROR_MESSAGE);
				}
				for(int k = 0; k < SIZE; k++){
					isValidSymbol = false;
					c = line.charAt(k);
					for(int h = 0; h < numberOfNames; h++){
						if(c == symbol[h] || c == '.'){
							isValidSymbol = true;
						}
					}
					if(!isValidSymbol){
						throw new IllegalArgumentException("Invalid character in file");
					}
					if(!Character.isAlphabetic(c) && c != '.'){
						throw new IllegalArgumentException("Invalid character in file");
					} 
					if(c == '.'){
						a = b;
					} else {
						a = getNewAnimal(c);
					}
					Location l = new Location(count - 1, k);
					simpleSystem.add(a, l);
				}
			}
			if(count < SIZE){
				throw new IllegalArgumentException(SIZE_ERROR_MESSAGE);
			}
			scan.close();
		} catch (Exception e){
			throw new IllegalArgumentException("File Not Found.");
		}
	}
	
	/**
	 * Constructor for the Simulator when both the required file, and the 
	 * configuration file are entered as command line arguments
	 * @param filename is the name of the file containing the required information
	 * @param configFilename is the name of the file containing configuration values
	 */
	@SuppressWarnings("resource")
	public AutomataSimulator(String filename, String configFilename) {
		String colorhex;
		String r;
		String g;
		String b;
		String line;
		Animal a = null;
		Animal a2 = null;
		char c;
		char n;
		int count = 0;
		try{
			File file = new File(filename);
			File configFile = new File(configFilename);
			Scanner scan = new Scanner(file);
			Scanner scanConfig = new Scanner(configFile);
			numberOfNames = scan.nextInt();
			if(numberOfNames < THRESHOLD){
				throw new IllegalArgumentException(THRESHOLD_ERROR_MESSAGE);
			}
			Color[] color = new Color[3];
			int[] starve = new int[3];
			int[] breed = new int[3];
			for(int i = 0; i < 3; i++){
				colorhex = scanConfig.next();
				r = colorhex.substring(0, 2);
				g = colorhex.substring(2, 4);
				b = colorhex.substring(4, 6);
				Integer rdecimal = Integer.parseInt(r, 16);
				Integer gdecimal = Integer.parseInt(g, 16);
				Integer bdecimal = Integer.parseInt(b, 16);
				color[i] = new Color(rdecimal, gdecimal, bdecimal);
			}
			scanConfig.nextLine();
			for(int i = 0; i < 3; i++){
				starve[i] = scanConfig.nextInt();
			}
			scanConfig.nextLine();
			for(int i = 0; i < 3; i++){
				breed[i] = scanConfig.nextInt();
			}
			symbol = new char[numberOfNames];
			names = new String[numberOfNames];
			for(int i = 0; i < numberOfNames; i++){
				n = scan.next().charAt(0);
				if(!Character.isAlphabetic(n)){
					throw new IllegalArgumentException("Invalid character in file");
				} 
				symbol[i] = n;
				names[i] = scan.nextLine().trim();
			}
			Configs.initConfigs(color, starve, breed);
			//for(int j = 0; j < SIZE; j++){
			while(scan.hasNextLine()){
				count++;
				if(count > SIZE){
					throw new IllegalArgumentException(SIZE_ERROR_MESSAGE);
				}
				line = scan.next();
				for(int k = 0; k < SIZE; k++){
					c = line.charAt(k);
					if(!Character.isAlphabetic(c) && c != '.'){
						throw new IllegalArgumentException("Invalid character in file");
					}
					if(c == '.'){
						a = a2;
					} else {
						a = getNewAnimal(c);
					}
					Location l = new Location(count - 1, k);
					simpleSystem.add(a, l);
				}
			}
			if(count < SIZE){
				throw new IllegalArgumentException(SIZE_ERROR_MESSAGE);
			}
			scan.close();
			scanConfig.close();
		} catch (Exception e){
			throw new IllegalArgumentException("File Not Found.");
		}
	}
	
	/**
	 * Creates and returns an Animal to be put into the Ecosystem grid
	 * 
	 * @param s symbol from the file
	 * @return New Animal object
	 */
	private Animal getNewAnimal(char s) {
		Animal a;
		if(s == symbol[0]){
			a = new PurePredator(s);
		} else if (s == symbol[symbol.length - 1]){
			a = new PurePrey(s);
		} else {
			a = new PredatorPrey(s);
		}
		return a;
	}
	
	
	/**
	 * Runs through a single step of simulation
	 */
	public void step(){
		Animal[][] creature = simpleSystem.getMap();
		simpleSystem.enableTheLiving();
		Animal a;
		char b;
		for(int j = 0; j < SIZE; j++){
			for(int k = 0; k < SIZE; k++){
				Location l = new Location(j, k);
				a = creature[j][k];
				if(a != null){
					b = a.getSymbol();
					if(a.canActThisStep && b != ' '){
						a.act(l, simpleSystem);
					}
					a.disable();
				}
			}
		}
		simpleSystem.buryTheDead();
	}
	
	/**
	 * Returns the view which is a 2D PaintedLocation array
	 * 
	 * @return The view which is a 2D PaintedLocation array
	 */
	public PaintedLocation[][] getView(){
		PaintedLocation[][] pl = new PaintedLocation[SIZE][SIZE];
		PaintedLocation loco;
		Animal a;
		Location l;
		char c;
		Color color;
		for(int j = 0; j < SIZE; j++){
			for(int k = 0; k < SIZE; k++){
				l = new Location(j, k);
				a = simpleSystem.getItemAt(l);
				if(a == null){
					color = Color.black;
					c = ' ';
				} else {
					color = a.getColor();
					c = a.getSymbol();
				}
				loco = new PaintedLocation(j, k, color, c);
				pl[j][k] = loco;
			}
		}
		return pl;
	}
	
	/**
	 * Returns a String array of Animal names
	 * 
	 * @return A String array of Animal names
	 */
	public String[] getNames(){
		String n;
		String[] legend = new String[numberOfNames];
		for(int i = 0; i < numberOfNames; i++){
			n = symbol[i] + ": " + names[i];
			legend[i] = n;
		}
		return legend;
	}
	
}