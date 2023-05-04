import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//Creating Filebufferreader and reading txt files
public class Filebufferreader {
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
