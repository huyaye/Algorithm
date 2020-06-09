package codility;

/*
 * https://app.codility.com/programmers/lessons/5-prefix_sums/passing_cars/
 */
public class PassingCars {

	public static void main(String[] args) {
		int[] A = { 0, 1, 0, 1, 1 };
		System.out.println(solution(A));
	}

	public static int solution(int[] A) {
		if (A.length == 1) {
			return 0;
		}

		int[] countOfOne = new int[A.length];
		countOfOne[A.length - 1] = A[A.length - 1] == 1 ? 1 : 0;

		for (int i = A.length - 2; i >= 0; i--) {
			if (A[i] == 1) {
				countOfOne[i] = countOfOne[i + 1] + 1;
			} else {
				countOfOne[i] = countOfOne[i + 1];
			}
		}

		int result = 0;
		for (int i = 0; i < A.length - 1; i++) {
			if (A[i] == 0) {
				result += countOfOne[i + 1];
				if (result > 1_000_000_000) {
					return -1;
				}
			}
		}

		return result;
	}
}
