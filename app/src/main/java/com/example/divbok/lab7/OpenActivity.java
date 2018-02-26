package com.example.divbok.lab7;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class OpenActivity extends AppCompatActivity {
    Boolean isSave;
    String snippetData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open);
        Button btn=findViewById(R.id.btn);
        Intent intent=getIntent();
        isSave=intent.getBooleanExtra("isSave",false);
        if(isSave)
        {
            btn.setText("Save");
            snippetData=intent.getStringExtra("snippetData");
        }
        else
        {
            btn.setText("Open");
        }
    }

    public void openSnippet(View view) {
        SharedPreferences sharedPreferences=this.getPreferences(Context.MODE_PRIVATE);
        EditText snippetField=findViewById(R.id.filename);
        if(isSave)
        {
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString(snippetField.getText().toString(),snippetData);
            editor.commit();
            Toast.makeText(this,"File Saved",Toast.LENGTH_LONG).show();
        }
        else
        {
                String defaultValue="No file";
                String snippetText=sharedPreferences.getString(snippetField.getText().toString(),defaultValue);
                if(snippetText.equals(defaultValue))
                {
                    Toast.makeText(this,"No such file exists",Toast.LENGTH_LONG).show();
                    return;

                }
                Intent intent=new Intent(this,MainActivity.class);
                intent.putExtra("code",snippetText);
                startActivity(intent);
        }
    }
}
