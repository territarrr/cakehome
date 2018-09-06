package com.test.cakehome;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.*;

/**
 * Created by Sovochka on 05.09.2018.
 */


public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private final List<IItem> cakes = new ArrayList<>();

    private OnClickListener listener;

    DataAdapter() {
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
                break;
            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adv, parent, false);
                break;
        }
        return new ViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return cakes.get(position).getViewType();
    }

    public void setData(List<IItem> cakes) {
        this.cakes.clear();
        this.cakes.addAll(cakes);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final DataAdapter.ViewHolder holder, final int position) {
        final IItem item = cakes.get(position);
        if(item instanceof Cake) {
            final Cake cake = (Cake) item;
            holder.imageView.setImageResource(cake.getImage());
            holder.nameView.setText(cake.getName());
            holder.descView.setText(cake.getDesc());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(cake);
                }
            });
        }
    }


    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }

    public interface OnClickListener {
        void onItemClick(Cake cake);
    }

    @Override
    public int getItemCount() {
        return cakes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageView;
        final TextView nameView, descView;

        ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.image);
            nameView = (TextView) view.findViewById(R.id.name);
            descView = (TextView) view.findViewById(R.id.desc);
        }
    }
}