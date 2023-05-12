import java.util.Scanner;

public class Switchsss {

public static void main(String[] args) {
//user input
Scanner sc = new Scanner(System.in);

System.out.println("ENTER THE SIZE");
int a = sc.nextInt();
//intialising condition
switch (a) {
//case initiated
case 1:
System.out.println("SIZE is SMALL");
//stop
break;

case 2:
System.out.println("SIZE is MEDIUM");
break;

case 3:
System.out.println("SIZE is LARGE");
break;

case 4:
System.out.println("SIZE is EXTRA LARGE");
break;

case 5:
System.out.println("SIZE is DOUBLE EXTRA LARGE");
break;

default:
System.out.println("INVALID PRATICE");
break;
}

}
}
