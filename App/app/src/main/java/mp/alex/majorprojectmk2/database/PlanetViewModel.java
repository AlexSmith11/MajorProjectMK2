package mp.alex.majorprojectmk2.database;


import android.app.Application;
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
public class PlanetViewModel {

    private MyRepository myRepository;

    private LiveData<List<PlanetEntity>> mAllPlanets;
    private List<PlanetEntity> mAllPlanetInfo;
    private LiveData<List<PlanetEntity>> searchByLiveData;


    public PlanetViewModel(Application application) {
        //super(application);
        myRepository = new MyRepository(application);
        mAllPlanets = myRepository.getAllPlanets();     //This is just the name. Call other method for
        //mAllPlanetInfo = myRepository.getAllPlanetInfo(lowestDistance, highestDistance);



    }
}
