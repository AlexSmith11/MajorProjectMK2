package mp.alex.majorprojectmk2.database;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import mp.alex.majorprojectmk2.database.entities.PlanetEntity;

/**
 * Allows us to maintain app data if, for example, screen is rotated.
 * Extends AndroidViewModel, not ViewModel because we want to have references to
 * activities that may be destroyed when rotation occurs.
 * AndroidVM uses application context to prevent old references.
 *
 * Provides data to UI from Repository, effectively separating the two (abstraction)
 */
public class PlanetViewModel extends AndroidViewModel {

    private MyRepository myRepository;

    private double distance;
    private LiveData<List<PlanetEntity>> mAllPlanets;
    private LiveData<List<PlanetEntity>> mAllPlanetsLessThanDist;

    public PlanetViewModel(Application application) {
        super(application);
        myRepository = new MyRepository(application);
        mAllPlanets = myRepository.getAllPlanets();     //This is just the name. Call other method for
        mAllPlanetsLessThanDist = myRepository.getAllPlanetsLessThanDistance(distance);

    }

    //------------------------------------- Database Methods ---------------------------------------
    //Get all planet names. Abstraction: Hides implementation from UI.

    public LiveData<List<PlanetEntity>> getAllPlanets() {
        return mAllPlanets;
    }

    //Get all planet names with calc data.
    public LiveData<List<PlanetEntity>> getAllPlanetsLessThanDist(double distance) {
        return mAllPlanetsLessThanDist;
    }
}
