package mp.alex.majorprojectmk2.ui.itineraryadapter;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mp.alex.majorprojectmk2.R;
import mp.alex.majorprojectmk2.database.ItineraryViewModel;
import mp.alex.majorprojectmk2.database.PlanetItineraryViewModel;
import mp.alex.majorprojectmk2.database.entities.ItineraryListEntity;
import mp.alex.majorprojectmk2.database.entities.PlanetEntity;
import mp.alex.majorprojectmk2.database.entities.PlanetItinerary;
import mp.alex.majorprojectmk2.ui.ItineraryCreate;
import mp.alex.majorprojectmk2.ui.planetadapter.SearchResult;
import mp.alex.majorprojectmk2.ui.planitinadapter.ItinerarySub;

/**
 * Displays All Current Itineraries in RecyclerView
 * Passes information from PlanetItineraryEntity to ItinerarySub which displays individual itineraries
 *
 * Display process for itineraries:
 * ItineraryListEntity -> DAOItineraries -> MyRepository -> ItineraryViewModel -> ItineraryMainAdapter ->
 * -> ItineraryMain -> ItinerarySub
 *
 * Display process for ItinerarySub:
 * PlanetItinerary -> DAOPlanetItinerary -> MyRepository ->
 *
 * How to assign planet to itinerary:
 * Call inner join in DAOPlanetItinerary
 *
 * make onclick listener (the same as how it is done with planetAdapter and SearchResult)
 * copy OnItemClick from SearchResult
 * send parcelable from here to ItinerarySub
 */
public class ItineraryMain extends AppCompatActivity {
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE =1;
    public static final String ITINERARY_KEY = "itinerary";

    private List<ItineraryListEntity> itineraryLists;

    private ItineraryViewModel mItineraryViewModel;
    private PlanetItineraryViewModel mPlanetItineraryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerary_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ItineraryMainAdapter adapter = new ItineraryMainAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mItineraryViewModel = ViewModelProviders.of(this).get(ItineraryViewModel.class);

        /*
        UI talks only to ViewModels. These persist when activity is destroyed so can keep info
        For whenever someone returns.
        */
        mItineraryViewModel.getAllItineraries().observe(this, new Observer<List<ItineraryListEntity>>() {
            @Override
            public void onChanged(@Nullable final List<ItineraryListEntity> itineraryListEntities) {
                //Update cached ver of Itineraries in adapter
                itineraryLists = itineraryListEntities;
                adapter.setItineraries(itineraryLists);
            }
        });


        mPlanetItineraryViewModel = ViewModelProviders.of(this).get(PlanetItineraryViewModel.class);
        //Setup click listener for any item in the list
        adapter.setClickListener(new ItineraryMainAdapter.OnClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                // Check Exists.
                ItineraryListEntity selected = itineraryLists.get(position);
                if (selected == null) {
                    return;
                }

                LiveData<List<PlanetEntity>> planets = mPlanetItineraryViewModel.getPlanetsForItineraries(selected.id);
                planets.observe(ItineraryMain.this, new Observer<List<PlanetEntity>>() {
                    @Override
                    public void onChanged(@Nullable List<PlanetEntity> planetEntities) {
                        // Check for Itineraries
                        if (planetEntities == null || planetEntities.size() == 0) {
                            Toast.makeText(getApplicationContext(), "No Planets are within this Itinerary",
                                    Toast.LENGTH_LONG).show();
                            return;
                        }

                        startItinerarySub(planetEntities);
                    }
                });
            }
        });

        //FAB
        FloatingActionButton fabItinMain = findViewById(R.id.fabItinMain);  //Creates fab, fabMenu is the button
        fabItinMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ItineraryMain.this, ItineraryCreate.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });

        // Add the functionality to swipe items in the
        // recycler view to delete that item
        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView,
                                          RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder,
                                         int direction) {
                        int position = viewHolder.getAdapterPosition();
                        ItineraryListEntity myItinerary = adapter.getItineraryAtPosition(position);
                        Toast.makeText(ItineraryMain.this, "Deleting " +
                                myItinerary.getItineraryListName(), Toast.LENGTH_LONG).show();

                        // Delete the word
                        mItineraryViewModel.deleteItinerary(myItinerary);
                    }
                });

        helper.attachToRecyclerView(recyclerView);


    }

    public void startItinerarySub(List<PlanetEntity> planetEntities) {
        Intent intent = new Intent(this, ItinerarySub.class);

        Bundle extras = new Bundle();
        extras.putParcelableArrayList(ItinerarySub.ITINERARY_KEY, new ArrayList<>(planetEntities));
        intent.putExtras(extras);

        startActivity(intent);
    }
}
