package mp.alex.majorprojectmk2.database.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Entity for the planet database.
 * Stores all ~100 columns, ~4000 rows of info
 *
 * Planets have one-to-many relations (one planet can be assigned to many itineraries)
 */

@Entity(tableName = "planet_table")
public class PlanetEntity implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    public int id;

    // region Variables

    String name, planet_status;
    double mass, mass_error_min, mass_error_max, mass_sini, mass_sini_error_min, mass_sini_error_max, radius, radius_error_min, radius_error_max, orbital_period, orbital_period_error_min, orbital_period_error_max, semi_major_axis, semi_major_axis_error_min, semi_major_axis_error_max, eccentricity, eccentricity_error_min, eccentricity_error_max, inclination, inclination_error_min, inclination_error_max, angular_distance, discovered;
    String updated;
    double omega, omega_error_min, omega_error_max, tperi, tperi_error_min, tperi_error_max, tconj, tconj_error_min, tconj_error_max, tzero_tr, tzero_tr_error_min, tzero_tr_error_max, tzero_tr_sec, tzero_tr_sec_error_min, tzero_tr_sec_error_max, lambda_angle, lambda_angle_error_min, lambda_angle_error_max, impact_parameter, impact_parameter_error_min, impact_parameter_error_max, tzero_vr, tzero_vr_error_min, tzero_vr_error_max, k, k_error_min, k_error_max, temp_calculated, temp_calculated_error_min, temp_calculated_error_max, temp_measured, hot_point_lon, geometric_albedo, geometric_albedo_error_min, geometric_albedo_error_max, log_g;
    String publication, detection_type, mass_detection_type, radius_detection_type, alternate_names, molecules, star_name;
    double ra, dec, mag_v, mag_i, mag_j, mag_h, mag_k, star_distance, star_metallicity, star_mass, star_radius;
    String star_sp_type;
    double star_age, star_teff;
    String star_alternate_names;

    // endregion

    // region Constructors

    //returns all column info
    //Need to find out how to insert id into sql table without disrupting the order - see the import method. Leave a blank space?
    public PlanetEntity(@NonNull String name, String planet_status, double mass, double mass_error_min, double mass_error_max, double mass_sini, double mass_sini_error_min, double mass_sini_error_max, double radius, double radius_error_min, double radius_error_max, double orbital_period, double orbital_period_error_min, double orbital_period_error_max, double semi_major_axis, double semi_major_axis_error_min, double semi_major_axis_error_max, double eccentricity, double eccentricity_error_min, double eccentricity_error_max, double inclination, double inclination_error_min, double inclination_error_max, double angular_distance, double discovered, String updated, double omega, double omega_error_min, double omega_error_max, double tperi, double tperi_error_min, double tperi_error_max, double tconj, double tconj_error_min, double tconj_error_max, double tzero_tr, double tzero_tr_error_min, double tzero_tr_error_max, double tzero_tr_sec, double tzero_tr_sec_error_min, double tzero_tr_sec_error_max, double lambda_angle, double lambda_angle_error_min, double lambda_angle_error_max, double impact_parameter, double impact_parameter_error_min, double impact_parameter_error_max, double tzero_vr, double tzero_vr_error_min, double tzero_vr_error_max, double k, double k_error_min, double k_error_max, double temp_calculated, double temp_calculated_error_min, double temp_calculated_error_max, double temp_measured, double hot_point_lon, double geometric_albedo, double geometric_albedo_error_min, double geometric_albedo_error_max, double log_g, String publication, String detection_type, String mass_detection_type, String radius_detection_type, String alternate_names, String molecules, String star_name, double ra, double dec, double mag_v, double mag_i, double mag_j, double mag_h, double mag_k, double star_distance, double star_metallicity, double star_mass, double star_radius, String star_sp_type, double star_age, double star_teff, String star_alternate_names) {
        this.name = name;
        this.planet_status = planet_status;
        this.mass = mass;
        this.mass_error_min = mass_error_min;
        this.mass_error_max = mass_error_max;
        this.mass_sini = mass_sini;
        this.mass_sini_error_min = mass_sini_error_min;
        this.mass_sini_error_max = mass_sini_error_max;
        this.radius = radius;
        this.radius_error_min = radius_error_min;
        this.radius_error_max = radius_error_max;
        this.orbital_period = orbital_period;
        this.orbital_period_error_min = orbital_period_error_min;
        this.orbital_period_error_max = orbital_period_error_max;
        this.semi_major_axis = semi_major_axis;
        this.semi_major_axis_error_min = semi_major_axis_error_min;
        this.semi_major_axis_error_max = semi_major_axis_error_max;
        this.eccentricity = eccentricity;
        this.eccentricity_error_min = eccentricity_error_min;
        this.eccentricity_error_max = eccentricity_error_max;
        this.inclination = inclination;
        this.inclination_error_min = inclination_error_min;
        this.inclination_error_max = inclination_error_max;
        this.angular_distance = angular_distance;
        this.discovered = discovered;
        this.updated = updated;
        this.omega = omega;
        this.omega_error_min = omega_error_min;
        this.omega_error_max = omega_error_max;
        this.tperi = tperi;
        this.tperi_error_min = tperi_error_min;
        this.tperi_error_max = tperi_error_max;
        this.tconj = tconj;
        this.tconj_error_min = tconj_error_min;
        this.tconj_error_max = tconj_error_max;
        this.tzero_tr = tzero_tr;
        this.tzero_tr_error_min = tzero_tr_error_min;
        this.tzero_tr_error_max = tzero_tr_error_max;
        this.tzero_tr_sec = tzero_tr_sec;
        this.tzero_tr_sec_error_min = tzero_tr_sec_error_min;
        this.tzero_tr_sec_error_max = tzero_tr_sec_error_max;
        this.lambda_angle = lambda_angle;
        this.lambda_angle_error_min = lambda_angle_error_min;
        this.lambda_angle_error_max = lambda_angle_error_max;
        this.impact_parameter = impact_parameter;
        this.impact_parameter_error_min = impact_parameter_error_min;
        this.impact_parameter_error_max = impact_parameter_error_max;
        this.tzero_vr = tzero_vr;
        this.tzero_vr_error_min = tzero_vr_error_min;
        this.tzero_vr_error_max = tzero_vr_error_max;
        this.k = k;
        this.k_error_min = k_error_min;
        this.k_error_max = k_error_max;
        this.temp_calculated = temp_calculated;
        this.temp_calculated_error_min = temp_calculated_error_min;
        this.temp_calculated_error_max = temp_calculated_error_max;
        this.temp_measured = temp_measured;
        this.hot_point_lon = hot_point_lon;
        this.geometric_albedo = geometric_albedo;
        this.geometric_albedo_error_min = geometric_albedo_error_min;
        this.geometric_albedo_error_max = geometric_albedo_error_max;
        this.log_g = log_g;
        this.publication = publication;
        this.detection_type = detection_type;
        this.mass_detection_type = mass_detection_type;
        this.radius_detection_type = radius_detection_type;
        this.alternate_names = alternate_names;
        this.molecules = molecules;
        this.star_name = star_name;
        this.ra = ra;
        this.dec = dec;
        this.mag_v = mag_v;
        this.mag_i = mag_i;
        this.mag_j = mag_j;
        this.mag_h = mag_h;
        this.mag_k = mag_k;
        this.star_distance = star_distance;
        this.star_metallicity = star_metallicity;
        this.star_mass = star_mass;
        this.star_radius = star_radius;
        this.star_sp_type = star_sp_type;
        this.star_age = star_age;
        this.star_teff = star_teff;
        this.star_alternate_names = star_alternate_names;
    }

    @Ignore
    protected PlanetEntity(Parcel in) {
        id = in.readInt();
        name = in.readString();
        planet_status = in.readString();
        mass = in.readDouble();
        mass_error_min = in.readDouble();
        mass_error_max = in.readDouble();
        mass_sini = in.readDouble();
        mass_sini_error_min = in.readDouble();
        mass_sini_error_max = in.readDouble();
        radius = in.readDouble();
        radius_error_min = in.readDouble();
        radius_error_max = in.readDouble();
        orbital_period = in.readDouble();
        orbital_period_error_min = in.readDouble();
        orbital_period_error_max = in.readDouble();
        semi_major_axis = in.readDouble();
        semi_major_axis_error_min = in.readDouble();
        semi_major_axis_error_max = in.readDouble();
        eccentricity = in.readDouble();
        eccentricity_error_min = in.readDouble();
        eccentricity_error_max = in.readDouble();
        inclination = in.readDouble();
        inclination_error_min = in.readDouble();
        inclination_error_max = in.readDouble();
        angular_distance = in.readDouble();
        discovered = in.readDouble();
        updated = in.readString();
        omega = in.readDouble();
        omega_error_min = in.readDouble();
        omega_error_max = in.readDouble();
        tperi = in.readDouble();
        tperi_error_min = in.readDouble();
        tperi_error_max = in.readDouble();
        tconj = in.readDouble();
        tconj_error_min = in.readDouble();
        tconj_error_max = in.readDouble();
        tzero_tr = in.readDouble();
        tzero_tr_error_min = in.readDouble();
        tzero_tr_error_max = in.readDouble();
        tzero_tr_sec = in.readDouble();
        tzero_tr_sec_error_min = in.readDouble();
        tzero_tr_sec_error_max = in.readDouble();
        lambda_angle = in.readDouble();
        lambda_angle_error_min = in.readDouble();
        lambda_angle_error_max = in.readDouble();
        impact_parameter = in.readDouble();
        impact_parameter_error_min = in.readDouble();
        impact_parameter_error_max = in.readDouble();
        tzero_vr = in.readDouble();
        tzero_vr_error_min = in.readDouble();
        tzero_vr_error_max = in.readDouble();
        k = in.readDouble();
        k_error_min = in.readDouble();
        k_error_max = in.readDouble();
        temp_calculated = in.readDouble();
        temp_calculated_error_min = in.readDouble();
        temp_calculated_error_max = in.readDouble();
        temp_measured = in.readDouble();
        hot_point_lon = in.readDouble();
        geometric_albedo = in.readDouble();
        geometric_albedo_error_min = in.readDouble();
        geometric_albedo_error_max = in.readDouble();
        log_g = in.readDouble();
        publication = in.readString();
        detection_type = in.readString();
        mass_detection_type = in.readString();
        radius_detection_type = in.readString();
        alternate_names = in.readString();
        molecules = in.readString();
        star_name = in.readString();
        ra = in.readDouble();
        dec = in.readDouble();
        mag_v = in.readDouble();
        mag_i = in.readDouble();
        mag_j = in.readDouble();
        mag_h = in.readDouble();
        mag_k = in.readDouble();
        star_distance = in.readDouble();
        star_metallicity = in.readDouble();
        star_mass = in.readDouble();
        star_radius = in.readDouble();
        star_sp_type = in.readString();
        star_age = in.readDouble();
        star_teff = in.readDouble();
        star_alternate_names = in.readString();
    }

    // endregion

    // region Setters and Getters

    public int getIdPlanet() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getPlanet_status() {
        return this.planet_status;
    }

    public double getMass() {
        return this.mass;
    }

    public double getMass_error_min() {
        return this.mass_error_min;
    }

    public double getMass_error_max() {
        return this.mass_error_max;
    }

    public double getMass_sini() {
        return this.mass_sini;
    }

    public double getMass_sini_error_min() {
        return this.mass_sini_error_min;
    }

    public double getMass_sini_error_max() {
        return this.mass_sini_error_max;
    }

    public double getRadius() {
        return this.radius;
    }

    public double getRadius_error_min() {
        return this.radius_error_min;
    }

    public double getRadius_error_max() {
        return this.radius_error_max;
    }

    public double getOrbital_period() {
        return this.orbital_period;
    }

    public double getOrbital_period_error_min() {
        return this.orbital_period_error_min;
    }

    public double getOrbital_period_error_max() {
        return this.orbital_period_error_max;
    }

    public double getSemi_major_axis() {
        return this.semi_major_axis;
    }

    public double getSemi_major_axis_error_min() {
        return this.semi_major_axis_error_min;
    }

    public double getSemi_major_axis_error_max() {
        return this.semi_major_axis_error_max;
    }

    public double getEccentricity() {
        return this.eccentricity;
    }

    public double getEccentricity_error_min() {
        return this.eccentricity_error_min;
    }

    public double getEccentricity_error_max() {
        return this.eccentricity_error_max;
    }

    public double getInclination() {
        return this.inclination;
    }

    public double getInclination_error_min() {
        return this.inclination_error_min;
    }

    public double getInclination_error_max() {
        return this.inclination_error_max;
    }

    public double getAngular_distance() {
        return this.angular_distance;
    }

    public double getDiscovered() {
        return this.discovered;
    }

    public String getUpdated() {
        return this.updated;
    }

    public double getOmega() {
        return this.omega;
    }

    public double getOmega_error_min() {
        return this.omega_error_min;
    }

    public double getOmega_error_max() {
        return this.omega_error_max;
    }

    public double getTperi() {
        return this.tperi;
    }

    public double getTperi_error_min() {
        return this.tperi_error_min;
    }

    public double getTperi_error_max() {
        return this.tperi_error_max;
    }

    public double getTconj() {
        return this.tconj;
    }

    public double getTconj_error_min() {
        return this.tconj_error_min;
    }

    public double getTconj_error_max() {
        return this.tconj_error_max;
    }

    public double getTzero_tr() {
        return this.tzero_tr;
    }

    public double getTzero_tr_error_min() {
        return this.tzero_tr_error_min;
    }

    public double getTzero_tr_error_max() {
        return this.tzero_tr_error_max;
    }

    public double getTzero_tr_sec() {
        return this.tzero_tr_sec;
    }

    public double getTzero_tr_sec_error_min() {
        return this.tzero_tr_sec_error_min;
    }

    public double getTzero_tr_sec_error_max() {
        return this.tzero_tr_sec_error_max;
    }

    public double getLambda_angle() {
        return this.lambda_angle;
    }

    public double getLambda_angle_error_min() {
        return this.lambda_angle_error_min;
    }

    public double getLambda_angle_error_max() {
        return this.lambda_angle_error_max;
    }

    public double getImpact_parameter() {
        return this.impact_parameter;
    }

    public double getImpact_parameter_error_min() {
        return this.impact_parameter_error_min;
    }

    public double getImpact_parameter_error_max() {
        return this.impact_parameter_error_max;
    }

    public double getTzero_vr() {
        return this.tzero_vr;
    }

    public double getTzero_vr_error_min() {
        return this.tzero_vr_error_min;
    }

    public double getTzero_vr_error_max() {
        return this.tzero_vr_error_max;
    }

    public double getK() {
        return this.k;
    }

    public double getK_error_min() {
        return this.k_error_min;
    }

    public double getK_error_max() {
        return this.k_error_max;
    }

    public double getTemp_calculated() {
        return this.temp_calculated;
    }

    public double getTemp_calculated_error_min() {
        return this.temp_calculated_error_min;
    }

    public double getTemp_calculated_error_max() {
        return this.temp_calculated_error_max;
    }

    public double getTemp_measured() {
        return this.temp_measured;
    }

    public double getHot_point_lon() {
        return this.hot_point_lon;
    }

    public double getGeometric_albedo() {
        return this.geometric_albedo;
    }

    public double getGeometric_albedo_error_min() {
        return this.geometric_albedo_error_min;
    }

    public double getGeometric_albedo_error_max() {
        return this.geometric_albedo_error_max;
    }

    public double getLog_g() {
        return this.log_g;
    }

    public String getPublication() {
        return this.publication;
    }

    public String getDetection_type() {
        return this.detection_type;
    }

    public String getMass_detection_type() {
        return this.mass_detection_type;
    }

    public String getRadius_detection_type() {
        return this.radius_detection_type;
    }

    public String getAlternate_names() {
        return this.alternate_names;
    }

    public String getMolecules() {
        return this.molecules;
    }

    public String getStar_name() {
        return this.star_name;
    }

    public double getRa() {
        return this.ra;
    }

    public double getDec() {
        return this.dec;
    }

    public double getMag_v() {
        return this.mag_v;
    }

    public double getMag_i() {
        return this.mag_i;
    }

    public double getMag_j() {
        return this.mag_j;
    }

    public double getMag_h() {
        return this.mag_h;
    }

    public double getMag_k() {
        return this.mag_k;
    }

    public double getStar_distance() {
        return this.star_distance;
    }

    public double getStar_metallicity() {
        return this.star_metallicity;
    }

    public double getStar_mass() {
        return this.star_mass;
    }

    public double getStar_radius() {
        return this.star_radius;
    }

    public String getStar_sp_type() {
        return this.star_sp_type;
    }

    public double getStar_age() {
        return this.star_age;
    }

    public double getStar_teff() {
        return this.star_teff;
    }

    public String getStar_alternate_names() {
        return this.star_alternate_names;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlanet_status(String planet_status) {
        this.planet_status = planet_status;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public void setMass_error_min(double mass_error_min) {
        this.mass_error_min = mass_error_min;
    }

    public void setMass_error_max(double mass_error_max) {
        this.mass_error_max = mass_error_max;
    }

    public void setMass_sini(double mass_sini) {
        this.mass_sini = mass_sini;
    }

    public void setMass_sini_error_min(double mass_sini_error_min) {
        this.mass_sini_error_min = mass_sini_error_min;
    }

    public void setMass_sini_error_max(double mass_sini_error_max) {
        this.mass_sini_error_max = mass_sini_error_max;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public void setRadius_error_min(double radius_error_min) {
        this.radius_error_min = radius_error_min;
    }

    public void setRadius_error_max(double radius_error_max) {
        this.radius_error_max = radius_error_max;
    }

    public void setOrbital_period(double orbital_period) {
        this.orbital_period = orbital_period;
    }

    public void setOrbital_period_error_min(double orbital_period_error_min) {
        this.orbital_period_error_min = orbital_period_error_min;
    }

    public void setOrbital_period_error_max(double orbital_period_error_max) {
        this.orbital_period_error_max = orbital_period_error_max;
    }

    public void setSemi_major_axis(double semi_major_axis) {
        this.semi_major_axis = semi_major_axis;
    }

    public void setSemi_major_axis_error_min(double semi_major_axis_error_min) {
        this.semi_major_axis_error_min = semi_major_axis_error_min;
    }

    public void setSemi_major_axis_error_max(double semi_major_axis_error_max) {
        this.semi_major_axis_error_max = semi_major_axis_error_max;
    }

    public void setEccentricity(double eccentricity) {
        this.eccentricity = eccentricity;
    }

    public void setEccentricity_error_min(double eccentricity_error_min) {
        this.eccentricity_error_min = eccentricity_error_min;
    }

    public void setEccentricity_error_max(double eccentricity_error_max) {
        this.eccentricity_error_max = eccentricity_error_max;
    }

    public void setInclination(double inclination) {
        this.inclination = inclination;
    }

    public void setInclination_error_min(double inclination_error_min) {
        this.inclination_error_min = inclination_error_min;
    }

    public void setInclination_error_max(double inclination_error_max) {
        this.inclination_error_max = inclination_error_max;
    }

    public void setAngular_distance(double angular_distance) {
        this.angular_distance = angular_distance;
    }

    public void setdiscovered(double discovered) {
        this.discovered = discovered;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public void setOmega(double omega) {
        this.omega = omega;
    }

    public void setOmega_error_min(double omega_error_min) {
        this.omega_error_min = omega_error_min;
    }

    public void setOmega_error_max(double omega_error_max) {
        this.omega_error_max = omega_error_max;
    }

    public void setTperi(double tperi) {
        this.tperi = tperi;
    }

    public void setTperi_error_min(double tperi_error_min) {
        this.tperi_error_min = tperi_error_min;
    }

    public void setTperi_error_max(double tperi_error_max) {
        this.tperi_error_max = tperi_error_max;
    }

    public void setTconj(double tconj) {
        this.tconj = tconj;
    }

    public void setTconj_error_min(double tconj_error_min) {
        this.tconj_error_min = tconj_error_min;
    }

    public void setTconj_error_max(double tconj_error_max) {
        this.tconj_error_max = tconj_error_max;
    }

    public void setTzero_tr(double tzero_tr) {
        this.tzero_tr = tzero_tr;
    }

    public void setTzero_tr_error_min(double tzero_tr_error_min) {
        this.tzero_tr_error_min = tzero_tr_error_min;
    }

    public void setTzero_tr_error_max(double tzero_tr_error_max) {
        this.tzero_tr_error_max = tzero_tr_error_max;
    }

    public void setTzero_tr_sec(double tzero_tr_sec) {
        this.tzero_tr_sec = tzero_tr_sec;
    }

    public void setTzero_tr_sec_error_min(double tzero_tr_sec_error_min) {
        this.tzero_tr_sec_error_min = tzero_tr_sec_error_min;
    }

    public void setTzero_tr_sec_error_max(double tzero_tr_sec_error_max) {
        this.tzero_tr_sec_error_max = tzero_tr_sec_error_max;
    }

    public void setLambda_angle(double lambda_angle) {
        this.lambda_angle = lambda_angle;
    }

    public void setLambda_angle_error_min(double lambda_angle_error_min) {
        this.lambda_angle_error_min = lambda_angle_error_min;
    }

    public void setLambda_angle_error_max(double lambda_angle_error_max) {
        this.lambda_angle_error_max = lambda_angle_error_max;
    }

    public void setImpact_parameter(double impact_parameter) {
        this.impact_parameter = impact_parameter;
    }

    public void setImpact_parameter_error_min(double impact_parameter_error_min) {
        this.impact_parameter_error_min = impact_parameter_error_min;
    }

    public void setImpact_parameter_error_max(double impact_parameter_error_max) {
        this.impact_parameter_error_max = impact_parameter_error_max;
    }

    public void setTzero_vr(double tzero_vr) {
        this.tzero_vr = tzero_vr;
    }

    public void setTzero_vr_error_min(double tzero_vr_error_min) {
        this.tzero_vr_error_min = tzero_vr_error_min;
    }

    public void setTzero_vr_error_max(double tzero_vr_error_max) {
        this.tzero_vr_error_max = tzero_vr_error_max;
    }

    public void setK(double k) {
        this.k = k;
    }

    public void setK_error_min(double k_error_min) {
        this.k_error_min = k_error_min;
    }

    public void setK_error_max(double k_error_max) {
        this.k_error_max = k_error_max;
    }

    public void setTemp_calculated(double temp_calculated) {
        this.temp_calculated = temp_calculated;
    }

    public void setTemp_calculated_error_min(double temp_calculated_error_min) {
        this.temp_calculated_error_min = temp_calculated_error_min;
    }

    public void setTemp_calculated_error_max(double temp_calculated_error_max) {
        this.temp_calculated_error_max = temp_calculated_error_max;
    }

    public void setTemp_measured(double temp_measured) {
        this.temp_measured = temp_measured;
    }

    public void setHot_point_lon(double hot_point_lon) {
        this.hot_point_lon = hot_point_lon;
    }

    public void setGeometric_albedo(double geometric_albedo) {
        this.geometric_albedo = geometric_albedo;
    }

    public void setGeometric_albedo_error_min(double geometric_albedo_error_min) {
        this.geometric_albedo_error_min = geometric_albedo_error_min;
    }

    public void setGeometric_albedo_error_max(double geometric_albedo_error_max) {
        this.geometric_albedo_error_max = geometric_albedo_error_max;
    }

    public void setLog_g(double log_g) {
        this.log_g = log_g;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public void setDetection_type(String detection_type) {
        this.detection_type = detection_type;
    }

    public void setMass_detection_type(String mass_detection_type) {
        this.mass_detection_type = mass_detection_type;
    }

    public void setRadius_detection_type(String radius_detection_type) {
        this.radius_detection_type = radius_detection_type;
    }

    public void setAlternate_names(String alternate_names) {
        this.alternate_names = alternate_names;
    }

    public void setMolecules(String molecules) {
        this.molecules = molecules;
    }

    public void setStar_name(String star_name) {
        this.star_name = star_name;
    }

    public void setRa(double ra) {
        this.ra = ra;
    }

    public void setDec(double dec) {
        this.dec = dec;
    }

    public void setMag_v(double mag_v) {
        this.mag_v = mag_v;
    }

    public void setMag_i(double mag_i) {
        this.mag_i = mag_i;
    }

    public void setMag_j(double mag_j) {
        this.mag_j = mag_j;
    }

    public void setMag_h(double mag_h) {
        this.mag_h = mag_h;
    }

    public void setMag_k(double mag_k) {
        this.mag_k = mag_k;
    }

    public void setStar_distance(double star_distance) {
        this.star_distance = star_distance;
    }

    public void setStar_metallicity(double star_metallicity) {
        this.star_metallicity = star_metallicity;
    }

    public void setStar_mass(double star_mass) {
        this.star_mass = star_mass;
    }

    public void setStar_radius(double star_radius) {
        this.star_radius = star_radius;
    }

    public void setStar_sp_type(String star_sp_type) {
        this.star_sp_type = star_sp_type;
    }

    public void setStar_age(double star_age) {
        this.star_age = star_age;
    }

    public void setStar_teff(double star_teff) {
        this.star_teff = star_teff;
    }

    public void setStar_alternate_names(String star_alternate_names) {
        this.star_alternate_names = star_alternate_names;
    }

    // endregion

    // region Parcelable

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(planet_status);
        dest.writeDouble(mass);
        dest.writeDouble(mass_error_min);
        dest.writeDouble(mass_error_max);
        dest.writeDouble(mass_sini);
        dest.writeDouble(mass_sini_error_min);
        dest.writeDouble(mass_sini_error_max);
        dest.writeDouble(radius);
        dest.writeDouble(radius_error_min);
        dest.writeDouble(radius_error_max);
        dest.writeDouble(orbital_period);
        dest.writeDouble(orbital_period_error_min);
        dest.writeDouble(orbital_period_error_max);
        dest.writeDouble(semi_major_axis);
        dest.writeDouble(semi_major_axis_error_min);
        dest.writeDouble(semi_major_axis_error_max);
        dest.writeDouble(eccentricity);
        dest.writeDouble(eccentricity_error_min);
        dest.writeDouble(eccentricity_error_max);
        dest.writeDouble(inclination);
        dest.writeDouble(inclination_error_min);
        dest.writeDouble(inclination_error_max);
        dest.writeDouble(angular_distance);
        dest.writeDouble(discovered);
        dest.writeString(updated);
        dest.writeDouble(omega);
        dest.writeDouble(omega_error_min);
        dest.writeDouble(omega_error_max);
        dest.writeDouble(tperi);
        dest.writeDouble(tperi_error_min);
        dest.writeDouble(tperi_error_max);
        dest.writeDouble(tconj);
        dest.writeDouble(tconj_error_min);
        dest.writeDouble(tconj_error_max);
        dest.writeDouble(tzero_tr);
        dest.writeDouble(tzero_tr_error_min);
        dest.writeDouble(tzero_tr_error_max);
        dest.writeDouble(tzero_tr_sec);
        dest.writeDouble(tzero_tr_sec_error_min);
        dest.writeDouble(tzero_tr_sec_error_max);
        dest.writeDouble(lambda_angle);
        dest.writeDouble(lambda_angle_error_min);
        dest.writeDouble(lambda_angle_error_max);
        dest.writeDouble(impact_parameter);
        dest.writeDouble(impact_parameter_error_min);
        dest.writeDouble(impact_parameter_error_max);
        dest.writeDouble(tzero_vr);
        dest.writeDouble(tzero_vr_error_min);
        dest.writeDouble(tzero_vr_error_max);
        dest.writeDouble(k);
        dest.writeDouble(k_error_min);
        dest.writeDouble(k_error_max);
        dest.writeDouble(temp_calculated);
        dest.writeDouble(temp_calculated_error_min);
        dest.writeDouble(temp_calculated_error_max);
        dest.writeDouble(temp_measured);
        dest.writeDouble(hot_point_lon);
        dest.writeDouble(geometric_albedo);
        dest.writeDouble(geometric_albedo_error_min);
        dest.writeDouble(geometric_albedo_error_max);
        dest.writeDouble(log_g);
        dest.writeString(publication);
        dest.writeString(detection_type);
        dest.writeString(mass_detection_type);
        dest.writeString(radius_detection_type);
        dest.writeString(alternate_names);
        dest.writeString(molecules);
        dest.writeString(star_name);
        dest.writeDouble(ra);
        dest.writeDouble(dec);
        dest.writeDouble(mag_v);
        dest.writeDouble(mag_i);
        dest.writeDouble(mag_j);
        dest.writeDouble(mag_h);
        dest.writeDouble(mag_k);
        dest.writeDouble(star_distance);
        dest.writeDouble(star_metallicity);
        dest.writeDouble(star_mass);
        dest.writeDouble(star_radius);
        dest.writeString(star_sp_type);
        dest.writeDouble(star_age);
        dest.writeDouble(star_teff);
        dest.writeString(star_alternate_names);
    }

    public static final Creator<PlanetEntity> CREATOR = new Creator<PlanetEntity>() {
        @Override
        public PlanetEntity createFromParcel(Parcel in) {
            return new PlanetEntity(in);
        }

        @Override
        public PlanetEntity[] newArray(int size) {
            return new PlanetEntity[size];
        }
    };

    // endregion
}
