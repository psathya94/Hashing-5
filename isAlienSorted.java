package week15.day4;

import java.util.HashMap;

class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        if (words.length == 0 || order == "")
            return false;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            char ch = order.charAt(i);
            map.put(ch, i);
        }
        for (int i = 0; i < words.length - 1; i++) {
            if(words[i].equals(words[i+1]))
                continue;
            char a = words[i].charAt(0);
            char b = words[i + 1].charAt(0);
            if (map.get(a) > map.get(b)) {
                return false;
            }
            else if(map.get(a) == map.get(b)){
                int j= 1;
                String word1 = words[i];
                String word2 = words[i+1];
                while(j<word1.length() && j<word2.length() && word1.charAt(j)==word2.charAt(j)){
                    j++;
                }
                if(j==word1.length())
                    continue;
                if(j==word2.length() || map.get(word1.charAt(j))> map.get(word2.charAt(j))){
                    return false;
                }
            }
        }
        return true;
    }
}
public class isAlienSorted {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
