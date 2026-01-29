package ec.edu.ups.services.shared;

import jakarta.ws.rs.core.Response;

public class ApiResponse<T> {

    public static <T> Response success(String message, T data, int status) {
        ApiSuccess<T> payload = new ApiSuccess<>();
        payload.success = true;
        payload.message = message;
        payload.data = data;
        return Response.status(status).entity(payload).build();
    }

    public static Response error(Throwable error, String fallbackMsg) {
        if (error instanceof AppError appError) {
            return appError.toResponse();
        }
        return AppError.internal(fallbackMsg != null ? fallbackMsg : "Error interno en el servidor")
                .toResponse();
    }

    public static Response error(Throwable error) {
        return error(error, "Error interno en el servidor");
    }

    public static class ApiSuccess<T> {
        public boolean success;
        public String message;
        public T data;
    }
}
