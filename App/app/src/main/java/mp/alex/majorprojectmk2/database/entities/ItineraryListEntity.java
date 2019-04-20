package mp.alex.majorprojectmk2.database.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Entity(model) for the itinerary database.
 * Stores all x columns, y rows of info
 */

@Entity(tableName = "itinerary_table")
public class ItineraryListEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "itineraryListName")
    public String itineraryListName;

    //Constructor
    public ItineraryListEntity(String itineraryListName) {
        this.itineraryListName = itineraryListName;
    }

    //----------------------------------- Setters & Getters -----------------------------------
    //setters
    public void setId(int id) {
        this.id = id;
    }

    public void setItineraryListName(String itineraryListName) {
        this.itineraryListName = itineraryListName;
    }

    //getters
    public double getId() {
        return this.id;
    }

    public String getItineraryListName() {
        return this.itineraryListName;
    }
}
