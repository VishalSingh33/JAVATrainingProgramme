public class Funareatriangle {
//method
    void areaoftriangle(int a , int b) {
        int sum = (a*b)/2;
        System.out.println("RESULATANT : "+sum);
    }
    public static void main(String[] args) {
        //object created
        Funareatriangle ob = new Funareatriangle();
        ob.areaoftriangle(45 , 36);//pasiing value to parameters
        ob.areaoftriangle(12 , 6);
        ob.areaoftriangle(10 , 15);
        ob.areaoftriangle(20 , 10);
        ob.areaoftriangle(8 , 32);
    }
}
