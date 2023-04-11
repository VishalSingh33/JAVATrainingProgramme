public class Methodsoverloading {
//parameterized function
    void add(int a, int b) {
        int sum = a + b;
        System.out.println("sum of two number is: " + sum);
    }
//parameterized function
    void add(int a, int b, int c) {
        int sum = a + b + c;
        System.out.println("sum of three number is: " + sum);
    }

    public static void main(String[] args) {
        //creating object of parent class
        Methodsoverloading obj = new Methodsoverloading();
        obj.add(10, 20);
        obj.add(10, 20, 90);

    }

}
