package mp.alex.majorprojectmk2;

import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
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

    private Button buttonItineraryMain, buttonSearchNew, buttonDeleteItineraries;
    private ItineraryViewModel mItineraryViewModel;

    /**
     * TO DO:
     * get method names don't line up (i.e. getItineraries, getAllItineraries, etc)
     *
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mItineraryViewModel = ViewModelProviders.of(this).get(ItineraryViewModel.class);

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

        //Delete Itineraries Button
        buttonDeleteItineraries = (Button) findViewById(R.id.buttonDeleteAllItineraries);
        buttonDeleteItineraries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupDelItin();
            }
        });
    }

    /**
     * If the user wishes to delete the Itineraries, prompt them to make sure when button tapped.
     */
    private void popupDelItin() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        mItineraryViewModel.deleteAllItineraries();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to delete all of your Itineraries?")
                .setPositiveButton("Delete", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();

    }

    //region Open Activities
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
    //endregion
}

