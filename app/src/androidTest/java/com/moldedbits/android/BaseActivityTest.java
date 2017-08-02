package com.moldedbits.android;

import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
@SmallTest
public class BaseActivityTest {

    @Rule
    public ActivityTestRule<BaseDrawerActivity> testRule =
            new ActivityTestRule<>(BaseDrawerActivity.class, true, false);

    @Before
    public void setup() {
        ((DebugBaseApplication) DebugBaseApplication.getInstance()).enableMockMode();
        testRule.launchActivity(null);
    }

    @After
    public void tearDown() {
        testRule.getActivity().finish();
    }

    @Test
    public void sampleTest() {
//        onView(withText("Hello World!"))
//                .check(matches(isDisplayed()));
    }
}
