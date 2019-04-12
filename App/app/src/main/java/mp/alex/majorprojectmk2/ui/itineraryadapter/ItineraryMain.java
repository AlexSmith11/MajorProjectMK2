package mp.alex.majorprojectmk2.ui.itineraryadapter;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import mp.alex.majorprojectmk2.R;
import mp.alex.majorprojectmk2.database.ItineraryViewModel;
import mp.alex.majorprojectmk2.database.entities.ItineraryListEntity;
import mp.alex.majorprojectmk2.ui.ItineraryCreate;

public class ItineraryMain extends AppCompatActivity {

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE =1;

    private ItineraryViewModel mItineraryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerary_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ItineraryMainAdapter adapter = new ItineraryMainAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        /*
        UI talks only to ViewModels. These persist when activity is destroyed so can keep info
        For whenever someone returns.
        */
        mItineraryViewModel = ViewModelProviders.of(this).get(ItineraryViewModel.class);
        mItineraryViewModel.getAllItineraries().observe(this, new Observer<List<ItineraryListEntity>>() {
            @Override
            public void onChanged(@Nullable final List<ItineraryListEntity> itineraryListEntities) {
                //Update cached ver of Itineraries in adapter
                adapter.setItineraries(itineraryListEntities);
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
}
