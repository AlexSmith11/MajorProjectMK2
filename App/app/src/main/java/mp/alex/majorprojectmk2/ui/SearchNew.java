package mp.alex.majorprojectmk2.ui;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mp.alex.majorprojectmk2.R;
import mp.alex.majorprojectmk2.database.PlanetViewModel;
import mp.alex.majorprojectmk2.database.entities.PlanetEntity;
import mp.alex.majorprojectmk2.ui.planetadapter.SearchResult;

/**
 * SearchNew handles any searches to DB
 * Current search: Less than Distance
 * Can add more as addition features.
 *
 * Also implements date picker (calendar view).
 * 
 * NOTE: CHECK IF ARRIVAL DATE IS LESS THEN LEAVE DATE. IF SO, ASK AGAIN
 */

public class SearchNew extends AppCompatActivity {

    public double distance;

    private TextView activeDateDisplay, leaveDateTextView, arrivalDateTextView;
    private Button leaveDateButton, arrivalDateButton, searchButton;
    private Calendar activeDate, leaveCalendar, arrivalCalendar;

    private PlanetViewModel planetViewModel;

    static final int DATE_DIALOG_ID = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_new);
        searchButton = (Button) findViewById(R.id.buttonSearch);
        searchButton.setOnClickListener(new View.OnClickListener() {
            //Only search if both values have ben set
            @Override
            public void onClick(View v) {
                searchEvent();
            }
        });

        planetViewModel = ViewModelProviders.of(this).get(PlanetViewModel.class);

        // capture our View elements for the leave date function
        leaveDateTextView = (TextView) findViewById(R.id.leaveDate);
        leaveDateButton = (Button) findViewById(R.id.buttonLeaveDate);

        // Get the current date to display
        leaveCalendar = Calendar.getInstance();

        // Add a click listener to the button
        leaveDateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDateDialog(leaveDateTextView, leaveCalendar);
            }
        });

        // Capture our View elements for the arrival date function
        arrivalDateTextView = (TextView) findViewById(R.id.arrivalDate);
        arrivalDateButton = (Button) findViewById(R.id.buttonArrivalDate);

        // Get the current date to display
        arrivalCalendar = Calendar.getInstance();

        // Add a click listener to the button
        arrivalDateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDateDialog(arrivalDateTextView, arrivalCalendar);
            }
        });

        // Display the current date (this method is below)
        updateDisplay(leaveDateTextView, leaveCalendar);
        updateDisplay(arrivalDateTextView, arrivalCalendar);
    }

    public void updateDisplay(TextView dateDisplay, Calendar calendar) {
        dateDisplay.setText(
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(calendar.get(Calendar.DAY_OF_MONTH)).append("/")
                        .append(calendar.get(Calendar.MONTH) + 1).append("/")
                        .append(calendar.get(Calendar.YEAR)));
    }

    public void showDateDialog(TextView dateDisplay, Calendar date) {
        activeDateDisplay = dateDisplay;
        activeDate = date;
        showDialog(DATE_DIALOG_ID);
    }

    public DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            activeDate.set(Calendar.YEAR, year);
            activeDate.set(Calendar.MONTH, monthOfYear);
            activeDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateDisplay(activeDateDisplay, activeDate);
            unregisterDateDisplay();
        }
    };

    private void unregisterDateDisplay() {
        activeDateDisplay = null;
        activeDate = null;
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(
                        this, dateSetListener,
                        activeDate.get(Calendar.YEAR),
                        activeDate.get(Calendar.MONTH),
                        activeDate.get(Calendar.DAY_OF_MONTH));
        }
        return null;
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);
        switch (id) {
            case DATE_DIALOG_ID:
                ((DatePickerDialog) dialog).updateDate(
                        activeDate.get(Calendar.YEAR),
                        activeDate.get(Calendar.MONTH),
                        activeDate.get(Calendar.DAY_OF_MONTH));
                break;
        }
    }

    /**
     * When search button is pressed
     *
     * This is where we do our calculations for the search event with a distance search filter.
     * We can add other filters to different search buttons.
     *
     * Error: Need to add additional parameter for if statements to make sure they aren't the same date or arrival is not before leaving
     */
    public void searchEvent() {
        Date arrivalDate = arrivalCalendar.getTime();
        Date leavingDate = leaveCalendar.getTime();

        long arrivalMilli = arrivalDate.getTime();
        long leavingMilli = leavingDate.getTime();

        /*
        To find the amount of travel time we have, we must subtract our leaving time from our arrival time.
        Math.abs is to give an absolute number. It is not signed so if the
        result is negative it does not matter - time cannot be negative.
         */
        long diffSeconds = Math.abs(arrivalMilli - leavingMilli) / 1000;
        Log.i("TEST", String.valueOf(diffSeconds));

        /*
        Calculation to find the distance we are able to travel within the given time frame.
        diffSeconds = the difference in seconds from when we leave Earth to when we arrive at our destination.
        296794533.42 = 99% the speed of light in m/s.
        3.086e+16 = how many meters are in a parsec.
        distance = the distance we travel given our speed and travel time. Unit: Parsecs
        Parsecs is the unit we need as this is what star_distance is stored as in the database.
        */
        double distance = (diffSeconds*296794533.42) / 3.086e+16;
        Log.i("TEST", String.valueOf(diffSeconds));


        LiveData<List<PlanetEntity>> planets = planetViewModel.getAllPlanetsLessThanDist(distance);
        planets.observe(this, new Observer<List<PlanetEntity>>() {
            @Override
            public void onChanged(@Nullable List<PlanetEntity> planetEntities) {
                if (planetEntities == null) {
                    return;
                }
                Log.i("TEST", planetEntities.toString());
                startSearchResult(planetEntities);
            }
        });
    }

    /**
     * Open SearchResult activity and send search information ahead
     * All searches (and the results they contain) are sent here first.
     * We then pass the results to SearchResult activity.
     * This is because we can populate SearchResult with the results of any search filter.
     *
     * Search filter: i.e. distance, how hot the star is, etc.
     *
     * We bundle all the search results into a parcelable array to send to SearchResult.
     * This allows this method to be called whenever a search is submitted, regardless of search filters/parameters.
     */
    public void startSearchResult(List<PlanetEntity> planetEntities) {
        Intent intent = new Intent(this, SearchResult.class);

        Bundle extras = new Bundle();
        extras.putParcelableArrayList(SearchResult.PLANET_RESULT_KEY, new ArrayList<>(planetEntities));
        intent.putExtras(extras);

        startActivity(intent);
    }
}