package mp.alex.majorprojectmk2.ui.planetsubadapter;

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

public class PlanetSubAdapter extends RecyclerView.Adapter<PlanetSubAdapter.PlanetSubViewHolder> {

    private final LayoutInflater mInflater;
    private List<PlanetEntity> mPlanetEntity;
    private OnClickListener clickListener;
    private String Meters = " Meters", Parsecs = " Parsecs";
    private long auMeters = 149597870700l;
    private double sfConstant = 0.00000005670367;
    private double solarRad = 695510000;

    private double luminosityCalc, luminosityCalcVar,  radiusAu, radiusMetric, starTeff; //luminosity calculation
    private double eccentricity, semiMajorAxisAU, semiMajorAxisMetric; //planet star distance
    private double planetStarDistMin, planetStarDistMax, avg;
    private double planetCalc, planetCalcVar, albedo; //planetTemp calc


    PlanetSubAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public PlanetSubViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycleview_item_planet, parent, false);
        return new PlanetSubViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PlanetSubViewHolder planetSubViewHolder, int position) {
        if (clickListener != null) {
            planetSubViewHolder.setListener(clickListener);
        }

        //Luminosity calculation here (new method)


        if(mPlanetEntity != null){
            PlanetEntity current = mPlanetEntity.get(position);

            calculations(current);

            planetSubViewHolder.planetItemView.setText(current.getName());
            planetSubViewHolder.distanceItemView.setText(Double.toString(current.getStar_distance()) + Parsecs);
            planetSubViewHolder.distanceMetersItemView.setText(Double.toString(current.getStar_distance()*3.086e+16) + Meters);
            planetSubViewHolder.starItemView.setText("Star Name: " + current.getStar_name());
            planetSubViewHolder.radius.setText("Planet Radius: " + Double.toString(current.getRadius()));
            planetSubViewHolder.discDate.setText("Discovery Date: " + Double.toString(current.getDiscovered()));
            planetSubViewHolder.starAge.setText("Star Age: " + Double.toString(current.getStar_age()));
            planetSubViewHolder.starTemp.setText("Star Temp: " + Double.toString(current.getStar_teff()));
            //Luminosity
            if (luminosityCalcVar != 0) {
                planetSubViewHolder.luminosity.setText("Luminosity: " + Double.toString(luminosityCalcVar));
            } else {
                planetSubViewHolder.luminosity.setText("Luminosity Unavailable");
            }
            //Planet Temp
            if (planetCalcVar != 0) {
                planetSubViewHolder.planetCalc.setText("Planet Temp: " + Double.toString(planetCalcVar));
            } else {
                planetSubViewHolder.planetCalc.setText("Planet Temp Unavailable");
            }
            //Whatever else we want
        } else {
            planetSubViewHolder.planetItemView.setText("No Planets Found");
        }
    }

    /**
     * Perform all calculations regarding temp of a planet
     *
     *     Calculation process:
     *     convert radiusAU into radiusMetric
     *     convert semiMajorAxisAu into semiMajorAxisMetric
     *
     *     luminosity of a star
     *     planet star distance
     *     planet temp
     *
     * @param current
     */
    private void calculations(PlanetEntity current) {
        radiusAu = current.getStar_radius();
        radiusMetric = radiusAu*solarRad;

        semiMajorAxisAU = current.getSemi_major_axis();
        semiMajorAxisMetric = semiMajorAxisAU*auMeters;

        starTeff = current.getStar_teff();

        //Calculate luminosity of a star:
        luminosityCalcVar = 4 * Math.PI * Math.pow(radiusMetric, 2) * sfConstant * Math.pow(starTeff, 4);

        //Calculate min/max dist from star
        eccentricity = current.getEccentricity();
        planetStarDistMax = semiMajorAxisMetric * 1 + eccentricity;
        planetStarDistMin = semiMajorAxisMetric * 1 - eccentricity;
        avg = Math.abs(planetStarDistMax + planetStarDistMin)/2;    //get average of orbit

        //Calculate temp of planet
        albedo = current.getGeometric_albedo();
        planetCalcVar = Math.sqrt(Math.sqrt(luminosityCalc*(1-albedo))/(4*Math.PI * Math.pow(avg, 2) * sfConstant));
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

    class PlanetSubViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView planetItemView, distanceItemView, distanceMetersItemView, starItemView, radius, discDate, starAge, starTemp, luminosity, planetCalc;
        private OnClickListener listener;

        private PlanetSubViewHolder(View itemView) {
            super(itemView);
            planetItemView = itemView.findViewById(R.id.planetView);
            distanceItemView = itemView.findViewById(R.id.distanceView);
            distanceMetersItemView = itemView.findViewById(R.id.distanceMetersView);
            starItemView = itemView.findViewById(R.id.starView);
            radius = itemView.findViewById(R.id.radius);
            discDate = itemView.findViewById(R.id.discovery_date);
            starAge = itemView.findViewById(R.id.star_age);
            starTemp = itemView.findViewById(R.id.star_temp);
            luminosity = itemView.findViewById(R.id.luminosity);
            planetCalc = itemView.findViewById(R.id.planet_temp);
            //Whatever else we want
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
