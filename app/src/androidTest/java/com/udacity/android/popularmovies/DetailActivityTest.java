package com.udacity.android.popularmovies;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
public class DetailActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule
            = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void whenLoad_shouldDisplayViewComponents() {
        onView(withId(R.id.rv_movie_list))
            .perform(RecyclerViewActions.actionOnItemAtPosition(15, click()));

        onView(withId(R.id.img_poster)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_release_date)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_movie_duration)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_rate)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_add_favorite)).check(matches(isDisplayed()));
    }

}