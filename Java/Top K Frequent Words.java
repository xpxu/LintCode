M

方法1：Brutle force用HashMap存frequency, 用ArrayList存lists of words。最后返回从尾部向前数的k个。   
   注意排序时Collection.sort()的cost是O(nLogk)


方法2: Trie && MinHeap屌炸天   
   http://www.geeksforgeeks.org/find-the-k-most-frequent-words-from-a-file/

```
/*
Given a list of words and an integer k, return the top k frequent words in the list.

Example
Given

[
    "yes", "lint", "code",
    "yes", "code", "baby",
    "you", "baby", "chrome",
    "safari", "lint", "code",
    "body", "lint", "code"
]
for k = 3, return ["code", "lint", "baby"].

for k = 4, return ["code", "lint", "baby", "yes"],

Note
You should order the words by the frequency of them in the return list, the most frequent one comes first. If two words has the same frequency, the one with lower alphabetical order come first.

Challenge
Do it in O(nlogk) time and O(n) extra space.

Extra points if you can do it in O(n) time with O(k) extra space.

Tags Expand 
Hash Table Heap Priority Queue
*/

/*
	Attempt1, Thoughts:
	Brutle force
	HashMap<word, frequency>, size n. O(n)
	ArrayList<ArrayList<String>> lists: each entry has a list of strings with index as frequency. O(n)
	Adding to result String[], do a Collections.sort(), which cause O(nlogk) with a O(n) for loop on top; It becomes O(nLogk)on average.
	Return lists' top k.
*/
public class Solution {
    public String[] topKFrequentWords(String[] words, int k) {
        String[] rst = new String[k];
    	if (words == null || words.length == 0 || k <= 0) {
    		return rst;
    	}
    
    	HashMap<String, Integer> map = new HashMap<String, Integer>();
    	ArrayList<ArrayList<String>> lists = new ArrayList<ArrayList<String>>();
    	for (int i = 0; i <= words.length; i++) {
    		lists.add(new ArrayList<String>());
    	}
    	//Fill map
    	for (int i = 0; i < words.length; i++) {
    		if (!map.containsKey(words[i])) {
    			map.put(words[i], 0);
    		}
    		map.put(words[i], map.get(words[i]) + 1);
    	}
    	//Fill ArrayList
    	for (Map.Entry<String, Integer> entry : map.entrySet()) {
    		int freqIndex = entry.getValue();
    		lists.get(freqIndex).add(entry.getKey());
    	}

    	int count = 0;
    	for (int i = lists.size() - 1; i >= 0; i--) {
    		ArrayList<String> list = lists.get(i);
    		Collections.sort(list);
    		for (int j = 0; j < list.size(); j++) {
    			if (count < k) {
	    			rst[count] = list.get(j);
	    			count++;
    			} 
    			if (count >= k) {
    				return rst;
    			}
    		}
    	}

    	return rst;
    }
}
```