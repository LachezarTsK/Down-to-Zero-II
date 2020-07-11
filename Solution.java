import java.util.Scanner;

public class Solution {

  // Maximum value of the start position, as per the challenge conditions.
  private static final int MAX_INPUT = (int) Math.pow(10, 6);

  /**
   * Array 'minMoves' stores the precomputed minimum moves for all possible 
   * input values of 'start', from 0 to MAX_INPUT.
   *
   * Since there are multiple queries, and some of them with quite high 
   * input values of 'start', not storing the precomputed minimum moves 
   * will result in time-out for some test cases of this challenge.
   */
  private static int[] minMoves = new int[MAX_INPUT + 1];

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    int numberOfQueries = scanner.nextInt();
    precompute_minimumNumberOfMoves_for_allValues_up_to_maxInput();

    while (numberOfQueries-- > 0) {
      int start = scanner.nextInt();
      System.out.println(minMoves[start]);
    }
    scanner.close();
  }

  /**
   * Calculates the minimum moves for all possible input values of 'start' 
   * and stores them in array 'minMoves'.
   *
   * As per the challenge conditions, the moves begin at the input value 
   * (variable 'start') and continue until zero is reached.
   *
   * The method calculates the minimum moves by working its way backwards, 
   * from the value of the goal(0) to the maximum value of the start(MAX_INPUT).
   */
  private static void precompute_minimumNumberOfMoves_for_allValues_up_to_maxInput() {

    Arrays.fill(minMoves, -1);
    minMoves[0] = 0;
    minMoves[1] = 1;
    minMoves[2] = 2;
    minMoves[3] = 3;

    // Checks for minimum moves by observing the substration condition of the challenge.
    for (int i = 0; i < minMoves.length; i++) {
      if (minMoves[i] == -1 || (minMoves[i] > 0 && minMoves[i] > minMoves[i - 1] + 1)) {
        minMoves[i] = minMoves[i - 1] + 1;
      }

      // Checks for minimum moves by observing the multiplication condition of the challenge.
      for (int j = 1; j <= i && j * i < minMoves.length; j++) {
        if (minMoves[j * i] == -1 || (minMoves[j * i] > 0 && minMoves[j * i] > minMoves[i] + 1)) {
          minMoves[j * i] = minMoves[i] + 1;
        }
      }
    }
  }
}
