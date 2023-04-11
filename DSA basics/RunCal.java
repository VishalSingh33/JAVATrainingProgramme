package RunCal;

import calpackage.calforpackage;

public class RunCal {

    public static void main(String[] args) {
        calforpackage obj = new calforpackage();
        
        int total = obj.add(22 , 32);
        System.out.println("total sum is " +total);
        
        int diff = obj.subtract(42 , 32);
        System.out.println("total sub is " +diff);
        
        System.out.println("total mult is " +obj.mutli(20 , 12));
        
        System.out.println("total mult is " +obj.division(40 , 4));
    }
}
