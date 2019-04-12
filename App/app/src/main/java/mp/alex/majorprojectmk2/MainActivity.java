package mp.alex.majorprojectmk2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import mp.alex.majorprojectmk2.ui.SearchNew;
import mp.alex.majorprojectmk2.ui.itineraryadapter.ItineraryMain;
import mp.alex.majorprojectmk2.database.ItineraryViewModel;

public class MainActivity extends AppCompatActivity {

    private Button buttonItineraryMain, buttonSearchNew;
    private ItineraryViewModel mItineraryViewModel;

    /**
     * TO DO:
     * get method names don't line up (i.e. getItineraries, getAllItineraries, etc)
     *
     *
     *
     *
     *
     *
     *
     *
     * @param savedInstanceState
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //New Search Activity Button
        buttonSearchNew = (Button) findViewById(R.id.buttonSearchNew);
        buttonSearchNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSearchNew();
            }
        });

        //Itinerary Activity Button
        buttonItineraryMain = (Button) findViewById(R.id.buttonItineraryMain);
        buttonItineraryMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openItineraryListMain();
            }
        });
    }

    /**
     * Delete all Itineraries
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.buttonDeleteAllItineraries) {
            Toast.makeText(this, "Deleting Itineraries",
                    Toast.LENGTH_SHORT).show();

            //Then delete data
            mItineraryViewModel.deleteAllItineraries();
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }


    /*
     * Methods that open pages when button pressed
     */
    //Opens ItineraryMain
    private void openItineraryListMain() {
        Intent intent = new Intent(this, ItineraryMain.class);
        startActivity(intent);
    }

    //Opens SearchNew
    private void openSearchNew() {
        Intent intent = new Intent(this, SearchNew.class);
        startActivity(intent);
    }
}

