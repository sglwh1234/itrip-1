package cn.itrip.auth.exception;

public class TokenFailedException extends Exception {
    public TokenFailedException(String msg) {
        super(msg);
    }
}
