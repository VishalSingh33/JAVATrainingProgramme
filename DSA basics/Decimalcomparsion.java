import java.util.Scanner;

public class Decimalcomparsion {

    public Decimalcomparsion() {
    }

    public static void main(String[] args) {
        //taking input of float type from user	
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the double value");
        double num = sc.nextDouble();

        //changing float value to string 
        String doubleAsString = String.valueOf(num);
        //values after decimal point
        int indexOfDecimal = doubleAsString.indexOf(".");

        System.out.println("Double Number: " + num);
        System.out.println("Integer Part: " + doubleAsString.substring(0, indexOfDecimal));
        System.out.println("Decimal Part: " + doubleAsString.substring(indexOfDecimal + 1));
     //equal or not

        if (doubleAsString.substring(indexOfDecimal + 1).equals(doubleAsString.substring(0, indexOfDecimal))) {

            System.out.println("Equal");
        } else {
            System.out.println("Not Equal");
        }
    }
}
