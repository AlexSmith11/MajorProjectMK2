package mp.alex.majorprojectmk2.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import mp.alex.majorprojectmk2.R;
import mp.alex.majorprojectmk2.database.ItineraryViewModel;
import mp.alex.majorprojectmk2.database.entities.ItineraryListEntity;

/**
 * Create a new Itinerary.
 * Does not display any itinerary.
 */
public class ItineraryCreate extends AppCompatActivity {

    public static final String EXTRA_REPLY = "mp.alex.majorprojectmk2.ui.REPLY";

    private EditText mEditItineraryView;
    private ItineraryViewModel mItineraryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerary_create);
        mEditItineraryView = findViewById(R.id.edit_itinerary);
        mItineraryViewModel = ViewModelProviders.of(this).get(ItineraryViewModel.class);

        final Button buttonSaveItinerary = findViewById(R.id.button_save);
        buttonSaveItinerary.setOnClickListener(new View.OnClickListener() {
            /**
             * puts new word into intent to be sent to database
             */
            public void onClick(View v) {
                EditText itineraryName = findViewById(R.id.edit_itinerary);
                Intent replyIntent = new Intent();
                if(TextUtils.isEmpty(mEditItineraryView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String itinerary = itineraryName.getText().toString();

                    ItineraryListEntity itineraryListEntity = new ItineraryListEntity(itinerary);
                    mItineraryViewModel.insert(itineraryListEntity);
                }
                finish();
            }
        });

    }
}
