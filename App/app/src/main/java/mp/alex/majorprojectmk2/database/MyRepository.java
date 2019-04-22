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

    private int itineraryId;

    //Constructor that gets handle to db & initialises the member variables
    MyRepository(Application application) {
        MyRoomDatabase db = MyRoomDatabase.getDatabase(application);

        mDAOItineraries = db.daoItineraries();
        mDAOPlanets = db.daoPlanets();
        mDAOPlanetItineraries = db.daoPlanetItinerary();

        mAllItineraries = mDAOItineraries.getItineraryListNames();
        mAllPlanets = mDAOPlanets.searchAllPlanets();
        //mAllPlanetItineraries = mDAOPlanetItineraries.getPlanetsForItineraries(itineraryId);
    }

    //region Getters
    //Add data to LiveData threads
    /**
     * Returns cached itineraries as LiveData to use on separate thread.
     * @return
     */
    LiveData<List<ItineraryListEntity>> getAllItineraries() {
        return mAllItineraries;
    }

    /**
     * Returns cached planet names as LiveData to use on separate thread.
     * This is just the planet name, not the whole row of data needed for calculations.
     * @return
     */
    LiveData<List<PlanetEntity>> getAllPlanets() {
        return mAllPlanets;
    }

    /**
     * Returns ver of all planets that match search criteria (star_dist < distance)
     * @return
     */
    LiveData<List<PlanetEntity>> getAllPlanetsLessThanDistance(double distance) {
        return mDAOPlanets.searchDistanceLessThan(distance);
    }

    /**
     * Returns cached planetItineraries as livedata to use on separate thread.
     * Adds planets to itineraries.
     * @return
     */
    LiveData<List<PlanetItinerary>> getAllPlanetItineraries() {
        return mAllPlanetItineraries;
    }
    //endregion

    //region Wrappers
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
     * Planet table insert. Won't be used unless specified by client.
     * Use async task to call insert on a thread that isn't using UI.
     * Stops app from crashing.
     * @param planetEntity
     */
    public void insert(PlanetEntity planetEntity) {
        new insertPlanetAsyncTask(mDAOPlanets).execute(planetEntity);
    }

    /**
     * Join table insert
     * Use async task to call insert on a thread that isn't using UI.
     * Stops app from crashing.
     * @param planetItinerary
     */
    public void insert(PlanetItinerary planetItinerary) {
        new insertPlanItinAsyncTask(mDAOPlanetItineraries).execute(planetItinerary);
    }

    public LiveData<List<PlanetEntity>> getPlanetsForItineraries(int itineraryId) {
        return mDAOPlanetItineraries.getPlanetsForItineraries(itineraryId);
    }

    //------------------------------ Wrappers for the delete methods -------------------------------

    /**
     * Delete a single Itinerary from the list
     * @param itineraryListEntity
     */
    public void deleteItinerary(ItineraryListEntity itineraryListEntity) {
        new deleteOneItineraryAsyncTask(mDAOItineraries).execute(itineraryListEntity);
    }

    /**
     * Delete all itineraries
     */
    public void deleteAllItineraries() {
        new deleteAllItinerariesAsyncTask(mDAOItineraries).execute();
    }

    /**
     * Delete a Planet -> Itinerary link (accessed from swiping on ItinerarySub activity)
     * @param planetItinerary
     */
    public void deletePlanItinLink(PlanetItinerary planetItinerary) {
        new deleteOnePlanItinLink(mDAOPlanetItineraries).execute(planetItinerary);
    }
    //endregion

    //region AsyncTasks
    //---------------------------------------- Async tasks -----------------------------------------
    /**
     * Uses AsyncTask to make sure we're using separate threads when we interact with our DB.
     */
    //---- Insert ----
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

    //----- Delete -----
    //Delete Single Itinerary Row
    private static class deleteOneItineraryAsyncTask extends AsyncTask<ItineraryListEntity, Void, Void> {
        private DAOItineraries mAsyncTaskDao;

        deleteOneItineraryAsyncTask(DAOItineraries daoItin) {
            mAsyncTaskDao = daoItin;
        }
        @Override
        protected  Void doInBackground(final  ItineraryListEntity... params) {
            mAsyncTaskDao.deleteItinerary(params[0]);
            return null;
        }
    }

    //Delete All Itineraries
    private static class deleteAllItinerariesAsyncTask extends AsyncTask<Void, Void, Void> {
        private DAOItineraries mAsyncTaskDao;

        deleteAllItinerariesAsyncTask(DAOItineraries daoItin) {
            mAsyncTaskDao = daoItin;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAllItineraries();
            return null;
        }
    }

    //Delete One planetItinerary link
    private static class deleteOnePlanItinLink extends AsyncTask<PlanetItinerary, Void, Void> {
        private DAOPlanetItinerary mAsyncTaskDao;

        deleteOnePlanItinLink(DAOPlanetItinerary daoItin) {
            mAsyncTaskDao = daoItin;
        }
        @Override
        protected  Void doInBackground(final  PlanetItinerary... params) {
            mAsyncTaskDao.deletePlanItinLink(params[0]);
            return null;
        }
    }
    //endregion

}
