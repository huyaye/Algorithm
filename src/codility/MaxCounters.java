package codility;

/*
 * https://app.codility.com/programmers/lessons/4-counting_elements/max_counters/
 */
public class MaxCounters {

	public static void main(String[] args) {
		int N = 5;
		int[] A = {3, 4, 4, 6, 1, 4, 4};
		int[] result = solution(N, A);
		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i] + " ");
		}
	}

	public static int[] solution(int N, int[] A) {
		int[] result = new int[N];

		int min = 0;
		int max = 0;
		for (int i = 0; i < A.length; i++) {
			if (A[i] > N) {
				min = max;
			} else {
				result[A[i] - 1] = Math.max(min, result[A[i] - 1]);
				++result[A[i] - 1];
				max = Math.max(max, result[A[i] - 1]);
			}
		}
		for (int i = 0; i < result.length; i++) {
			if (result[i] < min) {
				result[i] = min;
			}
		}
		return result;
	}

}

