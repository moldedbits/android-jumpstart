package com.moldedbits.android

import android.support.test.filters.SmallTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withText

@RunWith(AndroidJUnit4::class)
@SmallTest
class BaseActivityTest {

    @Rule
    var testRule = ActivityTestRule(BaseDrawerActivity::class.java, true, false)

    @Before
    fun setup() {
        (DebugBaseApplication.getInstance() as DebugBaseApplication).enableMockMode()
        testRule.launchActivity(null)
    }

    @After
    fun tearDown() {
        testRule.activity.finish()
    }

    @Test
    fun sampleTest() {
        onView(withText("Hello World!"))
                .check(matches(isDisplayed()))
    }
}
