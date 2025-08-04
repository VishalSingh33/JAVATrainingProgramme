//voting category
class Customsuperexcep extends Exception {
//handling exception
   //custom exception 
    Customsuperexcep(String s) {
        //super keyword
        super(s);
        
    }

}

 class invalidage {
//throw exception
    static void validate(int age)throws Customsuperexcep {
        if (age < 18) 
            throw new Customsuperexcep("not valid");
         else
            System.out.println("welcome to vote");
    }

    public static void main(String[] args) {
        //statements that may cause an exception
        try {
            validate(17);
        } 
        catch(Exception e)
        {
            System.out.println("Exception occured :" + e);
        }
        System.out.println("rest of code");
    }
}
