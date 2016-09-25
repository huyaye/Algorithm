package algospot;

/*
 * https://algospot.com/judge/problem/read/LAN
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class LAN {
	static int C; // 테스트케이스 갯수
	static int N; // 건물의 수
	static int M; // 이미 설치된 케이블의 수
	static List<Point> Points;
	static List<Edge> LinkedEdges;
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
		Point p1, p2;
		double distance;

		Edge(Point p1, Point p2) {
			this.p1 = p1;
			this.p2 = p2;
			this.distance = (p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y);
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
			return Math.sqrt(distance);
		}
		
		@Override
		public String toString() {
			return "Distance : " + getDistance() + ", Point : " + p1 + "<->" + p2;
		}
	}

	private static class DisJointSet {
		List<Set<Point>> setList = new ArrayList<Set<Point>>();

		public void merge(Point p1, Point p2) {
			Set<Point> set1 = find(p1);
			Set<Point> set2 = find(p2);
			if (set1.isEmpty()) {
				set1.add(p1);
				setList.add(set1);
			}
			if (set2.isEmpty()) {
				set2.add(p2);
			}
			set1.addAll(set2);
			setList.remove(set2);
		}

		public Set<Point> find(Point p) {
			for (int i = 0; i < setList.size(); i++) {
				if (setList.get(i) != null && setList.get(i).contains(p)) {
					return setList.get(i);
				}
			}
			return new HashSet<Point>();
		}
	}

	private static double makePath() {
		double ret = 0.0;
		for (int i = 0; i < Edges.size(); i++) {
			Point p1 = Edges.get(i).p1;
			Point p2 = Edges.get(i).p2;
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
			LinkedEdges = new ArrayList<Edge>();
			Edges = new ArrayList<Edge>();

			// Point 구성 
			int[] X = new int[N];
			for (int i = 0; i < N; i++) {
				X[i] = sc.nextInt();
			}
			for (int i = 0; i < N; i++) {
				Points.add(new Point(X[i], sc.nextInt()));
			}
			// 이미 연결되어 있는 Edge 구성
			for (int i = 0; i < M; i++) {
				Point p1 = Points.get(sc.nextInt());
				Point p2 = Points.get(sc.nextInt());
				Edges.add(new Edge(p1, p2));
			}
			disJointSet = new DisJointSet();
			// 이미 연결된 케이블로 DisJointSet 초기화
			makePath();
			// Edge 리스트 만들기
			Edges.clear();
			for (int i = 0; i < Points.size(); i++) {
				for (int j = i + 1; j < Points.size(); j++) {
					Edges.add(new Edge(Points.get(i), Points.get(j)));
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
