
import java.util.Scanner;

public class Switchmonthsel {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

//user input
        System.out.println("enter num b/w 1 & 12");
        int n = sc.nextInt();
//intialising condition
        switch (n) {
           //case initiated
            case 1:
                System.out.println("january");
                //stop
                break;

            case 2:
                System.out.println("febuary");
                break;
            case 3:
                System.out.println("march");
                break;
            default :
                System.out.println("inavalid");
                break;
        }
    }
}
