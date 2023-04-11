public class AssignmentException {

    public static void main(String[] args) {
        String str = "Hello how are you";
        for (int i = 0; i < str.length(); i++) {
            System.out.print(str.charAt(i));
        }
        System.out.println(str.length());
        //Accessing element at greater than the length of the String
        try {
            System.out.println(str.charAt(40));
        } catch (StringIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
}
