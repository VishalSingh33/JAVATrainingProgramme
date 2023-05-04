//performing multiple tasks from multiple threads
//inheriting thread class from library
class MultiThread1 extends Thread{
    //run method
    public void run()
    {
        System.out.println("first thread runing");
    }
}
class MultiThread2 extends Thread{
    public void run()
    {
        System.out.println("second thread runing");
    }
    
}
class MultiThread3 extends Thread{
    public void run()
    {
        System.out.println("third thread runing");
    }
 }
class ThreadCase4 {
    //creating object of thread class
    public static void main(String[] args) {
      MultiThread1 t1=new MultiThread1();
      t1.start();
      MultiThread2 t2=new MultiThread2();
      t2.start();
      MultiThread3 t3=new MultiThread3();
      t3.start();
    }
    
}