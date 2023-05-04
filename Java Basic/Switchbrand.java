package javaapplication1;

import java.util.Scanner;

public class Switchbrand {

public static void main(String[] args) {
//user input
Scanner sc = new Scanner(System.in);

System.out.println("Enter the KEY");
int a = sc.nextInt();
//intialising condition
switch (a) {
//case initiated
case 1:
System.out.println("LEVIS");
//stop 
break;

case 2:
System.out.println("TOMMY HILFIGER");
break;

case 3:
System.out.println("GAS");
break;

default:
System.out.println("INAVALID KEY");
break;

}
}
}

