package xu.com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。

注意：答案中不可以包含重复的三元组。

例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，

满足要求的三元组集合为：
[
  [-1, 0, 1],
  [-1, -1, 2]
]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/3sum
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class Solution0015 {

	/**
	 * @Desc 时间复杂度O(n^2)
	 * 
	 * 
	 * @param nums
	 * @return
	 */
	//解法1
	public List<List<Integer>> threeSum1(int[] nums) {

		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		HashSet<List<Integer>> set = new HashSet<List<Integer>>();

		for (int i = 0; i < nums.length; i++) {

			int target = 0 - nums[i];
			map.clear();

			for (int j = i + 1; j < nums.length; j++) {
				int complete = target - nums[j];
				if (map.containsKey(complete)) {

					// sort three nums
					int[] item = new int[] { nums[i], complete, nums[j] };
					Arrays.sort(item);
					List<Integer> list = new ArrayList<Integer>();
					list.add(item[0]);
					list.add(item[1]);
					list.add(item[2]);
					set.add(list);
					map.remove(complete);
					continue;
				}
				map.put(nums[j], j);
			}

		}

		Iterator<List<Integer>> iter = set.iterator();
		while(iter.hasNext()) {
			List<Integer> item = iter.next();
			result.add(item);
		}
		return result;
	}

	//解法二
	public List<List<Integer>> threeSum(int[] nums) {

		List<List<Integer>> result = new ArrayList<List<Integer>>();

		HashMap<Integer, Integer> numTimesMap = new HashMap<Integer, Integer>();

		for (int i = 0; i < nums.length; i++) {
			if (numTimesMap.containsKey(nums[i])) {
				numTimesMap.put(nums[i], numTimesMap.get(nums[i]) + 1);
			} else {
				numTimesMap.put(nums[i], 1);
			}
		}

		List<Integer> posList = new ArrayList<Integer>();
		List<Integer> negList = new ArrayList<Integer>();
		for (Integer num : numTimesMap.keySet()) {
			if (num >= 0)
				posList.add(num);
			else
				negList.add(num);
		}
		
		if(numTimesMap.containsKey(0) && numTimesMap.get(0) >= 3) {
			List<Integer> list = new ArrayList<Integer>();
			list.add(0);
			list.add(0);
			list.add(0);
			result.add(list);
		}

		for (Integer neg : negList) {
			for (Integer pos : posList) {
				int dif = 0 - pos - neg;

				if (numTimesMap.containsKey(dif)) {
					if ((dif == pos || dif == neg) && numTimesMap.get(dif) >= 2) {
						List<Integer> list = new ArrayList<Integer>();
						list.add(neg);
						list.add(pos);
						list.add(dif);
						result.add(list);
					} else {
						if (dif < neg || dif > pos) {
							List<Integer> list = new ArrayList<Integer>();
							list.add(neg);
							list.add(pos);
							list.add(dif);
							result.add(list);
						}
					}
				}

			}
		}

		return result;
	}

	public static void main(String[] args) {

		// nums = [-1, 0, 1, 2, -1, -4]，
		int[] nums = new int[] { -1, 0, 1, 2, -1, -4 };
		Solution0015 so = new Solution0015();
		so.threeSum(nums);

	}

}
