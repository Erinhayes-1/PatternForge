package patternforge;

import java.util.Arrays;

/**
 * A single generated problem instance.
 * Holds the question text, the input, and the correct answer,
 * so the generator can prove its own output is solvable before
 * it ever reaches a user.
 */
public class Problem {

    private final String description;
    private final int[] input;
    private final int[] expectedOutput;

    public Problem(String description, int[] input, int[] expectedOutput) {
        this.description = description;
        this.input = input;
        this.expectedOutput = expectedOutput;
    }

    public String getDescription() {
        return description;
    }

    public int[] getInput() {
        return input;
    }

    public int[] getExpectedOutput() {
        return expectedOutput;
    }

    @Override
    public String toString() {
        return description
                + "\nInput:    " + Arrays.toString(input)
                + "\nExpected: " + Arrays.toString(expectedOutput);
    }
}
    

