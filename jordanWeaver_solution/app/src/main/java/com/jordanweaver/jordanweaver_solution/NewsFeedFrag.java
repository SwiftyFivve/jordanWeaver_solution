package com.jordanweaver.jordanweaver_solution;

import android.app.Activity;
import android.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by jordanweaver on 1/20/16.
 */
public class NewsFeedFrag extends ListFragment implements Network.DisplayData {

    public static final String TAG = "NewsFeedFrag.TAG";

    public static NewsFeedFrag newIntance(){
        NewsFeedFrag newsFeedFrag = new NewsFeedFrag();
        return newsFeedFrag;
    }

    public interface UpdateView{
        public void changeFrag(String view, int postion);
    }

    public UpdateView mUpdate;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof UpdateView){
            mUpdate = (UpdateView)activity;
        } else {
            throw new IllegalArgumentException("Activity must implement the UpdateView interface");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        mUpdate.changeFrag("DetailsView", position);
    }

    @Override
    public void onResume() {
        super.onResume();

        Network connectNetwork = new Network(getActivity(), this);
        connectNetwork.execute("http://www.washingtonpost.com/wp-srv/simulation/simulation_test.json");
    }


    @Override
    public void updateArray(final ArrayList<ApiObject> news) {

        final ArrayList<String> newsTitle = new ArrayList<>();


        FileManager helper = new FileManager();
        ArrayList<ApiObject> feedArray = helper.loadData("SavedFeed.txt", getActivity());

        for(int i=0; i<news.size(); i++){

            if(feedArray.size() == 0){
                feedArray.add(0, news.get(i));
            } else {
                feedArray.add(feedArray.size(), news.get(i));
                Log.e("Saved Array", feedArray+"");
            }

            if(newsTitle.size() == 0){
                newsTitle.add(0, news.get(i).title);
            } else {
                newsTitle.add(newsTitle.size(), news.get(i).title);
            }

        }

        helper.saveObject(feedArray, "SavedFeed.txt", getActivity());

        ArrayAdapter<String> newAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_2, android.R.id.text1, newsTitle){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(news.get(position).title);
                text2.setText(news.get(position).date);
                return view;
            }
        };

        setListAdapter(newAdapter);
    }
}
