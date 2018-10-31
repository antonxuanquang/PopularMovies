package com.udacity.android.popularmovies;

import android.app.ActionBar;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.ActionBarContainer;
import android.support.v7.widget.ActionBarContextView;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.support.v7.widget.RecyclerView;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.udacity.android.popularmovies.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.greaterThan;


@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private MainActivity activity;

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule
            = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        activity = mainActivityActivityTestRule.getActivity();
    }

    @Test
    public void whenOpenApp_ShouldLoadAListOfMovies() {
        onView(withId(R.id.rv_movie_list))
            .check(withItemCount(greaterThan(10)));
    }

    @Test
    public void whenScrollDownToBottom_ShouldLoadNewMovies() {
        RecyclerView recyclerView = activity.findViewById(R.id.rv_movie_list);

        onView(withId(R.id.rv_movie_list))
            .perform(RecyclerViewActions.scrollToPosition(recyclerView.getAdapter().getItemCount() - 1));

        onView(withId(R.id.rv_movie_list))
                .check(matches(isDisplayed()));
    }

    @Test
    public void whenChooseTopRate_ShouldLoadTopRatedMovies() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText(R.string.top_rated)).perform(click());

        onView(withId(R.id.rv_movie_list))
                .check(withItemCount(greaterThan(10)));
    }

    @Test
    public void whenChoosePopular_ShouldLoadPopularMovies() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText(R.string.popular)).perform(click());

        onView(withId(R.id.rv_movie_list))
                .check(withItemCount(greaterThan(10)));
    }

    @Test
    public void whenScrollToAPosition_ImageShouldDisplayed() {
        onView(withId(R.id.rv_movie_list))
            .perform(RecyclerViewActions.actionOnItemAtPosition(15, click()));

        onView(withId(R.id.img_poster))
            .check(matches(isDisplayed()));
    }

}
