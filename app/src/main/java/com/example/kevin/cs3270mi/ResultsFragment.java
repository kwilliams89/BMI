package com.example.kevin.cs3270mi;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultsFragment extends Fragment {


    View rootView;

    public ResultsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView =  inflater.inflate(R.layout.fragment_results, container, false);
        return rootView;
    }

    public void displayResults(double bmi, double bodyFat){

        String BMI;
        String BodyFat;

        BodyFat = String.format("%.2f", bodyFat);
        BMI = String.format("%.2f", bmi);

        TextView txtBMI = (TextView) rootView.findViewById(R.id.txtBMI);
        TextView txtBodyFat = (TextView) rootView.findViewById(R.id.txtBodyFat);

        txtBMI.setText(BMI);
        txtBodyFat.setText(BodyFat);
    }

}
