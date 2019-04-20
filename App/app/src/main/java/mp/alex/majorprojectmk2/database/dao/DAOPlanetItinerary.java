package mp.alex.majorprojectmk2.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import mp.alex.majorprojectmk2.database.entities.ItineraryListEntity;
import mp.alex.majorprojectmk2.database.entities.PlanetEntity;
import mp.alex.majorprojectmk2.database.entities.PlanetItinerary;

/**
 * DAO for table with planet nd itinerary ID's for itinerary recycler lists
 *
 * Use LiveData to be notified of the query for names
 */
@Dao
public interface DAOPlanetItinerary {
    @Insert
    void insert(PlanetItinerary planetItinerary);

    //want an inner join

    @Query("SELECT * FROM planet_table INNER JOIN planet_itinerary_table pi ON" +
            " planet_table.id = pi.pi_planet_id WHERE pi.pi_itinerary_id = :itineraryId")
    LiveData<List<PlanetItinerary>> getPlanetsForItineraries(final int itineraryId);


}