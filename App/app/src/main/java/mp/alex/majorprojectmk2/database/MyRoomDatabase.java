package mp.alex.majorprojectmk2.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import mp.alex.majorprojectmk2.database.dao.DAOItineraries;
import mp.alex.majorprojectmk2.database.dao.DAOPlanetItinerary;
import mp.alex.majorprojectmk2.database.dao.DAOPlanets;
import mp.alex.majorprojectmk2.database.entities.ItineraryListEntity;
import mp.alex.majorprojectmk2.database.entities.PlanetEntity;
import mp.alex.majorprojectmk2.database.entities.PlanetItinerary;

/**
 * I use a Room Database as it adds a high amount of abstraction to my code.
 */
@Database(entities = {ItineraryListEntity.class, PlanetEntity.class, PlanetItinerary.class}, version = 3, exportSchema = false)
public abstract class MyRoomDatabase extends RoomDatabase {
    public abstract DAOItineraries daoItineraries();
    public abstract DAOPlanets daoPlanets();
    public abstract DAOPlanetItinerary daoPlanetItinerary();

    private static MyRoomDatabase INSTANCE;

    /**
     * Want to make sure that this is a singleton to prevent multiple instances of the DB being open
     * Fallback wipes and rebuilds the database instead of migrating when there is no migration object.
     * Migration is not needed here as the app is simple - optional feature to implement in time.
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
                            MyRoomDatabase.class, "app_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * When app is restarted, delete all content and repopulate the db
     * Need to use AsyncTask as room cannot perform on the UI thread
     *
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        //Start new AsyncTask to modify database with
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    //Populate database in the background
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final DAOItineraries itinDao;
        String[] itineraries;

        PopulateDbAsync(MyRoomDatabase db) {
            itinDao = db.daoItineraries();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            /*
            If I want to start app with clean db on every start. May help with update of planet info
             */
            /*
            if (itinDao.getAnyWord().length < 1) {
                for (int i = 0; i <= itineraries.length -1; i++) {
                    ItineraryListEntity itineraryListEntity = new ItineraryListEntity((itineraries[i]));
                    itinDao.insert(itineraryListEntity);
                }
            }
            */
            return null;
        }
    }
}
