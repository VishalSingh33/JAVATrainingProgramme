public class Excepttrycatchtest {
    public static void main(String[] args) {
         //statements that may cause an exception
        try
        {
            //datatype
            int data = 50/0;
        }
        //type of exception
        catch(ArithmeticException e)
                {
                    System.out.println(e);
                }
        System.out.println("rest of code");
    }
}
