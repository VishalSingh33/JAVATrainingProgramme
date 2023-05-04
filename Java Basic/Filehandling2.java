import java.io.File;
import java.io.IOException;
 class Filehandling2 {
     //thorws Exception
public static void main(String[] args) throws IOException {
    //creating new file object at this space
    File f1 = new File("vishal");
     // using the abstract path name
    f1.mkdir();
    File f2 = new File("singh","D:\\Project\\JAVA\\coool.txt");
    f1.createNewFile();
}    
    
}
