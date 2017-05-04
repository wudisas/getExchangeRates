package com.simanwoo.getexchangerates;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import static com.simanwoo.getexchangerates.MainActivity.adapter;
import static com.simanwoo.getexchangerates.MainActivity.finalResult;
import static com.simanwoo.getexchangerates.MainActivity.listview;

/**
 * Created by Siman on 5/2/2017.
 */

public class LoadingFinished extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rate_list);
        adapter = new CurrencyAdapter(this, finalResult);
        listview = (ListView) findViewById(R.id.rate_list);
        listview.setAdapter(adapter);

        Button b = new Button(this);
        b.setText("Back");
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoadingFinished.this, MainActivity.class);
                startActivity(i);
            }
        });
        listview.addFooterView(b);
    }
}
