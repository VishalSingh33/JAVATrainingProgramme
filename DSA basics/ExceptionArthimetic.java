
public class ExceptionArthimetic {
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
                  e.printStackTrace();
                }
        System.out.println("rest of code");
    }
}
    


//RUN: java.lang.ArithmeticException: / by zero
//	at AssignmentException.main(AssignmentException.java:7)
//rest of code