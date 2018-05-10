package uni.ml.dataset;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.AllArgsConstructor;
import lombok.ToString;
import uni.ml.util.Interval;

/**
 * Convenient class to sample instance indices.
 * @author Julian Brummer
 */
public class Sampling {
	
	@AllArgsConstructor
	@ToString(includeFieldNames=true)
	public static class Split {
		private List<Integer> first;
		private List<Integer> second;
		
		public int[] first() {
			return first.stream().mapToInt(i->i).toArray();
		}
		
		public int[] second() {
			return second.stream().mapToInt(i->i).toArray();
		}
		
		public int sizeFirst() {
			return first.size();
		}
		
		public int sizeSecond() {
			return second.size();
		}
	}
	
	public static Split randomSplit(float ratio, int numIndices) {
		int n = Math.min((int) Math.ceil(ratio*numIndices), numIndices);
		int[] indices = IntStream.range(0, numIndices).toArray();
		List<Integer> indexList = Arrays.stream(indices).boxed().collect(Collectors.toList());
		Collections.shuffle(indexList);
		return new Split(indexList.subList(0, n), indexList.subList(n, numIndices));
	}
	
	
	public static int[] weightedBootstrap(List<Interval> distribution) {
		int[] indices = new int[distribution.size()];
		for (int i = 0; i < indices.length; i++) {
			float draw = (float) Math.random();
			for (int j = 0; j < distribution.size(); j++) {
				if (distribution.get(j).containsValue(draw)) {
					indices[i] = j;
					break;
				}
			}	
		}
		return indices;
	}
}
