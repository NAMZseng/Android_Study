package com.iot.lesson7_registerdemo;

import android.app.ExpandableListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ChooseCityActivity extends ExpandableListActivity {
    private String[] provinces = new String[]{"山西", "湖南", "广东"};
    private String[][] cities = new String[][]{
            {"太原", "大同", "榆次", "平遥"},
            {"衡阳", "长沙", "常德", "岳阳"},
            {"东莞", "广州", "长安", "潮汕"}
    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 内部类访问只能访问final修饰的外部成员变量
        final ExpandableListAdapter adapter = new BaseExpandableListAdapter() {
            @Override
            //获取指定组位置的指定子列表项数据
            public Object getChild(int groupPosition, int childPosition) {
                return cities[groupPosition][childPosition];
            }
            @Override
            public long getChildId(int groupPosition, int childPosition) {
                return childPosition;
            }
            @Override
            //获取指定组的列表项数，即各省份的城市数
            public int getChildrenCount(int groupPosition) {
                return cities[groupPosition].length;
            }
            @Override
            //获取指定组位置处的组数据
            public Object getGroup(int groupPosition) {
                return provinces[groupPosition];
            }
            @Override
            //获取该扩展列表的组数，即省份数
            public int getGroupCount() {
                return provinces.length;
            }
            @Override
            //获取组的ID号，即省份的ID号
            public long getGroupId(int groupPosition) {
                return groupPosition;
            }
            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition) {
                return true;
            }
            @Override
            public boolean hasStableIds() {
                return true;
            }

            private TextView getTextView() {

                // 创建布局实例，设置TextView矩形框的大小
                AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, 148);

                TextView textView = new TextView(ChooseCityActivity.this);
                textView.setLayoutParams(lp);
                // 设置文字在TextView矩形框中的位置(居中/靠左/靠右/靠上/靠下)
                textView.setGravity( Gravity.CENTER_HORIZONTAL);
                //文字里TextView矩形框的上下左右间距
                textView.setPadding(0, 0, 0, 0);
                textView.setTextSize(36);
                return textView;
            }

            @Override
            //该方法决定每个子选项的外观
            public View getChildView(int groupPosition, int childPosition,
                                     boolean isLastChild, View convertView, ViewGroup parent) {
                TextView textView = getTextView();
                textView.setText(getChild(groupPosition, childPosition).toString());
                return textView;
            }

            @Override
            // 该方法决定每个组选项的外观
            public View getGroupView(int groupPosition, boolean isExpanded,
                                     View convertView, ViewGroup parent) {
                // 创建线性布局，并且加入到底层容器Activity中
                LinearLayout ll = new LinearLayout(ChooseCityActivity.this);
                ll.setOrientation(LinearLayout.VERTICAL);
                // 添加下拉三角图表
                ImageView logo = new ImageView(ChooseCityActivity.this);
                ll.addView(logo);
                TextView textView = getTextView();
                textView.setText(getGroup(groupPosition).toString());
                ll.addView(textView);
                return ll;
            }

        };

        // 设置该窗口显示列表
        setListAdapter(adapter);

        // 设置子节点的事件监听，即当点击城市时该执行的操作
        this.getExpandableListView().setOnChildClickListener(
                new ExpandableListView.OnChildClickListener() {
                    @Override
                    public boolean onChildClick(ExpandableListView parent, View source,
                                                int groupPosition, int childPosition, long id) {
                        // 获取启动该Activity之前的Activity对应的Intent
                        Intent intent = getIntent();
                        // 创建Bundle(key-value对，final类）对象,存储需要传递的数据
                        Bundle data = new Bundle();
                        // 设置键值key为city
                        data.putString("city", adapter.getChild(groupPosition,childPosition).toString());
                        // 将数据通过intent传递到主Activity
                        intent.putExtras(data);
                        // 设置该SelectActivity的结果码，并设置结束之后退回的Activity
                        ChooseCityActivity.this.setResult(0, intent);
                        // 结束SelectCityActivity,返回到主界面
                        ChooseCityActivity.this.finish();
                        return false;
                    }
                });
    }
}