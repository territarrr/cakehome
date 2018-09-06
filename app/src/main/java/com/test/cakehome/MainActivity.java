package com.test.cakehome;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    ListFragment list;
    android.app.FragmentTransaction fTrans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ListFragment();
        fTrans = getFragmentManager().beginTransaction();
        fTrans.add(R.id.frgmCont, list);
        fTrans.commit();
    }

}
