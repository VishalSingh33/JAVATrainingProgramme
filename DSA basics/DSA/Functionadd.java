public class Functionadd {
//parameters num1 and num2
    void addition(int a , int b) {
        int sum = a + b ;
        System.out.println("ADD: "+ sum);
    }
    public static void main(String[] args) {
        //creating objects and  passing values to paramteres
        Functionadd ob = new Functionadd();
        ob.addition(12 , 45);
        ob.addition(11 ,33);
    }
}
