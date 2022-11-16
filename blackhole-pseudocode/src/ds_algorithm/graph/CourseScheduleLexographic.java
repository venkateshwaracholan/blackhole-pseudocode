/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.graph;

/**
 *
 * @author vshanmugham
 */

import java.util.*;

/*

Academic Schedule
Programming challenge description:
Consider an academic program that consists of NN courses. Every course is designated by an integer index from 00 to N-1N−1.

Some courses have others as prerequisites. For example, if course 2 is a prerequisite of course 3 you must finish course 2 before enrolling in course 3.

You can attend only a single course at a time. Build a schedule to complete all courses in a linear sequence and to satisfy all prerequisites for every course.

If more than one variant of possible schedule exists, use the schedule where courses with smaller indices are finished first.

Input:
The first line contains a positive integer representing the number of courses in the academic program.

Each additional line describes the prerequisites of a given course as a space-delimited set of indices. Every set of prerequisites has at least two indices.

The first index in the set denotes the course for which a prerequisite exists. All other indices designate which courses are required for the first.

For example:

4
1 0
2 0
3 1 2
The first row means that there are 4 courses in the academic program. The second and third rows define that course 0 must be taken before courses 1 and 2. And the fourth row defines that courses 1 and 2 must be taken before course 3.

Output:
A schedule containing a space-delimited list of courses, in the order of their attendance. For example:

0 1 2 3
For this example, another schedule exists 0 2 1 3. But we must select the variant 0 1 2 3 where course 1 (with a smaller index) is attended before the course 2 (with a greater index).

Test 1
Test Input
Download Test 1 Input
4
1 0
2 0
3 1 2
Expected Output
Download Test 1 Input
0 1 2 3
Test 2
Test Input
Download Test 2 Input
7
0 1 2
1 3
2 3
3 4 5
4 6
5 6
Expected Output
Download Test 2 Input
6 4 5 3 1 2 0
Test 3
Test Input
Download Test 3 Input
8
4 0 2
0 1 6
2 3 7
1 5
6 5
3 5
7 5
Expected Output
Download Test 3 Input
5 1 3 6 0 7 2 4
Test 4
Test Input
Download Test 4 Input
4
2 1 3
Expected Output
Download Test 4 Input
0 1 3 2

*/


public class CourseScheduleLexographic {
  public static void main(String[] args){
    
    courseScheduleLexo(9, new int[][]{{4, 0, 2},{0, 1, 6},{2, 3, 7},{1, 5},{6, 5},{3, 5},{7, 5},{1, 8},{6, 8},{3, 8},{7, 8}});
    courseScheduleLexo(4, new int[][]{{1, 0},{2, 0},{3,1,2}});
    courseScheduleLexo(7, new int[][]{{0, 1, 2},{1, 3},{2, 3},{3, 4, 5},{4, 6},{5, 6}});
    courseScheduleLexo(8, new int[][]{{4, 0, 2},{0, 1, 6},{2, 3, 7},{1, 5},{6, 5},{3, 5},{7, 5}});
    courseScheduleLexo(4, new int[][]{{2, 1, 3}});

    System.out.println();
    
    courseScheduleLexoBFSKahn(9, new int[][]{{4, 0, 2},{0, 1, 6},{2, 3, 7},{1, 5},{6, 5},{3, 5},{7, 5},{1, 8},{6, 8},{3, 8},{7, 8}});
    courseScheduleLexoBFSKahn(4, new int[][]{{1, 0},{2, 0},{3,1,2}});
    courseScheduleLexoBFSKahn(7, new int[][]{{0, 1, 2},{1, 3},{2, 3},{3, 4, 5},{4, 6},{5, 6}});
    courseScheduleLexoBFSKahn(8, new int[][]{{4, 0, 2},{0, 1, 6},{2, 3, 7},{1, 5},{6, 5},{3, 5},{7, 5}});
    courseScheduleLexoBFSKahn(4, new int[][]{{2, 1, 3}});
    
  }
  
  public static void courseScheduleLexo(int n, int[][] prerequisites){
    Map<Integer, Queue<Integer>> graph = new HashMap();
    for(int i=0;i<n;i++) graph.put(i,new PriorityQueue());
    int inDegrees[] = new int[n];
    for(int edge[]: prerequisites){
      for(int i=1;i<edge.length;i++){
        graph.get(edge[i]).add(edge[0]);
        inDegrees[edge[0]]++;
      }
    }
    Set<Integer> global = new HashSet();
    for(int i=0;i<n;i++){
      if(!global.contains(i) && inDegrees[i]==0)
        dfs(i, inDegrees, global, graph);
    }
    System.out.println();
  }
  
  public static void dfs(int n, int inDegrees[], Set<Integer> global, Map<Integer, Queue<Integer>> graph){
    System.out.print(n+" ");
    global.add(n);
    Queue<Integer> nei = graph.get(n);
    while(!nei.isEmpty()){
      int c = nei.poll();
      inDegrees[c]--;
      if(!global.contains(c) && inDegrees[c]==0) dfs(c, inDegrees, global, graph);
    }
  }
  
  
  
//  just by using bfs
//  core Idea: using priorityqqueue instead of a normal queue for kahn iteration
//  so that smaller index with resolved dependencies are prioritized
  public static void courseScheduleLexoBFSKahn(int n, int[][] prerequisites){
    Map<Integer, List<Integer>> graph = new HashMap();
    for(int i=0;i<n;i++) graph.put(i,new ArrayList());
    int inDegrees[] = new int[n];
    for(int edge[]: prerequisites){
      for(int i=1;i<edge.length;i++){
        graph.get(edge[i]).add(edge[0]);
        inDegrees[edge[0]]++;
      }
    }
    Queue<Integer> q = new PriorityQueue<>();
    for(int i=0;i<n;i++)
      if(inDegrees[i]==0) q.add(i);
    while(!q.isEmpty()){
      int x = q.poll();
      System.out.print(x+" ");
      for(int nei: graph.get(x))
        if(--inDegrees[nei]==0) q.add(nei);
    }
    System.out.println();
  }
  
  // use priority queue instead of queue in khan's algorithm
  
/*
    4
  
  0   2
  
1 6   3  7
    5     
  
*/
// expected  
// 5 1 3 6 0 7 2 4
//result
// 5 1 3 6 0 7 2 4 6 7
  
  
}
