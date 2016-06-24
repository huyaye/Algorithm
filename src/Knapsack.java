
public class Knapsack {

	static int N = 5;
	static int W = 15;
	static int[] ci = { -1, 2, 1, 10, 2, 4 };
	static int[] wi = { -1, 2, 1, 4, 1, 12 };

	static int[][] M = new int[N + 1][W + 1];

	public static void main(String[] args) {
		for (int i = 0; i < N + 1; i++) {
			for (int j = 0; j < W + 1; j++) {
				M[i][j] = 0;
			}
		}
		
		for (int i = 1; i < N + 1; i++) {
			for (int w = 1; w < W + 1; w++) {
				if (i - 1 <= 0 && wi[i] >= w) {
					M[i][w] = ci[i];
				} else {
					int a = M[i - 1][w];
					int b = 0;
					if (w - wi[i] >= 0) {
						b = M[i - 1][w - wi[i]] + ci[i];
					}
					M[i][w] = Math.max(a, b);
				}
			}
		}
		
		System.out.println("Result is : " + M[N][W]);
	}

}
