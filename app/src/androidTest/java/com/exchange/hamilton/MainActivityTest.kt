package com.exchange.hamilton


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.rule.ActivityTestRule
import com.exchange.hamilton.presentation.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.regex.Pattern.matches

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {
    var intent: Intent? = null
    var preferencesEditor: SharedPreferences.Editor? = null

    @Rule
    var mActivityRule: ActivityTestRule<MainActivity> = ActivityTestRule(
        MainActivity::class.java,
        true,
        false
    ) // Activity is not launched immediately


    @Before
    fun setUp() {
        val targetContext: Context = getInstrumentation().getTargetContext()
        preferencesEditor = PreferenceManager.getDefaultSharedPreferences(targetContext).edit()
    }

    @Test
    fun populateUsernameFromSharedPrefsTest() {
        preferencesEditor?.putString("username", "testUsername")
        preferencesEditor?.commit()

        // Launch activity
        mActivityRule.launchActivity(Intent())
    }
}