package mp.alex.majorprojectmk2.ui.planetadapter;

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

public class PlanetAdapter extends RecyclerView.Adapter<PlanetAdapter.PlanetViewHolder> {

    private final LayoutInflater mInflater;
    private List<PlanetEntity> mPlanetEntity;
    private List<String> mPlanetEntityNames;

    PlanetAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public PlanetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new PlanetViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PlanetViewHolder planetViewHolder, int position) {
        if(mPlanetEntity != null){
            PlanetEntity current = mPlanetEntity.get(position);
            planetViewHolder.planetItemView.setText(current.getName());
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

    class PlanetViewHolder extends RecyclerView.ViewHolder {
        private final TextView planetItemView;

        private PlanetViewHolder(View itemView) {
            super(itemView);
            planetItemView = itemView.findViewById(R.id.textView);
        }
    }
}
