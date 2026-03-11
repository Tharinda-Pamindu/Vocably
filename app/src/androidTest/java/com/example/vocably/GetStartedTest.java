package com.example.vocably;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.vocably.view.GetStarted;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class GetStartedTest {

    @Rule
    public ActivityScenarioRule<GetStarted> activityRule =
            new ActivityScenarioRule<>(GetStarted.class);

    @Test
    public void testLogoIsDisplayed() {
        // Check if the logo ImageView is displayed on the screen
        onView(withId(R.id.ivGetStartedLogo)).check(matches(isDisplayed()));
    }
}