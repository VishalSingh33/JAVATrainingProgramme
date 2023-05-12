import java.util.Scanner;

public class Switchbus {

public static void main(String[] args) {

Scanner sc = new Scanner(System.in);

System.out.println("ENTER THE SHIFT-NUM");
int a = sc.nextInt();
//intialising condition
switch (a) {
//case initiated
case 1:
System.out.println("SHIFT-NUM IN MORNING");
//stop 
break;

case 2:
System.out.println("SHIFT-NUM IN AFTERNOON");
break;

case 3:
System.out.println("SHIFT-NUM IN EVENING");
break;

case 4:
System.out.println("SHIFT-NUM IN NIGHT");
break;

default:
System.out.println("NO SHIFT-NUM");
break;

}


}
}
