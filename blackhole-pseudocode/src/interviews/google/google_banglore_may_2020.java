/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curated_lists.interviews;

/**
 *
 * @author vshanmugham
 */
public class google_banglore_may_2020 {
  
}




/*

Please use this Google doc during your interview (your interviewer will see what you write here). To free your hands for typing, we recommend using a headset or speakerphone.

hi



FTE score: 
Here is an example of direct report map:
{“123”: [“234”, “345”], -> 5
  “234”: [“456”, “789”], -> 3
  “345”:[], -> 1
  “456”:[], -> 1
  “789”:[]} -> 1

1. What is the score for each eid in the given example?

2. In what cases do we consider the map as invalid?
cycle
one eid can only report to another one eid
only one root node in this map

3. Write a function, input is the given eid, calculate FTE score for the eid. Direct report map is a global variable that you can access anywhere in your code.

public int fteScore(int eid, Map<integer, List<Integer>> reportMap){
	List<Integer> reports = reportMap.get(eid);
int s = 1;
if(reports==null) return 0;
for(int x: reports){
	s+=fteScore(x, reportMap);
}
return s;
}












4. Assume the function in the last question will be called again and again with different input eid, how do we optimize the efficiency of lookup? Assume the map does not change, and you are able to preprocess the given map before anyone call the lookup function.

class FteScore{
private Map<Integer, List<Integer>> reportMap;
private Map<Integer, Integer>  fteMap;

public int fteScore(int eid){
	return fteScore(eid, reportMap, fteMap, new HashSet(), new HashSet());
}

public int fteScore(int eid, Map<integer, List<Integer>> reportMap,  Map<Integer, Integer> fteMap, Set<Integer> visited, Set<Integer> reportsVisited){
	List<Integer> reports = reportMap.get(eid);	
	Set<Integer> visited = new HashSet(), reportsVisited = new HashSet();
int s = 1;
if(reports==null) return 0;
for(int x: reports){
if(reportsVisited.contains(x)) throw new Exception("one eid can only report to another one eid");
	reportsVisited.add(x);
if(visited.contains(eid)) throw new Exception("cycle");
	s+=fteScore(x, reportMap, fteMap, visited, reportsVisited);
}
return s;
}


public int porcessFteScore(){
	Set<Integer> Visited = new HashSet();
	Set<Integer> reportsVisited = new HashSet();
	Stack<Integer> s = new Stack();
	for(int x: reportMap.keyset()){
fteScore(eid, reportMap, fteMap, Visited, reportsVisited);
	}
}

public int fteScore(int eid, Map<Integer, List<Integer>> reportMap, Map<Integer, Integer> fteMap, Set<Integer> visited, Set<Integer> reportsVisited){
	visited.add(eid);
	if(fteMap.containsKey(eid)) return fteMap.get(eid);
	List<Integer> reports = reportMap.get(eid);
int s = 1;
if(reports==null) return 0;
for(int x: reports){
	if(reportsVisited.contains(x)) throw new Exception("one eid can only report to another one eid");
	reportsVisited.add(x);
if(visited.contains(eid)) throw new Exception("cycle");
	s+=fteScore(x, reportMap);
}
fteMap.put(eid,s);
return s;
}
}


*/



/*

Please use this Google doc during your interview (your interviewer will see what you write here). To free your hands for typing, we recommend using a headset or speakerphone.

Given a real-time source of data, ignore all duplicates which were received within 10 seconds.

Example:
foo @ 0
bar @ 2
baz @ 5
foo @ 8
bar @ 13
foo @ 100

Prints: foo bar baz bar foo

print_occ(string s, int x)



hi
class PrintOccurances{
Map<String, Integer> map = new HashMap();
Queue<String> heap = new PriorityQueue<>((a,b)-> map.get(a)-map.get(b)); 
public void prin_occ(String s, int time){
    while(!heap.isEmpty() && heap.peek()<time-10){
        String x = heap.poll();
        map.remove(x);
} 


if(map.containsKey(s)){
    if(time-map.get(s)>10)
        System.out.println(s);
}
else
    System.out.prinln(s);
map.put(s,time);
heap.add(s);
}

}
map
// foo: 100, bar: 13, baz 5
heap
// foo,bar,baz,foo,bar
output
// foo bar baz bar foo

follow ups
1: how would you optimize space
use heap +map and remove unnecessary entires
2. what are the bottle necks and how do you avoid it?
when to many same time stamps come and sudden difference in time stamp occurs
there will be a lot to remove causing a bottleneck
solution: removing is optimization and have a max cap say 10 removal per requests,
so that removal latency is distributed across.


*/


