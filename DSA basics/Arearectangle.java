public class Arearectangle {
//method
 void rectangle(int length , int breadth) {
     int sum = length * breadth;
     System.out.println("RESULATANT: "+ sum);
 }
 public static void main(String[] args) {
     //object created
     Arearectangle ob = new Arearectangle();
     //giving values to parameter passed
     ob.rectangle(10, 20);
     ob.rectangle(22, 12);
     ob.rectangle(33, 13);
     ob.rectangle(10, 18);
     ob.rectangle(16, 20);
     
 }   
}
