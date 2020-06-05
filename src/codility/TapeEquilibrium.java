package codility;

/*
 * https://app.codility.com/programmers/lessons/3-time_complexity/tape_equilibrium/
 */
public class TapeEquilibrium {

	public static void main(String[] args) {
		int[] A = {3, 1, 2, 4, 3};
		System.out.println(solution(A));
	}

	public static int solution(int[] A) {
		int result = Integer.MAX_VALUE;

		int[] SUM = new int[A.length];
		SUM[0] = A[0];
		for (int i = 1; i < A.length; i++) {
			SUM[i] = A[i] + SUM[i - 1];
		}

		for (int i = 0; i < A.length - 1; i++) {
			int diff = Math.abs(SUM[i] - (SUM[A.length - 1] - SUM[i]));
			result = Math.min(result, diff);
		}

		return result;
	}

}
