public class ExceptionRuntime {

    public void testRuntimeException() {
        throw new MyException();
    }

    public static void main(String[] args) {
        try {
            new AssignmentException().testRuntimeException();
        } catch (Exception e) {
            System.out.println(e.getClass().getName());
        }
    }
}

class MyException extends RuntimeException {

    public MyException() {
        super();
    }
}


//RUN:java.lang.RuntimeException