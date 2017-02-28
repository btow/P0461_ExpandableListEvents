package com.example.samsung.p0461_expandablelistevents;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by samsung on 28.02.2017.
 */

public class AdapterHelper {

    final String ATTR_GROUP_NAME = "groupName",
                 ATTR_PHONE_NAME = "phoneName";

    //коллекция для групп
    ArrayList<Map<String, String>> groupData,
    //коллекция для элементов одной группы
    childDataItem;
    //общая коллекция для коллекций элементов
    ArrayList<ArrayList<Map<String, String>>> childData;
    //список атрибутов группы или элемента
    Map<String, String> map;

    Context context;

    public AdapterHelper (Context context) {
        this.context = context;
    }

    SimpleExpandableListAdapter adapter;

    SimpleExpandableListAdapter getAdapter () {

        //список атрибутов групп для чтения
        String[] groupFrom = new String[] {ATTR_GROUP_NAME};
        //список ID wiev-элементов, в которые будут помещены атриуты групп
        int[] groupTo = new int[] {android.R.id.text1};

        //создаём коллекцию для коллекций элементов
        childData = new ArrayList<ArrayList<Map<String,String>>>();

        //заполняем коллекцию групп из массива групп
        groupData = new ArrayList<Map<String, String>>();
        for (CharSequence group : context.getResources().getTextArray(R.array.groups)) {
            //заполняем список атрибутов для каждой группы
            map = new HashMap<String, String>();
            map.put(ATTR_GROUP_NAME, String.valueOf(group));
            groupData.add(map);
        }

        //список атрибутов элементов для чтения
        String[] childFrom = new String[] {ATTR_PHONE_NAME};
        //список ID wiev-элементов, в которые будут помещены атриуты элементов
        int[] childTo = new int[] {android.R.id.text1};

        //создаём коллекцию элементов для первой группы
        childDataItem = new ArrayList<Map<String, String>>();
        //заполняем список атрибутов для каждого элемента
        for (CharSequence phone : context.getResources().getTextArray(R.array.phonesHTC)) {
            map = new HashMap<String, String>();
            map.put(ATTR_PHONE_NAME, String.valueOf(phone)); //название телефона
            childDataItem.add(map);
        }
        //добавляем в коллекцию коллекций
        childData.add(childDataItem);

        //очищаем коллекцию элементов для второй группы
        childDataItem.clear();
        //заполняем список атрибутов для каждого элемента
        for (CharSequence phone : context.getResources().getTextArray(R.array.phonesSams)) {
            map = new HashMap<String, String>();
            map.put(ATTR_PHONE_NAME, String.valueOf(phone)); //название телефона
            childDataItem.add(map);
        }
        //добавляем в коллекцию коллекций
        childData.add(childDataItem);

        //очищаем коллекцию элементов для третей группы
        childDataItem.clear();
        //заполняем список атрибутов для каждого элемента
        for (CharSequence phone : context.getResources().getTextArray(R.array.phonesLG)) {
            map = new HashMap<String, String>();
            map.put(ATTR_PHONE_NAME, String.valueOf(phone)); //название телефона
            childDataItem.add(map);
        }
        //добавляем в коллекцию коллекций
        childData.add(childDataItem);

        adapter = new SimpleExpandableListAdapter(
                context, groupData, android.R.layout.simple_expandable_list_item_1, groupFrom, groupTo,
                childData, android.R.layout.simple_list_item_1, childFrom, childTo);

        return adapter;
    }

    String getGroupText (int groupPos) {
        return ((Map<String, String>)(adapter.getGroup(groupPos))).get(ATTR_GROUP_NAME);
    }

    String getChildText (int groupPos, int childPos) {
        return ((Map<String, String>)(adapter.getChild(groupPos, childPos))).get(ATTR_PHONE_NAME);
    }

    String getGroupChildText (int groupPos, int childPos) {
        return getGroupText(groupPos) + " " + getChildText(groupPos, childPos);
    }
}
