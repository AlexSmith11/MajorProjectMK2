package mp.alex.majorprojectmk2.ui.planetadapter;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import mp.alex.majorprojectmk2.R;
import mp.alex.majorprojectmk2.database.ItineraryViewModel;
import mp.alex.majorprojectmk2.database.PlanetItineraryViewModel;
import mp.alex.majorprojectmk2.database.PlanetViewModel;
import mp.alex.majorprojectmk2.database.dao.DAOPlanets;
import mp.alex.majorprojectmk2.database.entities.ItineraryListEntity;
import mp.alex.majorprojectmk2.database.entities.PlanetEntity;
import mp.alex.majorprojectmk2.database.entities.PlanetItinerary;
import mp.alex.majorprojectmk2.ui.SearchNew;

/**
 * Displays All Current Itineraries in RecyclerView
 *
 * This activity just displays the search results sent from SearchNew activity.
 * This allows us to receive and display any results sent, regardless of search filters/parameters.
 *
 * This clas handles the onclick
 */
public class SearchResult extends AppCompatActivity {
    public static final String PLANET_RESULT_KEY = "planet_result";

    private List<ItineraryListEntity> itineraries;
    private String[] itinerariesDialogTypes;

    private PlanetItineraryViewModel planetItineraryViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        final PlanetAdapter adapter = new PlanetAdapter(this);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));  //put physical divider lines in the recycler view
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Receive parcelable
        Bundle extras = getIntent().getExtras();
        ArrayList<PlanetEntity> planets = new ArrayList<>();
        if (extras != null && extras.containsKey(PLANET_RESULT_KEY)) {
            planets = extras.getParcelableArrayList(PLANET_RESULT_KEY);
        }
        adapter.setPlanetNameCalc(planets);

        if (planets.size() == 0) {
            Toast.makeText(getApplicationContext(), "No planets exist within set distance",
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Planets found: " + planets.size(),
                    Toast.LENGTH_SHORT).show();
        }

        planetItineraryViewModel = ViewModelProviders.of(this).get(PlanetItineraryViewModel.class);

        adapter.setClickListener(new PlanetAdapter.OnClickListener() {
            @Override
            public void onItemClick(final int position, View v) {
                // Check for Itineraries
                if (itineraries == null || itineraries.size() == 0) {
                    Toast.makeText(getApplicationContext(), "No Itineraries",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                final List<PlanetEntity> planets = adapter.getPlanetEntity();

                AlertDialog.Builder b = new AlertDialog.Builder(SearchResult.this);
                b.setTitle("Itineraries:");

                b.setItems(itinerariesDialogTypes, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        int planetId = planets.get(position).id;
                        int itineraryId = itineraries.get(which).id;
                        PlanetItinerary newLink = new PlanetItinerary(itineraryId, planetId);

                        // Use Planet Itinerary View Model to insert.
                        planetItineraryViewModel.insert(newLink);

                        // Insert Planet Itinerary then close.
                        Toast.makeText(getApplicationContext(), "Added to Itinerary: " + itineraries.get(which).getItineraryListName(),
                                Toast.LENGTH_LONG).show();
                    }

                });
                b.show();
            }
        });

        // Like below, get the PlanetItinerary View Model save as attribute.
        ItineraryViewModel itineraryViewModel = ViewModelProviders.of(this).get(ItineraryViewModel.class);
        LiveData<List<ItineraryListEntity>> result = itineraryViewModel.getAllItineraries();
        result.observe(this, new Observer<List<ItineraryListEntity>>() {
            @Override
            public void onChanged(@Nullable List<ItineraryListEntity> itineraryListEntities) {
                if (itineraryListEntities == null) {
                    // THROW SOME KIND OF ERROR.
                    return;
                }
                itineraries = itineraryListEntities;
                itinerariesDialogTypes = new String[itineraries.size()];
                for (int i = 0; i < itineraries.size(); i++) {
                    itinerariesDialogTypes[i] = itineraries.get(i).getItineraryListName();
                }
            }
        });

        // Set Up On Click Listener for Each Recycler View View Holder

        // When Clicked, get planetId from db and Display Dialog Box with Spinner which contains all Itineraries.

        // When the User presses ok, the selected itinerary in the spinner is combined with selected planet id, then this is inserted to the PlanetItinerary Table.
    }
}
