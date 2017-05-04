package com.simanwoo.getexchangerates;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Siman on 5/2/2017.
 */

public class CurrencyAdapter extends ArrayAdapter<Currency>{
    public CurrencyAdapter(Context context, ArrayList<Currency> currencies){
        super(context, 0, currencies);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // Get update currency textviews
        Currency currency = getItem(position);
        TextView currencyNameView = (TextView) listItemView.findViewById(R.id.currency);
        currencyNameView.setText(currency.getName());
        TextView currencyValueView = (TextView) listItemView.findViewById(R.id.value);
        currencyValueView.setText(currency.getValue());


        return listItemView;
    }
}
