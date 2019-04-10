package mp.alex.majorprojectmk2.database.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Model for the planet database.
 * Stores all ~100 columns, 4000 rows of info
 *
 * Planets have one-to-many relations (one planet can be assigned to many itineraries)
 */

@Entity(tableName = "planet_table")
public class PlanetEntity {

    //This sucks, but need all the variables (at least, the large majority).

    @PrimaryKey//(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    String name, planet_status;
    double mass, mass_error_min, mass_error_max, mass_sini, mass_sini_error_min, mass_sini_error_max, radius, radius_error_min, radius_error_max, orbital_period, orbital_period_error_min, orbital_period_error_max, semi_major_axis, semi_major_axis_error_min, semi_major_axis_error_max, eccentricity, eccentricty_error_min, eccentricity_error_max, inclination, inclination_error_min, inclination_error_max, angular_distance, discorvered;
    String updated;
    double omega, omega_error_min, omega_error_max, tperi, tperi_error_min, tperi_error_max, tconj, tconj_error_min, tconj_error_max, tzero_tr, tzero_tr_error_min, tzero_tr_error_max, tzero_tr_sec, tzero_tr_sec_error_min, tzero_tr_sec_error_max, lambda_angle, lambda_angle_error_min, lambda_angle_error_max, impact_parameter, impact_parameter_error_min, impact_parameter_error_max, tzero_vr, tzero_vr_error_min, tzero_vr_error_max, k, k_error_min, k_error_max, temp_calculated, temp_calculated_error_min, temp_calculated_error_max, temp_measured, hot_podouble_lon, geometric_albedo, geometric_albedo_error_min, geometric_albedo_error_max, log_g;
    String publication, detection_type, mass_detection_type, radius_detection_type, alternate_names, molecules, star_name;
    double ra, dec, mag_v, mag_i, mag_j, mag_h, mag_k, star_distance, star_metallicity, star_mass, star_radius;
    String star_sp_type;
    double star_age, star_teff;
    String star_alternate_names;

    //Constructors


    public PlanetEntity() {
        this.id = id;
        this.name = name;
    }


