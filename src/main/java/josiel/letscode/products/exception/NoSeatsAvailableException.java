package josiel.letscode.products.exception;

public class NoSeatsAvailableException extends RuntimeException {
    public NoSeatsAvailableException() {
        super("Não há vagas disponíveis para este curso");
    }
}
