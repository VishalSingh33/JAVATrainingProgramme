class Supermessage {
    void message()
    {
        System.out.println("person class");
    }
    
}
//parent class inheritance
class student extends Supermessage {
    void message()
    { 
        System.out.println("student class");
    }
void diplay()
{
message();
//super() keywor dto call the object of parent class 
super.message();
}
}
class test
{
    public static void main(String[] args)
    {
        student obj = new student();
        obj.diplay();
    }
}