package com.example.myapplication

import android.content.pm.ActivityInfo
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.NoActivityResumedException
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class NavigationTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testAbout() {
        launchActivity<MainActivity>()
        openAbout()
        onView(withId(R.id.activity_about))
            .check(matches(isDisplayed()))
    }

    @Test
    fun fragmentFirst() {

        // проверяем отображение самого фрагмента
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))

        //Проверяем содержимое
        // Есть ли активная кнопка с определенным текстом
        onView(withId(R.id.bnToSecond))
            .check(matches(isDisplayed()))
            .check(matches(isClickable()))
            .check(matches(withText(R.string.title_to_second)))

        //Нет других кнопок
        onView(withId(R.id.bnToFirst)).check(doesNotExist())
        onView(withId(R.id.bnToThird)).check(doesNotExist())

        //Проверим, что при повороте экрана все остается неизменным
        activityRule.scenario.onActivity { activity ->
            activity.setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            )
        }
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond))
            .check(matches(isDisplayed()))
            .check(matches(isClickable()))
            .check(matches(withText(R.string.title_to_second)))
        onView(withId(R.id.bnToFirst)).check(doesNotExist())
        onView(withId(R.id.bnToThird)).check(doesNotExist())

        //Перейдем обратно в портретную
        activityRule.scenario.onActivity { activity ->
            activity.setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            )
        }
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond))
            .check(matches(isDisplayed()))
            .check(matches(isClickable()))
            .check(matches(withText(R.string.title_to_second)))
        onView(withId(R.id.bnToFirst)).check(doesNotExist())
        onView(withId(R.id.bnToThird)).check(doesNotExist())

        //Осущесвтим переход к AboutActivity
        openAbout()
        // Проверим, что он открылся и активен
        onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
        //Что есть TextView
        onView(withId(R.id.tvAbout)).check(matches(isDisplayed()))
        // Перейдем обратно
        pressBack()
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))

        //Осущесвтим переход fragment1 -> fragment2
        onView(withId(R.id.bnToSecond)).perform(click())
        //Проверим то, что мы оказались в fragment2
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
    }

    @Test
    fun aboutFirstFragment() {
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        //Осущесвтим переход к AboutActivity
        openAbout()
        // Проверим, что он открылся и активен
        onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
        //Что есть TextView
        onView(withId(R.id.tvAbout)).check(matches(isDisplayed()))
        //Перевернем экран и осуществим проверку
        activityRule.scenario.onActivity { activity ->
            activity.setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            )
        }
        onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
        onView(withId(R.id.tvAbout)).check(matches(isDisplayed()))
        // Вернемся в портретную ориентацию
        activityRule.scenario.onActivity { activity ->
            activity.setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            )
        }
        onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
        onView(withId(R.id.tvAbout)).check(matches(isDisplayed()))
        // Перейдем обратно
        pressBack()
        // Проверим, что оказались в fragment1
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))

    }

    @Test
    fun fragmentSecond() {

        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()))

        onView(withId(R.id.bnToFirst))
            .check(matches(isDisplayed()))
            .check(matches(isClickable()))
            .check(matches(withText(R.string.title_to_first)))
        onView(withId(R.id.bnToThird))
            .check(matches(isDisplayed()))
            .check(matches(isClickable()))
            .check(matches(withText(R.string.title_to_third)))
        onView(withId(R.id.bnToSecond)).check(doesNotExist())

        //Проверим, что при повороте экрана все остается неизменным
        activityRule.scenario.onActivity { activity ->
            activity.setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            )
        }
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToFirst))
            .check(matches(isDisplayed()))
            .check(matches(isClickable()))
            .check(matches(withText(R.string.title_to_first)))
        onView(withId(R.id.bnToThird))
            .check(matches(isDisplayed()))
            .check(matches(isClickable()))
            .check(matches(withText(R.string.title_to_third)))
        onView(withId(R.id.bnToSecond)).check(doesNotExist())

        //Перейдем обратно в портретную
        activityRule.scenario.onActivity { activity ->
            activity.setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            )
        }
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToFirst))
            .check(matches(isDisplayed()))
            .check(matches(isClickable()))
            .check(matches(withText(R.string.title_to_first)))
        onView(withId(R.id.bnToThird))
            .check(matches(isDisplayed()))
            .check(matches(isClickable()))
            .check(matches(withText(R.string.title_to_third)))
        onView(withId(R.id.bnToSecond)).check(doesNotExist())

        //Осущесвтим переход к AboutActivity
        openAbout()
        onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
        onView(withId(R.id.tvAbout)).check(matches(isDisplayed()))
        pressBack()


        //Осущесвтим переход fragment2 -> fragment1
        onView(withId(R.id.bnToFirst)).perform(click())
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond)).perform(click())

        // fragment2 -> fragment3
        onView(withId(R.id.bnToThird)).perform(click())
        onView(withId(R.id.fragment3)).check(matches(isDisplayed()))
    }

    @Test
    fun aboutSecondFragment() {
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
        //Осущесвтим переход к AboutActivity
        openAbout()
        // Проверим, что он открылся и активен
        onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
        //Что есть TextView
        onView(withId(R.id.tvAbout)).check(matches(isDisplayed()))
        //Перевернем экран и осуществим проверку
        activityRule.scenario.onActivity { activity ->
            activity.setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            )
        }
        onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
        onView(withId(R.id.tvAbout)).check(matches(isDisplayed()))
        // Вернемся в портретную ориентацию
        activityRule.scenario.onActivity { activity ->
            activity.setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            )
        }
        onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
        onView(withId(R.id.tvAbout)).check(matches(isDisplayed()))
        // Перейдем обратно
        pressBack()
        // Проверим, что оказались в fragment2
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()))

    }

    @Test
    fun fragmentThird() {

        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        onView(withId(R.id.fragment3)).check(matches(isDisplayed()))

        onView(withId(R.id.bnToFirst))
            .check(matches(isDisplayed()))
            .check(matches(isClickable()))
            .check(matches(withText(R.string.title_to_first)))
        onView(withId(R.id.bnToSecond))
            .check(matches(isDisplayed()))
            .check(matches(isClickable()))
            .check(matches(withText(R.string.title_to_second)))
        onView(withId(R.id.bnToThird)).check(doesNotExist())

        //Проверим, что при повороте экрана все остается неизменным
        activityRule.scenario.onActivity { activity ->
            activity.setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            )
        }
        onView(withId(R.id.fragment3)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToFirst))
            .check(matches(isDisplayed()))
            .check(matches(isClickable()))
            .check(matches(withText(R.string.title_to_first)))
        onView(withId(R.id.bnToSecond))
            .check(matches(isDisplayed()))
            .check(matches(isClickable()))
            .check(matches(withText(R.string.title_to_second)))
        onView(withId(R.id.bnToThird)).check(doesNotExist())

        //Перейдем обратно в портретную
        activityRule.scenario.onActivity { activity ->
            activity.setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            )
        }
        onView(withId(R.id.fragment3)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToFirst))
            .check(matches(isDisplayed()))
            .check(matches(isClickable()))
            .check(matches(withText(R.string.title_to_first)))
        onView(withId(R.id.bnToSecond))
            .check(matches(isDisplayed()))
            .check(matches(isClickable()))
            .check(matches(withText(R.string.title_to_second)))
        onView(withId(R.id.bnToThird)).check(doesNotExist())

        //Осущесвтим переход к AboutActivity
        openAbout()
        onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
        onView(withId(R.id.tvAbout)).check(matches(isDisplayed()))
        pressBack()

        //fragment3 -> fragment2
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToThird)).perform(click())
        onView(withId(R.id.fragment3)).check(matches(isDisplayed()))

        //fragment3 -> fragment1
        onView(withId(R.id.bnToFirst)).perform(click())
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
    }

    @Test
    fun aboutThirdFragment() {
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToThird)).perform(click())
        onView(withId(R.id.fragment3)).check(matches(isDisplayed()))
        //Осущесвтим переход к AboutActivity
        openAbout()
        // Проверим, что он открылся и активен
        onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
        //Что есть TextView
        onView(withId(R.id.tvAbout)).check(matches(isDisplayed()))
        //Перевернем экран и осуществим проверку
        activityRule.scenario.onActivity { activity ->
            activity.setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            )
        }
        onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
        onView(withId(R.id.tvAbout)).check(matches(isDisplayed()))
        // Вернемся в портретную ориентацию
        activityRule.scenario.onActivity { activity ->
            activity.setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            )
        }
        onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
        onView(withId(R.id.tvAbout)).check(matches(isDisplayed()))
        // Перейдем обратно
        pressBack()
        // Проверим, что оказались в fragment3
        onView(withId(R.id.fragment3)).check(matches(isDisplayed()))

    }

    @Test
    fun checkBackStack_1_2_1() {
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToFirst)).perform(click())
        pressBackUnconditionally()
        assertTrue(activityRule.scenario.state.isAtLeast(Lifecycle.State.DESTROYED))
    }

    @Test
    fun checkBackStack_1_2_back() {
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond)).perform(click())
        pressBackUnconditionally()
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        pressBackUnconditionally()
        assertTrue(activityRule.scenario.state.isAtLeast(Lifecycle.State.DESTROYED))
    }

    @Test
    fun checkBackStack_1_2_3_back_back() {
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        pressBackUnconditionally()
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
        pressBackUnconditionally()
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        pressBackUnconditionally()
        assertTrue(activityRule.scenario.state.isAtLeast(Lifecycle.State.DESTROYED))
    }

    @Test
    fun checkBackStack_1_2_3_1() {
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        onView(withId(R.id.bnToFirst)).perform(click())
        pressBackUnconditionally()
        assertTrue(activityRule.scenario.state.isAtLeast(Lifecycle.State.DESTROYED))
    }

    @Test
    fun checkBackStack_1_2_3_2_back() {
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        onView(withId(R.id.bnToSecond)).perform(click())
        pressBackUnconditionally()
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        pressBackUnconditionally()
        assertTrue(activityRule.scenario.state.isAtLeast(Lifecycle.State.DESTROYED))
    }

    @Test
    fun checkBackStack_1_2_3_back_2_back() {
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        pressBackUnconditionally()
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToFirst)).perform(click())
        pressBackUnconditionally()
        assertTrue(activityRule.scenario.state.isAtLeast(Lifecycle.State.DESTROYED))
    }

    @Test
    fun checkBackStack_1_about_back() {
        openAbout()
        onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
        pressBackUnconditionally()
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        pressBackUnconditionally()
        assertTrue(activityRule.scenario.state.isAtLeast(Lifecycle.State.DESTROYED))
    }

    @Test
    fun checkBackStack_1_2_about_back_1() {
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond)).perform(click())
        openAbout()
        onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
        pressBackUnconditionally()
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToFirst)).perform(click())
        pressBackUnconditionally()
        assertTrue(activityRule.scenario.state.isAtLeast(Lifecycle.State.DESTROYED))
    }

    @Test
    fun checkBackStack_1_2_about_back_back() {
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond)).perform(click())
        openAbout()
        onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
        pressBackUnconditionally()
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
        pressBackUnconditionally()
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        pressBackUnconditionally()
        assertTrue(activityRule.scenario.state.isAtLeast(Lifecycle.State.DESTROYED))
    }

    @Test
    fun checkBackStack_1_2_3_about_back_back_back() {
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        openAbout()
        onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
        pressBackUnconditionally()
        onView(withId(R.id.fragment3)).check(matches(isDisplayed()))
        pressBackUnconditionally()
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
        pressBackUnconditionally()
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        pressBackUnconditionally()
        assertTrue(activityRule.scenario.state.isAtLeast(Lifecycle.State.DESTROYED))
    }

    @Test
    fun checkBackStack_1_2_3_about_back_1() {
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        openAbout()
        onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
        pressBackUnconditionally()
        onView(withId(R.id.fragment3)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToFirst)).perform(click())
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        pressBackUnconditionally()
        assertTrue(activityRule.scenario.state.isAtLeast(Lifecycle.State.DESTROYED))
    }

    @Test
    fun checkBackStack_1_2_3_about_back_2() {
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        openAbout()
        onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
        pressBackUnconditionally()
        onView(withId(R.id.fragment3)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
        pressBackUnconditionally()
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        pressBackUnconditionally()
        assertTrue(activityRule.scenario.state.isAtLeast(Lifecycle.State.DESTROYED))
    }

    @Test
    fun checkBackStack_1_2_3_3_about_back_back_2_back() {
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        openAbout()
        onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
        pressBackUnconditionally()
        onView(withId(R.id.fragment3)).check(matches(isDisplayed()))
        pressBackUnconditionally()
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
        pressBackUnconditionally()
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        pressBackUnconditionally()
        assertTrue(activityRule.scenario.state.isAtLeast(Lifecycle.State.DESTROYED))
    }

}