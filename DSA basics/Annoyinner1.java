//inner class
interface Annoyinner1 {
    void eat();
}
//child class
class fruits1 {
    public static void main(String[] args) {
        //created method
        Annoyinner1 obj = new Annoyinner1()
        {
            //refer to parent class object
          public void eat() {
        System.out.println("ORANGES");
    }
    };
        //calling method
    obj.eat();
    }
}
