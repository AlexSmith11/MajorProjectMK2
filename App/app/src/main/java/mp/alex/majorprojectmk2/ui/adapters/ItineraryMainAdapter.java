package mp.alex.majorprojectmk2.ui.adapters;

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

    class ItineraryViewHolder extends RecyclerView.ViewHolder {
        private final TextView itineraryItemView;

        private ItineraryViewHolder(View itemView) {
            super(itemView);
            itineraryItemView = itemView.findViewById(R.id.textView);
        }
    }

    /**
     * When there are no existing view holders to use, creates a new one.
     * Used when page(activity) is first opened.
     * Defines row layout (inflates xml to find view)
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ItineraryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ItineraryViewHolder(itemView);
    }

    /**
     * Replaces old data, puts into old view holders already made.
     * Gets the name of each planet from the PlanetModel class and adds to view holder IF:
     * there are any planets in planetList. If not, displays message: "No Planets"
     * (because... well, there aren't any left to display).
     *
     * Because of depreciation of 'fromHtml', need to check API level and use new or old method.
     * Sets the bullet point for every element in displayed recycler list.
     *
     * Populates the rows xml with info from the item.
     *
     * @param holder
     * @param position
     */
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

    /**
     * Gets position of a word so we can modify/delete specific ones (swipe/hold ability)
     */
    ItineraryListEntity getItineraryAtPosition(int position) {
        return mItineraries.get(position);
    }

    /**
     * When first called, planetList is null so use if not null.
     * @return
     */
    @Override
    public int getItemCount() {
        if (mItineraries != null)
            return mItineraries.size();
        else return 0;
    }
}
