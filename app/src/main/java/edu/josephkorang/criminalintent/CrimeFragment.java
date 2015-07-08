package edu.josephkorang.criminalintent;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class CrimeFragment extends Fragment {
    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

    public CrimeFragment() {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCrime = new Crime();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime, parent, false);
        mTitleField = (EditText) v.findViewById(R.id.crime_title);
        mDateButton = (Button) v.findViewById(R.id.crime_fragment_date_button);
        mSolvedCheckBox = (CheckBox) v.findViewById(R.id.crime_list_item_solvedCheckBox);

        mTitleField.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence c, int start, int before, int count) {
                mCrime.setTitle(c.toString());
            }
            public void beforeTextChanged(CharSequence c, int start, int count, int after) {
                // this space intentionally left blank
            }
            public void afterTextChanged(Editable c) {
                // this one too
            }
        });

        //Set mDateButton to current date.
        mDateButton.setText(mCrime.getDate().toString());
        mDateButton.setEnabled(false);
        return v;
    }
}