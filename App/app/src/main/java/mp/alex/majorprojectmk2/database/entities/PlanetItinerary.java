package mp.alex.majorprojectmk2.database.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * The two id's in this table are composite keys.
 * To make these they must be declared as both FK's and PK's.
 */
@Entity(tableName = "planet_itinerary_table",
        primaryKeys = {"pi_itinerary_id", "pi_planet_id"},
        foreignKeys = {
        @ForeignKey(entity = ItineraryListEntity.class,
                parentColumns = "id",
                childColumns = "pi_itinerary_id"
        ),
        @ForeignKey(entity = PlanetEntity.class,
                parentColumns = "id",
                childColumns = "pi_planet_Id"
        )
})
public class PlanetItinerary {
    @PrimaryKey
    @ColumnInfo(name = "pi_itinerary_id")
    public String mItineraryId;

    @ColumnInfo(name = "pi_planet_id")
    public String mPlanetId;

    //Constructor
    public PlanetItinerary(String mItineraryId, String mPlanetId) {
        this.mItineraryId = mItineraryId;
        this.mPlanetId = mPlanetId;
    }

    //----------------------------------- Setters & Getters -----------------------------------

    public String getItineraryId() {
        return this.mItineraryId;
    }

    public String getPlanetId() {
        return this.mPlanetId;
    }

}
