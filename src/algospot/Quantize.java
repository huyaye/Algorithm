/*
 * https://algospot.com/judge/problem/read/QUANTIZE
 */
package algospot;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Quantize {
	static int C;	// 테스트케이스 
	static int N;	// 수열의 길 (1<=N<=100)
	static int S;	// 사용할 숫자의 수 (1<=S<=10)
	static int[] Sequence;	// 수열
	
	public static void main(String[] args) {
		try {
			System.setIn(new FileInputStream("testcase/quantize.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Scanner sc = new Scanner(System.in);
		C = sc.nextInt();
		for (int testcase = 0; testcase < C; testcase++) {
			N = sc.nextInt();
			S = sc.nextInt();
			Sequence = new int[N];
			for (int i = 0; i < N; i++) {
				Sequence[i] = sc.nextInt();
			}
			Arrays.sort(Sequence);
		}
	}
}
