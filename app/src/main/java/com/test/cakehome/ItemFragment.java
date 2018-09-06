package com.test.cakehome;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.MenuItem;

/**
 * Created by Sovochka on 05.09.2018.
 */

public class ItemFragment extends Fragment {
    ImageView imageView;
    TextView revView, descView;
    MainActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.detail, null);
        imageView = (ImageView)view.findViewById(R.id.imageDetail);
        revView = (TextView) view.findViewById(R.id.revDetail);
        descView = (TextView) view.findViewById(R.id.descDetail);
        Bundle bundle = getArguments();
        if (bundle != null) {
            int img = bundle.getInt("cakeImg");
            String name = bundle.getString("cakeName");
            String desc = bundle.getString("cakeDesc");
            String rev = bundle.getString("cakeRev");
            imageView.setImageResource(img);
            descView.setText(desc);
            revView.setText(rev);
            activity.getSupportActionBar().setTitle(name);
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            activity.getSupportActionBar().setHomeButtonEnabled(true);
        }
        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                activity.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
