package dev.fonteles.mynotes.ui.login

import androidx.test.espresso.Espresso.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.fonteles.mynotes.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

//    @get:Rule
//    var activityScenarioRule = activityScenarioRule<LoginActivity>()

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule<LoginActivity>(LoginActivity::class.java)

    @Test
    fun invalidForm_LoginButtonDisabled() {
        onView(withId(R.id.username)).perform(typeText("username"))
        onView(withId(R.id.password)).perform(typeText("12345"))
        onView(withId(R.id.login_register)).check(matches(isNotEnabled()))
    }

    @Test
    fun emptyForm_LoginButtonDisabled() {
        onView(withId(R.id.login_register)).check(matches(isNotEnabled()))
    }

    @Test
    fun validForm_LoginButtonEnabled() {
        onView(withId(R.id.username)).perform(typeText("username"))
        onView(withId(R.id.password)).perform(typeText("123456"))
        onView(withId(R.id.login_register)).check(matches(isEnabled()))
    }
}