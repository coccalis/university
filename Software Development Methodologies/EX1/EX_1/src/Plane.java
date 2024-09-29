

public class Plane  extends Vehicle{        //κάνουμε extend την κλάση plane στην vehicle

    private boolean isFlying;
    private float wingspan;

    //κατασκευάζουμε τον constructor Plane
    public Plane(String make, String model, int year, float weight, float wingspan) {
        super(make, model, year, weight);

        isFlying = false;

        this.wingspan = wingspan;
    }

    //method fly. Ελέγχουμε αν η needsMaintenance είναι false. Αν είναι false τοτε θα προχωρήσει στο "να πετάξει" το αεροπλάνο και να αυξηθεί ο counter tripsSinceMaintenance. Αλλιώς θα χρειαστεί επισκευή και δεν θα πετάξει.
    public void fly(){
        if(!needsMaintenance){

            isFlying=true;
            tripsSinceMaintenance++;

        }
        else {

            System.out.println("You did more than 80 trips.");
            System.out.println("It is dangerous to fly this airplane. It needs service in order to fly...Please proceed to repair it");
            repair();

        }
    }

    //method land. Αν η tripsSinceMaintenance ξεπεράσει τα 80 ταξίδια τότε η needsMaintenance θα γίνει true και όταν έρθει η στιγμή να πετάξει δεν θα επιτραπεί στο αεροπλάνο μέχρι να γίνει repair.
    public void land(){

        isFlying= false;
        if(tripsSinceMaintenance>80){
            needsMaintenance=true;
        }
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
        return "Info of Plane:" + "\n" + "-Make: " +getMake() + "\n" + "-Model: " +getModel() +"\n" +"-Year: " +getYear() +"\n"+ "-Weight:  " +getWeight() +"\n" + "-Wing Span: " +wingspan  + "\n" + "-Needs Maintenance: " +needsMaintenance + "\n" + "-Trips Since Maintenance: " +tripsSinceMaintenance +"\n"+"------------------------------------------";

    }
}
