

public class Ticket implements Comparable <Ticket> {
        private String ticketID;
        private String issueDate;
        private String name;
        private String destination;
        private String dates;
        private String passengers;
        private String luggage;
        private String travelClass;
        private String price;

        public Ticket(String ticketID, String issueDate, String name, String destination,String price,String dates, String passengers, String luggage, String travelClass ){

                this.ticketID = ticketID;
                this.issueDate = issueDate;
                this.name= name;
                this.destination=destination;
                this.dates=dates;
                this.passengers=passengers;
                this.luggage=luggage;
                this.travelClass=travelClass;
                this.price=price;
        }

        // Αρχικοποιήση των minPrice και maxPrice στην κλάση TicketHistoryFrame.
        public Ticket(){
                this("","","","","","","","","");
        }

        public String getTicketID() {
                return ticketID;
        }

        public void setTicketID(String ticketID) {
                this.ticketID = ticketID;
        }

        public String getIssueDate() {
                return issueDate;
        }

        public void setIssueDate(String issueDate) {
                this.issueDate = issueDate;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getDestination() {
                return destination;
        }

        public void setDestination(String destination) {
                this.destination = destination;
        }

        public String getDates() {
                return dates;
        }

        public void setDates(String dates) {
                this.dates = dates;
        }

        public String getPassengers() {
                return passengers;
        }

        public void setPassengers(String passengers) {
                this.passengers = passengers;
        }

        public String getLuggage() {
                return luggage;
        }

        public void setLuggage(String luggage) {
                this.luggage = luggage;
        }

        public String getTravelClass() {
                return travelClass;
        }

        public void setTravelClass(String travelClass) {
                this.travelClass = travelClass;
        }

        public String getPrice() {
                return  price;
        }

        public void setPrice(String price) {
                this.price = price;
        }


        // toString για να εμφανίσει το περιεχόμενα του εισητηρίου
        @Override
        public String toString(){
                return ticketID + "\t" +  issueDate + "\t" + name + "\t" + destination+ "\t " +getPrice()  +"\t" + dates + "\t" + passengers + "\t" + luggage + "\t" + travelClass;
        }

        //Χρησιμοποιούμε την compareTo για την ταξινόμηση του ticketList
        @Override
        public int compareTo(Ticket ticket) {
                double price_1 = Double.parseDouble(getPrice());
                double price_2 = Double.parseDouble(ticket.getPrice());

                return Double.compare(price_1,price_2); // συγκρίνουμε τις τιμές του πίνακα.
        }






}
