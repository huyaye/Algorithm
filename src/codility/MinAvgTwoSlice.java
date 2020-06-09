package codility;

/*
 * https://app.codility.com/programmers/lessons/5-prefix_sums/min_avg_two_slice/
 */
public class MinAvgTwoSlice {

	public static void main(String[] args) {
		int[] A = { 4, 2, 2, 5, 1, 5, 8 };
//		int[] A = { -100000, 100000 };

		System.out.println(solution(A));
	}

	/*
	 * Timeout
	 * O(N*N)
	 */
	public static int solution2(int[] A) {
		int sum[] = new int[A.length];

		sum[0] = A[0];
		for (int i = 1; i < A.length; i++) {
			sum[i] = sum[i - 1] + A[i];
		}

		int result = 0;
		double minVal = Double.MAX_VALUE;
		for (int i = 1; i < sum.length; i++) {
			double avg = (double)sum[i] / (double)(i + 1);
			if (avg < minVal) {
				result = i - 1;
				minVal = avg;
			}
		}	

		for (int i = 1; i < sum.length; i++) {
			for (int j = i + 1; j < sum.length; j++) {
				int count = j - i + 1;
				int range = sum[j] - sum[i - 1];
				double avg = (double)range / (double)count;
				if (avg < minVal) {
					result = i;
					minVal = avg;
				}
			}
		}

		return result;
	}
	
	public static int solution(int[] A) {
		int sum[] = new int[A.length];
		double avgs[] = new double[A.length];

		sum[0] = A[0];
		for (int i = 1; i < A.length; i++) {
			sum[i] = sum[i - 1] + A[i];
			avgs[i] = (double)sum[i] / (double)(i + 1); 
		}

		int result = 0;
		double minVal = Double.MAX_VALUE;
		for (int i = 1; i < sum.length; i++) {
			double avg = (double)sum[i] / (double)(i + 1);
			if (avg < minVal) {
				result = i - 1;
				minVal = avg;
			}
		}	

		return result;
	}

}
// 0   1   2   3   4   5   6
// 4   6   8  13  14  19  27
