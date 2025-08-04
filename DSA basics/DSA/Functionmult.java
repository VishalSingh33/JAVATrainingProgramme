public class Functionmult {
//parameters num1 and num2
    void multiplication(int a , int b) {
        int sum = a * b;
        System.out.println("RESULATANT IS: "+sum);
    }
    public static void main(String[] args) {
            //creating objects and  passing values to paramteres
      Functionmult ob = new Functionmult();
      ob.multiplication(12 , 12);
      ob.multiplication(18 , 6);
      ob.multiplication(7 , 12);
      ob.multiplication(15 , 22);
      ob.multiplication(121 , 19);
      
    }
}
