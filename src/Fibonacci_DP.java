
/*
 * https://www.acmicpc.net/problem/1003
 */
import java.util.InputMismatchException;
import java.util.Scanner;

class Fibonacci_DP {
	private static int[] zero = new int[41];
	private static int[] one = new int[41];

	private static void logic(int N) {
		for (int i = 2; i <= N; i++) {
			zero[i] = zero[i - 1] + zero[i - 2];
			one[i] = one[i - 1] + one[i - 2];
		}
		System.out.println(zero[N] + " " + one[N]);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try {
			zero[0] = 1;
			zero[1] = 0;
			one[0] = 0;
			one[1] = 1;
			int T = sc.nextInt();
			int N[] = new int[T];
			for (int i = 0; i < T; i++) {
				N[i] = sc.nextInt();
			}
			for (int i = 0; i < T; i++) {
				logic(N[i]);
			}
		} catch (InputMismatchException e) {
			System.out.println("Input should be an integer.");
		} finally {
			sc.close();
		}
	}
}
