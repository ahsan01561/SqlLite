package com.example.ahsan.word;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText e1,e2,e3,e4;
DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=(EditText)findViewById(R.id.editText);
        e2=(EditText)findViewById(R.id.editText2);
        e3=(EditText)findViewById(R.id.editText3);
        e4=(EditText)findViewById(R.id.editText4);
        db=new DatabaseHelper( this);
    }

    public void insert(View view) {
        boolean ahsan=db.onCreate(e1.getText().toString(),e2.getText().toString(),e3.getText().toString(),e4.getText().toString());
        if(ahsan==true)
            Toast.makeText(getApplicationContext(),"Data saved",Toast.LENGTH_SHORT).show();
            else
            Toast.makeText(getApplicationContext(),"Data  not saved",Toast.LENGTH_SHORT).show();

    }

    public void ret(View view) {
        Cursor c=db.getAllData();
        if (c.getCount()==0)
        {
            showMessage("error","nothing to show");
            return;
        }
        StringBuffer b=new StringBuffer();
        while (c.moveToNext())
        {
            b.append("ID "+c.getString(0)+"\n");
            b.append("NAME "+c.getString(0)+"\n");
            b.append("SUBJECT "+c.getString(0)+"\n");
            b.append("MARKS "+c.getString(0)+"\n");
        }
        showMessage("data", b.toString());
    }

    private void showMessage (String title, String msg) {
        AlertDialog.Builder adb=new AlertDialog.Builder(this);
        adb.setCancelable(true);
        adb.setTitle(title);
        adb.setMessage(msg);
        adb.show();
    }

    public void up(View view) {
        boolean isUpdate=db.onUpdate(e1.getText().toString(),e2.getText().toString(),e3.getText().toString(),e4.getText().toString());
                if(isUpdate==true) {
                    Toast.makeText(getApplicationContext(), "Data updated", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getApplicationContext(), "Data updated", Toast.LENGTH_SHORT).show();


                }

    public void de(View view) {
        Integer deletR=db.delete(e1.getText().toString());

        if(deletR>0) {
            Toast.makeText(getApplicationContext(), "data Delete", Toast.LENGTH_SHORT).show();
                    }
        else {
            Toast.makeText(getApplicationContext(),"data not Delete",Toast.LENGTH_SHORT).show();
             }
        }
}