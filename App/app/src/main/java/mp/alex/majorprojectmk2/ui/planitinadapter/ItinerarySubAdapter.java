package mp.alex.majorprojectmk2.ui.planitinadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import mp.alex.majorprojectmk2.R;
import mp.alex.majorprojectmk2.database.entities.PlanetEntity;
import mp.alex.majorprojectmk2.database.entities.PlanetItinerary;

/**
 * This class renders recycler views with the pre-defined layouts
 * (recycle_listener, itinerary_sub_list_row),  <-- Replace with non-prototype names
 * Gets info from database, and adds it to the recycler view
 *
 * Only want to handle ItineraryEntity NOT PlanetEntity or PlanetItinerary
 * So only making new itinerary names
 */
public class ItinerarySubAdapter extends RecyclerView.Adapter<ItinerarySubAdapter.ItinerarySubViewHolder> {

    private final LayoutInflater mInflater;
    private List<PlanetEntity> mPlanetEntity;
    private List<PlanetItinerary> mPlanetItinerary;
    private OnClickListener clickListener;
    private String Meters = " Meters", Parsecs = " Parsecs";


    ItinerarySubAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ItinerarySubViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ItinerarySubViewHolder(itemView);
    }

    /**
     * Replaces old data, puts into old view holders already made.
     * Gets the name, distance, etc of each planet from the PlanetEntity class and adds to view holder IF:
     * there are any planets in planetList. If not, displays message: "No Planets Found"
     *
     * Populates the rows xml with info from the item.
     * @param itinerarySubViewHolder
     * @param position
     */
    @Override
    public void onBindViewHolder(ItinerarySubViewHolder itinerarySubViewHolder, int position) {
        if (clickListener != null) {
            itinerarySubViewHolder.setListener(clickListener);
        }

        if(mPlanetEntity != null){
            PlanetEntity current = mPlanetEntity.get(position);
            itinerarySubViewHolder.planetItemView.setText(current.getName());
            itinerarySubViewHolder.distanceItemView.setText(Double.toString(current.getStar_distance()) + Meters);
            itinerarySubViewHolder.distanceMetersItemView.setText(Double.toString(current.getStar_distance()*3.086e+16) + Parsecs);
            itinerarySubViewHolder.starItemView.setText(current.getStar_name());
        } else {
            itinerarySubViewHolder.planetItemView.setText("No Planets Found");
        }
    }

    @Override
    public int getItemCount() {
        if (mPlanetEntity != null)
            return mPlanetEntity.size();
        else return 0;
    }

    void setPlanetNameCalc(List<PlanetEntity> planets) {
        mPlanetEntity = planets;
        notifyDataSetChanged();
    }

    /**
     * Internal class to assign each element in an item of recycler view.
     * Uses internal listener to listen for when item is tapped.
     */
    class ItinerarySubViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView planetItemView, distanceItemView, distanceMetersItemView, starItemView;
        private OnClickListener listener;

        private ItinerarySubViewHolder(View itemView) {
            super(itemView);
            planetItemView = itemView.findViewById(R.id.planetView);
            distanceItemView = itemView.findViewById(R.id.distanceView);
            distanceMetersItemView = itemView.findViewById(R.id.distanceMetersView);
            starItemView = itemView.findViewById(R.id.starView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onItemClick(getAdapterPosition(), v);
            }
        }

        public void setListener(OnClickListener listener) {
            this.listener = listener;
        }
    }

    /**
     * Internal listener setup for when individual item is tapped
     * use when planet is tapped -> PlanetSub
     * @param clickListener
     */
    public void setClickListener(OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public List<PlanetEntity> getPlanetEntity() {
        return mPlanetEntity;
    }

    public interface OnClickListener {
        void onItemClick(int position, View v);
    }
}
