package com.iot.android.lesson13_recyclerviewdemo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nanrong Zeng
 * @version 1.0
 */
public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.MyViewHolder> {

    private List<Fruit> fruits = null;

    public FruitAdapter(List<Fruit> fruits) {
        this.fruits = fruits;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView fruit_image = null;
        private TextView fruit_name = null;

        // 显示初始化父类中带参数的构造方法
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            fruit_image = (ImageView) itemView.findViewById(R.id.fruit_Image);
            fruit_name = (TextView) itemView.findViewById(R.id.fruit_name);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.fruit_item, viewGroup, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Fruit fruit = fruits.get(i);
        myViewHolder.fruit_image.setImageResource(fruit.getImageId());
        myViewHolder.fruit_name.setText(fruit.getName());
    }

    @Override
    public int getItemCount() {
        return fruits.size();
    }
}
