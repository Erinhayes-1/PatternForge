package patternforge;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;

/**
 * Easy tier, HashSet / HashMap pattern.
 * Covers the "trade O(n^2) brute force for O(n) lookup" family:
 * Contains Duplicate (217), Valid Anagram (242), Two Sum (1).
 */
public class EasyHashSetProblemGenerator {

    private final Random random;

    public EasyHashSetProblemGenerator() {
        this.random = new Random();
    }

    public EasyHashSetProblemGenerator(long seed) {
        this.random = new Random(seed);
    }

    // ---------- 217: Contains Duplicate ----------

    public Problem generateContainsDuplicateProblem(int minLength, int maxLength, boolean forceDuplicate) {
        int length = minLength + random.nextInt(maxLength - minLength + 1);
        int[] input = new int[Math.max(length, 1)];

        for (int i = 0; i < input.length; i++) {
            input[i] = random.nextInt(50);
        }

        if (forceDuplicate && input.length >= 2) {
            input[input.length - 1] = input[0];
        }

        boolean hasDuplicate = containsDuplicate(input);
        int[] expected = { hasDuplicate ? 1 : 0 };

        String description = String.format(
                "Given an array of length %d, determine if any value appears more than once.",
                input.length
        );

        return new Problem(description, input.clone(), expected);
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

    // ---------- 242: Valid Anagram ----------

    /**
     * Problem only carries int[] in/out, not strings, so this encodes
     * both words as one int[]: [lengthOfS, charCodesOfS..., charCodesOfT...].
     * This is a workaround, not a clean design, flagged here on purpose.
     * Worth revisiting once Problem is refactored to support mixed types.
     */
    public Problem generateValidAnagramProblem(int minLength, int maxLength, boolean forceAnagram) {
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
        int[] expected = { isAnagram ? 1 : 0 };

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

        return new Problem(description, combinedInput, expected);
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

    // ---------- 1: Two Sum ----------

    /**
     * Input array packs nums followed by the target as the last element,
     * since Problem only holds one int[]. Output is the pair of indices.
     */
    public Problem generateTwoSumProblem(int minLength, int maxLength) {
        int length = minLength + random.nextInt(maxLength - minLength + 1);
        length = Math.max(length, 2);

        int[] nums = new int[length];
        for (int i = 0; i < length; i++) {
            nums[i] = random.nextInt(50);
        }

        // guarantee a valid pair exists by picking two indices
        // and setting target to their sum
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
                "Given an array of length %d and a target, return the indices of the two numbers that add up to the target.",
                length
        );

        return new Problem(description, combinedInput, expected);
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> seenValueToIndex = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (seenValueToIndex.containsKey(complement)) {
                return new int[] { seenValueToIndex.get(complement), i };
            }
            seenValueToIndex.put(nums[i], i);
        }
        return new int[0]; // no valid pair found
    }
}
    

