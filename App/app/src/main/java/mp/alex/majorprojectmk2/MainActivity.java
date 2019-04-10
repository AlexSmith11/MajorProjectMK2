package mp.alex.majorprojectmk2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import mp.alex.majorprojectmk2.ui.adapters.ItineraryMain;

public class MainActivity extends AppCompatActivity {

    private Button buttonItineraryMain;

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

        buttonItineraryMain = (Button) findViewById(R.id.buttonItineraryMain);
        buttonItineraryMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openItineraryListMain();
            }
        });
    }


    /*
     * Methods that open pages when button pressed
     */
    //Opens ItineraryMain
    private void openItineraryListMain() {
        Intent intent = new Intent(this, ItineraryMain.class);
        startActivity(intent);
    }
}

