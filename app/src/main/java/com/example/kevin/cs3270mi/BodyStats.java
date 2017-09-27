package com.example.kevin.cs3270mi;

/**
 * Created by Kevin on 6/20/2017.
 */

public class BodyStats {
    int weight;
    int height;
    int sex;
    int age;
    double bmi;
    double bodyFat;

    public BodyStats(int weight1, int height1, int sex1, int age1, double bmi1, double bodyFat1){

        weight = weight1;
        height = height1;
        sex = sex1;
        age = age1;
        bmi = bmi1;
        bodyFat = bodyFat1;
    }

    public BodyStats(){

        weight = 0;
        height = 0;
        sex = 2;
        age = 0;
        bmi = 0.0;
        bodyFat = 0.0;
    }

}
