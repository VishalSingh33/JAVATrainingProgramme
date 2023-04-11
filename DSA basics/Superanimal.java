class Superanimal {
  //eat method
    void eat() {
        System.out.println("eating...");
    }
}
//parent class inheritance
class Dog extends Superanimal {
//method
    void eat() {
        System.out.println("eating bread...");
    }
//method
    void bark() {
        System.out.println("barking...");
    }
//super() keywor dto call the object of parent class 
    void work() {
        super.eat();
        bark();
    }
}

class TestSuper2 {

    public static void main(String args[]) {
        //object created
        Dog d = new Dog();
        d.work();
    }
}
