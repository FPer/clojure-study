package ga;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Main {

	private static final int RANGE = 3;
	private static final int DATA_NUM = 10;
	private static final int GENE_NUM = 10;
	private static final int CHILD_NUM = 2;
	private static final int DICE_NUM = 2;
	private static final int[] ANSWER = new int[] { 2, 2, 1, 3, 1, 3, 1, 2, 3, 3 };

	private static final String KEY_DATA = "DATA";
	private static final String KEY_SCORE = "SCORE";
	private static final String KEY_GENERATION = "GENERATION";

	public static void main(String[] args) {

		List<Map<String, Object>> geneList = getInitGeneList();

		for (Map<String, Object> gene : geneList) {
			gene.put(KEY_SCORE, mark(gene));

			if ((int) gene.get(KEY_SCORE) == DATA_NUM) {
				return;
			}
		}

		int generation = 2;
		while (true) {

			for (Map<String, Object> gene : geneList) {
				print(gene);
			}

			sortGene(geneList);

			geneList.addAll(crossOver(geneList.get(GENE_NUM - 1), geneList.get(GENE_NUM - 2), generation));

			for (int i = 0; i < CHILD_NUM; i++) {
				geneList.remove(0);
			}

			for (Map<String, Object> gene : geneList) {
				gene.put(KEY_SCORE, mark(gene));
				print(gene);

				if ((int) gene.get(KEY_SCORE) == DATA_NUM) {
					return;
				}
			}

			generation++;
		}

	}

	public static List<Map<String, Object>> getInitGeneList() {
		List<Map<String, Object>> geneList = new ArrayList<>();
		for (int i = 0; i < GENE_NUM; i++) {
			geneList.add(createGene());
		}

		return geneList;
	}

	public static Map<String, Object> createGene() {

		Random random = new Random();

		int[] data = new int[DATA_NUM];
		for (int i = 0; i < data.length; i++) {
			data[i] = random.nextInt(RANGE) + 1;
		}

		Map<String, Object> gene = new HashMap<>();

		gene.put(KEY_DATA, data);
		gene.put(KEY_GENERATION, 1);

		return gene;
	}

	public static void print(Map<String, Object> gene) {

		int[] data = (int[]) gene.get(KEY_DATA);

		StringBuilder sb = new StringBuilder();
		Arrays.stream(data).forEach(value -> sb.append(value + " "));

		System.out.println(
				"世代：" + gene.get(KEY_GENERATION) + "　スコア：" + gene.get(KEY_SCORE) + "　遺伝子:[" + sb.toString() + "]");
	}

	public static void sortGene(List<Map<String, Object>> geneList) {
		Collections.sort(geneList, new Comparator<Map<String, Object>>() {

			@Override
			public int compare(Map<String, Object> gene1, Map<String, Object> gene2) {
				int score1 = (int) gene1.get(KEY_SCORE);
				int score2 = (int) gene2.get(KEY_SCORE);

				if (score1 > score2) {
					return 1;
				} else if (score1 == score2) {
					int generation1 = (int) gene1.get(KEY_GENERATION);
					int generation2 = (int) gene2.get(KEY_GENERATION);

					if (generation1 > generation2) {
						return 1;
					} else if (generation1 == generation2) {
						return 0;
					} else {
						return -1;
					}
				} else {
					return -1;
				}
			}
		});

	}

	public static List<Map<String, Object>> crossOver(Map<String, Object> gene1, Map<String, Object> gene2,
			int generation) {

		List<Map<String, Object>> childs = new ArrayList<>();

		int crossPosition = 0;
		for (int i = 0; i < DICE_NUM; i++) {
			crossPosition += new Random().nextInt(6) + 1;
		}

		Map<String, Object> child1 = createClossGene((int[]) gene1.get(KEY_DATA), (int[]) gene2.get(KEY_DATA),
				crossPosition, generation);
		Map<String, Object> child2 = createClossGene((int[]) gene2.get(KEY_DATA), (int[]) gene1.get(KEY_DATA),
				crossPosition, generation);

		childs.add(child1);
		childs.add(child2);

		return childs;
	}

	public static void mutation(int[] data) {

		int mutationPosition = 0;
		for (int i = 0; i < DICE_NUM; i++) {
			mutationPosition += new Random().nextInt(6) + 1;
		}

		if (mutationPosition == 11) {
			mutationPosition = 1;
		} else if (mutationPosition == 12) {
			return;
		}

		int before = data[mutationPosition - 1];
		data[mutationPosition - 1] = (before == 3) ? 1 : before + 1;
	}

	public static Map<String, Object> createClossGene(int[] src1, int[] src2, int crossPosition, int generation) {
		int[] dest = new int[DATA_NUM];

		int position = (crossPosition < 11) ? crossPosition - 1 : -1;

		if (position > 0) {
			System.arraycopy(src1, 0, dest, 0, position);
			System.arraycopy(src2, position, dest, position, dest.length - position);
		} else {
			System.arraycopy(src1, 0, dest, 0, src1.length);
		}

		Map<String, Object> gene = new HashMap<>();

		mutation(dest);

		gene.put(KEY_DATA, dest);
		gene.put(KEY_GENERATION, generation);

		return gene;
	}

	public static int mark(Map<String, Object> gene) {

		int[] data = (int[]) gene.get(KEY_DATA);

		int score = 0;

		for (int i = 0; i < data.length; i++) {
			if (ANSWER[i] == data[i]) {
				score++;
			}
		}

		return score;

	}
}
