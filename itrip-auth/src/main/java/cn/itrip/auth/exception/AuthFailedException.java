package cn.itrip.auth.exception;

public class AuthFailedException extends Exception {
    public AuthFailedException(String msg) {
        super(msg);
    }
}
