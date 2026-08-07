package patternforge;

/**
 * A generated problem instance.
 *
 * @param <I> Input type
 * @param <O> Expected output type
 */
public class Problem<I, O> {

    private final String description;
    private final I input;
    private final O expectedOutput;

    public Problem(
            String description,
            I input,
            O expectedOutput
    ) {
        this.description = description;
        this.input = input;
        this.expectedOutput = expectedOutput;
    }

    public String getDescription() {
        return description;
    }

    public I getInput() {
        return input;
    }

    public O getExpectedOutput() {
        return expectedOutput;
    }

    @Override
    public String toString() {
        return description
                + "\nInput:    " + input
                + "\nExpected: " + expectedOutput;
    }
}

