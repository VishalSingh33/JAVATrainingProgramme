
//performing multiple tasks from multiple threads
//inheriting thread class from library
class Multithread2 extends Thread{
     //run method
    public void run() {
    System.out.println("RUNNING");
                      }
    public static void main(String[] args) {
         //creating object of thread class
        Multithread2 obj = new Multithread2();
        obj.start();
    }
}
