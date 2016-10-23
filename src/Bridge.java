
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Bridge {
	private static int T;	// TC 개수
	private static int N;	// 다리 개수 (5 <= N <= 100)
	private static int K;	// 다리를 건널 수 있는 최대 횟수 (3 <= K <= 16)
	private static int G;	// 도착점 위치 (100 <= G <= 50,000)
	private static int[][] X;	// 거리
	private static int[][] Min;	// 메모이제이션 [K][N] (n번째 다리로부터 k횟수만큼 다리를 건넜을때의 최소 거리)
	private static int result;
	
	public static void main(String[] args) {
		try {
			System.setIn(new FileInputStream("testcase/bridge.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int testcase = 1; testcase <= T; testcase++) {
			N = sc.nextInt();
			K = sc.nextInt();
			G = sc.nextInt();
			X = new int[2][N];
			for (int i = 0; i < N; i++) {
				X[0][i] = sc.nextInt();
			}
			for (int i = 0; i < N; i++) {
				X[1][i] = sc.nextInt();
			}
			// 메모이제이션 테이블 초기화
			Min = new int[K + 1][N];
			for (int k = 1; k <= K; k++) {
				for (int n = 0; n < N; n++) {
					Min[k][n] = Integer.MAX_VALUE;
				}
			}
			result = Integer.MAX_VALUE;
			// 다리를 한번만 건넌 경우,
			for (int n = 0; n < N; n++) {
				Min[1][n] = X[0][n] + (G - X[1][n]);
				result = result > Min[1][n] ? Min[1][n] : result;
			}
			// 다리를 2번 이상 건너는 경우,
			for (int k = 2; k <= K; k++) {
				for (int n = 0; n < N - k + 1; n++) {
					Min[k][n] = X[(k % 2 == 0 ? 1 : 0)][n] + findPreviousMin(k, n) - X[k % 2 == 0 ? 0 : 1][n]; 
					if (k % 2 == 1) {	// 마지막은 강의 남단에 위치해야 하므로 홀수횟수 다리이동이 있는 경우에만 최소거리가 의미있음.
						result = result > Min[k][n] ? Min[k][n] : result;
					}
				}
			}
			System.out.println("#" + testcase + " " + result);
		}
	}
	
	// 이전 횟수의 다음지점까지의 이동거리중 최소값 리턴
	private static int findPreviousMin(int k, int n) {
		int min = Integer.MAX_VALUE;
		for (int i = n + 1; i < N; i++) {
			min = min > Min[k - 1][i] ? Min[k - 1][i] : min; 
		}
		return min;
	}
}
