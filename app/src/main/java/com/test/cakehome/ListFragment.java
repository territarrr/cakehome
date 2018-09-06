package com.test.cakehome;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sovochka on 05.09.2018.
 */

public class ListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    private DataAdapter adapter;
    MainActivity activity;
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity.getSupportActionBar().setTitle(R.string.app_name);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        View view = inflater.inflate(R.layout.list, null);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(
                getResources().getColor(R.color.deepestred),
                getResources().getColor(R.color.deeperred),
                getResources().getColor(R.color.red),
                getResources().getColor(R.color.lightred));

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter = new DataAdapter();
        adapter.setListener(new DataAdapter.OnClickListener() {
            @Override
            public void onItemClick(Cake cake) {
                ItemFragment irv = new ItemFragment();
                Bundle bundle = new Bundle();
                bundle.putString("cakeName", cake.getName());
                bundle.putString("cakeDesc", cake.getDesc());
                bundle.putString("cakeRev", cake.getReview());
                bundle.putInt("cakeImg", cake.getImage());
                irv.setArguments(bundle);
                android.app.FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.frgmCont, irv).commit();
            }
        });
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setInitialData();
    }

    private void setInitialData() {
        List<IItem> cakes = new ArrayList<>();
        String[] cakeName = {"ЙОГУРТОВЫЙ", "ШВЕДСКИЙ ПАЙ", "НАПОЛЕОН", "ДАМСКИЕ ПАЛЬЧИКИ"};
        String[] cakeDesc = {"Любимый с детства песочный торт с вареным сгущенным молоком",
                "Ароматная вишня с коньячными нотками и шоколадный бисквит",
                "Классический торт на основе рубленого теста с заварным сливочным кремом",
                "Миндальные коржи, сливочно-ванильный крем и глазурь из бельгийского шоколада"};
        int[] cakeImg = {R.drawable.cake1, R.drawable.cake2, R.drawable.cake3, R.drawable.cake4};
        String[] cakeRew = {"Дегустировали разные тортики, в основном с брусникой. В итоге на праздник заказали Сибирское чудо и не пожалели!",
                "Мой самый любимый торт из ваших! Скоро у меня день рождения – побалую себя",
                "Он и вправду нежнейший. С приятной кислинкой.", "Любимый тортик нашей семьи!"};
        for (int i = 0; i < 15; i++) {
            //cakes.add(new Cake(cakeName[i], cakeDesc[i], cakeImg[i], cakeRew[i]));
            cakes.add(new Cake(cakeName[(int) (Math.random() * (cakeName.length - 1))],
                cakeDesc[(int) (Math.random() * (cakeDesc.length - 1))],
                cakeImg[(int) (Math.random() * (cakeImg.length - 1))],
                cakeRew[(int) (Math.random() * (cakeRew.length - 1))]));
            if ((i + 1) % 3 == 0) {
                cakes.add(new Adv());
            }
        }
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
        adapter.setData(cakes);
    }

    @Override
    public void onRefresh() {
        setInitialData();
    }
}
