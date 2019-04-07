package mp.alex.majorprojectmk2.database.models;

/**
 * Model for the itinerary database.
 * Stores all x columns, y rows of info
 */

public class ItineraryListModel {

    int id;
    String ItineraryListName;

    /**
     * Constructors
     */
    public ItineraryListModel() {

    }

    //Just name
    public ItineraryListModel(String ItineraryListName) {
        this.ItineraryListName = ItineraryListName;
    }

    //Name and id (more useful)
    public ItineraryListModel(int id, String ItineraryListName) {
        this.id = id;
        this.ItineraryListName = ItineraryListName;
    }

    //setters
    public void setId(int id) {
        this.id = id;
    }

    public void setItineraryListName(String ItineraryListName) {
        this.ItineraryListName = ItineraryListName;
    }

    //getters
    public double getId() {
        return this.id;
    }

    public String getItineraryListName() {
        return this.ItineraryListName;
    }



}
