class Supervehile {

    int maxspeed = 120;

}
//parent class inheritance
class car extends Supervehile {

    int maxspeed = 180;
//method
    void display() {
        //super() keywor dto call the object of parent class 
        System.out.println("MAX SPEED= " + super.maxspeed);
    }
}

class test1 {

    public static void main(String[] args) {
       //object created
        car obj = new car();
        obj.display();
    }
}
