public class ExceptionArrayIndex {
      public static void main(String[] args) {
         //statements that may cause an exception
           try {
            //ar declartion
            int ar[] = {1, 5, 7, 8, 9, 6, 3};
            System.out.println(ar[10]);
            //type of exception
        } catch ( ArrayIndexOutOfBoundsException  e) {
            e.printStackTrace();
        }
        System.out.print("rest of code");
    }
}


//RUN: java.lang.ArrayIndexOutOfBoundsException: 10
//	at AssignmentException.main(AssignmentException.java:7)