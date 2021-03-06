package mp.alex.majorprojectmk2.ui.planetsubadapter;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import mp.alex.majorprojectmk2.R;
import mp.alex.majorprojectmk2.database.PlanetViewModel;
import mp.alex.majorprojectmk2.database.entities.PlanetEntity;

public class PlanetSub extends AppCompatActivity {

    private List<PlanetEntity> planets;
    private PlanetViewModel mPlanetViewModel;

    public static final String PLANET_DATA_KEY = "planet_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet_sub);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final PlanetSubAdapter adapter = new PlanetSubAdapter(this);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mPlanetViewModel = ViewModelProviders.of(this).get(PlanetViewModel.class);

        Bundle extras = getIntent().getExtras();
        ArrayList<PlanetEntity> planets = new ArrayList<>();
        if (extras != null && extras.containsKey(PLANET_DATA_KEY)) {
            PlanetEntity entity = extras.getParcelable(PLANET_DATA_KEY);
            planets.add(entity);
        }
        adapter.setPlanetNameCalc(planets);



    }
}