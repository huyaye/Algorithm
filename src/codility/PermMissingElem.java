package codility;

import java.util.Arrays;

public class PermMissingElem {

	public static void main(String[] args) {
		//		int[] A = {2, 3, 1, 5};
		int[] A = {2, 3};

		System.out.println(solution(A));
	}

	public static int solution(int[] A) {
		if (A.length == 0) {
			return 1;
		} else if (A.length == 1) {
			if (A[0] == 1) {
				return 2;
			} else {
				return 1;
			}
		}
		Arrays.sort(A);

		int start = A[0];
		for (int i = 1; i < A.length; i++) {
			if (A[i] - A[i - 1] != 1) {
				return A[i] - 1;
			}
		}

		return (start == 1) ? A.length + 1 : 1;
	}
}
