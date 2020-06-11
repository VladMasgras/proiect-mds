package com.example.proiect;

import android.app.Activity;
import android.app.Instrumentation;
import android.view.View;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

public class LoginTest {

    @Rule
    public ActivityTestRule<Login> loginActivityTestRule = new ActivityTestRule<Login>(Login.class);

    private Instrumentation.ActivityMonitor monitorAdmin = getInstrumentation().addMonitor(MeniuAdmin.class.getName(), null, false);

    private Login loginActivity = null;

    @Before
    public void setUp() throws Exception {
        loginActivity = loginActivityTestRule.getActivity();
    }

    @Test
    public void testLaunchTextFields(){
        View email = loginActivity.findViewById(R.id.text_input_email_login);
        View pass = loginActivity.findViewById(R.id.text_input_password_login);

        assertNotNull(email);
        assertNotNull(pass);
    }

    public void testLogin(){
        View email = loginActivity.findViewById(R.id.text_input_email_login);
        View pass = loginActivity.findViewById(R.id.text_input_password_login);

        String mailInput = "andrei@gmail.com";
        String passInput = "123456";

        Espresso.onView(withId(R.id.text_input_email_login)).perform(typeText(mailInput));
        Espresso.onView(withId(R.id.text_input_password_login)).perform(typeText(passInput));

        Espresso.onView(withId(R.id.email_sign_in_button)).perform(click());
        Activity meniuAdmin = getInstrumentation().waitForMonitorWithTimeout(monitorAdmin, 5000);

        assertNotNull(meniuAdmin);

    }

    @After
    public void tearDown() throws Exception {
        loginActivity = null;
    }
}