package mp.alex.majorprojectmk2.ui.planetadapter;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import mp.alex.majorprojectmk2.R;
import mp.alex.majorprojectmk2.database.entities.PlanetEntity;

public class PlanetAdapter extends RecyclerView.Adapter<PlanetAdapter.PlanetViewHolder> {

    private final LayoutInflater mInflater;
    private List<PlanetEntity> mPlanetEntity;
    private OnClickListener clickListener;
    private String Meters = " Meters", Parsecs = " Parsecs";


    PlanetAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public PlanetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new PlanetViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(PlanetViewHolder planetViewHolder, int position) {
        if (clickListener != null) {
            planetViewHolder.setListener(clickListener);
        }

        if(mPlanetEntity != null){
            PlanetEntity current = mPlanetEntity.get(position);
            planetViewHolder.planetItemView.setText(current.getName());
            planetViewHolder.distanceItemView.setText(Double.toString(current.getStar_distance()) + Parsecs);
            planetViewHolder.distanceMetersItemView.setText(Double.toString(current.getStar_distance()*3.086e+16) + Meters);
            planetViewHolder.starItemView.setText(current.getStar_name());
        } else {
            planetViewHolder.planetItemView.setText("No Planets Found");
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

    class PlanetViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView planetItemView, distanceItemView, distanceMetersItemView, starItemView;
        private OnClickListener listener;

        private PlanetViewHolder(View itemView) {
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
