package com.iot.android.lesson10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.PriorityQueue;

/**
 * 自定义的ListView Adapter
 * @author Nanrong Zeng
 * @version 1.0
 */
public class BookLvAdapter extends BaseAdapter {
    private Context context = null;
    private List<Book> list = null;

    public BookLvAdapter(Context context, List<Book> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout.result, parent, false);
            holder = new ViewHolder();
            holder.name = convertView.findViewById(R.id.bookName);
            holder.author = convertView.findViewById(R.id.bookAuthor);
            holder.press = convertView.findViewById(R.id.bookPress);
            holder.price = convertView.findViewById(R.id.bookPrice);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Book book = list.get(position);
        holder.name.setText(book.getName());
        holder.author.setText(book.getAuthor());
        holder.press.setText(book.getPress());
        holder.price.setText("" + book.getPrice());

        return convertView;
    }

    private class ViewHolder {
        public TextView name = null;
        public TextView author = null;
        public TextView press = null;
        public TextView price = null;
    }
}
