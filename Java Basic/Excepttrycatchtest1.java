public class Excepttrycatchtest1 {

    public static void main(String[] args) {
         //statements that may cause an exception
        try {
            //ar declartion
            int ar[] = {1, 5, 7, 8, 9, 6, 3};
            System.out.println(ar[10]);
            //type of exception
        } catch ( ArrayIndexOutOfBoundsException  e) {
            System.out.println(e);
        }
        System.out.print("rest of code");
    }
}
