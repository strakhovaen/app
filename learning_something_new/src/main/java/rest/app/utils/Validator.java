package rest.app.utils;

public abstract class Validator<T> {

    public boolean validate(T ojb) {
        return ojb != null;
    }
}
