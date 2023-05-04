class Address {
//declation of data type
    int streetNum;
    String city;
    String state;
    String country;

    Address(int street, String c, String st, String coun) {
        //this keyoword
        //refreing to call current class object
        this.streetNum = street;
        this.city = c;
        this.state = st;
        this.country = coun;
    }
}

class Student2 {

    int rollNum;
    String studentName;
    //Creating HAS-A relationship with Address class
    Address studentAddr;

    Student2(int roll, String name, Address addr) {
        this.rollNum = roll;
        this.studentName = name;
        this.studentAddr = addr;
    }

    public static void main(String args[]) {
        Address ad = new Address(55, "Agra", "UP", "India");
        Student2 obj = new Student2(123, "Chaitanya", ad);
       
        
        System.out.println(obj.rollNum);
        System.out.println(obj.studentName);
        System.out.println(obj.studentAddr.streetNum);
        System.out.println(obj.studentAddr.city);
        System.out.println(obj.studentAddr.state);
        System.out.println(obj.studentAddr.country);
        
        
    }
}