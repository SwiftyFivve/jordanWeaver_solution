package com.jordanweaver.jordanweaver_solution;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by jordanweaver on 1/20/16.
 */
public class DetailsActivity extends Activity {

    public static String Text_Extra_Title = "com.jweaver.android.Text_Extra_Title";
    public static String Text_Extra_Content = "com.jweaver.android.Text_Extra_Content";
    public static String Text_Extra_Date = "com.jweaver.android.Text_Extra_Date";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);

        if(savedInstanceState == null){
            getFragmentManager().beginTransaction().replace(R.id.detailsContainer,
                    DetailsFragment.newInstance(), DetailsFragment.TAG).commit();
        }
    }
}
