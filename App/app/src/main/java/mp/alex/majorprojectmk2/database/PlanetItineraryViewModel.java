package mp.alex.majorprojectmk2.database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import mp.alex.majorprojectmk2.database.entities.PlanetEntity;
import mp.alex.majorprojectmk2.database.entities.PlanetItinerary;


/**
 * Allows us to maintain app data if, for example, screen is rotated.
 * Extends AndroidViewModel, not ViewModel because we want to have references to
 * activities that may be destroyed when rotation occurs.
 * AndroidVM uses application context to prevent old references.
 *
 * Provides data to UI from Repository, effectively separating the two (abstraction)
 */
public class PlanetItineraryViewModel extends AndroidViewModel {

    private MyRepository myRepository;
    private LiveData<List<PlanetItinerary>> mAllPlanItins;

    public PlanetItineraryViewModel(Application application) {
        super(application);
        myRepository = new MyRepository(application);
        mAllPlanItins = myRepository.getAllPlanetItineraries();
    }

    //------------------------------------- Database Methods ---------------------------------------
    //Wrapper insert that references MyRepository's insert. Hides implementation of insert from UI.

    public void insert(PlanetItinerary planetItinerary) {
        myRepository.insert(planetItinerary);
    }

    public LiveData<List<PlanetEntity>> getPlanetsForItineraries(int itineraryId) {
        return myRepository.getPlanetsForItineraries(itineraryId);
    }

    //Delete specific Planet -> Itinerary link
    public void deletePlanItinLink(PlanetItinerary planetItinerary) {
        myRepository.deletePlanItinLink(planetItinerary);
    }
}