// Ambigram

/*

// mistakes:
  1. 2 variables in one line
  2. bad naming
  3. assigning or modifying char array, dont use char[]
  4. written more code than required, last if should be inside contains
  5. Not using helper method is taken as negative
  6. not reading the question properly, and thus u missed that input is a list of words
  7. 
  8. always use objects if possible
  9. changes in code/approach should be notified.
  
  // positives:
  1. tested written code
  2. used proper data structures
  3. finished fast
  4. used string builder
*/

/*



Please use this Google doc during your interview (your interviewer will see what you write here). To free your hands for typing, we recommend using a headset or speakerphone.


hi



ambigram

Q1. Write a function that outputs all the words in the list that look the same when turned upside down.
eg. axe, dip, dollop, mow

// a: e, e:a d:p, o:o, m:w,  
// k has no mapping
// axe => exa
// 

// axxe : exxa
// kite : 
// ""
// ake
//dollop

public boolean anbigram(String s, Map<Character, Character> map){
	char arr = s.toCharArray();int n = arr.length;
	for(int i=0;i<=arr.length/2;i++){
		if(map.containsKey())
			arr[i] = map.get(arr[i]);
    else return false;
    if(arr[i]!=arr[n-1-i]) return false;
  }
  return true;
}

test cases:
anbigram("", map)==true  // empty string return true
//valid cases
ambigram("axe", map)==true 
ambigram("dip", map)==true
ambigram("dollop", map)==true
ambigram("mow", map)==true)
ambigram("exxa", map)==true
//invalud cases
anbigram("kite", map)==false  // has no mapping in map, k
anbigram("eeee", map)==false // same character repeating itself fails


Q2. Write a function that outputs all the words that rotate to give a DIFFERENT word that is also in the list.
eg. am (we), did (pip), held (play), flays (shelf), loom (wool), ...
public List<String> rotate(List<String> words, Map<Character,Character>){
	Set<String> set = new HashSet(words);
	List<String> ans = new Arrayist();
	for(String word: words){
		char arr = s.toCharArray();int n = arr.length;
		String trans = New StringBuilder;
		for(int i=0;i<arr.length;i++){
		if(map.containsKey()) trans.append(0,map.get(arr[i]));
else return continue;
	}
	if(set.contains(trans.toString())) ans.add(trans.toString());
}
return ans
}


test cases:
//empty array
rotate([], map) =>["am", "we", "did", "pip"]

//valid test cases
rotate(["am", "we", "did", "pip"], map) =>["am", "we", "did", "pip"]
rotate(["am", "we", "did", "pip"], map) =>["am", "we", "did", "pip"]
rotate(["am"], map) =>[] // return empty because ambigram not present in words
rotate(["kite"], map) =>[] // return empty because no mapping






*/


// RandomizedKSongs
/*
boosan babu's questions

1. 
add two numbers in arrayList,
Multiply 2 numbers in arrayList

digits are provided in the list

solution:


2.
You are developing a music player, with shuffle feature. Which randomise the songs played from a playlist.
There’s a cool down number, c, meaning you should not repeat last  c recently played songs. The songs should be played completely randomised .

solution:
cool down count - k
random index - i
use queue and an arrayList

initially while playing songs until k, swap i to last and remove
so that time complexity of arraylist remove becomes O(1)
to swap list.set(index,value);
after k is reached

push val at i to queue and poll first value in queue and replace in i; 


*/



