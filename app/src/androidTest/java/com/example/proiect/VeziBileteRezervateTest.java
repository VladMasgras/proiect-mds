package com.example.proiect;

import android.view.View;
import android.widget.Adapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class VeziBileteRezervateTest {

    @Rule
    public ActivityTestRule<VeziBileteRezervate> vBileteActivityTestRule = new ActivityTestRule<VeziBileteRezervate>(VeziBileteRezervate.class);

    private VeziBileteRezervate vBileteActivity = null;

    @Before
    public void setUp() throws Exception {
        vBileteActivity = vBileteActivityTestRule.getActivity();
    }

    @Test
    public void testList(){

        ListView list = vBileteActivity.findViewById(R.id.listviewBilete);
        assertNotNull(list);
    }

    @After
    public void tearDown() throws Exception {
        vBileteActivity = null;
    }
}