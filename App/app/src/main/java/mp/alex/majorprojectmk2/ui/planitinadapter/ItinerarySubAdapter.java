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

public class ItinerarySubAdapter extends RecyclerView.Adapter<ItinerarySubAdapter.ItinerarySubViewHolder> {

    private final LayoutInflater mInflater;
    private List<PlanetEntity> mPlanetEntity;
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