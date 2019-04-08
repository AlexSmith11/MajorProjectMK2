package mp.alex.majorprojectmk2.database;

import android.arch.lifecycle.LiveData;

import java.util.List;

import mp.alex.majorprojectmk2.database.dao.DAOItineraries;
import mp.alex.majorprojectmk2.database.dao.DAOPlanets;
import mp.alex.majorprojectmk2.database.models.ItineraryListModel;
import mp.alex.majorprojectmk2.database.models.PlanetModel;

public class MyRepository {
    private DAOItineraries mDAOItineraries;
    private DAOPlanets mDAOPlanets;
    private LiveData<List<ItineraryListModel>> mAllItineraries;
    private LiveData<List<PlanetModel>> mAllPlanets;
}
