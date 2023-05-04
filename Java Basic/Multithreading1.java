
//performing multiple tasks from multiple threads
//inheriting thread class from library
class Multithreading1 extends Thread{
     //threadded method
    public void threadded() {
    System.out.println("thread first program");
}
    public static void main(String[] args) {
         //creating object of thread class
        Multithreading1 obj = new Multithreading1();
        obj.start();
    }
           }
