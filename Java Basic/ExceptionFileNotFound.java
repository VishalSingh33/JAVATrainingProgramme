import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//Creating Filebufferreader and reading txt files
public class ExceptionFileNotFound {
    //throwing Exceptions
public static void main(String[] args) throws IOException {
    //fileReader method and providing location
 FileReader fr = new FileReader("D:\\Project\\JAVA\\testbuffered.txt");
 BufferedReader br = new BufferedReader(fr);
 String Line = br.readLine();
 //null condtiton
 while(Line != null)
 {
     System.out.println(Line);
     Line= br.readLine();
 }
 
}
    
}

//RUN:Exception in thread "main" java.io.FileNotFoundException: D:\Project\JAVA\testbuffered.txt (The system cannot find the file specified)
//	at java.io.FileInputStream.open0(Native Method)
//	at java.io.FileInputStream.open(FileInputStream.java:195)
//	at java.io.FileInputStream.<init>(FileInputStream.java:138)
//	at java.io.FileInputStream.<init>(FileInputStream.java:93)
//	at java.io.FileReader.<init>(FileReader.java:58)
//	at AssignmentException.main(AssignmentException.java:10)