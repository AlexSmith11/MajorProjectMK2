package mp.alex.majorprojectmk2.database.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Entity(model) for the itinerary database.
 * Stores all x columns, y rows of info
 */
/*
@Entity(tableName = "itinerary_table",
        foreignKeys = @ForeignKey(entity = PlanetEntity.class,
        parentColumns = "id",
        childColumns = "id",
        onDelete = CASCADE))
        */
@Entity(tableName = "itinerary_table")
public class ItineraryListEntity {

    @PrimaryKey
    @ColumnInfo(name = "id")
    public int id;
    String itineraryListName;

    /**
     * Constructors
     */
    /*
    public ItineraryListEntity() {
    }

    //Just name
    public ItineraryListEntity(String itineraryListName) {
        this.itineraryListName = itineraryListName;
    }
    */

    //Name and id (more useful)
    public ItineraryListEntity(int id, String itineraryListName) {
        this.id = id;
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
