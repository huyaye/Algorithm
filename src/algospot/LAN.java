package algospot;

/*
 * https://algospot.com/judge/problem/read/LAN
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class LAN {
	static int C; // 테스트케이스 갯수
	static int N; // 건물의 수
	static int M; // 이미 설치된 케이블의 수
	static List<Point> Points;
	static boolean[][] linkedMap;
	static List<Edge> Edges;
	static DisJointSet disJointSet;

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

	private static class Edge implements Comparable<Edge> {
		int p1, p2;
		double distance;

		Edge(int p1, int p2) {
			this.p1 = p1;
			this.p2 = p2;
			if (linkedMap[p1][p2]) {
				this.distance = 0.0;
			} else {
				Point p1_ = Points.get(p1);
				Point p2_ = Points.get(p2);
				this.distance = (p2_.x - p1_.x) * (p2_.x - p1_.x) + (p2_.y - p1_.y) * (p2_.y - p1_.y);
			}
		}

		@Override
		public int compareTo(Edge o) {
			if (distance > o.distance) {
				return 1;
			} else if (distance < o.distance) {
				return -1;
			}
			return 0;
		}

		private double getDistance() {
			if (linkedMap[p1][p2]) {
				return distance;
			} else {
				return Math.sqrt(distance);
			}
		}

		@Override
		public String toString() {
			return "Distance : " + getDistance() + ", Point : " + Points.get(p1) + "<->" + Points.get(p2);
		}
	}

	private static class DisJointSet {
		int[] parent;

		DisJointSet(int n) {
			parent = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = i;
			}
		}

		public int find(int p) {
			if (p == parent[p]) {
				return p;
			}
			return find(parent[p]);
		}

		public void merge(int p1, int p2) {
			p1 = find(p1);
			p2 = find(p2);
			if (p1 == p2) {
				return;
			}
			parent[p1] = p2;
		}
	}

	private static double makePath() {
		double ret = 0.0;
		for (int i = 0; i < Edges.size(); i++) {
			int p1 = Edges.get(i).p1;
			int p2 = Edges.get(i).p2;
			/*
			 * 사이클 검사
			 */
			if (disJointSet.find(p1) == disJointSet.find(p2)) {
				continue;
			} else {
				disJointSet.merge(p1, p2);
				ret += Edges.get(i).getDistance();
			}
		}
		return ret;
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
			Points = new ArrayList<Point>();
			linkedMap = new boolean[N][N];
			Edges = new ArrayList<Edge>();

			// 기지 좌표 구성
			int[] X = new int[N];
			for (int i = 0; i < N; i++) {
				X[i] = sc.nextInt();
			}
			for (int i = 0; i < N; i++) {
				Points.add(new Point(X[i], sc.nextInt()));
			}
			// 이미 연결되어 있는 기지 표시
			for (int i = 0; i < M; i++) {
				int p1 = sc.nextInt();
				int p2 = sc.nextInt();
				linkedMap[p1][p2] = true;
				linkedMap[p2][p1] = true;
			}
			disJointSet = new DisJointSet(N);
			// Edge 리스트 만들기
			for (int i = 0; i < Points.size(); i++) {
				for (int j = i + 1; j < Points.size(); j++) {
					Edges.add(new Edge(i, j));
				}
			}
			// 거리 오름차순으로 Edge 리스트 정렬
			Collections.sort(Edges);
			// 필요한 최소 케이블 길이 구하기
			double ret = makePath();
			System.out.println(ret);
		}
	}
}
