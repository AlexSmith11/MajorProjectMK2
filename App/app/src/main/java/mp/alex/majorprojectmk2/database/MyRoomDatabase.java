package mp.alex.majorprojectmk2.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import mp.alex.majorprojectmk2.database.dao.DAOItineraries;
import mp.alex.majorprojectmk2.database.dao.DAOPlanets;
import mp.alex.majorprojectmk2.database.models.ItineraryListModel;
import mp.alex.majorprojectmk2.database.models.PlanetModel;

@Database(entities = {ItineraryListModel.class, PlanetModel.class}, version = 1, exportSchema = false)
public abstract class MyRoomDatabase extends RoomDatabase {
    public abstract DAOItineraries daoItineraries();
    public abstract DAOPlanets daoPlanets();

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
                    //Create the DB here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MyRoomDatabase.class, "app_database")
                            .fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }
}
