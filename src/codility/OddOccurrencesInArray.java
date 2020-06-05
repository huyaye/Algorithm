package codility;

import java.util.Arrays;

public class OddOccurrencesInArray {

	public static void main(String[] args) {
		int[] A = {9, 3, 9, 3, 9, 7, 9};
		//		int[] A = {2, 2, 3, 3, 4};

		System.out.println(solution(A));
	}

	public static int solution(int[] A) {
		if (A.length == 1) {
			return A[0];
		}
		Arrays.sort(A);

		int count = 1;
		int checkNum = A[0];
		for (int i = 1; i < A.length; i++) {
			if (checkNum != A[i]) {
				if (count % 2 == 1) {
					return A[i - 1];
				} else {
					count = 1;
					checkNum = A[i];
				}
			} else {
				++count;
			}
		}
		return checkNum;
	}

}
