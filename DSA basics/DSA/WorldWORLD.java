//Hello world-java is my world and please replace world to World in the program with 
class WorldWORLD{  
//main method
    public static void main(String args[]){  
   //string declation
        String s="world world";
   String s1 = "World World";
   String s2 = "WORLD WORLD";
   System.out.println("before chages: "+s +"\nsecond string: " +s1);
   //predefined keyword to replace value of string from s to s2  
   s = s.replace(s,s2);
   System.out.println("after chages: "+s+"\nsecond string: "+s1);  
 }  
} 