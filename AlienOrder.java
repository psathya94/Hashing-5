package week15.day4;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
//TC - O(n*l)
//SC - O(V+E)
//https://leetcode.com/problems/alien-dictionary/description/
class Solution2 {
	public String findAlienOrder(String[] words) {
		int[] indegree = new int[26];
		// HashSet - to avoid duplicate dependency
		HashMap<Character, HashSet<Character>> map = new HashMap<>();  //constant - max 26 alphabets
		buildgraph(words, indegree, map); //O(n*l)
		int count = 0;
		StringBuilder sb = new StringBuilder(); //constant - max 26 alphabets
		Queue<Character> q = new LinkedList<>(); //constant - max 26 alphabets will be added
		//go over keyset instead of indegree array, as it contains all 26 alphabets
		for(Character c : map.keySet()) {
			if(indegree[c-'a']==0) {
				q.add(c);
			}
		}
		while(!q.isEmpty()) { //O(V + E)  -- max 26*26
			Character curr = q.poll();
			sb.append(curr);
			count++;
			//reduce indegree of curr
			for(char baby :map.get(curr)) {
				indegree[baby-'a']--;
				if(indegree[baby-'a']==0) {
					q.add(baby);
				}
			}
		}
		if(count==map.size())
			return sb.toString();
		return "";
	}

	public void buildgraph(String[] words, int[] indegree, HashMap<Character, HashSet<Character>> map) {
		//iterate over the given list of words and add all unique characters into map
		//wrt, wrtz -- add w,r,t,z into map
		for(int i=0;i<words.length;i++) {
			for(char c:words[i].toCharArray()) {
				map.put(c, new HashSet<>());
			}
			if(map.size()==26)
				break;
		}
		for(int i=0;i<words.length-1;i++) {
			String word1 = words[i];
			String word2 = words[i+1];
			//apple, app -- clear map, as we don't need to add unique characters in mpa
			//if a,p,p,l,e are added to map and indegrees will be 0. We don't need to process them, so return ""
			if(word1.startsWith(word2) && word1.length()>word2.length()) {
				map.clear();
				return;
			}
			for(int j=0;j<word1.length() && j<word2.length();j++) {
				char ch1 = word1.charAt(j);
				char ch2 = word2.charAt(j);
				if(ch1!=ch2) {
					HashSet set = map.get(ch1);
					//dont increase indegree when w-->e dependency is already added
					//that's the reason we use hashset instead of list. If it was list, we need to check if e is already present linearly.
					if(!set.contains(ch2)) {	
						set.add(ch2);
						map.put(ch1, set);
						//map.get(ch1).add(ch2); //alternate
						indegree[ch2-'a']++;
					}
					break;  //very important
				}
			}
		}
	}
}

public class AlienOrder {

	public static void main(String[] args) {
		Solution2 ob = new Solution2();
		//String[] words = { "wrt", "wrf", "er", "ett", "rftt" };
		String[] words = { "apple", "app" };
		String order = ob.findAlienOrder(words);
		System.out.println("order is"+order);

	}

}
