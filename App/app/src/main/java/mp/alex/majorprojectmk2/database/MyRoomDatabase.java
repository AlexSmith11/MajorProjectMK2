package mp.alex.majorprojectmk2.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import mp.alex.majorprojectmk2.database.dao.DAOItineraries;
import mp.alex.majorprojectmk2.database.dao.DAOPlanetItinerary;
import mp.alex.majorprojectmk2.database.dao.DAOPlanets;
import mp.alex.majorprojectmk2.database.entities.ItineraryListEntity;
import mp.alex.majorprojectmk2.database.entities.PlanetEntity;

/**
 * I use a Room Database as it adds a high amount of abstraction to my code.
 */
@Database(entities = {ItineraryListEntity.class, PlanetEntity.class, PlanetEntity.class}, version = 1, exportSchema = false)
public abstract class MyRoomDatabase extends RoomDatabase {
    public abstract DAOItineraries daoItineraries();
    public abstract DAOPlanets daoPlanets();
    public abstract DAOPlanetItinerary daoPlanetItinerary();

    private static MyRoomDatabase INSTANCE;

    /**
     * Want to make sure that this is a singleton to prevent multiple instances of the DB being open
     * Fallback wipes and rebuilds the database instead of migrating when there is no migration object.
     * Migration is not needed here as the app is simple - optional feature to implement in time.
     *
     * @param context
     * @return
     */
    public static MyRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MyRoomDatabase.class) {
                if (INSTANCE == null) {
                    //Creates the DB here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MyRoomDatabase.class, "app_database")
                            .fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }
}
