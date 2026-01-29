package ec.edu.ups.services.shared;

public class ApiSuccess<T> {
    public boolean success = true;
    public String message;
    public T data;

    public ApiSuccess() {}

    public ApiSuccess(String message, T data) {
        this.message = message;
        this.data = data;
    }
}