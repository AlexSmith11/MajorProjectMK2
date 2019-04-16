package mp.alex.majorprojectmk2.ui.planetadapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import mp.alex.majorprojectmk2.R;
import mp.alex.majorprojectmk2.ui.SearchNew;

/**
 * Displays All Current Itineraries in RecyclerView
 *
 */
public class SearchResult extends AppCompatActivity {

    private static final String TAG = "SearchResult";

    TextView testGetLeaveDate, testGetArriveDate;
    String StringTestGetLeaveDate, StringTestGetArriveDate;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        SearchNew mySearch = new SearchNew();       //Create SearchNew obj to get date info

        //Test Leave
        testGetLeaveDate = (TextView) findViewById(R.id.testGetLeaveDate);

        StringTestGetLeaveDate = mySearch.getLeaveSeconds();
        testGetLeaveDate.setText(StringTestGetLeaveDate);

        //Test Arrive
        testGetArriveDate = (TextView) findViewById(R.id.testGetArriveDate);

        StringTestGetArriveDate = mySearch.getArrivalSeconds();
        testGetArriveDate.setText(StringTestGetArriveDate);

        Log.v("SearchResult","leave value: " + StringTestGetLeaveDate);

    }

}
