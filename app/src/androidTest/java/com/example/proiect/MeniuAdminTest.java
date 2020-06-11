package com.example.proiect;

import android.app.Activity;
import android.app.Instrumentation;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

public class MeniuAdminTest {

    @Rule
    public ActivityTestRule<MeniuAdmin> meniuAdminActivityTestRule = new ActivityTestRule<MeniuAdmin>(MeniuAdmin.class);

    private MeniuAdmin meniuAdmin = null;
    private Instrumentation.ActivityMonitor monitorVag = getInstrumentation().addMonitor(AdaugaVagoane.class.getName(), null, false);
    private Instrumentation.ActivityMonitor monitorTren = getInstrumentation().addMonitor(AdaugaTren.class.getName(), null, false);

    @Before
    public void setUp() throws Exception {
        meniuAdmin = meniuAdminActivityTestRule.getActivity();
    }

    @Test
    public void testLaunchAddVagoane(){
        assertNotNull(meniuAdmin.findViewById(R.id.admin_adauga_vagon));
        onView(withId(R.id.admin_adauga_vagon)).perform(click());

        Activity addVagon = getInstrumentation().waitForMonitorWithTimeout(monitorVag, 5000);

        assertNotNull(addVagon);
        addVagon.finish();

    }

    @Test
    public void testLaunchAddTren(){
        assertNotNull(meniuAdmin.findViewById(R.id.admin_adauga_tren));
        onView(withId(R.id.admin_adauga_tren)).perform(click());

        Activity addTren = getInstrumentation().waitForMonitorWithTimeout(monitorTren, 5000);

        assertNotNull(addTren);
        addTren.finish();
    }

    @After
    public void tearDown() throws Exception {
        meniuAdmin = null;
    }
}