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

        int left = 0;
        int right = s.length - 1;

        while (left < right){
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;

            left++;
            right--;
        }

        return s;
        
    }

    // 125

    public Problem<String, int[]> generateValidPalindromeProblem(int minLength, int maxLength, boolean forcePalindrome){

        int length = minLength + random.nextInt(maxLength - minLength + 1);
        length = Math.max(length, 1);

        String input;

        if (forcePalindrome){
            input = new String(randomPalindrome(length));
        }else {
            input = new String(randomLowerCaseWord(length));
        }

        boolean isPalindrome = isValidPalindrome(input);

        int expectedValue = 0;

        if (isPalindrome){
            expectedValue = 1;
        }

        int[] expected = {expectedValue};

        String description = String.format("Given a string of length %d, determine if it is a palindrome");

        return new Problem<>(description, input, expected);
    }

    //model answer, two-pointer
    public static boolean isValidPalindrome(String s){

        int left = 0;
        int right = s.length() - 1;

        while(left < right){
            if (s.charAt(left) != s.charAt(right)){
                return false;
            }

            left++;
            right--;

        }

        return true;
    }

    // 557

    public Problem<String, String> generateReverseWordsProblem(int minWords, int maxWords, int minWordLength,
        int maxWordLength){

            int wordCount = minWords + random.nextInt(maxWords- minWords + 1);
            wordCount = Math.max(wordCount, 1);

            StringBuilder sentence = new StringBuilder();

            for (int i = 0; i < wordCount; i++){

                int wordLength = minWordLength + random.nextInt(maxWordLength - minWordLength + 1);
                wordLength = Math.max(wordLength, 1);

                sentence.append(randomLowercaseWord(wordLength));

                 if (i < wordCount - 1) {
                sentence.append(' ');
            }
        }

        String input = sentence.toString();
        String expected = reverseWordsInString(input);

        String description = String.format(
                "Given a sentence of %d word(s), reverse the characters of each word while preserving word order.",
                wordCount
        );

        return new Problem<>(description, input, expected);
    }

    // model answer, split on spaces then StringBuilder.reverse() each word

    public static String reverseWordsInString(String s){

        String[] words = s.split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++){

            result.append(new StringBuilder(words[i]).reverse());

            if (i < words.length - 1){
                result.append(' ');
            }
        }

        return result.toString();
    }

    // 14

    public Problem<String[], String> generateLongestCommonPrefixProblem(int minStrings, int maxStrings, int prefixLength, int maxSuffixLength, boolean forceNoCommonPrefix){

        int stringCount = minStrings + random.nextInt(maxStrings - minStrings + 1);
        stringCount = Math.max(stringCount, 1);

        String[] input = new String[stringCount];

        if (forceNoCommonPrefix && stringCount >= 2){

            char firstLetter = (char) ('a' + random.nextInt(26));
            char secondLetter;

            do {
                secondLetter = (char) ('a' + random.nextInt(26));
            } while (secondLetter == firstLetter);

            char[] suffix0 = randomLowerCaseWord(random.nextInt(maxSuffixLength + 1));
            char[] suffix1 = randomLowerCaseWord(random.nextInt(maxSuffixLength + 1));

            input[0] = firstLetter + new String(suffix0);
            input[1] = secondLetter + new String(suffix1);

            for (int i = 2; i < stringCount; i++){
                char[] word = randomLowerCaseWord(1 + random.nextInt(maxSuffixLength + 1));
                input[i] = new String(word);
            }

        }else {

            prefixLength = Math.max(prefixLength, 1);
            char[] prefix = randomLowerCaseWord(prefixLength);

            for (int i = 0; i < stringCount; i++){
                char[] suffix = randomLowerCaseWord(random.nextInt(maxSuffixLength + 1));
                input[i] = new String(prefix) + new String(suffix);
            }
        }

        String expected = longestCommonPrefix(input);

        String description = String.format("Given %d strings, return their longest common prefix.", stringCount);

        return new Problem<>(description, input, expected);
    }

    // model answer, shrink candidate prefix from the first string until every string starts with it

    public static String longestCommonPrefix(String[] strs){

        if (strs.length == 0){
            return "";
        }

        String prefix = strs[0];

        for (int i = 1; i < strs.length; i++){

            while (!strs[i].startsWith(prefix)){
                prefix = prefix.substring(0, prefix.length() - 1);

                if (prefix.isEmpty()){
                    return "";
                }
            }
        }

        return prefix;
    }

}
