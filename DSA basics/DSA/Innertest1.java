public class Innertest1 {
//providing data within class
    private int data = 55/5;
    void display() {
        class local {
            void msg() {
                System.out.println(data);
            }
        }
        //creating objects of child class
        local ob = new local();
        ob.msg();
    }
    public static void main(String[] args) {
         //creating objects
        Innertest1 obj = new Innertest1();
        obj.display();
    }
}
