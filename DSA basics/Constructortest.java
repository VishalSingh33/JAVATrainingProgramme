public class Constructortest {

    int x;
//    default constructor
    Constructortest() {
        System.out.println("this is default constructor");
    }
    public static void main(String[] args) {
        //created object here
    Constructortest obj = new Constructortest();
    System.out.println(obj.x);
    
}
}
