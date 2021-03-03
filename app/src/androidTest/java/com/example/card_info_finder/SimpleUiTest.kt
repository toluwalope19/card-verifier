package com.example.card_info_finder

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


private const val MESSAGE = 1234
private const val MESSAGE1 = 123456


@RunWith(AndroidJUnit4::class)
class SimpleUiTest
{

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun checkIfButtonIsNotEnabled(){
        onView(withId(R.id.cardNumber)).perform(typeText(MESSAGE.toString()), closeSoftKeyboard())
        onView(withId(R.id.checkCard)).perform(click())
        onView(withId(R.id.checkCard)).check(matches(not(isEnabled())))

    }

    @Test
    fun checkIfButtonIsEnabled(){
        onView(withId(R.id.cardNumber)).perform(typeText(MESSAGE1.toString()), closeSoftKeyboard())
        onView(withId(R.id.checkCard)).perform(click())
        onView(withId(R.id.checkCard)).check(matches((isEnabled())))

    }
}