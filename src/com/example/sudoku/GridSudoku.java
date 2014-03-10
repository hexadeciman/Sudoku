package com.example.sudoku;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.sudoku.Grid.IMyEventListener;

public class GridSudoku extends Activity {
	int level ;
	
	ListView listView2 ;
	Grid v ;
	int menuOpened;
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	    super.onConfigurationChanged(newConfig);
	    return;
	}
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        menuOpened =1;
        setContentView(R.layout.activity_grid_sudoku);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
		level = extras.getInt("level");
		
    	v = (Grid) findViewById(R.id.maGrille);
    	v.setDiff(level);
    	v.setEventListener(new IMyEventListener() {
            @Override
            public void onEventAccured() {
            	if(menuOpened==1){
            		menuOpened=0;
           	 		openContextMenu(listView2);
           	 		
            	}
            }
        });
    	
        listView2 = (ListView) findViewById(R.id.listView2);
        registerForContextMenu(listView2);
        listView2.getLayoutParams().width = 1;

	}
	
	@Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        menuOpened = 1;
        v.setListenner();
    }
	
	 @Override
	 public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
	    	super.onCreateContextMenu(menu, v, menuInfo);  
	        menu.setHeaderTitle("Value");  
	        menu.add(0, v.getId(), 0, "1");//groupId, itemId, order, title   
	        menu.add(0, v.getId(), 0, "2");  
	        menu.add(0, v.getId(), 0, "3");  
	        menu.add(0, v.getId(), 0, "4");  
	        menu.add(0, v.getId(), 0, "5");  
	        menu.add(0, v.getId(), 0, "6");  
	        menu.add(0, v.getId(), 0, "7");  
	        menu.add(0, v.getId(), 0, "8");  
	        menu.add(0, v.getId(), 0, "9");  
	 }
	    @Override    
	    public boolean onContextItemSelected(MenuItem item){    
	         CharSequence i = item.getTitle();  
	         int number = Integer.parseInt(i.toString());
	         System.out.println("Le numero special est: " +number);
	         v.addNumber(number);
	         v.setListenner();
	          return true;    
	    } 
}
