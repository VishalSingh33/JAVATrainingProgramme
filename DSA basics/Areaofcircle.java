package javaapplication1;

public class Areaofcircle {
//METHOD
    void circlearea(int a ) {
        int sum = (22*a*a)/7;
        System.out.println("RESULATANT: "+sum );
    }    
       public static void main(String[] args) {
        //object created
           Areaofcircle ob = new Areaofcircle();
          ob.circlearea(7);//giving values to parameter passed
          ob.circlearea(14);//giving values to parameter passed
          ob.circlearea(63);//giving values to parameter passed
          ob.circlearea(3388);//giving values to parameter passed
          ob.circlearea(21);//giving values to parameter passed
}
}
