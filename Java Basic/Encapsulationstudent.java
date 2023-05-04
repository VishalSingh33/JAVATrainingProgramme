
public class Encapsulationstudent{   
private String name;   
public String getName(){  
return name;  
}  
//setter method for name  
public void setName(String name){  
this.name="name";  
}  
}
class Test{  
public static void main(String[] args){  
Encapsulationstudent s=new Encapsulationstudent();  
s.setName("vijay");  
System.out.println(s.getName());  
}  
}  