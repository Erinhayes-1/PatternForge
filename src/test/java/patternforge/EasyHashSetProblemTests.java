
package patternforge;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class EasyHashSetProblemTests {

        // ---------- Contains Duplicate (217) ----------

    @Test
    void containsDuplicateDetectsARepeat() {
        int[] input = {1, 2, 3, 2, 5};
        assertTrue(EasyHashSetProblemGenerator.containsDuplicate(input));
    }

    @Test
    void containsDuplicateReturnsFalseWhenAllUnique() {
        int[] input = {1, 2, 3, 4, 5};
        assertFalse(EasyHashSetProblemGenerator.containsDuplicate(input));
    }

    @Test
    void containsDuplicateHandlesSingleElement() {
        int[] input = {7};
        assertFalse(EasyHashSetProblemGenerator.containsDuplicate(input));
    }

    @Test
    void containsDuplicateHandlesEmptyArray() {
        int[] input = {};
        assertFalse(EasyHashSetProblemGenerator.containsDuplicate(input));
    }

    @Test
    void generatedDuplicateProblemHonoursForceFlag() {
        EasyHashSetProblemGenerator generator = new EasyHashSetProblemGenerator(1L);
        Problem problem = generator.generateContainsDuplicateProblem(5, 10, true);

        // expectedOutput[0] == 1 means "has duplicate"
        assertEquals(1, problem.getExpectedOutput()[0]);
    }

    // ---------- Valid Anagram (242) ----------

    @Test
    void validAnagramDetectsTrueAnagram() {
        assertTrue(EasyHashSetProblemGenerator.isAnagram("listen", "silent"));
    }

    @Test
    void validAnagramRejectsDifferentLetterCounts() {
        assertFalse(EasyHashSetProblemGenerator.isAnagram("aab", "abb"));
    }

    @Test
    void validAnagramRejectsDifferentLengths() {
        assertFalse(EasyHashSetProblemGenerator.isAnagram("abc", "ab"));
    }

    @Test
    void validAnagramHandlesEmptyStrings() {
        assertTrue(EasyHashSetProblemGenerator.isAnagram("", ""));
    }

    @Test
    void generatedAnagramProblemHonoursForceFlag() {
        EasyHashSetProblemGenerator generator = new EasyHashSetProblemGenerator(2L);
        Problem problem = generator.generateValidAnagramProblem(4, 8, true);

        assertEquals(1, problem.getExpectedOutput()[0]);
    }

    // ---------- Two Sum (1) ----------

    @Test
    void twoSumFindsCorrectPair() {
        int[] nums = {2, 7, 11, 15};
        int[] result = EasyHashSetProblemGenerator.twoSum(nums, 9);
        assertArrayEquals(new int[] {0, 1}, result);
    }

    @Test
    void twoSumReturnsEmptyWhenNoPairExists() {
        int[] nums = {1, 2, 3};
        int[] result = EasyHashSetProblemGenerator.twoSum(nums, 100);
        assertEquals(0, result.length);
    }

    @Test
    void generatedTwoSumProblemHasValidPairInExpectedOutput() {
        EasyHashSetProblemGenerator generator = new EasyHashSetProblemGenerator(3L);
        Problem problem = generator.generateTwoSumProblem(3, 8);

        // expected output should be exactly two indices
        assertEquals(2, problem.getExpectedOutput().length);
    }
}



