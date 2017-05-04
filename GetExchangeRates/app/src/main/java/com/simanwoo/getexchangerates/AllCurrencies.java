package com.simanwoo.getexchangerates;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import static com.simanwoo.getexchangerates.MainActivity.currencyFullNames;
import static com.simanwoo.getexchangerates.MainActivity.currencyNames;
import static com.simanwoo.getexchangerates.MainActivity.listview;

/**
 * Created by Siman on 5/3/2017.
 */

public class AllCurrencies extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rate_list);

        ArrayList<Currency> arrayList = new ArrayList<Currency>();
        for(int i = 0; i < currencyNames.length; ++i){
            arrayList.add(new Currency(currencyNames[i],currencyFullNames[i]));
        }
        CurrencyAdapter adapter = new CurrencyAdapter(this,arrayList);
        listview = (ListView) findViewById(R.id.rate_list);
        listview.setAdapter(adapter);
        Button b = new Button(this);
        b.setText("Back");
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AllCurrencies.this, MainActivity.class);
                startActivity(i);
            }
        });
        listview.addFooterView(b);
    }
}
