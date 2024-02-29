import java.util.Scanner;

public class Reversedigit {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Entre the Num");
        int a = sc.nextInt();
        int sum = 0 , r=0;
        //loop
        while (a > 0) {
            
            int c = a%10;
            sum = sum*10+c;
            a = a/10;
        }
        System.out.println("REVERSE OF DIGIT IS: " +sum);
    }

      public static boolean isVowel(char c) {
            return "aeiouAEIOU".indexOf(c) != -1;
       }
}
