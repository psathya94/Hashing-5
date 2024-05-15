package week15.day4;

import java.util.HashMap;

//TC - O(n*l)
//SC - O(1)
//https://leetcode.com/problems/verifying-an-alien-dictionary/
class Solution1 {
	public boolean isAlienSorted(String[] words, String order) {
		HashMap<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < order.length(); i++) {
			char ch = order.charAt(i);
			map.put(ch, i);
		}
		for (int i = 0; i < words.length - 1; i++) {
			String first = words[i];
			String second = words[i + 1];

			if (!inOrder(first, second, map)) {
				return false;
			}
		}
		return true;
	}

	public boolean inOrder(String first, String second, HashMap<Character, Integer> map) {
		for (int i = 0; i < first.length() && i < second.length(); i++) {
			char a = first.charAt(i);
			char b = second.charAt(i);
			if (map.get(a) < map.get(b)) {
				return true;
			} else if (map.get(a) > map.get(b)) {
				return false;
			}
		}
		if (first.length() > second.length())
			return false;

		return true;
	}
}

public class isAlienSorted_Optimal {

	public static void main(String[] args) {
		Solution1 ob = new Solution1();
		String[] words = { "hello", "leetcode" };
		String order = "hlabcdefgijkmnopqrstuvwxyz";
		ob.isAlienSorted(words, order);

	}

}
