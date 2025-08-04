//even array in string
import java.util.Scanner;
//class declartion
public class Advancevenarray {

    public static void main(String[] args) {
        //input from user 
        Scanner sc = new Scanner(System.in);
        //7 string space allocation
        int ar[]= new int[7];
        //start count from
        int count=0;
        
        System.out.println("ENTER THE ARRAY's");
        for(int i =0; i<7; i++)
        {
            
            ar[i] = sc.nextInt();
        }
        for(int i =0; i<7; i++)
        {
            if(ar[i]%2==0)
            {
                count++;
//             System.out.print("THE EVEN ARRAY,s ARE: "+ ar[i]);  
            }
        }
        
        int evenArr[] = new int[count];
        int j=0;
        for(int k=0;k<7;k++){
            if(ar[k]%2==0){
                evenArr[j]=ar[k];
                j++;
            }
        }
    
    
    System.out.println("The Even Array Are:-");
    for(int i=0;i<evenArr.length;i++){
    System.out.println(evenArr[i]);
    }
    }
}
