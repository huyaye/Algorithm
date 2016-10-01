package algospot;

/*
 * https://algospot.com/judge/problem/read/LAN
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LAN_Prim {
	static int C; // 테스트케이스 갯수
	static int N; // 건물의 수
	static int M; // 이미 설치된 케이블의 수
	static Point[] Points;
	static boolean[][] linkedMap;	// 이미 설치된 건물 표시
	static double[] minDistance;	// 연결된 부모건물들과의 거리 중 최소거리
	static boolean[] added;	// 연결된 건물 표시

	private static class Point {
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "(" + x + ", " + y + ")";
		}
	}

	public static void main(String[] args) {
		try {
			System.setIn(new FileInputStream("testcase/lan.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Scanner sc = new Scanner(System.in);
		C = sc.nextInt();
		for (int test_case = 1; test_case <= C; test_case++) {
			N = sc.nextInt();
			M = sc.nextInt();
			Points = new Point[N];
			linkedMap = new boolean[N][N];
			minDistance = new double[N];
			added = new boolean[N];
			for (int i = 0; i < N; i++) {
				minDistance[i] = Double.MAX_VALUE;
			}

			// 기지 좌표 구성
			int[] X = new int[N];
			for (int i = 0; i < N; i++) {
				X[i] = sc.nextInt();
			}
			for (int i = 0; i < N; i++) {
				Points[i] = new Point(X[i], sc.nextInt());
			}
			// 이미 연결되어 있는 기지 표시
			for (int i = 0; i < M; i++) {
				int p1 = sc.nextInt();
				int p2 = sc.nextInt();
				linkedMap[p1][p2] = true;
				linkedMap[p2][p1] = true;
			}
			/*
			 * Prim 알고리즘
			 */
			double ret = 0.0;
			minDistance[0] = 0.0;
			for (int i = 0; i < N; i++) {
				// 트리에 추가할 건물(u)을 찾는다. u는 트리로부터 가장 거리가 짧은 건물.
				int u = -1;
				for (int j = 0; j < N; j++) {
					if (!added[j] && (u == -1 || minDistance[u] > minDistance[j])) {
						u = j;
					}
				}
				// 찾아낸 건물(u)을 트리에 추가하고 거리(ret)를 누적시킨다.
				added[u] = true;
				ret += minDistance[u];
				// u 와 연결된 건물들(트리에는 포함되지 않은) 의 minDistance 를 갱신.
				for (int j = 0; j < N; j++) {
					if (j != u && !added[j]) {
						double distance = linkedMap[j][u] ? 0.0 : 
							(Math.sqrt((Points[u].x - Points[j].x) * (Points[u].x - Points[j].x) + 
									(Points[u].y - Points[j].y) * (Points[u].y - Points[j].y)));
						if (minDistance[j] > distance) {
							minDistance[j] = distance; 
						}
					}
				}
			}
			System.out.println(ret);
		}
	}
}
