package com.example.divbok.lab7;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    AlertDialog.Builder helpDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        editText=findViewById(R.id.edittxt);
        helpDialog=new AlertDialog.Builder(this,R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        helpDialog.setTitle("Help")
                .setMessage("Welcome to Medit.It's free and always will be");
        registerForContextMenu(editText);
        Intent intent=getIntent();
        String code=intent.getStringExtra("code");
        editText.setText(code);

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu,view,menuInfo);
        menu.setHeaderTitle("Select Font weight");
        menu.add(0,1,0,"Bold");
        menu.add(0,2,0,"Italic");
        menu.add(0,3,0,"Bold-Italic");
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getTitle().toString().equals("Bold")){
            editText.setTypeface(null, Typeface.BOLD);
        }
        else if (item.getTitle().toString().equals("Italic")){
            editText.setTypeface(null, Typeface.ITALIC);
        }
        else if(item.getTitle().toString().equals("Italic")) {
            editText.setTypeface(null,Typeface.BOLD_ITALIC );
        }
        else
            return false;
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the eaction bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_clear) {
            editText.setText("");
            return true;
        }
        else if(id==R.id.action_help)
        {
            helpDialog.show();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    public void popupmenu(View view) {
        PopupMenu popupMenu=new PopupMenu(this,view);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu,popupMenu.getMenu());
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent=new Intent(MainActivity.this,OpenActivity.class);
                switch (item.getItemId())
                {
                    case R.id.popup_open:
                        intent.putExtra("isSave",false);
                        startActivity(intent);
                        return true;
                    case R.id.popup_save:
                        intent.putExtra("isSave",true);
                        intent.putExtra("snippetData",editText.getText().toString());
                        startActivity(intent);
                        return true;
                    default:
                        return false;
                }
            }
        });
    }
}
