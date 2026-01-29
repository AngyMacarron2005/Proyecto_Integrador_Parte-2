package ec.edu.ups.services.shared;

import jakarta.ws.rs.core.Response;

public class AppError extends RuntimeException {

    public final int status;
    public final String code;
    public final Object details;
    public final Object cause; // opcional

    public AppError(String message) {
        this(message, null);
    }

    public AppError(String message, AppErrorOpts opts) {
        super(message);
        this.status = (opts != null && opts.status != null) ? opts.status : 500;
        this.code = (opts != null && opts.code != null) ? opts.code : "INTERNAL_ERROR";
        this.details = (opts != null) ? opts.details : null;
        this.cause = (opts != null) ? opts.cause : null;
    }

    // -------- Factories --------

    public static AppError validation() {
        return validation("Datos inválidos", null);
    }

    public static AppError validation(String message, Object details) {
        return new AppError(message, new AppErrorOpts(400, "VALIDATION_ERROR", details, null));
    }

    public static AppError unauthorized() {
        return unauthorized("No autorizado");
    }

    public static AppError unauthorized(String message) {
        return new AppError(message, new AppErrorOpts(401, "UNAUTHORIZED", null, null));
    }

    public static AppError forbidden() {
        return forbidden("Prohibido");
    }

    public static AppError forbidden(String message) {
        return new AppError(message, new AppErrorOpts(403, "FORBIDDEN", null, null));
    }

    public static AppError notFound() {
        return notFound("No encontrado");
    }

    public static AppError notFound(String message) {
        return new AppError(message, new AppErrorOpts(404, "NOT_FOUND", null, null));
    }

    public static AppError hasDependencies() {
        return hasDependencies("No se puede eliminar: tiene registros asociados");
    }

    public static AppError hasDependencies(String message) {
        return new AppError(message, new AppErrorOpts(409, "HAS_DEPENDENCIES", null, null));
    }

    public static AppError conflict() {
        return conflict("Conflicto");
    }

    public static AppError conflict(String message) {
        return new AppError(message, new AppErrorOpts(409, "CONFLICT", null, null));
    }

    public static AppError createFailed() {
        return createFailed("No se pudo crear el registro", null);
    }

    public static AppError createFailed(String message, Object details) {
        return new AppError(message, new AppErrorOpts(422, "CREATE_FAILED", details, null));
    }

    public static AppError dependencyInvalid() {
        return dependencyInvalid("Relación inválida o inexistente");
    }

    public static AppError dependencyInvalid(String message) {
        return new AppError(message, new AppErrorOpts(422, "DEPENDENCY_INVALID", null, null));
    }

    public static AppError internal() {
        return internal("Error interno en el servidor");
    }

    public static AppError internal(String message) {
        return new AppError(message, new AppErrorOpts(500, "INTERNAL_ERROR", null, null));
    }

    // -------- Response (como toResponse(res)) --------

    public Response toResponse() {
        ApiError payload = new ApiError();
        payload.success = false;
        payload.message = this.getMessage();
        payload.code = this.code;
        payload.errors = this.details; // null si no hay
        return Response.status(this.status).entity(payload).build();
    }

    // -------- DTOs internos --------

    public static class ApiError {
        public boolean success;
        public String message;
        public String code;
        public Object errors;
    }

    public static class AppErrorOpts {
        public Integer status;
        public String code;
        public Object details;
        public Object cause;

        public AppErrorOpts() {}

        public AppErrorOpts(Integer status, String code, Object details, Object cause) {
            this.status = status;
            this.code = code;
            this.details = details;
            this.cause = cause;
        }
    }
}
