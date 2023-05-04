//performing multiple tasks from multiple threads
//inheriting thread class from library
class multithread2 extends Thread{
    //run method
public void run() {
    System.out.println("grand sport");
}    
}
 class multithread3 extends Thread {
    public void run() {
        System.out.println("lykan hypersport");
    }
}
class multithread4 extends Thread {
    public void run() {
        System.out.println("aventadore coupe");
    }
}
    class allthread {
    public static void main(String[] args) {
        //creating object of thread class
        multithread2 t2 = new multithread2();
        t2.start();
        multithread3 t3 = new multithread3();
        t3.start();
        multithread4 t4 = new multithread4();
        t4.start();
    }
}