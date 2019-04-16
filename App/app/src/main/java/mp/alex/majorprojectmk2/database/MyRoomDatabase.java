package mp.alex.majorprojectmk2.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import mp.alex.majorprojectmk2.database.dao.DAOItineraries;
import mp.alex.majorprojectmk2.database.dao.DAOPlanetItinerary;
import mp.alex.majorprojectmk2.database.dao.DAOPlanets;
import mp.alex.majorprojectmk2.database.entities.ItineraryListEntity;
import mp.alex.majorprojectmk2.database.entities.PlanetEntity;
import mp.alex.majorprojectmk2.database.entities.PlanetItinerary;

/**
 * I use a Room Database as it adds a high amount of abstraction to my code.
 */
@Database(entities = {ItineraryListEntity.class, PlanetEntity.class, PlanetItinerary.class}, version = 8, exportSchema = false)
public abstract class MyRoomDatabase extends RoomDatabase {
    private static final String TAG = "RoomDatabase";
    private static final String DATABASE_NAME = "app_database";

    public abstract DAOItineraries daoItineraries();

    public abstract DAOPlanets daoPlanets();

    public abstract DAOPlanetItinerary daoPlanetItinerary();

    private static MyRoomDatabase INSTANCE;

    /**
     * Want to make sure that this is a singleton to prevent multiple instances of the DB being open
     * Fallback wipes and rebuilds the database instead of migrating when there is no migration object.
     * Migration is not needed here as the app is simple - optional feature to implement in time.
     *
     * Reads from the CSV using buffered readers and stores the values from it as content value pairs.
     * Iterates through CSV, adding to CV pairs and then stores into planet table.
     *
     * @param context
     * @return
     */
    public static MyRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MyRoomDatabase.class) {
                if (INSTANCE == null) {
                    //Creates the DB here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MyRoomDatabase.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .addCallback(new RoomDatabase.Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    try {
                                        BufferedInputStream inputStream = new BufferedInputStream(context.getAssets().open("data.csv"));

                                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                                        String line;
                                        line = reader.readLine();
                                        String[] columnNames = line.split(",");
                                        db.beginTransaction();
                                        while ((line = reader.readLine()) != null) {
                                            String[] commaSeperated = line.split(",");
                                            ContentValues contentValues = new ContentValues();
                                            for (int i = 0; i < columnNames.length; i++) {
                                                String value = "";
                                                if (commaSeperated.length - 1 > i) {
                                                    value = commaSeperated[i].trim();
                                                }
                                                if (isNumeric(value)) {
                                                    contentValues.put(columnNames[i], value.equals("") ? 0 : Double.valueOf(value));
                                                } else {
                                                    contentValues.put(columnNames[i], value);
                                                }
                                            }
                                            db.insert("planet_table", OnConflictStrategy.REPLACE, contentValues);
                                        }
                                        try {
                                            db.setTransactionSuccessful();
                                        } finally {
                                            db.endTransaction();
                                        }
                                    } catch (IOException ex) {
                                        Log.e(TAG, ex.getLocalizedMessage());
                                    }
                                }
                            })
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
