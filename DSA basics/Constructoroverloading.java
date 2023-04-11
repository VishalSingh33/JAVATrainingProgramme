public class Constructoroverloading extends superkappl{
//default constructor
   Constructoroverloading()
   {
         System.out.println("This is Test class constructor");
   }

    public static void main(String[] args)
    {
        //creatign and calling object
        Constructoroverloading obj = new Constructoroverloading();
       
    }

}

   class superkappl {

   superkappl()
   {
       System.out.println("This is Apple constructor");
   }

}