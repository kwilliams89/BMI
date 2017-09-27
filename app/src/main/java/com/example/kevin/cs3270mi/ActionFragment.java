package com.example.kevin.cs3270mi;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.view.View.OnClickListener;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class ActionFragment extends Fragment {

    View rootView;
    Button btnCalculate;
    RadioButton rbtnFemale;
    RadioButton rbtnMale;
    EditText editWeight;
    EditText editHeight;
    EditText editAge;
    BodyStats fragStats;

    public ActionFragment() {
        fragStats = new BodyStats();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView =  inflater.inflate(R.layout.fragment_action, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnCalculate = (Button) rootView.findViewById(R.id.btnCalculate);
        rbtnFemale = (RadioButton) rootView.findViewById(R.id.rbtnFemale);
        rbtnMale = (RadioButton) rootView.findViewById(R.id.rbtnMale);
        editWeight = (EditText) rootView.findViewById(R.id.editWeight);
        editHeight = (EditText) rootView.findViewById(R.id.editHeight);
        editAge = (EditText) rootView.findViewById(R.id.editAge);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                fragStats.weight = Integer.parseInt(editWeight.getText().toString());
                fragStats.height = Integer.parseInt(editHeight.getText().toString());
                fragStats.age = Integer.parseInt(editAge.getText().toString());
                fragStats.bmi = calcBMI(fragStats.weight, fragStats.height);
                fragStats.bodyFat = calcBodyFat(fragStats.bmi, fragStats.age, fragStats.sex);
                MainActivity ma = (MainActivity) getActivity();
                ma.setResults(fragStats.bmi, fragStats.bodyFat, fragStats.weight, fragStats.height, fragStats.sex, fragStats.age);
                ma.displayResults();
            }
        });

        OnClickListener gender = new OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((RadioButton) v).isChecked();

                switch(v.getId()) {
                    case R.id.rbtnFemale:
                        if (checked)
                            fragStats.sex = 0;
                        break;
                    case R.id.rbtnMale:
                        if (checked)
                            fragStats.sex = 1;
                        break;
                }
            }
        };

        rbtnMale.setOnClickListener(gender);
        rbtnFemale.setOnClickListener(gender);
    }


    private double calcBMI(int weight, int height){
        return ((weight / Math.pow(height, 2)) * 703);
    }

    private double calcBodyFat(double bmi, int age, int sex){
        return (1.20 * bmi) + (0.23 * age) - (10.8 * sex) - 5.4;
    }

   @Override
    public void onPause(){
        super.onPause();
            MainActivity ma = (MainActivity) getActivity();
            ma.saveState();
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity ma = (MainActivity) getActivity();
        ma.restoreState();
        fragStats.weight = ma.getWeight();
        fragStats.height = ma.getHeight();
        fragStats.age  = ma.getAge();
        fragStats.sex = ma.getSex();
        fragStats.bmi = ma.getBMI();
        fragStats.bodyFat = ma.getBodyFat();

        if (fragStats.weight != 0) {
            editWeight.setText(String.valueOf(fragStats.weight));
        } else {
            editWeight.setText("");
        }

        if (fragStats.height != 0) {
            editHeight.setText(String.valueOf(fragStats.height));
        } else {
            editHeight.setText("");
        }

        if (fragStats.age != 0) {
            editAge.setText(String.valueOf(fragStats.age));
        } else {
            editAge.setText("");
        }

        if (fragStats.sex == 0) {
            rbtnFemale.toggle();
        } else if (fragStats.sex == 1) {
            rbtnMale.toggle();
        } else {
            rbtnFemale.setChecked(false);
            rbtnMale.setChecked(false);
        }
        if (fragStats.bmi != 0.0){
            ma.displayResults();
        }
    }

}
