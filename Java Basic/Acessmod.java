class Acessmod {
//making private 
    private  int sum =40;
private void msg() {
    System.out.println("hello");
}
//inherting
 class zecmod extends acessmod {
    //main method
     public static void main(String[] args) {
         //parent class object
        acessmod obj = new acessmod();
        System.out.println(obj.sum);
        //calling parent class object
        obj.msg();
    }
            
}
}
