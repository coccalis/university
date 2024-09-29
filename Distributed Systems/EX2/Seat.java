/*

ΤΜΗΜΑ: ΣΤ3-Β
*/ 
import java.io.Serializable;
//Create seat object
public class Seat implements Serializable{
    String type;
    int availableSeats;
    double cost;

    public Seat(String type, int availableSeats, double cost){
        this.type = type;
        this.availableSeats = availableSeats;
        this.cost = cost;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString(){
         return "\nSeat : " + type  + "\nNumber of seats :" + availableSeats + "\nCost :" + cost + "\n";
    }

}
