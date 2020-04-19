package com.example.isupportlockdown;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.assist.AssistStructure;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int a=5;
    TableLayout t1;
    TableRow tr;
    TextView t;
    EditText e1,e2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1 = (TableLayout) findViewById(R.id.table);
        t1.setStretchAllColumns(true);
        Button addRow = (Button) findViewById(R.id.addrow);
        addRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attachRow();
            }
        });
        Button send = (Button) findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }
    public void attachRow(){
        tr=new TableRow(MainActivity.this);
        t=new TextView(MainActivity.this);
        a++;
        t.setText(String.valueOf(a));
        t.setTextSize(15);
        tr.addView(t);
        e1=new EditText(MainActivity.this);
        tr.addView(e1);
        e2=new EditText(MainActivity.this);
        tr.addView((e2));
        t1.addView(tr);
    }
    public void sendMessage()
    {
        EditText number=(EditText) findViewById(R.id.shopkeeper);
        String no="91"+number.getText().toString();
        EditText name=(EditText) findViewById(R.id.name);
        String nam= name.getText().toString();
        EditText address=(EditText) findViewById(R.id.address);
        String add= address.getText().toString();
        String message=nam+"\n"+add;
        int c=0;
        for(int i=0;i<a;i++)
        {
            TableRow row = (TableRow)t1.getChildAt(i);
            EditText e1 = (EditText) row.getChildAt(1);
            EditText e2 = (EditText) row.getChildAt(2);
            String str = e1.getText().toString();
            if (str.isEmpty()){
                continue;
            }
            else{
                c++;
                str=Integer.toString(c)+"."+str+"-"+e2.getText().toString();
            }
            message=message+"\n"+str;
        }
        share(message,no);
    }
    public void share(String message,String no) {

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,message);
        sendIntent.setType("text/plain");
        sendIntent.setPackage("com.whatsapp");
        sendIntent.putExtra("jid", no + "@s.whatsapp.net");
        startActivity(sendIntent);
    }
}

