//performing multiple tasks from multiple threads
//inheriting thread class from library
 //it ain't program of thread though it is to undertsand how sleep k.word works 
class Multithreadsleep {
    public static void main(String args[])
        throws InterruptedException {
        String importantInfo[] = {
            "Mares eat oats",
            "Does eat oats",
            "Little lambs eat ivy",
            "A kid will eat ivy too"
        };

        for (int i = 0; i < importantInfo.length; i++) {
            Thread.sleep(2000);
            System.out.println(importantInfo[i]);
        }
    }
}