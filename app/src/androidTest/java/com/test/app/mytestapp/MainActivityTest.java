package com.test.app.mytestapp;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.view.View;
import com.test.app.mytestapp.view.MainActivity;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule= new ActivityTestRule<MainActivity>(MainActivity.class);
    private MainActivity mActivity=null;

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }

    @Test
    public void testLaunchOfMainActivityScreen() {
        //Check activity is not null
        assertNotNull(mActivity);

        //Check button is not null
        assertNotNull(mActivity.findViewById(R.id.listViewAlbums));
    }

    @Test
    public void testRecyclerDataOfMainActivityScreen() {
        //match data
        onView(withId(R.id.listViewAlbums))
                .check(matches(hasDescendant(withText("ad voluptas nostrum et nihil"))));

        //perform click
        onView(withId(R.id.listViewAlbums)).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
    }

    @Test
    public void testProgressBarViewOnMainActivity() {
        //Check activity is not null
        assertNotNull(mActivity);

        //Check button is not null
        assertNotNull(mActivity.findViewById(R.id.progress_bar_view));
    }

    @Test
    public void testProgressBarViewVisibilityOnMainActivity() {
        //Check activity is not null
        assertNotNull(mActivity);

        //Check button is not null
        assertNotNull(mActivity.findViewById(R.id.progress_bar_view));

        //check progressbar visibility
        assertEquals(mActivity.findViewById(R.id.progress_bar_view).getVisibility(), View.INVISIBLE);
    }


}
