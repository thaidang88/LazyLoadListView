package com.thaidang.lazyloadlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listview_links;
    private int index=0;
    private boolean flag_loading=false;
    private List<String> list_links_display;
    private ArrayAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        index=0;
        list_links_display= new ArrayList<String>();
        for(int i=0;i<10;i++)
        {
            list_links_display.add("Item :"+String.valueOf(i));
        }
        myAdapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, list_links_display);
        listview_links = (ListView) findViewById(R.id.lv);
        listview_links.setAdapter(myAdapter);

        listview_links.setOnScrollListener(new AbsListView.OnScrollListener() {

            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {

                if(firstVisibleItem+visibleItemCount == totalItemCount && totalItemCount!=0)
                {
                    if(flag_loading == false)
                    {
                        flag_loading = true;
                        additems();
                    }
                }
            }
        });

    }

    private void additems()
    {
        index=index+10;
        for(int i=index;i<index+10;i++)
        {
            list_links_display.add("Item :"+String.valueOf(i));
        }

        myAdapter.notifyDataSetChanged();

        flag_loading=false;
    }

}
