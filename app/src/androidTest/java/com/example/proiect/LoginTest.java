package com.example.proiect;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginTest {

    @Rule
    public ActivityTestRule<Login> loginActivityTestRule = new ActivityTestRule<Login>(Login.class);

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

    @After
    public void tearDown() throws Exception {
        loginActivity = null;
    }
}