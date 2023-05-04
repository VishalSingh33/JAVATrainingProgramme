import java.io.File;
import java.io.IOException;


class Filehadling1 {
    //thorws Exception
public static void main(String[] args) throws IOException {
    //creating new at this space
    File f = new File("D:\\Project\\JAVA\\cool.txt");
    System.out.println(f.exists());
     //creating new files
    f.createNewFile();
    System.out.println(f.exists());
    }    
}
