package com.eone.submission1.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.eone.submission1.EspressoIdlingResource
import com.eone.submission1.R
import com.eone.submission1.data.DataDummy
import com.eone.submission1.ui.home.activity.HomeActivity
import org.junit.After
import org.junit.Before
import org.junit.Test

class HomeActivityTest {

//    private val dummyMovies = DataDummy.getMovies()
//    private val dummyTvShow = DataDummy.getTvShows()

    @Before
    fun setup() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

//    @get:Rule
//    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun loadHome() {
        onView(withId(R.id.collapsing_home)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.cv_poster))
            .perform(ViewActions.swipeLeft())
        onView(withId(R.id.tabLayout))
            .perform(ViewActions.swipeUp())
        onView(withId(R.id.cv_poster)).perform(ViewActions.click())
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movie))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                19
            )
        )
    }

    @Test
    fun loadDetailMovies() {
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        onView(withId(R.id.tv_title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//        onView(withId(R.id.tv_title))
//            .check(ViewAssertions.matches(ViewMatchers.withText(dummyMovies[0].title)))
        onView(withId(R.id.tv_description))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//        onView(withId(R.id.tv_description))
//            .check(ViewAssertions.matches(ViewMatchers.withText(dummyMovies[0].overview)))
        onView(withId(R.id.tv_duration))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//        onView(withId(R.id.tv_duration))
//            .check(ViewAssertions.matches(ViewMatchers.withText(dummyMovies[0].duration)))
        onView(withId(R.id.tv_genre))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//        onView(withId(R.id.tv_genre))
//            .check(ViewAssertions.matches(ViewMatchers.withText(dummyMovies[0].genre)))
        onView(withId(R.id.poster_img))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.bg_image))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_release_date)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.collapsing_detail)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.cl_detail)).perform(ViewActions.swipeUp())
        onView(withId(R.id.cv_vote)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun loadTvShows() {
        onView(ViewMatchers.withText("Tv Shows")).perform(ViewActions.click())
        onView(withId(R.id.rv_tvShow))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_tvShow)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                19
            )
        )
    }

    @Test
    fun loadDetailTvShows() {
        onView(ViewMatchers.withText("Tv Shows")).perform(ViewActions.click())
        onView(withId(R.id.rv_tvShow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        onView(withId(R.id.tv_title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//        onView(withId(R.id.tv_title))
//            .check(ViewAssertions.matches(ViewMatchers.withText(dummyTvShow[0].title)))
        onView(withId(R.id.tv_description))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//        onView(withId(R.id.tv_description))
//            .check(ViewAssertions.matches(ViewMatchers.withText(dummyTvShow[0].overview)))
        onView(withId(R.id.tv_duration))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//        onView(withId(R.id.tv_duration))
//            .check(ViewAssertions.matches(ViewMatchers.withText(dummyTvShow[0].duration)))
        onView(withId(R.id.tv_genre))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//        onView(withId(R.id.tv_genre))
//            .check(ViewAssertions.matches(ViewMatchers.withText(dummyTvShow[0].genre)))
        onView(withId(R.id.poster_img))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.bg_image))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_release_date)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.collapsing_detail)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.cl_detail)).perform(ViewActions.swipeUp())
        onView(withId(R.id.cv_vote)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}