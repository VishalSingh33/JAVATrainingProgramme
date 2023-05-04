//interface
interface Bankinterface {
//float instruction
    float rateOfInterest();
}
//inheriting
class SBI implements Bankinterface {

    public float rateOfInterest() {
        return 9.15f;
    }
}
//inheriting
class PNB implements Bankinterface {

    public float rateOfInterest() {
        return 9.7f;
    }
}
//main class
class TestInterface2 {
//main method
    public static void main(String[] args) {
        Bankinterface obj = new SBI();//creating object
        System.out.println("ROI: " + obj.rateOfInterest());
    }
}
