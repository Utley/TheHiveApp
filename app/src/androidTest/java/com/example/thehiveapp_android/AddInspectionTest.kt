package com.example.thehiveapp_android


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import io.realm.Realm
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.*
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.text.DateFormat
import java.util.*

@LargeTest
@RunWith(AndroidJUnit4::class)
class AddInspectionTest {

    var inspectionDate: Date = Date()

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setup() {
        // Reset data for test
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        realm.deleteAll()
        realm.commitTransaction()

        // Create hive with inspection
        val bottomNavigationItemView = onView(
            allOf(
                withId(R.id.navigation_home), withContentDescription("Home"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_view),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView.perform(click())

        val appCompatEditText = onView(
            allOf(
                withId(R.id.nameInput),
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
        appCompatEditText.perform(replaceText("InspectionCreateTest"), closeSoftKeyboard())

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.frameCountInput),
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
        appCompatEditText2.perform(replaceText("1"), closeSoftKeyboard())

        val appCompatButton = onView(
            allOf(
                withId(R.id.saveButton), withText("Save"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        0
                    ),
                    6
                ),
                isDisplayed()
            )
        )
        appCompatButton.perform(click())

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

        val appCompatButton2 = onView(
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
        appCompatButton2.perform(click())

        val appCompatRadioButton = onView(
            allOf(
                withId(R.id.honey1),
                childAtPosition(
                    allOf(
                        withId(R.id.honeyInput),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            7
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatRadioButton.perform(click())

        val appCompatRadioButton2 = onView(
            allOf(
                withId(R.id.brood2),
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
                withId(R.id.pollen3),
                childAtPosition(
                    allOf(
                        withId(R.id.pollenInput),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            11
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatRadioButton3.perform(click())

        val switch_ = onView(
            allOf(
                withId(R.id.seen_eggs_switch), withText("Did you see new eggs?"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        switch_.perform(click())

        val switch_2 = onView(
            allOf(
                withId(R.id.seen_mites_switch), withText("Did you see mites?"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        switch_2.perform(click())

        val switch_3 = onView(
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
        switch_3.perform(click())

        val appCompatEditText3 = onView(
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
        appCompatEditText3.perform(click())

        val appCompatEditText4 = onView(
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
        appCompatEditText4.perform(replaceText("Notes"), closeSoftKeyboard())

        val appCompatButton3 = onView(
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
        inspectionDate = Date()
        appCompatButton3.perform(click())
    }

    @Test
    fun inspectionCreatedTest() {
        val appCompatTextView = onView(
            allOf(
                withId(R.id.inspectionDate), withText(DateFormat.getDateTimeInstance().format(inspectionDate)),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatTextView.perform(click())

        val textView = onView(
            allOf(
                withId(R.id.honeyCount),
                isDisplayed()
            )
        )
        textView.check(matches(withText("1")))

        val textView2 = onView(
            allOf(
                withId(R.id.broodCount),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("2")))

        val textView3 = onView(
            allOf(
                withId(R.id.pollenCount),
                isDisplayed()
            )
        )
        textView3.check(matches(withText("3")))

        val checkBox = onView(
            allOf(
                withId(R.id.eggCheckbox),
                isDisplayed()
            )
        )
        checkBox.check(matches(isDisplayed()))

        val checkBox2 = onView(
            allOf(
                withId(R.id.miteCheckbox),
                isDisplayed()
            )
        )
        checkBox2.check(matches(isDisplayed()))

        val checkBox3 = onView(
            allOf(
                withId(R.id.queenCheckbox),
                isDisplayed()
            )
        )
        checkBox3.check(matches(isDisplayed()))

        val editText = onView(
            allOf(
                withId(R.id.notesText),
                isDisplayed()
            )
        )
        editText.check(matches(withText("Notes")))
    }

    @Test
    fun deleteInspectionTest() {
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

        val appCompatTextView = onView(
            allOf(
                withId(R.id.deleteInspection), withText("X"),
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
        appCompatTextView.perform(click())

        val textView = onView(
            allOf(
                withId(R.id.deleteInspection), withText("X"),
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
        textView.check(ViewAssertions.doesNotExist())
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
