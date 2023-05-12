import java.util.*;

public class CountInArray {
	public static void main(String[] args) {

		String str = "this it tips";
		char[] arr = str.toCharArray();
			int [] fr = new int [arr.length];  
			int visited = -1;  
			for(int i = 0; i < arr.length; i++){  
					int count = 1;  
					for(int j = i+1; j < arr.length; j++){  
							if(arr[i] == arr[j]){  
									count++;  
									//To avoid counting same element again  
									fr[j] = visited;  
							}  
					}  
					if(fr[i] != visited)  
							fr[i] = count;  
			}  

			//Displays the frequency of each element present in array  
			System.out.println("---------------------------------------");  
			System.out.println(" Element | Frequency");  
			System.out.println("---------------------------------------");  
			for(int i = 0; i < fr.length; i++){  
					if(fr[i] != visited && arr[i] != ' ' )  
							System.out.println("    " + arr[i] + "    |    " + fr[i]);  
			}  

	
		
		
  }
}
