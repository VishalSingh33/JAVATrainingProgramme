public class Superspeed {

    void speed() {
        System.out.println("ASTON MARTIN VULCAN");
    }
}
//parent class inheritance
class flash extends Superspeed {

    void speed()
    {
        System.out.println("FERARRI LAFERRARI");
    }  
     void pickup() {
         
        System.out.println("cherevotte b sport");
    }

    void display() {
        //super() keywor dto call the object of parent class 
        super.speed();
        pickup();
    }
}

class testsuper {

    public static void main(String[] args) {
        flash obj = new flash();
        obj.display();

    }
}
