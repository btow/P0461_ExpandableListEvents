package com.example.samsung.p0461_expandablelistevents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";

    ExpandableListView elvMain;
    TextView tvInfo;
    AdapterHelper adapterHelper;
    SimpleExpandableListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        tvInfo = (TextView) findViewById(R.id.tvInfo);

        //создаём адаптер
        adapterHelper = new AdapterHelper(this);
        adapter = adapterHelper.getAdapter();
        //присваиваем адаптер дереву элементов
        elvMain = (ExpandableListView) findViewById(R.id.elvMain);
        elvMain.setAdapter(adapter);

        //присваиваем дереву элементов обработчики
        //--- нажатия на элемент
        elvMain.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                String massage = "onChildClick groupPosition = " + groupPosition
                        + ", childPosition = " + childPosition + ", id = " + id;
                Log.d(LOG_TAG, massage);
                tvInfo.setText(adapterHelper.getGroupChildText(groupPosition, childPosition));
                return false;
            }
        });
        //--- нажатия на группу
        elvMain.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                String massage = "onGroupdClick groupPosition = " + groupPosition + ", id = " + id;
                Log.d(LOG_TAG, massage);
                //блокируем дальнейшую обработку группы с позицией 1
                if (groupPosition == 1) {
                    return true;
                }
                return false;
            }
        });
        //--- сворачивания группы
        elvMain.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                String massage = "onGroupCollapse groupPosition = " + groupPosition;
                Log.d(LOG_TAG, massage);
                tvInfo.setText("Свернули группу: " + adapterHelper.getGroupText(groupPosition));
            }
        });
        //--- разворачивания группы
        elvMain.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                String massage = "onGroupExpand groupPosition = " + groupPosition;
                Log.d(LOG_TAG, massage);
                tvInfo.setText("Развернули группу: " + adapterHelper.getGroupText(groupPosition));
            }
        });
        //разворачиваем группу с позицией 2
        elvMain.expandGroup(2);
    }
}
