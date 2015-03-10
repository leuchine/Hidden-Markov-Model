package test;

public class ViterbiAlgorithm {
	public static double[][] tran = { { 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 1, 0, 0, 0 }, { 0, 0, 0.5, 0.5, 0, 0 },
			{ 0, 0, 0, 0.8, 0.2, 0 }, { 0, 0, 0, 0, 0.3, 0.7 },
			{ 0, 0, 0, 0, 0, 0 } };
	public static double[][] obser = { { 0, 0, 0, 0 }, { 0, 0, 0, 0 },
			{ 0, 0.8, 0.1, 0.1 }, { 0, 0.2, 0.3, 0.5 }, { 0, 0.2, 0.7, 0.1 } };

	// public static int[] obersequence = { 0, 1, 2, 2, 1, 3, 2, 3, 2, 2, 2, 3
	// };
	public static int[] obersequence = { 0, 1, 2, 3, 3, 2, 1 };
	public static int start = 2;
	public static int end = 4;

	public static void main(String[] args) {
		double[][] prob = new double[obser.length][obersequence.length];
		int[][] back = new int[obser.length][obersequence.length];
		for (int i = start; i <= end; i++) {
			prob[i][1] = tran[1][i] * obser[i][obersequence[1]];
		}
		for (int i = 2; i < obersequence.length; i++) {
			for (int j = start; j <= end; j++) {
				int position = 0;
				double max = 0;
				for (int k = 2; k <= end; k++) {
					if (prob[k][i - 1] * tran[k][j] * obser[j][obersequence[i]] > max) {
						max = prob[k][i - 1] * tran[k][j]
								* obser[j][obersequence[i]];
						position = k;
					}
				}
				prob[j][i] = max;
				back[j][i] = position;
			}
		}
		int position = 0;
		double max = 0;
		for (int k = 2; k <= end; k++) {
			if (prob[k][obersequence.length - 1] * prob[k][end + 1] > max) {
				max = prob[k][obersequence.length - 1] * tran[k][end + 1];
				position = k;
			}
		}
		System.out.println(max);

		for (int i = 0; i < prob.length; i++) {
			for (int j = 0; j < prob[0].length; j++) {
				System.out.print(prob[i][j] + " ");
			}
			System.out.println();
		}
		System.out.print(position + "  ");
		System.out.println(max);
		int p = position;
		for (int i = obersequence.length - 1; i >= 2; i--) {
			System.out.print(back[p][i] + " ");
			System.out.println(prob[p][i]);
			p = back[p][i];
		}
	}
}     