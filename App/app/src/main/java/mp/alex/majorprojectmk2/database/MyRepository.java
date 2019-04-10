package mp.alex.majorprojectmk2.database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import mp.alex.majorprojectmk2.database.dao.DAOItineraries;
import mp.alex.majorprojectmk2.database.dao.DAOPlanetItinerary;
import mp.alex.majorprojectmk2.database.dao.DAOPlanets;
import mp.alex.majorprojectmk2.database.entities.ItineraryListEntity;
import mp.alex.majorprojectmk2.database.entities.PlanetEntity;
import mp.alex.majorprojectmk2.database.entities.PlanetItinerary;

public class MyRepository {
    private DAOItineraries mDAOItineraries;
    private DAOPlanets mDAOPlanets;
    private DAOPlanetItinerary mDAOPlanetItineraries;
    private LiveData<List<ItineraryListEntity>> mAllItineraries;
    private LiveData<List<PlanetEntity>> mAllPlanets;
    private LiveData<List<PlanetItinerary>> mAllPlanetItineraries;

    //Constructor that gets handle to db & initialises the member variables
    MyRepository(Application application) {
        MyRoomDatabase db = MyRoomDatabase.getDatabase(application);

        mDAOItineraries = db.daoItineraries();
        mDAOPlanets = db.daoPlanets();
        mDAOPlanetItineraries = db.daoPlanetItinerary();

        mAllItineraries = mDAOItineraries.getItineraryListNames();
        mAllPlanets = mDAOPlanets.getPlanetNames();
        mAllPlanetItineraries = mDAOPlanetItineraries.getPlanetItineraryIds();
    }

    //Add data to LiveData threads
    /**
     * Returns cached itineraries as LiveData to use on separate thread.
     * @return
     */
    LiveData<List<ItineraryListEntity>> getAllItineraries() {
        return mAllItineraries;
    }

    /**
     * Returns cached planets as LiveData to use on separate thread.
     * @return
     */
    LiveData<List<PlanetEntity>> getAllPlanets() {
        return mAllPlanets;
    }

    /**
     * Returns cached planetItineraries as livedata to use on separate thread.
     * @return
     */
    LiveData<List<PlanetItinerary>> getAllPlanetItineraries() {
        return mAllPlanetItineraries;
    }

    //
    //------------------------------ Wrappers for the insert methods -------------------------------
    /**
     * Itinerary insert table
     * Use async task to call insert on a thread that isn't using UI.
     * Stops app from crashing.
     * @param itineraryListEntity
     */
    public void insert(ItineraryListEntity itineraryListEntity) {
        new insertItineraryAsyncTask(mDAOItineraries).execute(itineraryListEntity);
    }

    /**
     * Planet table insert. typically wont be used
     * Use async task to call insert on a thread that isn't using UI.
     * Stops app from crashing.
     * @param planetEntity
     */
    public void insert (PlanetEntity planetEntity) {
        new insertPlanetAsyncTask(mDAOPlanets).execute(planetEntity);
    }

    /**
     * Join table insert
     * Use async task to call insert on a thread that isn't using UI.
     * Stops app from crashing.
     * @param planetItinerary
     */
    public void insert (PlanetItinerary planetItinerary) {
        new insertPlanItinAsyncTask(mDAOPlanetItineraries).execute(planetItinerary);
    }

    //---------------------------------------- Async tasks -----------------------------------------
    //Insert
    /**
     * Insert method
     * Uses AsyncTask to make sure we're using seperate threads when we interact with our DB
     */
    private static class insertItineraryAsyncTask extends AsyncTask<ItineraryListEntity, Void, Void> {
        private DAOItineraries mAsyncTaskDao;

        insertItineraryAsyncTask(DAOItineraries daoItin) {
            mAsyncTaskDao = daoItin;
        }
        @Override
        protected Void doInBackground(final ItineraryListEntity... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class insertPlanetAsyncTask extends AsyncTask<PlanetEntity, Void, Void> {
        private DAOPlanets mAsyncTaskDao;

        insertPlanetAsyncTask(DAOPlanets daoPlan) {
            mAsyncTaskDao = daoPlan;
        }
        @Override
        protected Void doInBackground(final PlanetEntity... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class insertPlanItinAsyncTask extends AsyncTask<PlanetItinerary, Void, Void> {
        private DAOPlanetItinerary mAsyncTaskDao;

        insertPlanItinAsyncTask(DAOPlanetItinerary daoPlanItin) {
            mAsyncTaskDao = daoPlanItin;
        }
        @Override
        protected Void doInBackground(final PlanetItinerary... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    //
}