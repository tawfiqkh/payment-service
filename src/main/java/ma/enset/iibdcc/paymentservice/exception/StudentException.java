package ma.enset.iibdcc.paymentservice.exception;

public class StudentException{

    public static class StudentNotFound extends Exception {
        public StudentNotFound(String message) {
            super(message);
        }
    }
}
