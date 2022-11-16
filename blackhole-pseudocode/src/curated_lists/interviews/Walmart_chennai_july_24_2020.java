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
public class Walmart_chennai_july_24_2020 {
  
}


/*





src to dest multiplier
"Meter", "inch", 2
"Inch", "cm", 2
"cm", "Foot", 2

find src to destination multiplier for any 2 nodes


class Node{
    int multiplier;
    String unit;
    Node(String unit, int multiplier){
        this.unit = unit;
        this.multiplier = multiplier;
    }
}

a:  b, 1
b:  c, 2
c:  d, 3


public static int fetchMultiplier(Map<String, Node> unitMap,String src, String dest){
    return dfs(unitMap, src, dest,1);
}

//src: a, dest: e
public static int dfs(Map<String, Node> unitMap, String src, String dest, int multiplier){
    
    if(!unitMap.containskey(src)) {
        return -1;
    }
    if(src==dest){
        return multiplier;
    }
    for(Node n: unitMap.get(src)){
        int mul = dfs(unitMap, n.unit, multiplier* n.multiplier)
        if(mul!=-1){
            return mul;
        }
    }
    return -1;
}


2.
List of strings



["app","apple","ball","cat"]

n length of list
s max length of a word

O(n*s)

        prefix map{}
         /  | \  
        a   b  c
        |   |  |
        p   a  a 
        |   |  |
        p   l  t
        |   |
        l   l
        |
        e

"a"

class TrieNode{
    char c;
    boolean isWord;
    Map<character, TrieNode> charMap;
    
    TrieNode(char c){
        thsi.c = c;
        isWord = false;
        charMap = new HashMap();
    }
    
    public void endLetter(){
        thsi.isWord = true;
    }
}


Map<character, TrieNode> prefixMap = new HashMap();

// app
Public static void insertWord(String word, Map<character, TrieNode> prefixMap){
    Map<character, TrieNode> temp = prefixMap;
    for(int i=0;i<word.length();i++){
        int c = word.charAt(i);
        if(!temp.containsKey(c)){
            temp.put(c, new TrieNode(c));
        }
        TrieNode node = temp.get(c);
        temp = node.charMap;
        if(i==word.length()-1){
            node.endLetter();
        }
    }
}


        prefix map{}
         /  | \  
        a   b  c
        |   |  |
        p   a  a 
        |   |  |
        p   l  t
        |   |
        l   l
        |
        e


// appp
public static int getWordCount(String prefix, Map<character, TrieNode> prefixMap){
    
    Map<character, TrieNode> temp = prefixMap;
    int c = 0;
    for(int i=0;i<prefix.length();i++){
        int c = prefix.charAt(i);
        if(!temp.containsKey(c)){
            return 0;
        }
        TrieNode node = temp.get(c);
        if(node.isWord == true){
            c++;
        }
        temp = node.charMap;
    }
    return c;
}




round 2

implement API



input = [1,2,3,4,5,6,7,8,9,10]

[5,6,7,8,9,10]

processing = []
completed = [1,2]

3,2,1

n = 3
c

t1
t2
t3


ExecutorService executor = Executors.newFixedThreadPool(n);

q.addAll(input)
while(!q.empty()){
  if(processing.length<n){
    int value = q.poll();
    Runnable worker = new WorkerThread(value); 
    executor.execute(worker);
    processing.add(value);
  }

}


class WorkerThread implements Runnable {  
    private int message;  
    public WorkerThread(int s){  
        this.message=s;  
    }  
     public void run() {  
        int temp = message;
        while(temp>0){
          sleep(1);
          System.out.println(System.currentTimeMillis());
          temp--;
        }
        processing.remove(message);
    }  

}  




Employee table  
int  String    String    timestamp id  emp_name  emp_sal  update_ts  

1   sourav     1111     24-04-20:10:10:30        10
1   sourav     1111     24-04-20:10:10:30        10
2   tom       1234      22-04-20:10:10:30        30
3   harry     2222      21-04-20:10:10:30        30



select concat(employeee.name, ',') from  employee  where employee.emp_sal = (select max(emp_sal) from employee;

select concat(employeee.name, ',') from  employee  where employee.emp_sal = (
select emp_sal from employee limit 2 offset 1 order by empl_sal desc group by emp_sal;
) from employee)









*/
