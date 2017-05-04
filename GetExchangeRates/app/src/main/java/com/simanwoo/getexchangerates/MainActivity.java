package com.simanwoo.getexchangerates;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static CurrencyAdapter adapter;
    public static ListView listview;
    public static ArrayList<Currency> finalResult;
    public static String USGS_REQUEST_URL = "http://api.fixer.io/latest";
    public static String OriginHttpLink = "http://api.fixer.io/latest";
    public static final String[] currencyNames = {"AUD","BGN","BRL","CAD","CHF","CNY","CZK","DKK","EUR","GBP","HKD","HRK","HUF","IDR","ILS","INR","JPY","KRW","MXN","MYR","NOK","NZD","PHP","PLN","RON","RUB","SEK","SGD","THB","TRY","USD","ZAR"};
    public static final String[] currencyFullNames = {"Australian Dollar","Bulgarian Lev","Brazilian Real","Canadian Dollar","Swiss Franc","Chinese Yuan","Czech Republic Koruna","Danish Krone","Euro","British Pound",
            "Hong Kong Dollar","Croatian Kuna","Hungarian Forint","Indonesian Rupiah","Israeli New Shekel","Indian Rupee","Japanese Yen","South Korean Won","Mexican Peso","Malaysian Ringgit","Norwegian Krone","New Zealand Dollar",
            "Philippine Peso","Polish Zloty","Romanian Leu","Russian Ruble","Swedish Krona","Singapore Dollar","Thai Baht","Turkish Lira","US Dollar","South African Rand"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView loading_text = (TextView) findViewById(R.id.loading_text);
        Button b = (Button) findViewById(R.id.viewcurrencies);
        loading_text.setVisibility(View.GONE);
        b.setVisibility(View.VISIBLE);
    }

    public void getExchanges(View view){
        EditText input = (EditText) findViewById(R.id.input);
        String inputString = input.getText().toString();
        boolean inputIsValid = false;
        for(int i = 0; i < currencyNames.length; ++i){
            if(currencyNames[i].equals(inputString)) {
                inputIsValid = true;
                break;
            }
        }


        if(inputIsValid || inputString.isEmpty()){
            Button b = (Button) findViewById(R.id.viewcurrencies);
            TextView loading_text = (TextView) findViewById(R.id.loading_text);
            b.setVisibility(View.GONE);
            loading_text.setVisibility(View.VISIBLE);
            USGS_REQUEST_URL = OriginHttpLink;
            if(!inputString.isEmpty())USGS_REQUEST_URL = USGS_REQUEST_URL + "?base=" + inputString;
            CurrencyBackground task = new CurrencyBackground(this);
            task.execute();
        }
        else{
            TextView hint = (TextView) findViewById(R.id.hint);
            hint.setText(inputString + " is an invalid currency name, please retype your base currency.");
        }

    }

    public void jumpToAllCurrencies(View view){
        Intent intent = new Intent(this, AllCurrencies.class);
        this.startActivity(intent);
    }
}
