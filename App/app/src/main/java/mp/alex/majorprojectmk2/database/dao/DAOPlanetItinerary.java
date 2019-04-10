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

    @Query("DELETE FROM planet_itinerary_table")
    void deleteAll();

    @Query("SELECT * from planet_itinerary_table ORDER BY pi_itinerary_id ASC")
    LiveData<List<PlanetItinerary>> getPlanetItineraryIds();

    /*
    @Query("SELECT * FROM itinerary_table INNER JOIN planet_itinerary_table ON" +
            " itinerary_table.id = planet_itinerary_table.pi_itinerary_id WHERE" +
            " planet_itinerary_table.pi_planet_id=:planetId")
    List<ItineraryListEntity> getItinerariesForPlanets(final int planetId);
    */

    @Query("SELECT * FROM planet_table INNER JOIN planet_itinerary_table ON" +
            " planet_table.id = planet_itinerary_table.pi_planet_id WHERE" +
            " planet_itinerary_table.pi_itinerary_id=:itineraryId")
    List<PlanetEntity> getPlanetsForItineraries(final int itineraryId);

}