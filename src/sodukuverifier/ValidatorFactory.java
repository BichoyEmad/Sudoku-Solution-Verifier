package sodukuverifier;
public class ValidatorFactory {

    public static Validator create(int mode, SudokuBoard board) {

        if (mode == 0)
            return new SequentialValidator(board);

        if (mode == 3)
            return new ThreeThreadValidator(board);

        return new FullParallelValidator(board); // mode 27
    }
}
