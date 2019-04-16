package mp.alex.majorprojectmk2.ui;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import mp.alex.majorprojectmk2.R;
import mp.alex.majorprojectmk2.ui.planetadapter.SearchResult;

/**
 * NOTE: CHECK IF ARRIVAL DATE IS LESS THEN LEAVE DATE. IF SO, ASK AGAIN
 */

public class SearchNew extends AppCompatActivity {

    public static String leaveSeconds, arrivalSeconds;
    double leaveDay, leaveMonth, leaveYear, arrivalDay, arrivalMonth, arrivalYear;

    private TextView activeDateDisplay, leaveDateTextView, arrivalDateTextView;
    private Button leaveDateButton, arrivalDateButton, searchButton;
    private Calendar activeDate, leaveDate, arrivalDate;

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

        // capture our View elements for the leave date function
        leaveDateTextView = (TextView) findViewById(R.id.leaveDate);
        leaveDateButton = (Button) findViewById(R.id.buttonLeaveDate);

        // Get the current date to display
        leaveDate = Calendar.getInstance();

        // Add a click listener to the button
        leaveDateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDateDialog(leaveDateTextView, leaveDate);
            }
        });

        // Capture our View elements for the arrivval date function
        arrivalDateTextView = (TextView) findViewById(R.id.arrivalDate);
        arrivalDateButton = (Button) findViewById(R.id.buttonArrivalDate);

        // Get the current date to display
        arrivalDate = Calendar.getInstance();

        // Add a click listener to the button
        arrivalDateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDateDialog(arrivalDateTextView, arrivalDate);
            }
        });

        // Display the current date (this method is below)
        updateDisplay(leaveDateTextView, leaveDate);
        updateDisplay(arrivalDateTextView, arrivalDate);
    }

    public void updateDisplay(TextView dateDisplay, Calendar date) {
        dateDisplay.setText(
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(date.get(Calendar.DAY_OF_MONTH)).append("/")
                        .append(date.get(Calendar.MONTH) + 1).append("/")
                        .append(date.get(Calendar.YEAR)));

        //Set leaveSeconds and arrivalSeconds to be passed to search result
        leaveSeconds = (leaveDateTextView.getText()).toString();
        arrivalSeconds = (arrivalDateTextView.getText()).toString();
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
     * Error: Need to add additional parameter for if statements to make sure they aren't the same date or arrival is not before leaving
     */
    public void searchEvent() {
        if(leaveSeconds != null && !leaveSeconds.isEmpty()) {
            if(arrivalSeconds != null && !arrivalSeconds.isEmpty()) {
                if (leaveSeconds.equals(arrivalDate)) {
                    startSearchResult();
                }
            } else {
                Toast.makeText(getApplicationContext(),
                        "Please select an arrival time",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(),
                    "Please select a leaving time",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Open SearchResult activity
     */
    public void startSearchResult() {
        Intent intent = new Intent(this, SearchResult.class);
        startActivity(intent);
    }

    public String getLeaveSeconds() {
        return leaveSeconds;
    }

    public String getArrivalSeconds() {
        return arrivalSeconds;
    }

    public static void setLeaveSeconds(String leaveSeconds) {
        SearchNew.leaveSeconds = leaveSeconds;
    }

    public static void setArrivalSeconds(String arrivalSeconds) {
        SearchNew.arrivalSeconds = arrivalSeconds;
    }
}