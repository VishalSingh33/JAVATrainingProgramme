
import java.util.ArrayList;

public class CollectionArraylist {
  public static void main(String[] args) {
      
      ArrayList obj = new ArrayList();
      obj.add("A");
      obj.add("A");
      obj.add("b");
      obj.remove(2);
      System.out.println(obj);
      obj.add("A");
      obj.add("A");
      obj.add("b");
      System.out.println(obj);
      obj.remove(1);
      System.out.println(obj);
      
      
  }    
}
