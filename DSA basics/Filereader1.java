import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Filereader1 {
 //thorws Exception
    public static void main(String[] args) throws IOException {
       //Creating filreader and reading txt files 
        FileReader fr= new FileReader("D:\\Project\\JAVA\\testout.txt");
        // read till the end of the stream
        int i = fr.read();
        while(i!= -1)
        {
            System.out.print((char)i);
            i = fr.read();
        }
    }
}
