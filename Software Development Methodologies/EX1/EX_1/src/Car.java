

public class Car extends Vehicle{           //κάνουμε extend την κλάση car στην vehicle

    private boolean isDriving;
    private int horsepower;

    // Δημιουργούμε τον constructor.
    public Car(String make, String model, int year, float weight, int horsepower) {
        super(make, model, year, weight);
        this.isDriving = isDriving;
        this.horsepower = horsepower;
    }

    //method Drive.. Αν η tripsSinceMaintenance ξεπεράσει το 100 τοτε θα κληθεί η repair για να "επιδιορθώσει" το όχημα
    public void drive(){
        isDriving=true;
        tripsSinceMaintenance++;
        if(tripsSinceMaintenance>100){
            System.out.println("You did more than 100 trips..The car needs repairing.");
            needsMaintenance=true;
            repair();
        }

    }

    public void stop() {

        isDriving=false;

    }

    //κάνουμε override την method repair από την κλάση vehicle.
    @Override
    public void repair() {
        System.out.println("----------------------------------------");
        System.out.println("Please be patient while we fix the issue.");
        tripsSinceMaintenance = 0;
        needsMaintenance = false;
        System.out.println("Successfully fixed!");
        System.out.println("----------------------------------------");

    }
    // Επίσης κάνουμε override την method toString από την κλάση vehicle
    @Override
    public String toString() {

        System.out.println("------------------------------------------");
        return "Info of Car:" + "\n" + "-Make: " +getMake() + "\n" + "-Model: " +getModel() +"\n" +"-Year: " +getYear() +"\n"+ "-Weight:  " +getWeight() +"\n" + "-Horse Power: " +horsepower + "\n" + "-Needs Maintenance: " +needsMaintenance   + "\n" +"-Trips Since Maintenance: " +tripsSinceMaintenance +"\n"+ "------------------------------------------";

    }
}
