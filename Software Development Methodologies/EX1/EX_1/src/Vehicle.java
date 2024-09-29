
public class Vehicle {

    // Παραθέτουμε αρχικά τις ιδιότητες με τους κατάλληλους τύπους.
    private String make;
    private String model;
    private int year;
    private float weight;

    protected boolean needsMaintenance;
    protected int tripsSinceMaintenance;

    //κατασκευάζουμε τον constructor vehicle..
    public Vehicle(String make, String model, int year, float weight) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.weight = weight;

        this.needsMaintenance = false;
        this.tripsSinceMaintenance=0;

    }

    // Δημιουργούμε τα getters and setters.


    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public boolean isNeedsMaintenance() {
        return needsMaintenance;
    }

    public void setNeedsMaintenance(boolean needsMaintenance) {
        this.needsMaintenance = needsMaintenance;
    }

    public int getTripsSinceMaintenance() {
        return tripsSinceMaintenance;
    }

    public void setTripsSinceMaintenance(int tripsSinceMaintenance) {
        this.tripsSinceMaintenance = tripsSinceMaintenance;
    }


    //method repair
    public void repair(){
        System.out.println("----------------------------------------");
        System.out.println("Please be patient while we fix the issue");
        tripsSinceMaintenance = 0;
        needsMaintenance = false;
        System.out.println("Successfully fixed!");
        System.out.println("----------------------------------------");
    }

    //method toString για την εκτύπωση πληροφοριών
    public String toString(){

        System.out.println("------------------------------------------");
        return "For Vehicle:" + "\n" + "-Make: " +make + "\n" + "-Model: " +model +"\n" +"-Year: " +year +"\n"+ "-Weight:  " +weight +"\n" + "-Needs Maintenance: " +needsMaintenance + "\n" + "-Trips Since Maintenance: " +tripsSinceMaintenance + "\n" + "------------------------------------------";

    }

}
