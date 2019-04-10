package mp.alex.majorprojectmk2.ui;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import mp.alex.majorprojectmk2.R;

public class ItineraryMain extends AppCompatActivity {

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerary_main);

        //FAB
        FloatingActionButton fabItinMain = findViewById(R.id.fabItinMain);  //Creates fab, fabMenu is the button
        fabItinMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ItineraryMain.this, ItinerarySub.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });
    }
}
