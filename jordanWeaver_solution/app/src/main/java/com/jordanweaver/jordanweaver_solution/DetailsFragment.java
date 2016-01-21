package com.jordanweaver.jordanweaver_solution;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by jordanweaver on 1/20/16.
 */
public class DetailsFragment extends Fragment{

    public static final String TAG = "DetailsFragment.TAG";

    public static  DetailsFragment newInstance(){
        DetailsFragment detailsFragment = new DetailsFragment();
        return detailsFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.details_fragment, container, false);

        Intent startingIntent = getActivity().getIntent();

        TextView titleText = (TextView) view.findViewById(R.id.titleText);
        TextView contentText = (TextView) view.findViewById(R.id.contentText);
        TextView dateText = (TextView) view.findViewById(R.id.dateText);

        if(startingIntent.hasExtra(DetailsActivity.Text_Extra_Content) &&
                startingIntent.hasExtra(DetailsActivity.Text_Extra_Title) &&
                startingIntent.hasExtra(DetailsActivity.Text_Extra_Date)){
            titleText.setText(startingIntent.getStringExtra(DetailsActivity.Text_Extra_Title));
            contentText.setText(startingIntent.getStringExtra(DetailsActivity.Text_Extra_Content));
            dateText.setText(startingIntent.getStringExtra(DetailsActivity.Text_Extra_Date));
        }

        return view;
    }
}
