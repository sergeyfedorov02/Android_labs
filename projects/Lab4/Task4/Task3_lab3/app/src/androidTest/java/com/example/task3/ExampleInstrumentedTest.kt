package com.example.task3

import android.content.pm.ActivityInfo
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class NavigationTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(FirstActivity::class.java)

    @Test
    fun testAbout() {
        launchActivity<FirstActivity>()
        openAbout()
        Espresso.onView(withId(R.id.activity_about))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun fragmentFirst() {

        // проверяем отображение самого фрагмента
        Espresso.onView(withId(R.id.fragment1))
            .check(ViewAssertions.matches(isDisplayed()))

        //Проверяем содержимое
        // Есть ли активная кнопка с определенным текстом
        Espresso.onView(withId(R.id.bnToSecond))
            .check(ViewAssertions.matches(isDisplayed()))
            .check(ViewAssertions.matches(isClickable()))
            .check(ViewAssertions.matches(withText(R.string.title_to_second)))

        //Нет других кнопок
        Espresso.onView(withId(R.id.bnToFirst)).check(ViewAssertions.doesNotExist())
        Espresso.onView(withId(R.id.bnToThird)).check(ViewAssertions.doesNotExist())

        //Проверим, что при повороте экрана все остается неизменным
        activityRule.scenario.onActivity { activity ->
            activity.setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            )
        }
        Thread.sleep(1000)
        Espresso.onView(withId(R.id.fragment1))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToSecond))
            .check(ViewAssertions.matches(isDisplayed()))
            .check(ViewAssertions.matches(isClickable()))
            .check(ViewAssertions.matches(withText(R.string.title_to_second)))
        Espresso.onView(withId(R.id.bnToFirst)).check(ViewAssertions.doesNotExist())
        Espresso.onView(withId(R.id.bnToThird)).check(ViewAssertions.doesNotExist())

        //Перейдем обратно в портретную
        activityRule.scenario.onActivity { activity ->
            activity.setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            )
        }
        Thread.sleep(1000)
        Espresso.onView(withId(R.id.fragment1))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToSecond))
            .check(ViewAssertions.matches(isDisplayed()))
            .check(ViewAssertions.matches(isClickable()))
            .check(ViewAssertions.matches(withText(R.string.title_to_second)))
        Espresso.onView(withId(R.id.bnToFirst)).check(ViewAssertions.doesNotExist())
        Espresso.onView(withId(R.id.bnToThird)).check(ViewAssertions.doesNotExist())

        //Осущесвтим переход к AboutActivity
        openAbout()
        // Проверим, что он открылся и активен
        Espresso.onView(withId(R.id.activity_about))
            .check(ViewAssertions.matches(isDisplayed()))
        //Что есть TextView
        Espresso.onView(withId(R.id.tvAbout))
            .check(ViewAssertions.matches(isDisplayed()))
        // Перейдем обратно
        Espresso.pressBack()
        Thread.sleep(1000)
        Espresso.onView(withId(R.id.fragment1))
            .check(ViewAssertions.matches(isDisplayed()))

        //Осущесвтим переход fragment1 -> fragment2
        Espresso.onView(withId(R.id.bnToSecond)).perform(ViewActions.click())
        //Проверим то, что мы оказались в fragment2
        Espresso.onView(withId(R.id.fragment2))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun aboutFirstFragment() {
        Espresso.onView(withId(R.id.fragment1))
            .check(ViewAssertions.matches(isDisplayed()))
        //Осущесвтим переход к AboutActivity
        openAbout()
        // Проверим, что он открылся и активен
        Espresso.onView(withId(R.id.activity_about))
            .check(ViewAssertions.matches(isDisplayed()))
        //Что есть TextView
        Espresso.onView(withId(R.id.tvAbout))
            .check(ViewAssertions.matches(isDisplayed()))
        //Перевернем экран и осуществим проверку
        activityRule.scenario.onActivity { activity ->
            activity.setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            )
        }
        Thread.sleep(1000)
        Espresso.onView(withId(R.id.activity_about))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.tvAbout))
            .check(ViewAssertions.matches(isDisplayed()))
        // Вернемся в портретную ориентацию
        activityRule.scenario.onActivity { activity ->
            activity.setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            )
        }
        Thread.sleep(1000)
        Espresso.onView(withId(R.id.activity_about))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.tvAbout))
            .check(ViewAssertions.matches(isDisplayed()))
        // Перейдем обратно
        Espresso.pressBack()
        // Проверим, что оказались в fragment1
        Espresso.onView(withId(R.id.fragment1))
            .check(ViewAssertions.matches(isDisplayed()))

    }

    @Test
    fun fragmentSecond() {

        Espresso.onView(withId(R.id.bnToSecond)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.fragment2))
            .check(ViewAssertions.matches(isDisplayed()))

        Espresso.onView(withId(R.id.bnToFirst))
            .check(ViewAssertions.matches(isDisplayed()))
            .check(ViewAssertions.matches(isClickable()))
            .check(ViewAssertions.matches(withText(R.string.title_to_first)))
        Espresso.onView(withId(R.id.bnToThird))
            .check(ViewAssertions.matches(isDisplayed()))
            .check(ViewAssertions.matches(isClickable()))
            .check(ViewAssertions.matches(withText(R.string.title_to_third)))
        Espresso.onView(withId(R.id.bnToSecond)).check(ViewAssertions.doesNotExist())

        //Проверим, что при повороте экрана все остается неизменным
        activityRule.scenario.onActivity { activity ->
            activity.setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            )
        }
        Thread.sleep(1000)
        Espresso.onView(withId(R.id.fragment2))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToFirst))
            .check(ViewAssertions.matches(isDisplayed()))
            .check(ViewAssertions.matches(isClickable()))
            .check(ViewAssertions.matches(withText(R.string.title_to_first)))
        Espresso.onView(withId(R.id.bnToThird))
            .check(ViewAssertions.matches(isDisplayed()))
            .check(ViewAssertions.matches(isClickable()))
            .check(ViewAssertions.matches(withText(R.string.title_to_third)))
        Espresso.onView(withId(R.id.bnToSecond)).check(ViewAssertions.doesNotExist())

        //Перейдем обратно в портретную
        activityRule.scenario.onActivity { activity ->
            activity.setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            )
        }
        Thread.sleep(1000)
        Espresso.onView(withId(R.id.fragment2))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToFirst))
            .check(ViewAssertions.matches(isDisplayed()))
            .check(ViewAssertions.matches(isClickable()))
            .check(ViewAssertions.matches(withText(R.string.title_to_first)))
        Espresso.onView(withId(R.id.bnToThird))
            .check(ViewAssertions.matches(isDisplayed()))
            .check(ViewAssertions.matches(isClickable()))
            .check(ViewAssertions.matches(withText(R.string.title_to_third)))
        Espresso.onView(withId(R.id.bnToSecond)).check(ViewAssertions.doesNotExist())

        //Осущесвтим переход к AboutActivity
        openAbout()
        Espresso.onView(withId(R.id.activity_about))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.tvAbout))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.pressBack()


        //Осущесвтим переход fragment2 -> fragment1
        Espresso.onView(withId(R.id.bnToFirst)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.fragment1))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToSecond)).perform(ViewActions.click())

        // fragment2 -> fragment3
        Espresso.onView(withId(R.id.bnToThird)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.fragment3))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun aboutSecondFragment() {
        Espresso.onView(withId(R.id.fragment1))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToSecond)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.fragment2))
            .check(ViewAssertions.matches(isDisplayed()))
        //Осущесвтим переход к AboutActivity
        openAbout()
        // Проверим, что он открылся и активен
        Espresso.onView(withId(R.id.activity_about))
            .check(ViewAssertions.matches(isDisplayed()))
        //Что есть TextView
        Espresso.onView(withId(R.id.tvAbout))
            .check(ViewAssertions.matches(isDisplayed()))
        //Перевернем экран и осуществим проверку
        activityRule.scenario.onActivity { activity ->
            activity.setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            )
        }
        Thread.sleep(1000)
        Espresso.onView(withId(R.id.activity_about))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.tvAbout))
            .check(ViewAssertions.matches(isDisplayed()))
        // Вернемся в портретную ориентацию
        activityRule.scenario.onActivity { activity ->
            activity.setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            )
        }
        Thread.sleep(1000)
        Espresso.onView(withId(R.id.activity_about))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.tvAbout))
            .check(ViewAssertions.matches(isDisplayed()))
        // Перейдем обратно
        Espresso.pressBack()
        // Проверим, что оказались в fragment2
        Espresso.onView(withId(R.id.fragment2))
            .check(ViewAssertions.matches(isDisplayed()))

    }

    @Test
    fun fragmentThird() {

        Espresso.onView(withId(R.id.bnToSecond)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.bnToThird)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.fragment3))
            .check(ViewAssertions.matches(isDisplayed()))

        Espresso.onView(withId(R.id.bnToFirst))
            .check(ViewAssertions.matches(isDisplayed()))
            .check(ViewAssertions.matches(isClickable()))
            .check(ViewAssertions.matches(withText(R.string.title_to_first)))
        Espresso.onView(withId(R.id.bnToSecond))
            .check(ViewAssertions.matches(isDisplayed()))
            .check(ViewAssertions.matches(isClickable()))
            .check(ViewAssertions.matches(withText(R.string.title_to_second)))
        Espresso.onView(withId(R.id.bnToThird)).check(ViewAssertions.doesNotExist())

        //Проверим, что при повороте экрана все остается неизменным
        activityRule.scenario.onActivity { activity ->
            activity.setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            )
        }
        Thread.sleep(1000)
        Espresso.onView(withId(R.id.fragment3))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToFirst))
            .check(ViewAssertions.matches(isDisplayed()))
            .check(ViewAssertions.matches(isClickable()))
            .check(ViewAssertions.matches(withText(R.string.title_to_first)))
        Espresso.onView(withId(R.id.bnToSecond))
            .check(ViewAssertions.matches(isDisplayed()))
            .check(ViewAssertions.matches(isClickable()))
            .check(ViewAssertions.matches(withText(R.string.title_to_second)))
        Espresso.onView(withId(R.id.bnToThird)).check(ViewAssertions.doesNotExist())

        //Перейдем обратно в портретную
        activityRule.scenario.onActivity { activity ->
            activity.setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            )
        }
        Thread.sleep(1000)
        Espresso.onView(withId(R.id.fragment3))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToFirst))
            .check(ViewAssertions.matches(isDisplayed()))
            .check(ViewAssertions.matches(isClickable()))
            .check(ViewAssertions.matches(withText(R.string.title_to_first)))
        Espresso.onView(withId(R.id.bnToSecond))
            .check(ViewAssertions.matches(isDisplayed()))
            .check(ViewAssertions.matches(isClickable()))
            .check(ViewAssertions.matches(withText(R.string.title_to_second)))
        Espresso.onView(withId(R.id.bnToThird)).check(ViewAssertions.doesNotExist())

        //Осущесвтим переход к AboutActivity
        openAbout()
        Espresso.onView(withId(R.id.activity_about))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.tvAbout))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.pressBack()

        //fragment3 -> fragment2
        Espresso.onView(withId(R.id.bnToSecond)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.fragment2))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToThird)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.fragment3))
            .check(ViewAssertions.matches(isDisplayed()))

        //fragment3 -> fragment1
        Espresso.onView(withId(R.id.bnToFirst)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.fragment1))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun aboutThirdFragment() {
        Espresso.onView(withId(R.id.fragment1))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToSecond)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.fragment2))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToThird)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.fragment3))
            .check(ViewAssertions.matches(isDisplayed()))
        //Осущесвтим переход к AboutActivity
        openAbout()
        // Проверим, что он открылся и активен
        Espresso.onView(withId(R.id.activity_about))
            .check(ViewAssertions.matches(isDisplayed()))
        //Что есть TextView
        Espresso.onView(withId(R.id.tvAbout))
            .check(ViewAssertions.matches(isDisplayed()))
        //Перевернем экран и осуществим проверку
        activityRule.scenario.onActivity { activity ->
            activity.setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            )
        }
        Thread.sleep(1000)
        Espresso.onView(withId(R.id.activity_about))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.tvAbout))
            .check(ViewAssertions.matches(isDisplayed()))
        // Вернемся в портретную ориентацию
        activityRule.scenario.onActivity { activity ->
            activity.setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            )
        }
        Thread.sleep(1000)
        Espresso.onView(withId(R.id.activity_about))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.tvAbout))
            .check(ViewAssertions.matches(isDisplayed()))
        // Перейдем обратно
        Espresso.pressBack()
        // Проверим, что оказались в fragment3
        Espresso.onView(withId(R.id.fragment3))
            .check(ViewAssertions.matches(isDisplayed()))

    }

    @Test
    fun checkBackStack_1_2_1() {
        Espresso.onView(withId(R.id.fragment1))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToSecond)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.bnToFirst)).perform(ViewActions.click())
        Espresso.pressBackUnconditionally()
        assertTrue(activityRule.scenario.state.isAtLeast(Lifecycle.State.DESTROYED))
    }

    @Test
    fun checkBackStack_1_2_back() {
        Espresso.onView(withId(R.id.fragment1))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToSecond)).perform(ViewActions.click())
        Espresso.pressBackUnconditionally()
        Espresso.onView(withId(R.id.fragment1))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.pressBackUnconditionally()
        assertTrue(activityRule.scenario.state.isAtLeast(Lifecycle.State.DESTROYED))
    }

    @Test
    fun checkBackStack_1_2_3_back_back() {
        Espresso.onView(withId(R.id.fragment1))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToSecond)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.bnToThird)).perform(ViewActions.click())
        Espresso.pressBackUnconditionally()
        Espresso.onView(withId(R.id.fragment2))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.pressBackUnconditionally()
        Espresso.onView(withId(R.id.fragment1))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.pressBackUnconditionally()
        assertTrue(activityRule.scenario.state.isAtLeast(Lifecycle.State.DESTROYED))
    }

    @Test
    fun checkBackStack_1_2_3_1() {
        Espresso.onView(withId(R.id.fragment1))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToSecond)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.bnToThird)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.bnToFirst)).perform(ViewActions.click())
        Espresso.pressBackUnconditionally()
        assertTrue(activityRule.scenario.state.isAtLeast(Lifecycle.State.DESTROYED))
    }

    @Test
    fun checkBackStack_1_2_3_2_back() {
        Espresso.onView(withId(R.id.fragment1))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToSecond)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.bnToThird)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.bnToSecond)).perform(ViewActions.click())
        Espresso.pressBackUnconditionally()
        Espresso.onView(withId(R.id.fragment1))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.pressBackUnconditionally()
        assertTrue(activityRule.scenario.state.isAtLeast(Lifecycle.State.DESTROYED))
    }

    @Test
    fun checkBackStack_1_2_3_back_2_back() {
        Espresso.onView(withId(R.id.fragment1))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToSecond)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.bnToThird)).perform(ViewActions.click())
        Espresso.pressBackUnconditionally()
        Espresso.onView(withId(R.id.fragment2))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToFirst)).perform(ViewActions.click())
        Espresso.pressBackUnconditionally()
        assertTrue(activityRule.scenario.state.isAtLeast(Lifecycle.State.DESTROYED))
    }

    @Test
    fun checkBackStack_1_about_back() {
        openAbout()
        Espresso.onView(withId(R.id.activity_about))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.pressBackUnconditionally()
        Espresso.onView(withId(R.id.fragment1))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.pressBackUnconditionally()
        assertTrue(activityRule.scenario.state.isAtLeast(Lifecycle.State.DESTROYED))
    }

    @Test
    fun checkBackStack_1_2_about_back_1() {
        Espresso.onView(withId(R.id.fragment1))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToSecond)).perform(ViewActions.click())
        openAbout()
        Espresso.onView(withId(R.id.activity_about))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.pressBackUnconditionally()
        Espresso.onView(withId(R.id.fragment2))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToFirst)).perform(ViewActions.click())
        Espresso.pressBackUnconditionally()
        assertTrue(activityRule.scenario.state.isAtLeast(Lifecycle.State.DESTROYED))
    }

    @Test
    fun checkBackStack_1_2_about_back_back() {
        Espresso.onView(withId(R.id.fragment1))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToSecond)).perform(ViewActions.click())
        openAbout()
        Espresso.onView(withId(R.id.activity_about))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.pressBackUnconditionally()
        Espresso.onView(withId(R.id.fragment2))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.pressBackUnconditionally()
        Espresso.onView(withId(R.id.fragment1))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.pressBackUnconditionally()
        assertTrue(activityRule.scenario.state.isAtLeast(Lifecycle.State.DESTROYED))
    }

    @Test
    fun checkBackStack_1_2_3_about_back_back_back() {
        Espresso.onView(withId(R.id.fragment1))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToSecond)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.bnToThird)).perform(ViewActions.click())
        openAbout()
        Espresso.onView(withId(R.id.activity_about))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.pressBackUnconditionally()
        Espresso.onView(withId(R.id.fragment3))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.pressBackUnconditionally()
        Espresso.onView(withId(R.id.fragment2))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.pressBackUnconditionally()
        Espresso.onView(withId(R.id.fragment1))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.pressBackUnconditionally()
        assertTrue(activityRule.scenario.state.isAtLeast(Lifecycle.State.DESTROYED))
    }

    @Test
    fun checkBackStack_1_2_3_about_back_1() {
        Espresso.onView(withId(R.id.fragment1))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToSecond)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.bnToThird)).perform(ViewActions.click())
        openAbout()
        Espresso.onView(withId(R.id.activity_about))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.pressBackUnconditionally()
        Espresso.onView(withId(R.id.fragment3))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToFirst)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.fragment1))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.pressBackUnconditionally()
        assertTrue(activityRule.scenario.state.isAtLeast(Lifecycle.State.DESTROYED))
    }

    @Test
    fun checkBackStack_1_2_3_about_back_2() {
        Espresso.onView(withId(R.id.fragment1))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToSecond)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.bnToThird)).perform(ViewActions.click())
        openAbout()
        Espresso.onView(withId(R.id.activity_about))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.pressBackUnconditionally()
        Espresso.onView(withId(R.id.fragment3))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToSecond)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.fragment2))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.pressBackUnconditionally()
        Espresso.onView(withId(R.id.fragment1))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.pressBackUnconditionally()
        assertTrue(activityRule.scenario.state.isAtLeast(Lifecycle.State.DESTROYED))
    }

    @Test
    fun checkBackStack_1_2_3_3_about_back_back_2_back() {
        Espresso.onView(withId(R.id.fragment1))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToSecond)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.bnToThird)).perform(ViewActions.click())
        openAbout()
        Espresso.onView(withId(R.id.activity_about))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.pressBackUnconditionally()
        Espresso.onView(withId(R.id.fragment3))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.pressBackUnconditionally()
        Espresso.onView(withId(R.id.fragment2))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.pressBackUnconditionally()
        Espresso.onView(withId(R.id.fragment1))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.pressBackUnconditionally()
        assertTrue(activityRule.scenario.state.isAtLeast(Lifecycle.State.DESTROYED))
    }

    @Test
    fun navigationBackFirst() {
        Espresso.onView(withId(R.id.fragment1))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToSecond)).perform(ViewActions.click())
        // возвращение назад при помощи кнопки ActionBar
        Espresso.onView(withContentDescription(R.string.nav_app_bar_navigate_up_description))
            .perform(ViewActions.click())
        Espresso.onView(withId(R.id.fragment1))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun navigationBackSecond() {
        Espresso.onView(withId(R.id.fragment1))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToSecond)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.fragment2))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToThird)).perform(ViewActions.click())
        Espresso.onView(withContentDescription(R.string.nav_app_bar_navigate_up_description))
            .perform(ViewActions.click())
        Espresso.onView(withId(R.id.fragment2))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun navigationBackFirstAbout() {
        Espresso.onView(withId(R.id.fragment1))
            .check(ViewAssertions.matches(isDisplayed()))
        openAbout()
        Espresso.onView(withContentDescription(R.string.nav_app_bar_navigate_up_description))
            .perform(ViewActions.click())
        Espresso.onView(withId(R.id.fragment1))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun navigationBackSecondAbout() {
        Espresso.onView(withId(R.id.fragment1))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToSecond)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.fragment2))
            .check(ViewAssertions.matches(isDisplayed()))
        openAbout()
        Espresso.onView(withContentDescription(R.string.nav_app_bar_navigate_up_description))
            .perform(ViewActions.click())
        Espresso.onView(withId(R.id.fragment2))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun navigationBackThirdAbout() {
        Espresso.onView(withId(R.id.fragment1))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToSecond)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.fragment2))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToThird)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.fragment3))
            .check(ViewAssertions.matches(isDisplayed()))
        openAbout()
        Espresso.onView(withContentDescription(R.string.nav_app_bar_navigate_up_description))
            .perform(ViewActions.click())
        Espresso.onView(withId(R.id.fragment3))
            .check(ViewAssertions.matches(isDisplayed()))
    }
}