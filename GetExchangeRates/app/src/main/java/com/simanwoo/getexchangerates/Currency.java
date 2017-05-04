package com.simanwoo.getexchangerates;

/**
 * Created by Siman on 5/2/2017.
 */

public class Currency {
    private String name, value;

    public Currency(String n, String v){
        name = n;
        value = v;
    }

    public String getName(){return name;}
    public String getValue(){return value;}
    public void setName(String n){name = n;}
    public void setValue(String v){value = v;}

}
