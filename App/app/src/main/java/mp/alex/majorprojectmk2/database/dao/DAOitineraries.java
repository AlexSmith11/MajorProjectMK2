package mp.alex.majorprojectmk2.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import mp.alex.majorprojectmk2.database.entities.ItineraryListEntity;

/**
 * DAO for itinerarys
 * Create new DAO in future for 3rd table for better normalisation
 *
 * Use LiveData to be notified of query for itinerary name
 */
@Dao
public interface DAOItineraries {
    @Insert
    void insert(ItineraryListEntity itineraryListEntity);

    @Query("DELETE FROM itinerary_table")
    void deleteAllItineraries();

    @Delete
    void deleteItinerary(ItineraryListEntity itineraryListEntity);

    @Query("SELECT * from itinerary_table ORDER BY itineraryListName ASC")
    LiveData<List<ItineraryListEntity>> getItineraryListNames();

    //Checks if database is populated (on startup)
    @Query("SELECT * from itinerary_table LIMIT 1")
    ItineraryListEntity[] getAnyWord();
}
