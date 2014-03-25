package com.example.sudoku;

public class Tuple { 
	  private  int x; 
	  private  int y; 
	  private int xf;
	  private int yf;
	  
	  public Tuple(int x, int y) { 
	    this.x = x; 
	    this.y = y; 
	  } 
	  public void ChangeData(int x, int y){
		    this.x = x; 
		    this.y = y; 
	  }
	  public int getX(){
		  return x;
	  }
	  public int getY(){
		  return y;
	  }
	  public int getXf(){
		  return xf;
	  }
	  public int getYf(){
		  return yf;
	  }
		  
		  
} 