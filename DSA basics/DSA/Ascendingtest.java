import java.util.Scanner;

 class Ascendingtest {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int temp = 0;
        int ar[] = new int[5];
        for (int k = 0; k < 5; k++) {
            for (int i = 0; i < ar.length - 1; i++) {
                System.out.println("ENTER THE ARRAY's");
                ar[i] = sc.nextInt();

                if (ar[i] > ar[i + 1]) {
                    temp = ar[i];
                    ar[i] = ar[i + 1];
                    temp = ar[i + 1];
                }
            }

            for (int i = 0; i < 5; i++) {
                System.out.println("ASCENDING  ARRAY,s YOU HAVE: " + ar[i]);
            }
        }
    }
}
