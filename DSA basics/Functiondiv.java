public class Functiondiv {
//method
    void division(int a  , int b) {
        int sum = a/b;
        System.out.println("RESULATANT: "+sum);
    }
       public static void main(String[] args) {
        //object created
           Functiondiv ob = new Functiondiv();
          ob.division(33 , 11);//value passed to parameter
          ob.division(33 , 3);
          ob.division(121 , 11);
          ob.division(55 , 11);
          ob.division(45550 , 5);
          
}
}
