package mp.alex.majorprojectmk2.ui.itineraryadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import mp.alex.majorprojectmk2.R;

/**
 * Class which will be populated with certain itineraries. Only need one of these. (parcelable)
 *
 * Have recycler list populated with data from table 3 based off a search query i.e:
 * all itineraries with id = 4 (itinerary 4). Query sent when itinerary tapped in ItineraryMain.
 */

public class ItinerarySub extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerary_sub);
    }
}
