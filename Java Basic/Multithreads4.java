//performing multiple tasks from multiple threads
//inheriting thread class from library
class Multithreads4 implements Runnable {
    public void run() {
        for(int i=1; i<=10; i++)
        {
            System.out.print(" "+i);
        }
    }
        public static void main(String[] args) {
             //creating object of thread class
            Multithreads4 obj = new Multithreads4();
            Thread ob= new Thread(obj);
            Thread ob1= new Thread(obj);
            ob.start();
            ob1.start();
        }
    }
