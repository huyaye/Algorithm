
public class Sort {
	private static int[] input = { 6, 40, 32, 60, 1, 100, 89, 41, 57, 23 };

	public static void main(String[] args) {
		selectionSort(input.length);

		System.out.print("sort result : [ ");
		System.out.print(input[0]);
		for (int i = 1; i < input.length; i++) {
			System.out.print(", " + input[i]);
		}
		System.out.println(" ]");
	}

	private static void selectionSort(int N) {
		for (int i = 0; i < N - 1; i++) {
			int min = input[i];
			int targetIndex = i;
			for (int j = i + 1; j < N; j++) {
				if (min > input[j]) {
					min = input[j];
					targetIndex = j;
				}
			}
			int temp = input[i];
			input[i] = min;
			input[targetIndex] = temp;
		}
	}
	
	private static void insertSort()  {
		
	}

}
