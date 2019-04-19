package mp.alex.majorprojectmk2.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import mp.alex.majorprojectmk2.database.entities.PlanetEntity;

/**
 * DAO for planets
 *
 * Use LiveData to be notified of any queries
 *
 * CAN USE JUNIT FOR DAO TESTING
 */
@Dao
public interface DAOPlanets {
    @Insert
    void insert(PlanetEntity planetEntity);

    @Query("DELETE FROM planet_table")
    void deleteAll();

    //To get calculation data? Or should I integrate this with search?
    @Query("SELECT * FROM planet_table ORDER BY name")
    LiveData<List<PlanetEntity>> searchAllPlanets();

    //Search query for planets less distance than x (planets within time frame)
    @Query("SELECT * FROM planet_table WHERE star_distance < :distance ORDER BY name")
    LiveData<List<PlanetEntity>> searchDistanceLessThan(double distance);


}
