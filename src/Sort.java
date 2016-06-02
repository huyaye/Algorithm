
public class Sort {
	private static int[] input = { 6, 40, 32, 60, 1, 100, 89, 41, 57, 23, 1 };
//	private static int[] input = { 6 };

	public static void main(String[] args) {
//		selectionSort(input.length);
		insertionSort(input.length);

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
	
	private static void insertionSort(int N)  {
		for (int i = 1; i < N; i++) {
			int insertionValue = input[i];
			for (int j = i - 1; j >= 0; j--) {
				if (insertionValue > input[j]) {
					break;
				} else {
					input[j+1] = input[j];
					input[j] = insertionValue;
				}
			}
		}
	}

}
