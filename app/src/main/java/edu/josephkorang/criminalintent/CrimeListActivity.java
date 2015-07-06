package edu.josephkorang.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by root on 7/6/15.
 */
public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}