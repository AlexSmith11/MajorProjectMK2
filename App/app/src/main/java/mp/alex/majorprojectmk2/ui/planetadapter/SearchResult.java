package mp.alex.majorprojectmk2.ui.planetadapter;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.media.Image;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import mp.alex.majorprojectmk2.R;
import mp.alex.majorprojectmk2.database.PlanetViewModel;
import mp.alex.majorprojectmk2.database.dao.DAOPlanets;
import mp.alex.majorprojectmk2.database.entities.PlanetEntity;
import mp.alex.majorprojectmk2.ui.SearchNew;

/**
 * Displays All Current Itineraries in RecyclerView
 *
 * This activity just displays the search results sent from SearchNew activity.
 * This allows us to receive and display any results sent, regardless of search filters/parameters.
 */
public class SearchResult extends AppCompatActivity {
    public static final String PLANET_RESULT_KEY = "planet_result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final PlanetAdapter adapter = new PlanetAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey(PLANET_RESULT_KEY)) {
            ArrayList<PlanetEntity> planets = extras.getParcelableArrayList(PLANET_RESULT_KEY);
            adapter.setPlanetNameCalc(planets);
        }

    }
}
