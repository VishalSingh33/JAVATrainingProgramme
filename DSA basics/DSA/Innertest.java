public class Innertest {
 //providing data within class
    private int data = 30;
    
    class image {
        void message() {
            System.out.println("data is: "+ data);
        }
    }
    public static void main(String[] args) {
        //creating objects
        Innertest obj = new Innertest();
        //creating objects of child class
        Innertest.image ob = obj.new image();
        ob.message();
    }
}
