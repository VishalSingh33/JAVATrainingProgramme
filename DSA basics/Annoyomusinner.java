//abstraction class
abstract class Annoyomusinner {

    abstract void eat();
    }
//child class
class fruits {
    public static void main(String[] args) {
        //created method 
        Annoyomusinner obj = new Annoyomusinner()
         {
             //refer to parent class object
             void eat() {
                 System.out.println("apple");
             }
         };
        //calling method
         obj.eat();
    }
    
}
