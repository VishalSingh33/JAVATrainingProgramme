//performing multiple tasks from multiple threads
//inheriting thread class from library
 class Multithreadsleep1 extends Thread {

    public void run() {
        for (int i = 1; i < 5; i++) 
        {
            try 
            {
                Thread.sleep(2000);
            } 
            catch (InterruptedException e) 
            {
                System.out.println(i);
            }
        }
    }
       public static void main(String[] args)
       {
           //creating object of thread class
        Multithreadsleep1 t1 = new Multithreadsleep1();
        Multithreadsleep1 t2 = new Multithreadsleep1();
        t1.start();
        t2.start();
    }
}
