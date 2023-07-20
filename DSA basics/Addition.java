import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class Addition {

    public static void main(String[] args) {

        public class Base {   
            public void methodOne()
            {    
                System.out.print("A");     
                methodTwo();   }  
        public void methodTwo(){    System.out.print("B");   } } public class Derived extends Base {  @Override public void methodOne(){    super.methodOne();    System.out.print("C");   }  @Override   public void methodTwo(){    5. System.out.print("D");     super.methodTwo();       } } class Main { public static void main(String[] args) {   Base b = new Derived();             b.methodOne(); } }
    }
}