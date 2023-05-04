//inheriting exception from parent library
class MyException extends Exception
{
    //handling exception
   //custom exception
	public MyException(String s)
	{
            //super keyword
		super(s);
	}
}

 class Main {
    
	public static void main(String args[]) {
            //statements that may cause an exception
		try
		{
                    //throw exception
		throw new MyException("GeeksGeeks");
		}
		catch (MyException ex)
		{
			System.out.println("Caught");
                        System.out.println(ex.getMessage());
		}
	}
}
