package jpabook.jpashop.jpaoptimization.exception;

public class NotEnoughPriceException extends RuntimeException {
    public NotEnoughPriceException() {
        super();
    }

    public NotEnoughPriceException(String message) {
        super(message);
    }

    public NotEnoughPriceException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughPriceException(Throwable cause) {
        super(cause);
    }
}
