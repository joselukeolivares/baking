package com.example.baking;

import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Assertions implements ViewAssertion {

    private final int mExpectedNumberOfItems;

    @Override
    public void check(View view, NoMatchingViewException noViewFoundException) {
        if (noViewFoundException != null) {
            throw noViewFoundException;
        }

        RecyclerView recyclerView = (RecyclerView) view;
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        Log.i("Testing Assertions",""+adapter.getItemCount());
        assertThat(adapter.getItemCount(), is(mExpectedNumberOfItems));
    }


    public Assertions(int expectedNumberOfItems) {
        mExpectedNumberOfItems = expectedNumberOfItems;
    }


}
