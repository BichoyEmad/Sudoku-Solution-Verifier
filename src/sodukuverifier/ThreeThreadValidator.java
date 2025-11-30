package sodukuverifier;
public class ThreeThreadValidator extends Validator {

    public ThreeThreadValidator(SudokuBoard board) {
        super(board);
    }

    public void validate() {
        SequentialValidator seq = new SequentialValidator(board);

        Thread t1 = new Thread(() -> seq.checkRows());
        Thread t2 = new Thread(() -> seq.checkCols());
        Thread t3 = new Thread(() -> seq.checkBoxes());

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (Exception e) {}
    }
}
