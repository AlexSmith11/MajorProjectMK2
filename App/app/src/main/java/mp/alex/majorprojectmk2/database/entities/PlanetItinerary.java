package mp.alex.majorprojectmk2.database.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

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
                childColumns = "pi_itinerary_id",
                onDelete = CASCADE          //When itinerary is deleted, its planets are too.
        ),
        @ForeignKey(entity = PlanetEntity.class,
                parentColumns = "id",
                childColumns = "pi_planet_id"
        ),
})
public class PlanetItinerary {
    @NonNull
    @ColumnInfo(name = "pi_itinerary_id")
    public int mItineraryId;

    @NonNull
    @ColumnInfo(name = "pi_planet_id")
    public int mPlanetId;

    //Constructor
    public PlanetItinerary(int mItineraryId, int mPlanetId) {
        this.mItineraryId = mItineraryId;
        this.mPlanetId = mPlanetId;
    }

    // region Getters and Setters

    public int getItineraryId() {
        return this.mItineraryId;
    }

    public int getPlanetId() {
        return this.mPlanetId;
    }

    public void setItinId(int mItineraryId) {
        this.mItineraryId = mItineraryId;
    }

    public void setPlanId(int mPlanetId) {
        this.mPlanetId = mPlanetId;
    }
}
