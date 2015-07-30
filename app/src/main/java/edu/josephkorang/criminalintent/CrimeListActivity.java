package edu.josephkorang.criminalintent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by root on 7/6/15.
 */
public class CrimeListActivity extends SingleFragmentActivity
        implements CrimeListFragment.Callbacks, CrimeFragment.Callbacks {

    public static final String EXTRA_EULA =
            "edu.josephkorang.criminalintent.EULA";
    private SharedPreferences prefs;

    @Override
    public void onStart() {
        super.onStart();
        checkEULA();
    }


    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_masterdetail;
    }

    public void onCrimeSelected(Crime crime) {
        if (findViewById(R.id.detailFragmentContainer) == null) {
            // Start an instance of CrimePagerActivity
            Intent i = new Intent(this, CrimePagerActivity.class);
            i.putExtra(CrimeFragment.EXTRA_CRIME_ID, crime.getId());
            startActivity(i);
        } else {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            Fragment oldDetail = fm.findFragmentById(R.id.detailFragmentContainer);
            Fragment newDetail = CrimeFragment.newInstance(crime.getId());

            if (oldDetail != null) {
                ft.remove(oldDetail);
            }

            ft.add(R.id.detailFragmentContainer, newDetail);
            ft.commit();
        }
    }

    public void onCrimeUpdated(Crime crime) {
        FragmentManager fm = getSupportFragmentManager();
        CrimeListFragment listFragment = (CrimeListFragment)
                fm.findFragmentById(R.id.fragmentContainer);
        listFragment.updateUI();
    }

    private void checkEULA() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean eulaCheck = prefs.getBoolean(EXTRA_EULA, false);

        if (eulaCheck == true) {
            //Do nothin'. We good.
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setTitle(this.getString(R.string.app_name))
                    .setMessage(this.getString(R.string.eula))
                    .setCancelable(false)
                    .setPositiveButton(R.string.accept,
                            new Dialog.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface di, int i) {
                                    SharedPreferences.Editor editor = prefs.edit();
                                    editor.putBoolean(EXTRA_EULA, true);
                                    editor.commit();
                                    di.dismiss();
                                }
                            })
                    .setNegativeButton(android.R.string.cancel, new Dialog.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
            builder.create().show();
        }
    }
}