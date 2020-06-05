package codility;

import java.util.Arrays;

public class MissingInteger {

	public static void main(String[] args) {
		int[] A = {1, 3, 6, 4, 1, 2};
		System.out.println(solution(A));
	}

	public static int solution(int[] A) {
		int result = 1;

		Arrays.sort(A);

		for (int i = 0; i < A.length; i++) {
			if (result < A[i]) {
				return result;
			}
			if (result == A[i]) {
				++result;
			}
		}

		return result;
	}

}
