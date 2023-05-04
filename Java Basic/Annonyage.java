//Annonymous class
//parent class
interface Annonymousdemo {

    int agee = 23;
    void getAge();
}
//child class
class Anonyage
{
    public static void main(String[] args) 
    {
       Myclass obj=new Myclass();//parent class object is created
       obj.getAge();     //parent class obejct is called
    }
}
//main metod 
class Myclass implements Annonymousdemo {
     public void getAge()
    {
        System.out.print("Age is:" + agee);
    }
}
