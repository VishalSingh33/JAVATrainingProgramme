class encapsulationname {
public static void main(String[] args) {
 
    Encapsulationemployee ob = new Encapsulationemployee();
    ob.setId(101);
    ob.setName("vishal");
    
    
    System.out.println("id= "+ob.getId());
    System.out.println("name= "+ob.getName());
}    

}
public class Encapsulatiotest {

    private int id;
    private String name;
    
    public int getid() {
        return id;
    }
    public void setid(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
  }
