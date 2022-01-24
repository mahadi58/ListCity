
package com.example.simpleparadox.listycity;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import android.app.Activity;
import android.widget.EditText;
import android.widget.ListView;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class activityTest {
    private Solo solo_MainActivity, solo_showActivity;
    @Rule
    public ActivityTestRule<MainActivity> rule_MainActivity =
            new ActivityTestRule<>(MainActivity.class, true, true);
    public ActivityTestRule<showActivity> rule_showActivity =
            new ActivityTestRule<>(showActivity.class, true, true);



    @Before
    public void setUp() throws Exception {
        solo_MainActivity = new Solo(InstrumentationRegistry.getInstrumentation(), rule_MainActivity.getActivity());
        solo_showActivity = new Solo(InstrumentationRegistry.getInstrumentation(), rule_showActivity.getActivity());
    }



    @Test
    public void start() throws Exception {
        Activity activity_MainActivity = rule_MainActivity.getActivity();
        Activity activity_showActivity = rule_showActivity.getActivity();
    }








    @Test
    public void testing() {
        // checking if initially i'm in MainActivity or not
        solo_MainActivity.assertCurrentActivity("this is not MainActivity", MainActivity.class);

        // adding some data
        solo_MainActivity.clickOnButton("ADD CITY");
        solo_MainActivity.enterText((EditText) solo_MainActivity.getView(R.id.editText_name), "1707058");
        solo_MainActivity.clickOnButton("CONFIRM");
        solo_MainActivity.waitForText("Edmonton", 1, 2000);

        solo_MainActivity.clickOnButton("ADD CITY");
        solo_MainActivity.enterText((EditText) solo_MainActivity.getView(R.id.editText_name), "shakil");
        solo_MainActivity.clickOnButton("CONFIRM");
        solo_MainActivity.waitForText("Edmonton", 1, 2000);


        // Get MainActivity to access its variables and methods
        MainActivity activity = (MainActivity) solo_MainActivity.getCurrentActivity();
        final ListView cityList = activity.cityList; // Get the listview
        String city = (String) cityList.getItemAtPosition(0); // Get item from first position
        // checking if indeed roll number is the first data
        assertEquals("1707058", city);


        // this should send to showActivity activity
        solo_MainActivity.clickInList(0);


        // checking if i'm in showActivity activity
        solo_showActivity.assertCurrentActivity("this is not showActivity", showActivity.class);
        // is the back button working?if it's working, then it should send me back to MainActivity
        //            // ty
        solo_showActivity.clickOnButton("BACK");

        // am i successfully return to MainActivity
        solo_MainActivity.assertCurrentActivity("this is not MainActivity", MainActivity.class);

        // clearing all the data
        solo_MainActivity.clickOnButton("ClEAR ALL");
    }














    /**
     * Close activity after each test
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        solo_MainActivity.finishOpenedActivities();
    }
}