import java.util.Scanner;
public class Reversename {

	public static void main(String[] args) {
            //take input
	Scanner str = new Scanner(System.in);
	System.out.println("Enter the name");
	//input type
        //string stype
	String name = str.nextLine();
        //string into a sequence of characters
	char string[]  = name.toCharArray();
	//loop to reverse calculate
	for(int i = name.length()-1 ; i>=0 ; i--)
	{
		System.out.print(string[i]);
	}
	
	}
}