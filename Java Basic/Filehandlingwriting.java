import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Filehandlingwriting {
     //thorws Exception
    public static void main(String[] args) throws IOException {
         //creating new write file object at this space
        FileWriter fw = new FileWriter("D:\\Project\\JAVA\\test.txt", true);
        //writing file
        fw.write(98);
        fw.write("\n");
        fw.write("vishal");
        fw.write("\n");
        fw.write("singh");
        fw.write("\n");
        char[] ch = {'a','b','c','d'};
        fw.write(ch);
        fw.write("\n");
        //clear the stream of any element that may be or maybe not inside the stream
        fw.flush();
        //closing file
        fw.close();
    }
}
