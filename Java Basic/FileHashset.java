import java.util.LinkedList;
import java.util.HashSet;
import java.util.Iterator;

public class FileHashset {
public static void main(String[] args) {
      //Creating HashSet and adding elements
    HashSet<String> set= new HashSet();
    set.add("Vishal");
    set.add("Singh");
    set.add("novice coder");
    //Traversing elements  
    Iterator<String> i = set.iterator();
    while(i.hasNext())
     {
    System.out.println(i.hasNext());
    }
}    
}
