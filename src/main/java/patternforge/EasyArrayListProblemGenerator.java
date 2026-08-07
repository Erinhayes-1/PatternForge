package patternforge;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Easy tier, ArrayList pattern:
 * Problems where List.add(index, value)
 * or dynamic growth is genuinely the technique.
 *
 * Covers:
 * - Create Target Array (1389)
 * - Pascal's Triangle row (118)
 * - Decompress RLE List (1313)
 */
public class EasyArrayListProblemGenerator {

    private final Random random;

    public EasyArrayListProblemGenerator() {
        this.random = new Random();
    }

    public EasyArrayListProblemGenerator(long seed) {
        this.random = new Random(seed);
    }


    // ---------- 1389: Create Target Array ----------

    public Problem<int[], int[]> generateCreateTargetArrayProblem(int length) {

        int[] nums = new int[length];
        int[] index = new int[length];

        for (int i = 0; i < length; i++) {
            nums[i] = random.nextInt(100);
            index[i] = random.nextInt(i + 1);
        }

        int[] expected = createTargetArray(nums, index);

        int[] combinedInput = new int[length * 2];

        System.arraycopy(nums, 0, combinedInput, 0, length);
        System.arraycopy(index, 0, combinedInput, length, length);

        String description = String.format(
                "Given %d values and indexes, insert each value at its index and return the final array.",
                length
        );

        return new Problem<>(description, combinedInput, expected);
    }


    public static int[] createTargetArray(int[] nums, int[] index) {

        List<Integer> target = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            target.add(index[i], nums[i]);
        }

        int[] result = new int[target.size()];

        for (int i = 0; i < result.length; i++) {
            result[i] = target.get(i);
        }

        return result;
    }


    // ---------- 118: Pascal's Triangle Row ----------

    public Problem<Integer, int[]> generatePascalsTriangleRowProblem(
            int minRow,
            int maxRow) {

        int rowIndex = minRow + random.nextInt(maxRow - minRow + 1);
        int[] expected = pascalsTriangleRow(rowIndex);

        String description = String.format(
                "Return row %d of Pascal's Triangle (0-indexed).",
                rowIndex
        );

        return new Problem<>(description, rowIndex, expected);
    }


    public static int[] pascalsTriangleRow(int rowIndex) {

        List<Integer> row = new ArrayList<>();
        row.add(1);

        for (int r = 1; r <= rowIndex; r++) {

            List<Integer> nextRow = new ArrayList<>();
            nextRow.add(1);

            for (int i = 1; i < r; i++) {
                nextRow.add(row.get(i - 1) + row.get(i));
            }

            nextRow.add(1);
            row = nextRow;
        }

        int[] result = new int[row.size()];

        for (int i = 0; i < result.length; i++) {
            result[i] = row.get(i);
        }

        return result;
    }


    // ---------- 1313: Decompress Run-Length Encoded List ----------

    public Problem<int[], int[]> generateDecompressRLEProblem(int pairCount) {

        int[] encoded = new int[pairCount * 2];

        for (int i = 0; i < pairCount; i++) {
            encoded[i * 2] = 1 + random.nextInt(4);
            encoded[i * 2 + 1] = random.nextInt(20);
        }

        int[] expected = decompressRLElist(encoded);

        String description = String.format(
                "Given %d frequency/value pairs, decompress into the full list.",
                pairCount
        );

        return new Problem<>(description, encoded, expected);
    }


    public static int[] decompressRLElist(int[] nums) {

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < nums.length; i += 2) {

            int freq = nums[i];
            int val = nums[i + 1];

            for (int f = 0; f < freq; f++) {
                result.add(val);
            }
        }

        int[] output = new int[result.size()];

        for (int i = 0; i < output.length; i++) {
            output[i] = result.get(i);
        }

        return output;
    }
}