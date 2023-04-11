class A {
//parameterized function
    void add(int a, int b) {
        int sum = a + b;
        System.out.println("Adding two number in class A: " + sum);
    }

}
//inheriting properties of parent class
public class Methodsoveriding extends A{
//parameterized function
    void addition(int a, int b) {
        int sum = a + b;
        System.out.println("Adding two number in class Test: " + sum);
    }


    public static void main(String[] args) {
        //object of parent class
        Methodsoveriding obj = new Methodsoveriding();
        obj.add(23,12);
        //obj.add();

    }

}