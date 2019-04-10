package mp.alex.majorprojectmk2.database;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import mp.alex.majorprojectmk2.R;
import mp.alex.majorprojectmk2.database.entities.ItineraryListEntity;

/**
 * This class renders recycler views with the pre-defined layouts
 * (recycle_listener, itinerary_sub_list_row),  <-- Replace with non-prototype names
 * Gets info from database, and adds it to the recycler view
 *
 * Only want to handle ItineraryEntity NOT PlanetEntity or PlanetItinerary
 * So only making new itinerary names
 */

public class ItineraryMainAdapter extends RecyclerView.Adapter<ItineraryMainAdapter.ItineraryViewHolder> {

    private final LayoutInflater mInflater;
    private List<ItineraryListEntity> mItineraries;

    ItineraryMainAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ItineraryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ItineraryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItineraryViewHolder holder, int position) {
        if(mItineraries != null) {
            ItineraryListEntity current = mItineraries.get(position);
            holder.itineraryItemView.setText(current.getItineraryListName());
        } else {
            holder.itineraryItemView.setText("No Itinerary");
        }
    }

    void setItineraries(List<ItineraryListEntity> itineraries) {
        mItineraries = itineraries;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mItineraries != null)
            return mItineraries.size();
        else return 0;
    }

    class ItineraryViewHolder extends RecyclerView.ViewHolder {
        private final TextView itineraryItemView;

        private ItineraryViewHolder(View itemView) {
            super(itemView);
            itineraryItemView = itemView.findViewById(R.id.textView);
        }
    }

}
