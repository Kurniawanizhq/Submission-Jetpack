package com.eone.submission2.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.eone.submission3.R
import com.eone.submission3.ui.home.activity.HomeActivity
import com.eone.submission3.utils.EspressoIdlingResource
import com.eone.submission3.utils.FakeDataDummy
import org.junit.*
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class HomeActivityTest {

    private val dummyMovies = FakeDataDummy.getDummyMovie()
    private val dummyTvShow = FakeDataDummy.getDummyTvShow()
    private val dummyMovieDetail = FakeDataDummy.getDummyMovieDetail()
    private val dummyTvShowDetail = FakeDataDummy.getDummyTvShowDetail()

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Before
    fun setup() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadHome() {
        onView(withId(R.id.collapsing_home)).check(matches(isDisplayed()))
        onView(withId(R.id.cv_poster))
            .perform(ViewActions.swipeLeft())
        onView(withId(R.id.tabLayout))
            .perform(ViewActions.swipeUp())
        onView(withId(R.id.cv_poster)).perform(click())
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movie))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovies.size
            )
        )
        onView(withId(R.id.fam_menu)).perform(click())
        onView(withId(R.id.fabNewest)).perform(click())
        onView(withId(R.id.fabOldest)).perform(click())
        onView(withId(R.id.fabPopularity)).perform(click())
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
    }

    @Test
    fun loadDetailMovies() {
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.collapsing_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.cl_detail)).perform(ViewActions.swipeUp())
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_vote)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genre))
            .check(matches(isDisplayed()))
        onView(withId(R.id.poster_img))
            .check(matches(isDisplayed()))
        onView(withId(R.id.bg_image))
            .check(matches(isDisplayed()))
        onView(withId(R.id.cv_vote)).check(matches(isDisplayed()))

    }

    @Test
    fun loadTvShows() {
        onView(withText("Tv Shows")).perform(click())
        onView(withId(R.id.rv_tvShow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvShow)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTvShow.size
            )
        )
    }


    @Test
    fun favoriteTest() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.nav_favorite)).perform(click())
        onView(withText(R.string.movies)).perform(click())
        onView(withText(R.string.tvShow)).perform(click())
        onView(withId(R.id.nav_home)).perform(click())
    }

    @Test
    fun insertUpdateFavoriteTest() {
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                4,
                click()
            )
        )
        onView(withId(R.id.fab_add_to_favorite)).perform(click())
        Espresso.pressBack()
        onView(withText(R.string.tvShow)).perform(click())
        onView(withId(R.id.rv_tvShow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvShow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                4,
                click()
            )
        )
        onView(withId(R.id.fab_add_to_favorite)).perform(click())
        Espresso.pressBack()

        onView(withId(R.id.nav_favorite)).perform(click())
        onView(withId(R.id.rv_favorite_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
            0,
            click()
        )
        )
        onView(withId(R.id.fab_add_to_favorite)).perform(click())
        Espresso.pressBack()

        onView(withText(R.string.tvShow)).perform(click())
        onView(withId(R.id.rv_favorite_tvshow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
            0,
            click()
        )
        )
        onView(withId(R.id.fab_add_to_favorite)).perform(click())
        Espresso.pressBack()
    }

    @Test
    fun loadDetailTvShows() {
        onView(withText(R.string.tvShow)).perform(click())
        onView(withId(R.id.rv_tvShow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_vote)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.poster_img)).check(matches(isDisplayed()))
        onView(withId(R.id.bg_image)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.collapsing_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.cl_detail)).perform(ViewActions.swipeUp())
        onView(withId(R.id.cv_vote)).check(matches(isDisplayed()))
    }
}