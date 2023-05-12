import java.util.Scanner;

public class Pellindrome {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("ENTER THE DIGHIT");
        int a = sc.nextInt(); //456
        int sum=0;
        int t=a;
      //loop  
        while(a>0)
        {
         int c = a%10;
         sum = sum*10+c;
         a = a/10;
         }
        //condition
        if(t==sum)
        {
        System.out.println("THE NUMBER IS PELLINDROME");
         }
        else
                {
                    System.out.println("THE NUMBER ISn'T PELLINDROME");
                }
                }
    }
