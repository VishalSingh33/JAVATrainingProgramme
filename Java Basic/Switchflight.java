import java.util.Scanner;

public class Switchflight {

public static void main(String[] args) {
//user input
Scanner sc = new Scanner(System.in);

System.out.println("ENTER THE NUM");
int a = sc.nextInt();
//intialising condition
switch (a) {
//case initiated
case 1:
System.out.println("FLIGHT AT 3:00 AM");
//stop 
break;

case 2:
System.out.println("FLIGHT AT 5:00 AM");
break;

case 3:
System.out.println("FLIGHT AT 7:00 AM");
break;

case 4:
System.out.println("FLIGHT AT 9:00 AM");
break;

default:
System.out.println("NO FLIGHT");
break;

}
}
}
