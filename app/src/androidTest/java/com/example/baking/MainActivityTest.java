package com.example.baking;


import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    public static final String MYRecipe = "Nutella Pie";
    public static final int mRecipeCount = 4;
    public static final int ITEM_AT_POSITION = 1;
    public static final String TAG = MainActivityTest.class.getSimpleName();

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void clickRecipeItem() {
        //find the view
        //Perform an action on the view
        //check the assertion results is expected output
        onView(withId (R.id.recipes_list))
                .check(matches(isDisplayed()));

        //check to see if Nutella Pie is displayed
        onView(withText(MYRecipe)).check(matches(isDisplayed()));

        //click on Nutella

        onView(withId(R.id.recipes_list)).perform(RecyclerViewActions.actionOnItemAtPosition(
                ITEM_AT_POSITION, click()));

    }


    @Test
    public void countNumberOfCardViews(){

        onView(withId(R.id.recipes_list)).check(new Assertions(mRecipeCount));

    }


}
