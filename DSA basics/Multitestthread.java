//performing multiple tasks from multiple threads
//inheriting Runnable class from library

class Multitestthread implements Runnable {
    //run method
    public void run() {
        System.out.println("LION");
    }
    public static void main(String[] args) {
         //obj of parent class
        Multitestthread obj = new Multitestthread();
        Thread t = new Thread(obj);
        t.start();
        System.out.println(obj);
        System.out.println(t);
    }
    
}
