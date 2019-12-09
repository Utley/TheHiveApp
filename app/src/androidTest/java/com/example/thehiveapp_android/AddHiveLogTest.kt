package com.example.thehiveapp_android


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.*
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class AddHiveLogTest {

    /*  You may need to go to the Gradle menu (in the bar on the right),
        then go to app -> Tasks -> install -> installDebugAndroidTest,
        run that (by double-clicking on it).
        I mean, it still doesn't run for me, but it gives a different error now.
        - Zac
     */

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun addHiveLogTest() {
        val bottomNavigationItemView = onView(
            allOf(
                withId(R.id.navigation_hive_list), withContentDescription("List of Hives"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_view),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView.perform(click())

        val textView = onView(
            allOf(
                withId(R.id.inspections), withText("Test Hive"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Test Hive")))

        val hiveListItemView = onData(anything())
            .inAdapterView(
                allOf(
                    withId(R.id.hive_selection_list_view),
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        0
                    )
                )
            )
            .atPosition(0)
        hiveListItemView.perform(click())

        val textView2 = onView(
            allOf(
                withId(R.id.title), withText("Test Hive"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.FrameLayout::class.java),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("Test Hive")))

        val appCompatButton = onView(
            allOf(
                withId(R.id.add_inspection), withText("+"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.FrameLayout")),
                        0
                    ),
                    5
                ),
                isDisplayed()
            )
        )
        appCompatButton.perform(click())

        val appCompatRadioButton = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.honeyInput),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            7
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatRadioButton.perform(click())

        val appCompatRadioButton2 = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.broodInput),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            9
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatRadioButton2.perform(click())

        val appCompatRadioButton3 = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.pollenInput),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            11
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatRadioButton3.perform(click())

        val switch_ = onView(
            allOf(
                withId(R.id.seen_queen_switch), withText("Did you see the queen?"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        switch_.perform(click())

        val appCompatEditText = onView(
            allOf(
                withId(R.id.notesInput),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        0
                    ),
                    12
                ),
                isDisplayed()
            )
        )
        appCompatEditText.perform(replaceText("Test"), closeSoftKeyboard())

        val appCompatButton2 = onView(
            allOf(
                withId(R.id.saveButton), withText("Save"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatButton2.perform(click())

        val linearLayout = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.inspections),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            4
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        linearLayout.perform(click())

        val editText = onView(
            allOf(
                withId(R.id.notesText), withText("Test"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.FrameLayout::class.java),
                        0
                    ),
                    14
                ),
                isDisplayed()
            )
        )
        editText.check(matches(withText("Test")))

        val textView3 = onView(
            allOf(
                withId(R.id.honeyCount), withText("2"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.FrameLayout::class.java),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        textView3.check(matches(withText("2")))

        val textView4 = onView(
            allOf(
                withId(R.id.broodCount), withText("2"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.FrameLayout::class.java),
                        0
                    ),
                    6
                ),
                isDisplayed()
            )
        )
        textView4.check(matches(withText("2")))

        val textView5 = onView(
            allOf(
                withId(R.id.pollenCount), withText("2"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.FrameLayout::class.java),
                        0
                    ),
                    10
                ),
                isDisplayed()
            )
        )
        textView5.check(matches(withText("2")))

        val checkBox = onView(
            allOf(
                withId(R.id.eggCheckbox),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.FrameLayout::class.java),
                        0
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        checkBox.check(matches(isDisplayed()))

        val checkBox2 = onView(
            allOf(
                withId(R.id.miteCheckbox),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.FrameLayout::class.java),
                        0
                    ),
                    8
                ),
                isDisplayed()
            )
        )
        checkBox2.check(matches(isDisplayed()))

        val checkBox3 = onView(
            allOf(
                withId(R.id.queenCheckbox),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.FrameLayout::class.java),
                        0
                    ),
                    12
                ),
                isDisplayed()
            )
        )
        checkBox3.check(matches(isDisplayed()))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
