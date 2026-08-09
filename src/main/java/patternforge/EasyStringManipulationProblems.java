package patternforge;

import java.util.Random;

    /** Easy tier String manipulation questions
     * 
     * Chosen questions that cover common themes in most easy questions:
     * - Reverse String (344)
     * - Valid Palindrome (125)
     * - Reverse Words in a String 3 (557)
     * - Longest Common Prefix (14)
     * - Is Subsequence (392)
     * - First Unique Char in a String (387)
     */

public class EasyStringManipulationProblems {

    private final Random random;

    public EasyStringManipulationProblems(){
        this.random = new Random();
    }

    public EasyStringManipulationProblems(long seed){
        this.random = new Random(seed);
    }

    //344

    public Problem<char[], char[]> generateReverseStringProblem(int minLength, int maxLength){

        // returns a random int in the range [0, bound), ensures its never less than 1
        int length = minLength + random.nextInt(maxLength - minLength + 1);
        length = Math.max(length, 1);

        char[] input = randomLowerCaseWord(length);
        char[] expected = reverseString(input.clone());

        String description = String.format( "Given a character array of length %d, reverse it in place.",
        length );

        return new Problem<>(description, input.clone(), expected);
    }

    // model output

    public static char[] reverseString(char[] s){
        
    }


    
}
