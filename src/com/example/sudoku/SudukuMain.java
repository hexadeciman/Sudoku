package com.example.sudoku;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

public class SudukuMain extends Activity {
	//Constants for context menu options
	public static final int MENU_MARK = 1;
	public static final int MENU_REMOVE = 2;
	ListView listView1 ;
	int level ;
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suduku_main);
        

    	listView1 = (ListView) findViewById(R.id.listView1);
        // Register the ListView  for Context menu  
        registerForContextMenu(listView1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_top_home, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
    	
    	AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.about:
            	return true;
            case R.id.difficulty:
           	 openContextMenu(listView1);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
    	super.onCreateContextMenu(menu, v, menuInfo);  
        menu.setHeaderTitle("Difficulty");  
        menu.add(0, v.getId(), 0, "Easy");//groupId, itemId, order, title   
        menu.add(0, v.getId(), 0, "Medium");  
        menu.add(0, v.getId(), 0, "Hard");  
    }
    
    @Override    
    public boolean onContextItemSelected(MenuItem item){    
            if(item.getTitle()=="Easy"){  
                  level=1;
            }    
            else if(item.getTitle()=="Medium"){  
                level=2;

            }
            else if(item.getTitle()=="Hard"){  
                level=3;

            }else{  
               return false;  
            }
            
          return true;    
      }    
    
}
