package com.jordanweaver.jordanweaver_solution;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends Activity implements NewsFeedFrag.UpdateView{

    NewsFeedFrag newsFeedFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            newsFeedFrag = NewsFeedFrag.newIntance();
            getFragmentManager().beginTransaction().replace(R.id.feedContainer, newsFeedFrag,
                    NewsFeedFrag.TAG).commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_alphabet) {
            sortData(true);


        } else if (id == R.id.action_date) {
            sortData(false);

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void changeFrag(String view, int postion) {
        if(view.equals("DetailsView")){

            ApiObject selectedObject = null;
            ArrayList<ApiObject> selectedArray = new ArrayList<>();
            FileManager helper = new FileManager();

            ArrayList<ApiObject> loadedNews = helper.loadData("SavedFeed.txt", this);
            selectedObject = loadedNews.get(postion);

            if(selectedObject != null){

                Intent intent = new Intent(this, DetailsActivity.class);
                intent.putExtra(DetailsActivity.Text_Extra_Title, selectedObject.title);
                intent.putExtra(DetailsActivity.Text_Extra_Content, selectedObject.content);
                intent.putExtra(DetailsActivity.Text_Extra_Date, selectedObject.date);
                startActivity(intent);
            }
        }

    }


    public void sortData(Boolean sortType){

        FileManager helper = new FileManager();
        final ArrayList<ApiObject> feedArray = helper.loadData("SavedFeed.txt", this);

        if(sortType){

            Collections.sort(feedArray, new Comparator<ApiObject>() {
                @Override
                public int compare(ApiObject news1, ApiObject news2) {
                    return news1.getTitle().compareToIgnoreCase(news2.getTitle());
                }
            });

        } else {

            Collections.sort(feedArray, new Comparator<ApiObject>() {
                @Override
                public int compare(ApiObject date1, ApiObject date2) {
                    return date1.getDate().compareToIgnoreCase(date2.getDate());
                }
            });
        }
//
//        ArrayList<String> newsTitle = new ArrayList<>();
//
//        if(newsTitle.size() == 0){
//            newsTitle.add(0, news.get(i).title);
//        } else {
//            newsTitle.add(newsTitle.size(), news.get(i).title);
//        }
//
//        ArrayAdapter<ApiObject> newAdapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_2, android.R.id.text1, feedArray){
//            @Override
//            public View getView(int position, View convertView, ViewGroup parent) {
//                View view = super.getView(position, convertView, parent);
//                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
//                TextView text2 = (TextView) view.findViewById(android.R.id.text2);
//
//                text1.setText(feedArray.get(position).title);
//                text2.setText(feedArray.get(position).date);
//                return view;
//            }
//        };
//
//        newsFeedFrag.setListAdapter(arrayAdapter);

    }
}
