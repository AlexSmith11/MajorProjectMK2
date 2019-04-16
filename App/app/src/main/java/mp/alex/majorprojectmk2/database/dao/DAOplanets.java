package mp.alex.majorprojectmk2.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import mp.alex.majorprojectmk2.database.entities.PlanetEntity;

/**
 * DAO for planets
 * Create new DAO in future for 3rd table for better normalisation
 *
 * Use JOIN keyword to get a record
 *
 * Don't want to use Insert if 3rd table used
 *
 * Use LiveData to be notified of the query for names
 *
 * CAN USE JUNIT FOR DAO TESTING
 */
@Dao
public interface DAOPlanets {
    @Insert
    void insert(PlanetEntity planetEntity);

    @Query("SELECT * FROM planet_table WHERE star_distance < :distance ORDER BY name")
    PlanetEntity[] searchDistanceOfPlanet(int distance);

    @Query("DELETE FROM planet_table")
    void deleteAll();

    @Query("SELECT * from planet_table ORDER BY name ASC")  //name is the column that stores name of planet. Orders alphabetically
    LiveData<List<PlanetEntity>> getPlanetNames();
}
