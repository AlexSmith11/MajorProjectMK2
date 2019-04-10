package mp.alex.majorprojectmk2.database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import mp.alex.majorprojectmk2.database.entities.ItineraryListEntity;

/**
 * Allows us to maintain app data if, for example, screen is rotated.
 * Extends AndroidViewModel, not ViewModel because we want to have references to
 * activities that may be destroyed when rotation occurs.
 * AndroidVM uses application context to prevent old references.
 *
 * Provides data to UI from Repository, effectively separating the two (abstraction)
 */
public class ItineraryViewModel extends AndroidViewModel {
    private MyRepository myRepository;
    private LiveData<List<ItineraryListEntity>> mAllItineraries;

    //makes reference to MyRepository. Gets list of Itineraries.
    public ItineraryViewModel(Application application) {
        super(application);
        myRepository = new MyRepository(application);
        mAllItineraries = myRepository.getAllItineraries();
    }

    //------------------------------------- Database Methods ---------------------------------------
    //Get all itineraries. Abstraction: Hides implementation from UI.
    LiveData<List<ItineraryListEntity>> getmAllItineraries() {
        return mAllItineraries;
    }

    //Wrapper insert that references MyRepository's insert. Hides implementation of insert from UI.
    public void insert(ItineraryListEntity itineraryListEntity) {
        myRepository.insert(itineraryListEntity);
    }
}
