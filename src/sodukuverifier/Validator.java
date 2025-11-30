package sodukuverifier;
public abstract class Validator {
    protected SudokuBoard board;
    protected ValidationResult result;

    public Validator(SudokuBoard board) {
        this.board = board;
        this.result = ValidationResult.getInstance();
    }

    public abstract void validate();
}
