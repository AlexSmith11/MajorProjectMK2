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

    private double distance = 0d;
    private LiveData<List<PlanetEntity>> mAllPlanets;
    private LiveData<List<PlanetEntity>> mAllPlanetsLessThanDist;

    public PlanetViewModel(Application application) {
        super(application);
        myRepository = new MyRepository(application);

        mAllPlanets = myRepository.getAllPlanets();     //For just planet names
        mAllPlanetsLessThanDist = myRepository.getAllPlanetsLessThanDistance(distance);     //For all planet data less than set distance
    }

    //------------------------------------- Database Methods ---------------------------------------
    //Abstraction: Hides implementation from UI.

    //Get all planet names
    public LiveData<List<PlanetEntity>> getAllPlanets() {
        return mAllPlanets;
    }

    //Get all planets with distance search.
    public LiveData<List<PlanetEntity>> getAllPlanetsLessThanDist(double distance) {
        if (this.distance == distance) {
            return mAllPlanetsLessThanDist;
        }
        this.distance = distance;
        mAllPlanetsLessThanDist = myRepository.getAllPlanetsLessThanDistance(distance);
        return mAllPlanetsLessThanDist;
    }
}
