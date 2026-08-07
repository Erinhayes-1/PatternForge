package patternforge;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EasyArrayListProblemTests {


    //  Create Target Array (1389)

    @Test
    void createTargetArrayInsertsAtGivenIndices() {

        int[] nums = {0, 1, 2, 3, 4};
        int[] index = {0, 1, 2, 2, 1};

        int[] expected = {0, 4, 1, 3, 2};

        assertArrayEquals(
                expected,
                EasyArrayListProblemGenerator.createTargetArray(nums, index)
        );
    }


    @Test
    void createTargetArrayHandlesSingleElement() {

        int[] nums = {42};
        int[] index = {0};

        assertArrayEquals(
                new int[]{42},
                EasyArrayListProblemGenerator.createTargetArray(nums, index)
        );
    }


    @Test
    void generatedCreateTargetArrayProblemMatchesRequestedLength() {

        EasyArrayListProblemGenerator generator =
                new EasyArrayListProblemGenerator(4L);


        Problem<int[], int[]> problem =
                generator.generateCreateTargetArrayProblem(6);


        assertEquals(
                6,
                problem.getExpectedOutput().length
        );
    }



    // ---------- Pascal's Triangle row (118) ----------


    @Test
    void pascalsTriangleRowZeroIsJustOne() {

        assertArrayEquals(
                new int[]{1},
                EasyArrayListProblemGenerator.pascalsTriangleRow(0)
        );
    }


    @Test
    void pascalsTriangleRowFourIsCorrect() {

        int[] expected = {1, 4, 6, 4, 1};


        assertArrayEquals(
                expected,
                EasyArrayListProblemGenerator.pascalsTriangleRow(4)
        );
    }


    @Test
    void generatedPascalsTriangleRowHasCorrectLength() {

        EasyArrayListProblemGenerator generator =
                new EasyArrayListProblemGenerator(5L);


        Problem<Integer, int[]> problem =
                generator.generatePascalsTriangleRowProblem(3, 6);


        int rowIndex = problem.getInput();


        assertEquals(
                rowIndex + 1,
                problem.getExpectedOutput().length
        );
    }



    // Decompress RLE List (1313)


    @Test
    void decompressRLEListExpandsCorrectly() {

        int[] input = {1, 2, 3, 4};

        int[] expected = {2, 4, 4, 4};


        assertArrayEquals(
                expected,
                EasyArrayListProblemGenerator.decompressRLElist(input)
        );
    }


    @Test
    void decompressRLEListHandlesSinglePair() {

        int[] input = {5, 9};

        int[] expected = {9, 9, 9, 9, 9};


        assertArrayEquals(
                expected,
                EasyArrayListProblemGenerator.decompressRLElist(input)
        );
    }


    @Test
    void generatedDecompressProblemLengthMatchesFrequencies() {

        EasyArrayListProblemGenerator generator =
                new EasyArrayListProblemGenerator(6L);


        Problem<int[], int[]> problem =
                generator.generateDecompressRLEProblem(4);


        int expectedLength = 0;


        int[] input = problem.getInput();


        for (int i = 0; i < input.length; i += 2) {
            expectedLength += input[i];
        }


        assertEquals(
                expectedLength,
                problem.getExpectedOutput().length
        );
    }
}

