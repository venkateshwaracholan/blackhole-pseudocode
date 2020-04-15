/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.queue;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author vshanmugham
 */
public class QueueUsingTwoStacks {
  public static void main(String args[]){
    ArrayList<String> list = new ArrayList();
  }
}


ArrayList<String> ans = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for(String s: possibleFeatures)
            set.add(s);
        Map<String, Integer> map = new HashMap<>();
        for(String featureReq : featureRequests){
            String words[] = featureReq.split("\\W");
            Set<String> added = new HashSet<>();
            for(String word : words){
                word = word.toLowerCase();
                if(set.contains(word)  && !added.contains(word)){
                    map.put(word, map.getOrDefault(word,0)+1);
                    added.add(word);
                }
            }
        }
        Queue<Map.Entry<String,Integer>> maxHeap = new PriorityQueue<>((a,b) -> a.getValue() == b.getValue() ? a.getKey().compareTo(b.getKey()) : b.getValue() - a.getValue() );
        for(Map.Entry<String,Integer> entry: map.entrySet())
            maxHeap.add(entry);
        while(!maxHeap.isEmpty() && topFeatures>0){
            ans.add(maxHeap.poll().getKey());
            topFeatures--;
        }
        return ans;


/*


Q1:

Time complexity - O(Nlog(N)+K)
Space complexity - O(N+K)

First i am creating a set of possibleFeatures so that contains operation will become O(1)
then a map to hold the frequency of the matching possibleFeatures in the feature request text.

two for loops to iterrate the list of feature requests and split each word in that feature requests text.
the added set will prevent counting if the keyword is present in same feature request text more than once.
for every feaure request text, if words match the keywords in possible features and not added for the text already, 
i increase the count in frequency map.

Finally i add the map enties in a max heap and i use priority queue to achieve it.
i add all the entires in priority by overriding the comparator using java lambda function.
if the frequency is same, it is ordered alphabetically.

and then i K times iterate and poll from the max heap to add them in the result.








Q2:

conviction:

Time complexity - O(n)
Space complexity - O(n)

Traversal Index - i
last index - j
variable to store starting index of the group - k

First find the last index of the chacater, and then
I can conclude it as a group if all the characters
existing between the range have their last index between the traversal and maximum of the last index, if not
I can overrite the last index to the last index of the character which is out of bound
and continue the same approach up unitil the traversal index and the last index match.

first I need to create a map to store last index of a character in a map,
I can simply achieve this by over writing index in the map as I traverse the list of characters.
And then I traverse the characters with the above mentioned algorithm and add the number of characters in the result list
if i and j matches.


*/



