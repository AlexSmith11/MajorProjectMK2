package mp.alex.majorprojectmk2.ui.planetadapter;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import mp.alex.majorprojectmk2.R;
import mp.alex.majorprojectmk2.database.PlanetViewModel;
import mp.alex.majorprojectmk2.database.dao.DAOPlanets;
import mp.alex.majorprojectmk2.database.entities.PlanetEntity;
import mp.alex.majorprojectmk2.ui.SearchNew;

/**
 * Displays All Current Itineraries in RecyclerView
 *
 */
public class SearchResult extends AppCompatActivity {

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE =1;

    private PlanetViewModel mPlanetViewModel;
    private double distance;

    private static final String TAG = "SearchResult";

    TextView testGetLeaveDate, testGetArriveDate;
    String StringTestGetLeaveDate, StringTestGetArriveDate;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final PlanetAdapter adapter = new PlanetAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        /*
        UI talks only to ViewModels. These persist when activity is destroyed so can keep info
        For whenever someone returns.
        */

        mPlanetViewModel = ViewModelProviders.of(this).get(PlanetViewModel.class);

        //For planet name
        mPlanetViewModel.getAllPlanets().observe(this, new Observer<List<PlanetEntity>>() {
            @Override
            public void onChanged(@Nullable final List<PlanetEntity> planetEntities) {
                //Update cached ver of Itineraries in adapter
                adapter.setPlanetNameCalc(planetEntities);
            }
        });

        //For planet name and calculation data
        mPlanetViewModel.getAllPlanetsLessThanDist(distance).observe(this, new Observer<List<PlanetEntity>>() {
            @Override
            public void onChanged(@Nullable final List<PlanetEntity> planetEntities) {
                //Update cached ver of Itineraries in adapter
                adapter.setPlanetNameCalc(planetEntities);
            }
        });
    }
}
