import java.util.Scanner;

public class Addition {

public static void main(String[] args) {
  Scanner sc = new Scanner(System.in);
  
//user input   
   System.out.println("Enter the num1");
   int a = sc.nextInt();
//user input   
   System.out.println("Enter num2");
   int b = sc.nextInt();
   
//opration
   int sum = a + b ;
   
   System.out.println("TOTAL SUM: "+sum);
}    
}
