package ma.enset.iibdcc.paymentservice.exception;

public class PaymentException {

    public static class PaymentNotFound extends Exception {
        public PaymentNotFound(String message) {
            super(message);
        }
    }
}