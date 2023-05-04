import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Filebufferwriter {
    //thorws Exception
public static void main(String[] args) throws IOException {
    //Creating filewriter and creating txt files
    FileWriter fw = new FileWriter("D:\\Project\\JAVA\\testbuffered.txt");
    //creating bufferd method
    BufferedWriter bw= new BufferedWriter(fw);
    //writng objects
    bw.write(100);
    bw.newLine();
    bw.write("vishal");
    bw.write("  ");
    bw.write("singh");
    bw.newLine();
    char[] ch = {'p','q','r','s','v'};
    bw.write(ch);
    bw.newLine();
    bw.write("full stack developer");
    bw.flush();
    bw.close();
    
}    
}
