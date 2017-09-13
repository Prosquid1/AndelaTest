//package com.example.lg.andelatask;
//
//import android.support.test.rule.ActivityTestRule;
//import android.support.test.runner.AndroidJUnit4;
//
//import com.example.lg.andelatask.Activities.MainActivity;
//
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import static android.support.test.espresso.Espresso.onView;
//import static android.support.test.espresso.action.ViewActions.click;
//import static android.support.test.espresso.assertion.ViewAssertions.matches;
//import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
//import static android.support.test.espresso.matcher.ViewMatchers.withId;
//import static android.support.test.espresso.matcher.ViewMatchers.withText;
//
//@RunWith(AndroidJUnit4.class)
//public class MainActivityTest {
//  @Rule
//  public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);
//
//  @Test
//  public void clickItem() {
//    onView(withId(R.id.all_users_recycler))
//        .perform(RecyclerViewActions.actionOnItemAtPosition(27, click()));
//
//
//    //Test URL call is successful,
//    //Test it takes you to another page
//    //Test you can click button on user page
//    //Test Picasso loads correctly
//    //Test Share Intent can launch
//
//    onView(withId(R.id.all_users_recycler))
//            .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
//
//    onView(withId(R.id.text))
//        .check(matches(withText("27")))
//        .check(matches(isDisplayed()));
//  }
//}