package codility;

import java.util.Arrays;

public class PermCheck {

	public static void main(String[] args) {
		int[] A = {4, 1, 2, 3};
		System.out.println(solution(A));
	}

	public static int solution(int[] A) {
		Arrays.sort(A);

		if (A[0] != 1) {
			return 0;
		}

		int value = A[0];
		for (int i = 1; i < A.length; i++) {
			if (A[i] - value != 1) {
				return 0;
			}
			value = A[i];
		}

		return 1;
	}

}
