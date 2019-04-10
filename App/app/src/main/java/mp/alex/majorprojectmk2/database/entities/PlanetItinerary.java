package mp.alex.majorprojectmk2.database.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * The two id's in this table are composite keys.
 * To make these they must be declared as both FK's and PK's.
 *
 * Use Entity primaryKeys to define keys as composite
 * Indices index's pi_planet_id to quicken query (stops search of whole table)
 */
@Entity(tableName = "planet_itinerary_table",
        primaryKeys = {"pi_itinerary_id", "pi_planet_id"},
        indices={@Index("pi_planet_id")},
        foreignKeys = {
        @ForeignKey(entity = ItineraryListEntity.class,
                parentColumns = "id",
                childColumns = "pi_itinerary_id"
        ),
        @ForeignKey(entity = PlanetEntity.class,
                parentColumns = "id",
                childColumns = "pi_planet_id"
        ),
})
public class PlanetItinerary {
    @NonNull
    @ColumnInfo(name = "pi_itinerary_id")
    public String mItineraryId;

    @NonNull
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
