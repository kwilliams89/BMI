package com.example.kevin.cs3270mi;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    BodyStats myStats;
    SharedPreferences sp;
    String sharedPrefFile = "com.example.android.sharedprefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.contActionFragment, new ActionFragment(), "AF").addToBackStack(null).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.contResultsFragment, new ResultsFragment(), "RF").addToBackStack(null).commit();

        if (savedInstanceState == null) {
            reset();
        }
        myStats = new BodyStats();
    }

    public void setResults(double fBMI, double fBodyFat, int fWeight, int fHeight, int fSex, int fAge){
        myStats.weight =fWeight;
        myStats.height =fHeight;
        myStats.sex = fSex;
        myStats.age = fAge;
        myStats.bmi = fBMI;
        myStats.bodyFat = fBodyFat;
    }

    public void displayResults(){
        ResultsFragment fragmentBottom = (ResultsFragment) getSupportFragmentManager().findFragmentByTag("RF");
        fragmentBottom.displayResults(myStats.bmi, myStats.bodyFat);
    }
    public void saveState(){
        sp = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        SharedPreferences.Editor spEditor = sp.edit();
        spEditor.putInt("weight",  myStats.weight);
        spEditor.putInt("height",  myStats.height);
        spEditor.putInt("sex",  myStats.sex);
        spEditor.putInt("age",  myStats.age);
        spEditor.putLong("bmi",  (long) myStats.bmi);
        spEditor.putLong("bodyFat",  (long) myStats.bodyFat);
        spEditor.apply();

    }

    public void reset() {

        PreferenceManager.getDefaultSharedPreferences(getBaseContext()).
                edit().clear().apply();

    }

    public void restoreState(){
        if (myStats != null) {
            sp = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
            myStats.weight = sp.getInt("weight", 0);
            myStats.height = sp.getInt("height", 0);
            myStats.sex = sp.getInt("sex", 2);
            myStats.age = sp.getInt("age", 0);
            myStats.bmi = sp.getLong("bmi", 0);
            myStats.bodyFat = sp.getLong("bodyFat", 0);
       }
    }


    public int getWeight(){
        if (myStats != null) {
            return myStats.weight;
        }
        return 0;
    }

    public int getHeight(){
        if (myStats != null) {
            return myStats.height;
        }
        return 0;
    }
    public int getAge(){
        if (myStats != null) {
            return myStats.age;
        }
        return 0;
    }
    public int getSex(){
        if (myStats != null) {
            return myStats.sex;
        }
        return 2;
    }
    public double getBMI(){
        if (myStats != null) {
            return myStats.bmi;
        }
        return 0.0;
    }
    public double getBodyFat(){
        if (myStats != null) {
            return myStats.bodyFat;
        }
        return 0.0;
    }

}
