package com.eone.submission1.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.rule.ActivityTestRule
import com.eone.submission1.R
import com.eone.submission1.data.DataDummy
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {

    private val dummyMovie = DataDummy.getMovies()
    private val dummyTvShow = DataDummy.getTvShows()

    @get:Rule
    var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun loadMovie() {
        Espresso.onView(withId(R.id.rv_movie))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_movie))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun loadDetailMovie() {
        Espresso.onView(withId(R.id.rv_movie))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        Espresso.onView(withId(R.id.tv_title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_title))
            .check(ViewAssertions.matches(withText(dummyMovie[0].title)))
        Espresso.onView(withId(R.id.tv_description))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_description))
            .check(ViewAssertions.matches(withText(dummyMovie[0].overview)))
        Espresso.onView(withId(R.id.tv_duration))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_duration))
            .check(ViewAssertions.matches(withText(dummyMovie[0].duration)))
        Espresso.onView(withId(R.id.tv_genre))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_genre))
            .check(ViewAssertions.matches(withText(dummyMovie[0].genre)))
        Espresso.onView(withId(R.id.poster_img))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.bg_image))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun loadTvShow() {
        Espresso.onView(withText("Tv Shows")).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_tvShow))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_tvShow))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShow.size))
    }

    @Test
    fun loadDetailTvShow() {
        Espresso.onView(withText("Tv Shows")).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_tvShow))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_tvShow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        Espresso.onView(withId(R.id.tv_title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_title))
            .check(ViewAssertions.matches(withText(dummyTvShow[0].title)))
        Espresso.onView(withId(R.id.tv_description))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_description))
            .check(ViewAssertions.matches(withText(dummyTvShow[0].overview)))
        Espresso.onView(withId(R.id.tv_duration))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_duration))
            .check(ViewAssertions.matches(withText(dummyTvShow[0].duration)))
        Espresso.onView(withId(R.id.tv_genre))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_genre))
            .check(ViewAssertions.matches(withText(dummyTvShow[0].genre)))
        Espresso.onView(withId(R.id.poster_img))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.bg_image))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}