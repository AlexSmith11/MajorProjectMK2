package mp.alex.majorprojectmk2.ui.planetsubadapter;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import mp.alex.majorprojectmk2.R;
import mp.alex.majorprojectmk2.database.PlanetViewModel;
import mp.alex.majorprojectmk2.database.entities.PlanetEntity;

public class PlanetSub extends AppCompatActivity {

    private List<PlanetEntity> planets;
    private PlanetViewModel mPlanetViewModel;

    public static final String PLANET_DATA_KEY = "planet_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet_sub);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final PlanetSubAdapter adapter = new PlanetSubAdapter(this);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mPlanetViewModel = ViewModelProviders.of(this).get(PlanetViewModel.class);

        /*
        //Setup recycler view list. Setup getSinglePlanet from db
        mItineraryViewModel.getAllItineraries().observe(this, new Observer<List<ItineraryListEntity>>() {
            @Override
            public void onChanged(@Nullable final List<ItineraryListEntity> itineraryListEntities) {
                //Update cached ver of Itineraries in adapter
                itineraryLists = itineraryListEntities;
                adapter.setItineraries(itineraryLists);
            }
        });
        */

        Bundle extras = getIntent().getExtras();
        ArrayList<PlanetEntity> planets = new ArrayList<>();
        if (extras != null && extras.containsKey(PLANET_DATA_KEY)) {
            planets = extras.getParcelableArrayList(PLANET_DATA_KEY);
        }
        adapter.setPlanetNameCalc(planets);



    }
}
