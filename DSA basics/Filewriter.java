import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Filewriter {

    public static void main(String args[]) {
        //here exception can occur
        try {
            //creating new object to write file at given memory
            FileWriter fw = new FileWriter("D:\\Project\\JAVA\\testout.txt");
            fw.write("Welcome to File Handling Vishal");
            //close file
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Success...");
    }
}
