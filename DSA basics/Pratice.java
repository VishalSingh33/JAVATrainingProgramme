import java.util.Scanner;

class Solution {
    int print2largest(int arr[], int n) {

        int secondLargest;
        int largest;
        for (int i = 0; i > arr.length; i++) {

            if (arr[i] > largest) {
                secondLargest = largest;
                largest = arr[i];

            } else if (arr[i] > secondLargest && secondLargest != arr[i]) {
                secondLargest = arr[i];
            }
        }
    }
    System.out.println("First largest element: "+ largest);
    System.out.println("Second largest element: "+ secondLargest);
 }

public static void main(String[] args) {
    
     Scanner scanner = new Scanner(System.in);
     int n = scanner.nextInt();
     int[] arr = new int[scanner.nextInt()];
     for (int i = 0; i < arr.length; i++)
     {
       arr[i] = scanner.nextInt();
     }
     scanner.close();
     
     Solution solution = new Solution();
     solution.print2largest(arr, n);
   
  }
