
public class Main {

    public static void main(String[] args) {

        //object car1
        Car car1=new Car("make_1", "model_1", 2018, 800, 130);

        System.out.println("Trip by car1");
        System.out.println("-----------");
        // Χρησιμοποιούμε την επανάληψη for για να πραγματοποιήσουμε τα ταξίδια.
        for(int i=0; i < 110; i++ ){

            car1.drive();
            car1.stop();


        }
        //Εμφανίζουμε τα στοιχεία του object
        System.out.println(car1);


        //object car2
        Car car2=new Car("make_2", "model_2", 2019, 100, 100);

        System.out.println("Trip by car2");
        System.out.println("-----------");
        // Χρησιμοποιούμε την επανάληψη for για να πραγματοποιήσουμε τα ταξίδια.
        for(int i=0; i < 100; i++ ){

            car2.drive();
            car2.stop();


        }
        //Εμφανίζουμε τα στοιχεία του object
        System.out.println(car2);


        //object car3
        Car car3=new Car("make_3", "model_3", 2020, 1200, 150);

        System.out.println("Trip by car3");
        System.out.println("-----------");
        // Χρησιμοποιούμε την επανάληψη for για να πραγματοποιήσουμε τα ταξίδια.
        for(int i=0; i < 60; i++ ){

            car3.drive();
            car3.stop();


        }
        //Εμφανίζουμε τα στοιχεία του object
        System.out.println(car3);

        //----------------------------------------------------------------------------------------------------

        //object plane1
        Plane plane1=new Plane("make_1", "model_1", 2018, 80000, 500);

        System.out.println("Trip by Plane1");
        System.out.println("-------------");

        for (int i=0; i<90; i++){

            plane1.fly();
            plane1.land();

        }

        System.out.println(plane1);

        //object plane2
        Plane plane2=new Plane("make_2", "model_2", 2019, 90000, 300);

        System.out.println("Trip by Plane2");
        System.out.println("-------------");

        for (int i=0; i<80; i++){

            plane2.fly();
            plane2.land();

        }

        System.out.println(plane2);


        //object plane3
        Plane plane3=new Plane("make_3", "model_3", 2020, 100000, 200);

        System.out.println("Trip by Plane3");
        System.out.println("-------------");

        for (int i=0; i<60; i++){

            plane3.fly();
            plane3.land();

        }

        System.out.println(plane3);

    }



}