    //returns all column info
    //Need to find out how to insert id into sql table without disrupting the order - see the import method. Leave a blank space?
    public PlanetEntity(int id, String name, String Planet_status, double mass, double mass_error_min, double mass_error_max, double mass_sini, double mass_sini_error_min, double mass_sini_error_max, double radius, double radius_error_min, double radius_error_max, double orbital_period, double orbital_period_error_min, double orbital_period_error_max, double semi_major_axis, double semi_major_axis_error_min, double semi_major_axis_error_max, double eccentricity, double eccentricty_error_min, double eccentricity_error_max, double inclination, double inclination_error_min, double inclination_error_max, double angular_distance, double discorvered, String updated, double omega, double omega_error_min, double omega_error_max, double tperi, double tperi_error_min, double tperi_error_max, double tconj, double tconj_error_min, double tconj_error_max, double tzero_tr, double tzero_tr_error_min, double tzero_tr_error_max, double tzero_tr_sec, double tzero_tr_sec_error_min, double tzero_tr_sec_error_max, double lambda_angle, double lambda_angle_error_min, double lambda_angle_error_max, double impact_parameter, double impact_parameter_error_min, double impact_parameter_error_max, double tzero_vr, double tzero_vr_error_min, double tzero_vr_error_max, double k, double k_error_min, double k_error_max, double temp_calculated, double temp_calculated_error_min, double temp_calculated_error_max, double temp_measured, double hot_podouble_lon, double geometric_albedo, double geometric_albedo_error_min, double geometric_albedo_error_max, double log_g, String publication, String detection_type, String mass_detection_type, String radius_detection_type, String alternate_names, String molecules, String star_name, double ra, double dec, double mag_v, double mag_i, double mag_j, double mag_h, double mag_k, double star_distance, double star_metallicity, double star_mass, double star_radius, String star_sp_type, double star_age, double star_teff, String star_alternate_names) {
        this.id = id;
        this.name = name;
        this.planet_status = Planet_status;
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
        this.eccentricty_error_min = eccentricty_error_min;
        this.eccentricity_error_max = eccentricity_error_max;
        this.inclination = inclination;
        this.inclination_error_min = inclination_error_min;
        this.inclination_error_max = inclination_error_max;
        this.angular_distance = angular_distance;
        this.discorvered = discorvered;
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
        this.hot_podouble_lon = hot_podouble_lon;
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

    //----------------------------------- Setters & Getters -----------------------------------
    //*****************************************Getters*****************************************

    public int getIdPlanet() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPlanet_status() {
        return planet_status;
    }

    public double getMass() {
        return mass;
    }

    public double getMass_error_min() {
        return mass_error_min;
    }

    public double getMass_error_max() {
        return mass_error_max;
    }

    public double getMass_sini() {
        return mass_sini;
    }

    public double getMass_sini_error_min() {
        return mass_sini_error_min;
    }

    public double getMass_sini_error_max() {
        return mass_sini_error_max;
    }

    public double getRadius() {
        return radius;
    }

    public double getRadius_error_min() {
        return radius_error_min;
    }

    public double getRadius_error_max() {
        return radius_error_max;
    }

    public double getOrbital_period() {
        return orbital_period;
    }

    public double getOrbital_period_error_min() {
        return orbital_period_error_min;
    }

    public double getOrbital_period_error_max() {
        return orbital_period_error_max;
    }

    public double getSemi_major_axis() {
        return semi_major_axis;
    }

    public double getSemi_major_axis_error_min() {
        return semi_major_axis_error_min;
    }

    public double getSemi_major_axis_error_max() {
        return semi_major_axis_error_max;
    }

    public double getEccentricity() {
        return eccentricity;
    }

    public double getEccentricty_error_min() {
        return eccentricty_error_min;
    }

    public double getEccentricity_error_max() {
        return eccentricity_error_max;
    }

    public double getInclination() {
        return inclination;
    }

    public double getInclination_error_min() {
        return inclination_error_min;
    }

    public double getInclination_error_max() {
        return inclination_error_max;
    }

    public double getAngular_distance() {
        return angular_distance;
    }

    public double getDiscovered() {
        return discorvered;
    }

    public String getUpdated() {
        return updated;
    }

    public double getOmega() {
        return omega;
    }

    public double getOmega_error_min() {
        return omega_error_min;
    }

    public double getOmega_error_max() {
        return omega_error_max;
    }

    public double getTperi() {
        return tperi;
    }

    public double getTperi_error_min() {
        return tperi_error_min;
    }

    public double getTperi_error_max() {
        return tperi_error_max;
    }

    public double getTconj() {
        return tconj;
    }

    public double getTconj_error_min() {
        return tconj_error_min;
    }

    public double getTconj_error_max() {
        return tconj_error_max;
    }

    public double getTzero_tr() {
        return tzero_tr;
    }

    public double getTzero_tr_error_min() {
        return tzero_tr_error_min;
    }

    public double getTzero_tr_error_max() {
        return tzero_tr_error_max;
    }

    public double getTzero_tr_sec() {
        return tzero_tr_sec;
    }

    public double getTzero_tr_sec_error_min() {
        return tzero_tr_sec_error_min;
    }

    public double getTzero_tr_sec_error_max() {
        return tzero_tr_sec_error_max;
    }

    public double getLambda_angle() {
        return lambda_angle;
    }

    public double getLambda_angle_error_min() {
        return lambda_angle_error_min;
    }

    public double getLambda_angle_error_max() {
        return lambda_angle_error_max;
    }

    public double getImpact_parameter() {
        return impact_parameter;
    }

    public double getImpact_parameter_error_min() {
        return impact_parameter_error_min;
    }

    public double getImpact_parameter_error_max() {
        return impact_parameter_error_max;
    }

    public double getTzero_vr() {
        return tzero_vr;
    }

    public double getTzero_vr_error_min() {
        return tzero_vr_error_min;
    }

    public double getTzero_vr_error_max() {
        return tzero_vr_error_max;
    }

    public double getK() {
        return k;
    }

    public double getK_error_min() {
        return k_error_min;
    }

    public double getK_error_max() {
        return k_error_max;
    }

    public double getTemp_calculated() {
        return temp_calculated;
    }

    public double getTemp_calculated_error_min() {
        return temp_calculated_error_min;
    }

    public double getTemp_calculated_error_max() {
        return temp_calculated_error_max;
    }

    public double getTemp_measured() {
        return temp_measured;
    }

    public double getHot_podouble_lon() {
        return hot_podouble_lon;
    }

    public double getGeometric_albedo() {
        return geometric_albedo;
    }

    public double getGeometric_albedo_error_min() {
        return geometric_albedo_error_min;
    }

    public double getGeometric_albedo_error_max() {
        return geometric_albedo_error_max;
    }

    public double getLog_g() {
        return log_g;
    }

    public String getPublication() {
        return publication;
    }

    public String getDetection_type() {
        return detection_type;
    }

    public String getMass_detection_type() {
        return mass_detection_type;
    }

    public String getRadius_detection_type() {
        return radius_detection_type;
    }

    public String getAlternate_names() {
        return alternate_names;
    }

    public String getMolecules() {
        return molecules;
    }

    public String getStar_name() {
        return star_name;
    }

    public double getRa() {
        return ra;
    }

    public double getDec() {
        return dec;
    }

    public double getMag_v() {
        return mag_v;
    }

    public double getMag_i() {
        return mag_i;
    }

    public double getMag_j() {
        return mag_j;
    }

    public double getMag_h() {
        return mag_h;
    }

    public double getMag_k() {
        return mag_k;
    }

    public double getStar_distance() {
        return star_distance;
    }

    public double getStar_metallicity() {
        return star_metallicity;
    }

    public double getStar_mass() {
        return star_mass;
    }

    public double getStar_radius() {
        return star_radius;
    }

    public String getStar_sp_type() {
        return star_sp_type;
    }

    public double getStar_age() {
        return star_age;
    }

    public double getStar_teff() {
        return star_teff;
    }

    public String getStar_alternate_names() {
        return star_alternate_names;
    }

    //*******************************************Setters***************************************

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlanet_status(String lanet_status) {
        this.planet_status = lanet_status;
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

    public void setEccentricty_error_min(double eccentricty_error_min) {
        this.eccentricty_error_min = eccentricty_error_min;
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

    public void setDiscorvered(double discorvered) {
        this.discorvered = discorvered;
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

    public void setHot_podouble_lon(double hot_podouble_lon) {
        this.hot_podouble_lon = hot_podouble_lon;
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
}
