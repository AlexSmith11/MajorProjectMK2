package mp.alex.majorprojectmk2.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import mp.alex.majorprojectmk2.database.models.ItineraryListModel;

/**
 * DAO for itinerarys
 * Create new DAO in future for 3rd table for better normalisation
 *
 * Use LiveData to be notified of query for itinerary name
 */
@Dao
public interface DAOItineraries {
    @Insert
    void insert(ItineraryListModel itineraryListModel);

    @Query("DELETE FROM itinerary_table")
    void deleteAll();

    @Query("SELECT * from itinerary_table ORDER BY ItineraryListName")
    LiveData<List<ItineraryListModel>> getItineraryListName();
}
