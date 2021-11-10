package com.example.task1

import android.content.pm.ActivityInfo
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Rule


@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun buttonOnScreen() {
        onView(withId(R.id.button)).check(matches(isDisplayed()))
        onView(withId(R.id.button)).check(matches(isClickable()))
    }

    @Test
    fun textViewOnScreen() {
        onView(withId(R.id.txtGreeting)).check(matches(isDisplayed()))
        onView(withId(R.id.txtGreeting)).check(matches(isClickable()))
    }

    @Test
    fun checkButtonText() {

        // Проверяем текст на кнопке
        onView(withId(R.id.button)).check(matches(withText(R.string.not_pressed)))

        // Нажимаем кнопку
        onView(withId(R.id.button)).perform(click())

        // Кнопка должна остаться, а текст должен измениться
        onView(withId(R.id.button))
            .check(matches(isDisplayed()))
            .check(matches(isClickable()))
            .check(matches(withText(R.string.pressed)))

        // Поворачиваем экран
        activityRule.scenario.onActivity { activity ->
            activity.setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            )
        }

        //Подождем одну секунду
        Thread.sleep(1000)

        //кнопка должна остаться, а текст изменитсья на изначальный
        onView(withId(R.id.button))
            .check(matches(isDisplayed()))
            .check(matches(isClickable()))
            .check(matches(withText(R.string.not_pressed)))

        //Еще раз нажмем, повернем экран и выполним проверку
        onView(withId(R.id.button))
            .perform(click())
            .check(matches(isDisplayed()))
            .check(matches(isClickable()))
            .check(matches(withText(R.string.pressed)))

        activityRule.scenario.onActivity { activity ->
            activity.setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            )
        }
        Thread.sleep(1000)
        onView(withId(R.id.button))
            .check(matches(isDisplayed()))
            .check(matches(isClickable()))
            .check(matches(withText(R.string.not_pressed)))

        //Теперь повторим цикл поворотов, но без нажатия кнопки
        activityRule.scenario.onActivity { activity ->
            activity.setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            )
        }
        Thread.sleep(1000)
        onView(withId(R.id.button))
            .check(matches(isDisplayed()))
            .check(matches(isClickable()))
            .check(matches(withText(R.string.not_pressed)))

        //вернемся в альбомную ориентацию
        activityRule.scenario.onActivity { activity ->
            activity.setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            )
        }
        Thread.sleep(1000)
        onView(withId(R.id.button))
            .check(matches(isDisplayed()))
            .check(matches(isClickable()))
            .check(matches(withText(R.string.not_pressed)))
    }

    @Test
    fun checkEditText() {

        // Проверяем текст на кнопке и EditText
        onView(withId(R.id.txtGreeting)).check(matches(withText(R.string.greeting)))

        // Нажимаем кнопку
        onView(withId(R.id.button)).perform(click())

        // Текст в EditText не должен измениться
        onView(withId(R.id.txtGreeting))
            .check(matches(isDisplayed()))
            .check(matches(isClickable()))
            .check(matches(withText(R.string.greeting)))

        // Поворачиваем экран
        activityRule.scenario.onActivity { activity ->
            activity.setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            )
        }

        // Проверяем наличие EditText и то, что содержимое не изменилось
        onView(withId(R.id.txtGreeting))
            .check(matches(isDisplayed()))
            .check(matches(isClickable()))
            .check(matches(withText(R.string.greeting)))

        //Нажмем на кнопку и повернем экран еще раз
        onView(withId(R.id.button)).perform(click())

        onView(withId(R.id.txtGreeting))
            .check(matches(isDisplayed()))
            .check(matches(isClickable()))
            .check(matches(withText(R.string.greeting)))

        activityRule.scenario.onActivity { activity ->
            activity.setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            )
        }

        onView(withId(R.id.txtGreeting))
            .check(matches(isDisplayed()))
            .check(matches(isClickable()))
            .check(matches(withText(R.string.greeting)))


        // Проверим то, что текст также не изменится без нажатия кнопки
        activityRule.scenario.onActivity { activity ->
            activity.setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            )
        }

        onView(withId(R.id.txtGreeting))
            .check(matches(isDisplayed()))
            .check(matches(isClickable()))
            .check(matches(withText(R.string.greeting)))

        //вернемся в альбомную ориентацию
        activityRule.scenario.onActivity { activity ->
            activity.setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            )
        }

        onView(withId(R.id.txtGreeting))
            .check(matches(isDisplayed()))
            .check(matches(isClickable()))
            .check(matches(withText(R.string.greeting)))

    }
}