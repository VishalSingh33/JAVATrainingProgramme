import java.io.*;
  
class ExceptionNullPointer
{
    public static void main (String[] args)
    {
        // Initializing String variable with null value
        String str = null;
  
        // Checking if str.equals null or works fine.
        try
        {
            // This line of code throws NullPointerException
            // because str is null
            if (str.equals("AssignmentException"))
                System.out.print("Same");
            else 
                System.out.print("Not Same");
        }
        catch(NullPointerException e)
        {
            e.printStackTrace();
        }
    }
}

//RUN: java.lang.NullPointerException
//	at AssignmentException.main(AssignmentException.java:15)
