package mp.alex.majorprojectmk2.ui.planitinadapter;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mp.alex.majorprojectmk2.R;
import mp.alex.majorprojectmk2.database.PlanetItineraryViewModel;
import mp.alex.majorprojectmk2.database.PlanetViewModel;
import mp.alex.majorprojectmk2.database.entities.ItineraryListEntity;
import mp.alex.majorprojectmk2.database.entities.PlanetEntity;
import mp.alex.majorprojectmk2.database.entities.PlanetItinerary;
import mp.alex.majorprojectmk2.ui.itineraryadapter.ItineraryMainAdapter;
import mp.alex.majorprojectmk2.ui.planetsubadapter.PlanetSub;

/**
 * Class which will be populated with certain itineraries. Only need one of these. (parcelable)
 *
 * Have recycler list populated with data from table 3 based off a search query i.e:
 * all itineraries with id = 4 (itinerary 4). Query sent when itinerary tapped in ItineraryMain.
 *
 * Query we want to call: getPlanetsForItineraries
 *
 * Want to add onclicklistener to go to 'Planets' page for more details on the planet
 */

public class ItinerarySub extends AppCompatActivity {
    public static final String ITINERARY_KEY = "itinerary";

    private List<PlanetEntity> planets;
    private List<ItineraryListEntity> itineraries;
    private String[] planetDialogTypes;

    private PlanetItineraryViewModel planetItineraryViewModel;
    private PlanetViewModel planetViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerary_sub);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ItinerarySubAdapter adapter = new ItinerarySubAdapter(this);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Get array list of PlanetItinEntities from parcel
        Bundle extras = getIntent().getExtras();
        ArrayList<PlanetEntity> planItin = new ArrayList<>();
        if (extras != null && extras.containsKey(ITINERARY_KEY)) {
            planItin = extras.getParcelableArrayList(ITINERARY_KEY);
        }
        adapter.setPlanetNameCalc(planItin);

        planetItineraryViewModel = ViewModelProviders.of(this).get(PlanetItineraryViewModel.class);

        adapter.setClickListener(new ItinerarySubAdapter.OnClickListener() {
            @Override
            public void onItemClick(final int position, View v) {
                final List<PlanetEntity> planItin = adapter.getPlanetEntity();


            }
        });
    }

    public void startPlanetSub(final boolean displayData) {
        LiveData<List<PlanetEntity>> planets = planetViewModel.getAllPlanets();
        planets.observe(this, new Observer<List<PlanetEntity>>() {
            @Override
            public void onChanged(@Nullable List<PlanetEntity> planetEntities) {
                if (planetEntities == null) {
                    return;
                }
                Log.i("TEST", planetEntities.toString());

                if (displayData) {
                    openPlanetSub(planetEntities);
                    return;
                }
            }
        });
    }

    public void openPlanetSub(List<PlanetEntity> planetEntities) {
        Intent intent = new Intent(this, PlanetSub.class);

        Bundle extras = new Bundle();
        extras.putParcelableArrayList(PlanetSub.PLANET_DATA_KEY, new ArrayList<>(planetEntities));
        intent.putExtras(extras);

        startActivity(intent);
    }
}
