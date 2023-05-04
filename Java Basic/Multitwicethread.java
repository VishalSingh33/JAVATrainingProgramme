//performing multiple tasks from multiple threads
//inheriting thread class from library

public class Multitwicethread extends Thread{
//run method
    public void run() {
    System.out.println("running");
}
public static void main(String[] args) {
    //obj of parent class
    Multitwicethread t1 = new Multitwicethread();
    t1.start();
    t1.start();
}
}
