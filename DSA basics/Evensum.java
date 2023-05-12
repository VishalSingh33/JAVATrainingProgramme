public class Evensum {

    public static void main(String[] args) {
            int sum =0 ;
       //loop frpm 1 to 15
            for (int i = 1; i <= 15; i++) {
            //even condition
                if (i % 2 == 0) {
                sum = sum + i; //sum of all even num
            }
            }
        System.out.println("Sum of all even num is: " + sum);
    }
}
