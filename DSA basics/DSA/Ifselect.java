import java.util.Scanner;

public class Ifselect {

public static void main(String[] args) {
    
    Scanner sc = new Scanner(System.in);
    
    System.out.println("Enter num1");
    int a = sc.nextInt();
    
    System.out.println("Enter num2");
    int b = sc.nextInt();
    //gretare num condition
    if(a>b)
    {
        System.out.println("num1 is greater");
    }
    else if(b>a)
            {
                System.out.println("num2 is greater");
            }
    else
    {
        System.out.println("num1 is equal to num2");
    }
}    

}
