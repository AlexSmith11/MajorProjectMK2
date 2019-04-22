package mp.alex.majorprojectmk2.ui.itineraryadapter;

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
    private OnClickListener clickListener;

    ItineraryMainAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
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
     * Gets the name of each itinerary from the ItineraryListEntity class and adds to view holder IF:
     * there are any itineraries in itineraryList. If not, displays message: "No itineraries Found"
     *
     * Populates the rows xml with info from the item.
     *
     * @param itineraryViewHolder
     * @param position
     */
    @Override
    public void onBindViewHolder(ItineraryViewHolder itineraryViewHolder, int position) {
        if (clickListener != null) {
            itineraryViewHolder.setListener(clickListener);
        }
        if(mItineraries != null) {
            ItineraryListEntity current = mItineraries.get(position);
            itineraryViewHolder.itineraryItemView.setText(current.getItineraryListName());
        } else {
            itineraryViewHolder.itineraryItemView.setText("No Itinerary");
        }
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

    /**
     * Gets position of a word so we can modify/delete specific ones (swipe/hold ability)
     */
    ItineraryListEntity getItineraryAtPosition(int position) {
        return mItineraries.get(position);
    }

    void setItineraries(List<ItineraryListEntity> itineraries) {
        mItineraries = itineraries;
        notifyDataSetChanged();
    }

    class ItineraryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView itineraryItemView;
        private OnClickListener listener;

        private ItineraryViewHolder(View itemView) {
            super(itemView);
            itineraryItemView = itemView.findViewById(R.id.planetView);
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

    public void setClickListener(OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public List<ItineraryListEntity> getItineraryListEntity() {
        return mItineraries;
    }

    public interface OnClickListener {
        void onItemClick(int position, View v);
    }










}