/*

Please use this Google doc during your interview (your interviewer will see what you write here). To free your hands for typing, we recommend using a headset or speakerphone.

this is rotten oranges problem
find the time to rot first orange


Given a 2D grid of characters, find the shortest Manhattan (L1) distance between an X and a Y. 
 
Example Input: 
XOOO 
XOOO 
OOYY 
 
Output: 3 (The X in the 2nd row and the Y in the 3rd column form the closest XY pair) 

O((mn)*2**(m+n))

x - 1,0
y - 2,1

2-0 + 2-1


O((m*n)*(m*n))


O((m*n)* (m*n) )


XOOOOOOOOOO
OOOOOOOOOOY


X123
123
X3

 
YOOOO
OOOOO
OOXOO
OOXOO
OOYOO
 
OO2OO
O212O
21O12
O212O
OO2OO
 
 
public static void manahttanDistance(char a[][]){
	int m = a.length, n = m==0 ? 0 : a[0].length;
Queue<Integer[]> q = new LinkedList();
for(int i=0;i<m;i++){
	for(int j=0;j<n;j++){
	if(a[i][j]=='x'){
		q.add(new Integer[]{i,j});
}
}
}
int minDist = Integer.MAX_VALUE;
int dir[][] = new int[][]{{0,1},{0,-1},{1,0},{-1,0}}; 
while(!q.isEmpty()){
		int pos[] = q.poll();
	a[pos[0]][pos[1]] = 'O';
	for(int k = 0;k<dir.length;k++){
		
		int r = pos[0]+dir[k][0];
		int c = pos[1]+dir[k][1];
		if(r>=0 && r< m && c>=0 &c<n){
			q.add(new Integer[]{r,c});
			if(a[r][c]=='Y'){
					int d = computeDistance(pos, new Integer[]{r,c});
				minDist = Math.min(d,minDist);
}
		}
}
}
}
 
public static void bfs(){
	
}
 
public static int computeDIstance(int src[], int dest[]){
 
}



*/



/*

Please use this Google doc during your interview (your interviewer will see what you write here). To free your hands for typing, we recommend using a headset or speakerphone.


Can you see me typing?

Given an arbitrary string like "helloooworld", find the longest substring whose alphabet consists of no more than 2 characters.

A string's alphabet is the set of distinct characters it consists of.  For example,

  "he" → {'h', 'e'} (alphabet of size 2)
  "hel" → {'h', 'e', 'l'} (alphabet of size 3)
  "llooo" → {'l', 'o'} (alphabet of size 2)

For "helloooworld", either of the following two answers is acceptable: "llooo" or "ooowo".


//hellooowworld

 
map:
h - 0
e - 0
l - 0
oooo - 4
w - 1


maxs = 1
maxe = 4
start - 9
end - 9

k = 3

1 < k 
hellooowworld

public static String substringTwoChar(String s, int k){
	Map<Character, Integer> charMap = new HashMap();
int maxStart= 0, maxEnd = 0;
	int start = 0, end = 0;
	while(end<s.length()){
		if(!charMap.containsKey(s.charAt(end)) && charMap.size()>=k){
			char startChar = s.charAt(start);
if(charMap.get(startChar)==0){
charMap.remove(startChar);
}else{
	charMap.put(startChar, charMap.get(startChar)-1);
}
			start+=charMap.get(s.charAt(start));
		}
charMap.put(s.charAt(end), charMap.getOrDefault(s.charAt(end),0)+1);
		end++;
if(end-start>maxStart-maxEnd){
	maxStart = start;
	maxEnd = end;
}
}
return s.substring(maxStart, maxEnd);
}

hehelloooworld

Time Comeplxity: O(n)
Space complexity: O(n)


Test cases:
null
""
"a"
"aaaaaaa"
"aaaabbbb"
"helloooworld"

k:
1
k<s.length
k>s.length





1. raltime source of data ignore duplicates within 10 sec
feedback
foo5
foo5
bar13


grasps, and worked solution
nice testing, figured logic bug
quick solution with expanded discussion

2. function outputs looks the same when turned upside down

axe
dip
gallop
mow

rotate to different word on list
we,am, did,pip, held,play, flays(sheft), loom(bool)

worked quickly nearly working code
sloppy code
readablity worse
multiples statement on one line
bad variables
no helpers
algo was good

understood problem
could have ddiscussed better
tested and missed mistakes
insufficient test case


map, set, list


3. 2d grid of char, find shortesest manhattan l1 distance

x o o o
x o o o
o o y y

visiting grid more than once and noit propogating 

did bruteforce and didnot understand bfs
i didnt ask p[roper questions
i didnt clear;ly explain thoughts
he provided way more hints
coding a fast but non trivial errors



4. helloooworld find the longest substring whose alphabet consists of no more than 2 characters

whats time and spacxre complexity

lets make 2 as k>1

idomatic java, but intorduced read after delete problem
came close to impl optimal linear solution
easy to implement quadratic solution
stumbledn on space complexity

took a ittle long to smoke out error s it contained
good list of etstcase
didnt include null check
rushed into coding before understanding problem
failed to adress central problem

iteratively
sliding window
discovered and fixed problems
char freq
nullity
repeated chartAt move into variable









*/