package com.moldedbits.android;

import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class BaseActivityTest {

    @Rule
    public ActivityTestRule<BaseDrawerActivity> mTestRule =
            new ActivityTestRule<>(BaseDrawerActivity.class, true, false);

    @Before
    public void setup() {
        ((DebugBaseApplication) DebugBaseApplication.getInstance()).enableMockMode();
        mTestRule.launchActivity(null);
    }

    @After
    public void tearDown() {
        mTestRule.getActivity().finish();
    }

    @Test
    public void sampleTest() {
        onView(withText("Hello World!"))
                .check(matches(isDisplayed()));
    }
}
