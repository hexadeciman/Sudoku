package com.example.sudoku;

import java.util.ArrayList;

/**
 * Create a SudokuGrid
 */
public class SudokuGrid{

	// The grid
	private int[][] grid;
	// A boolean representation of the grid to know the cases that are setted at the beggining
	private boolean[][] isSetAtFirst;
	
	/** 
	 * These arrays contains the values setted in the grid attibute. Simplifies the checking of
	 * the insertion of a value in the grid.
	 */ 
	private ArrayList<ArrayList<Integer>> lines;
	private ArrayList<ArrayList<Integer>> columns;
	private ArrayList<ArrayList<Integer>> squares;
	
	private int enteredNumbers;
	
	/**
	 * Create a new sudoku grid according to the selected difficulty.
	 * @param difficulty The difficulty chosen by the user (from 0 to 2 <-> easy to difficult). 
	 */
	public SudokuGrid(int difficulty) {
		enteredNumbers = 0;
		grid = new int[9][9];
		isSetAtFirst = new boolean[9][9];
		lines = new ArrayList<ArrayList<Integer>>(9);
		columns = new ArrayList<ArrayList<Integer>>(9);
		squares = new ArrayList<ArrayList<Integer>>(9);
		for (int i = 0; i < 9; i++) {
			lines.add(new ArrayList<Integer>(9));
			columns.add(new ArrayList<Integer>(9));
			squares.add(new ArrayList<Integer>(9));
		}
		setGridValues(difficulty);
		//generateRandomGrid();
	}
	
	public void  changeDiff(int difficulty){
		setGridValues(difficulty);

	}
	
	/**
	 * Sets the grid values at init
	 * @param diff Difficulty chosen by the user
	 */
	private void setGridValues(int diff){
		switch (diff) {
		case 0:
			grid = new int[][] {
					{0,0,0,3,0,0,0,4,1},{0,7,3,0,1,0,0,5,0},{0,0,0,6,7,0,3,0,0},
					{5,6,0,0,3,0,0,0,8},{0,0,0,0,0,0,0,0,0},{4,0,0,0,8,0,0,9,5},
					{0,0,5,0,2,6,0,0,0},{0,4,0,0,9,0,1,6,0},{2,9,0,0,0,7,0,0,0}};
			setAttributesFromGrid();
			return;
		case 1:
			grid = new int[][] {
					{0,0,0,4,0,0,8,0,7},{0,0,4,0,0,1,0,3,0},{0,0,0,0,0,0,2,6,0},
					{1,0,0,0,6,0,0,5,0},{8,0,2,0,0,0,3,0,6},{0,4,0,0,3,0,0,0,8},
					{0,5,7,0,0,0,0,0,0},{0,1,0,2,0,0,6,0,0},{3,0,9,0,0,5,0,0,0}};
			setAttributesFromGrid();
			return;
		case 2:
			grid = new int[][] {
					{8,5,0,9,0,0,1,2,0},{2,0,0,0,7,0,0,8,0},{3,1,0,0,8,0,0,0,0},
					{0,0,0,7,0,0,0,0,5},{4,0,0,0,0,0,0,0,8},{7,0,0,0,0,6,0,0,0},
					{0,0,0,0,1,0,0,9,4},{0,9,0,0,6,0,0,0,3},{0,4,8,0,0,9,0,6,1}};
			setAttributesFromGrid();
			return;
		default:
			break;
		}
	}
	
	/**
	 * Method to be called when the grid difficulty is selected (after grid 
	 * attibute is setted the first time).
	 * Inits the isSetAtFirst grid, and adds values to the lines, columns and squares arrays.
	 */
	private void setAttributesFromGrid() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				isSetAtFirst[i][j] = false;
				if (grid[i][j] != 0){
					if (canAddAt(grid[i][j], i, j)){
						int tmp = grid[i][j];
						grid[i][j] = 0;
						addAt(tmp, i, j);
						isSetAtFirst[i][j] = true;
					}
				}
			}
		}
	}

	/*
	private void generateRandomGrid(){
		Random rand = new Random(SystemClock.currentThreadTimeMillis());
		for (int i = 0; i < 9; i++) {
			this.canAddAt(rand.nextInt(9)+1, rand.nextInt(9), i);
		}
	}
	*/
	
	public boolean PlayerAdd(int i, int j, int val){
		if (canAddAt(val, i, j)){
			addAt(val, i, j);
			return true;
		}
		return false;
	}
	
	
	/**
	 * Add the specified value in the grid at the specified coordinates
	 * No verification is done here, use canAddAt to verify the feasibility.
	 * @param val The value do add
	 * @param line The line to add at
	 * @param column The column to add at
	 */
	public void addAt(int val, int line, int column){
		int square = (column/3)+(line/3)*3;
		if (grid[line][column] == 0){
			lines.get(line).add(val);
			columns.get(column).add(val);
			squares.get(square).add(val);
			grid[line][column] = val;
			enteredNumbers++;
		}
	}

	/**
	 * Empties the specified cell in the grid
	 * @param line The cell line number
	 * @param column The cell column number
	 */
	public boolean deleteAt(int line, int column) {
		int squareIndex = (column/3)+(line/3)*3;
		int val = grid[line][column];
		if (val != 0){
			if (isSetAtFirst[line][column]){
				return false;
			}
			squares.get(squareIndex).remove(squares.get(squareIndex).indexOf(val));
			lines.get(line).remove(lines.get(line).indexOf(val));
			columns.get(column).remove(columns.get(column).indexOf(val));
			grid[line][column] = 0;
			enteredNumbers--;
			return true;
		}
		return false;
	}
	
	/**
	 * Says if possible to insert the value in the Sudoku Grid
	 * @param val The value to insert
	 * @param line The line to insert at
	 * @param column The column to insert at
	 * @return True if the value can been inserted
	 */
	public Boolean canAddAt(int val, int line, int column){
		// TODO: modify this to take into account the inversion of a new value in a previously entered value
		if (!canAddInSquare(val, line, column)){
			return false;
		}
		if (!canAddInLine(val, line)){
			return false;
		}
		if (!canAddInColumn(val, column)){
			return false;
		}
		return true;
	}
	
	private Boolean canAddInLine(int val, int line){
		if (lines.get(line).contains(val)){
			return false;
		}
		return true;
	}
	
	private Boolean canAddInColumn(int val, int column){
		if (columns.get(column).contains(val)){
			return false;
		}
		return true;
	}
	
	private Boolean canAddInSquare(int val, int line, int column){
		int index = (column/3)+(line/3)*3;
		if (squares.get(index).contains(val)){
			return false;
		}
		return true;
	}
	
	public int getValueAt(int line, int column){
		return grid[line][column];
	}
	
	public boolean isFinished(){
		if (enteredNumbers == 81){
			return true;
		}else{
			return false;
		}
	}

}