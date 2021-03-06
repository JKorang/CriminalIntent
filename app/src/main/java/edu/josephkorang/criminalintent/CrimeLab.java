package edu.josephkorang.criminalintent;

import android.content.Context;
import android.os.Environment;

import java.util.ArrayList;
import java.util.UUID;

public class CrimeLab {
    private static final String TAG = "CrimeLab";
    private static final String FILENAME = Environment.getExternalStorageDirectory() + "/crimes.json";
    private static CrimeLab sCrimeLab;
    private ArrayList<Crime> mCrimes;
    private CriminalIntentJSONSerializer mSerializer;
    private Context mAppContext;

    private CrimeLab(Context appContext) {
        mAppContext = appContext;
        mSerializer = new CriminalIntentJSONSerializer(mAppContext, FILENAME);

        try {
            mCrimes = mSerializer.loadCrimes();
        } catch (Exception e) {
            mCrimes = new ArrayList<Crime>();
        }
    }

    public static CrimeLab get(Context c) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(c.getApplicationContext());
        }
        return sCrimeLab;
    }

    public Crime getCrime(UUID id) {
        for (Crime c : mCrimes) {
            if (c.getId().equals(id))
                return c;
        }
        return null;
    }

    public void addCrime(Crime c) {
        mCrimes.add(c);
        saveCrimes();
    }

    public ArrayList<Crime> getCrimes() {
        return mCrimes;
    }

    public void deleteCrime(Crime c) {
        mCrimes.remove(c);
        saveCrimes();
    }

    public boolean saveCrimes() {
        try {
            mSerializer.saveCrimes(mCrimes);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

