package edu.josephkorang.criminalintent;

import java.util.Date;
import java.util.UUID;
public class Crime {
    private UUID mId;
    private String mTitle;
    private boolean mSolved;
    private Date mDate;
    public Crime() {
        mId = UUID.randomUUID();
        mSolved = false;
        mDate = new Date();
    }
    public String getTitle() {
        return mTitle;
    }
    public void setTitle(String title) {
        mTitle = title;
    }
    public UUID getId() {
        return mId;
    }
    @Override
    public String toString() {
        return mTitle;
    }
    public void setSolved(boolean x) {
        if (x == true) {
            mSolved = true;
        }
        else {
            mSolved = false;
        }
    }

    public boolean isSolved() {
        return mSolved;
    }

    public Date getDate() {
        return mDate;
    }
}
