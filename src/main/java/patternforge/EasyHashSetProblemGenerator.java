package patternforge;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;

/**
 * Easy tier, HashSet / HashMap pattern.
 *
 * Covers:
 * - Contains Duplicate (217)
 * - Valid Anagram (242)
 * - Two Sum (1)
 */
public class EasyHashSetProblemGenerator {

    private final Random random;

    public EasyHashSetProblemGenerator() {
        this.random = new Random();
    }

    public EasyHashSetProblemGenerator(long seed) {
        this.random = new Random(seed);
    }


    // 217: Contains Duplicate

    public Problem<int[], int[]> generateContainsDuplicateProblem(
            int minLength,
            int maxLength,
            boolean forceDuplicate) {

        int length = minLength + random.nextInt(maxLength - minLength + 1);
        int[] input = new int[Math.max(length, 1)];

        for (int i = 0; i < input.length; i++) {
            input[i] = random.nextInt(50);
        }

        if (forceDuplicate && input.length >= 2) {
            input[input.length - 1] = input[0];
        }

        boolean hasDuplicate = containsDuplicate(input);
        int[] expected = {hasDuplicate ? 1 : 0};

        String description = String.format(
                "Given an array of length %d, determine if any value appears more than once.",
                input.length
        );

        return new Problem<>(description, input.clone(), expected);
    }


    public static boolean containsDuplicate(int[] input) {

        HashSet<Integer> seen = new HashSet<>();

        for (int value : input) {
            if (!seen.add(value)) {
                return true;
            }
        }

        return false;
    }


    // 242: Valid Anagram

    public Problem<int[], int[]> generateValidAnagramProblem(
            int minLength,
            int maxLength,
            boolean forceAnagram) {

        int length = minLength + random.nextInt(maxLength - minLength + 1);
        length = Math.max(length, 1);

        char[] s = randomLowercaseWord(length);
        char[] t;

        if (forceAnagram) {
            t = s.clone();
            shuffle(t);
        } else {
            t = randomLowercaseWord(length);
        }

        boolean isAnagram = isAnagram(new String(s), new String(t));
        int[] expected = {isAnagram ? 1 : 0};

        // Temporary encoding:
        // [length][chars of s][chars of t]

        int[] combinedInput = new int[1 + s.length + t.length];
        combinedInput[0] = s.length;

        for (int i = 0; i < s.length; i++) {
            combinedInput[1 + i] = s[i];
        }

        for (int i = 0; i < t.length; i++) {
            combinedInput[1 + s.length + i] = t[i];
        }

        String description = String.format(
                "Given two strings of length %d, determine if the second is an anagram of the first.",
                length
        );

        return new Problem<>(description, combinedInput, expected);
    }


    public static boolean isAnagram(String s, String t) {

        if (s.length() != t.length()) {
            return false;
        }

        Map<Character, Integer> counts = new HashMap<>();

        for (char c : s.toCharArray()) {
            counts.merge(c, 1, Integer::sum);
        }

        for (char c : t.toCharArray()) {
            counts.merge(c, -1, Integer::sum);
        }

        for (int count : counts.values()) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }


    private char[] randomLowercaseWord(int length) {

        char[] word = new char[length];

        for (int i = 0; i < length; i++) {
            word[i] = (char) ('a' + random.nextInt(26));
        }

        return word;
    }


    private void shuffle(char[] chars) {

        for (int i = chars.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);

            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }
    }


    // 1: Two Sum

    public Problem<int[], int[]> generateTwoSumProblem(
            int minLength,
            int maxLength) {

        int length = minLength + random.nextInt(maxLength - minLength + 1);
        length = Math.max(length, 2);

        int[] nums = new int[length];

        for (int i = 0; i < length; i++) {
            nums[i] = random.nextInt(50);
        }

        int i = random.nextInt(length);
        int j;

        do {
            j = random.nextInt(length);
        } while (j == i);

        int target = nums[i] + nums[j];
        int[] expected = twoSum(nums, target);

        int[] combinedInput = new int[length + 1];
        System.arraycopy(nums, 0, combinedInput, 0, length);
        combinedInput[length] = target;

        String description = String.format(
                "Given an array of length %d and a target, return the indices of two numbers that add up to the target.",
                length
        );

        return new Problem<>(description, combinedInput, expected);
    }


    public static int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> seenValueToIndex = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            if (seenValueToIndex.containsKey(complement)) {
                return new int[]{seenValueToIndex.get(complement), i};
            }

            seenValueToIndex.put(nums[i], i);
        }

        return new int[0];
    }
}
