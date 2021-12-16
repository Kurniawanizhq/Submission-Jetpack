package com.eone.submission1.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.eone.submission1.R
import com.eone.submission1.data.DataDummy
import com.eone.submission1.ui.home.activity.HomeActivity
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {

    private val dummyMovies = DataDummy.getMovies()
    private val dummyTvShow = DataDummy.getTvShows()

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun loadHome(){
        onView(ViewMatchers.withId(R.id.cv_poster))
            .perform(ViewActions.swipeLeft())
        onView(ViewMatchers.withId(R.id.tabLayout))
            .perform(ViewActions.swipeUp())
    }

    @Test
    fun loadMovies() {
        onView(ViewMatchers.withId(R.id.rv_movie))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovies.size
            )
        )
    }

    @Test
    fun loadDetailMovies() {
        onView(ViewMatchers.withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        onView(ViewMatchers.withId(R.id.tv_title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.tv_title))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyMovies[0].title)))
        onView(ViewMatchers.withId(R.id.tv_description))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.tv_description))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyMovies[0].overview)))
        onView(ViewMatchers.withId(R.id.tv_duration))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.tv_duration))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyMovies[0].duration)))
        onView(ViewMatchers.withId(R.id.tv_genre))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.tv_genre))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyMovies[0].genre)))
        onView(ViewMatchers.withId(R.id.poster_img))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.bg_image))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))}

    @Test
    fun loadTvShows() {
        onView(ViewMatchers.withText("Tv Shows")).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.rv_tvShow))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.rv_tvShow)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTvShow.size
            )
        )
    }

    @Test
    fun loadDetailTvShows() {
        onView(ViewMatchers.withText("Tv Shows")).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.rv_tvShow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        onView(ViewMatchers.withId(R.id.tv_title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.tv_title))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyTvShow[0].title)))
        onView(ViewMatchers.withId(R.id.tv_description))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.tv_description))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyTvShow[0].overview)))
        onView(ViewMatchers.withId(R.id.tv_duration))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.tv_duration))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyTvShow[0].duration)))
        onView(ViewMatchers.withId(R.id.tv_genre))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.tv_genre))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyTvShow[0].genre)))
        onView(ViewMatchers.withId(R.id.poster_img))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.bg_image))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}