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
public class microsoft_sep_2020 {
  
}

/*

Given a set of words and a source and target string,&nbsp;
trace out a path in set of words that converts source to target but every step of path/conversion should be of max 1 character replacement
&nbsp;
Input: DAMP, LIKE
&nbsp;
Set of Words: DAMP, CAME, LAMP, LIMP,, TAME, LIME, LIKE&nbsp;
&nbsp;
Output:DAMP, LAMP, LIMP, LIME, LIKE

package com.codility;

// you can also use imports, for example:
// import java.util.*;

public class Solution {

    public static void main(String [] args) {
        // you can write to stdout for debugging purposes, e.g.
        System.out.println("This is a debug message");
        int input[][] = new int[][]{{1,2,3},{4,5,6},{7,8,9},{10,11,12}};
        printSpiral(input);
    }
    
// m = 4
// n = 4
// x 1, y 1
// i 1
// j 1

// 1   2  3  4  5
// 6   7  8  9 10
// 11 12 13 14 15
// 16 17 18 19 20


// 21 22 23 24 25
    
    public static void printSpiral(int arr[][]){
        int m = arr.length, n = arr.length==0 ? 0 : arr[0].length;
        int c = 0;
        c = m*n;
        int x=0,y=0;
        int i=0, j=0;
        System.out.println(m +"::"+n);
        // for(int k=0;k<5;k++){
        //     System.out.println(arr[0][k]);
        // }
        while(c>0){
            for(;j<n-y;j++){
                System.out.print(arr[i][j] +" ");
                c--;
            }
            i++;
            j--;
            for(;i<m-x;i++){
               System.out.print(arr[i][j] +" ");
               c--;
            }
            j--;
            i--;
            for(;j>=y;j--){
                System.out.print(arr[i][j] +" ");
                c--;
            }
            i--;
            x++;
            j++;
            for(;i>=x;i--){
                System.out.print(arr[i][j] +" ");
                c--;
            }
            j++;
            y++;
            i++;
        }
        
    }
}

/*Test cases:

null
[]
[[1]]
[[1,2,3]]
[[1],[2],[3]]
[[1,2,3,4,5],[1,2,3,4,5],[1,2,3,4,5],[1,2,3,4,5],[1,2,3,4,5]]
[[1,2,3,4,5],[1,2,3,4,5],[1,2,3,4,5],[1,2,3,4,5]]
[[1,2,3,4],[1,2,3,4],[1,2,3,4],[1,2,3,4],[1,2,3,4]]
*/


// x,y

// i x, j y to n,
// i x to m, j n 
// i m, j n to y
// i m to x, j y




// n
// k O(1)
// O(n) * O(26)

//O(nk)


/*
nulls
emptys
different length
input and out being same
input that has a valid path
input that has not path
output string is not present in set


*/



/*

package com.codility;

// you can also use imports, for example:
// import java.util.*;

public class Solution {

    public static void main(String [] args) {
        // you can write to stdout for debugging purposes, e.g.
        System.out.println("This is a debug message");
        int arr[] = sortZerosOnesTwos(new int[]{2});
        for(int k=0;k<arr.length;k++){
            System.out.print(arr[k]+" ");
        }
    }
    
    public static int[] sortZerosOnesTwos(int arr[]){
        int i=0,j=0;
        for(int k=0;k<arr.length;k++){
            if(arr[k]==1){
                if(arr[k]<arr[j]){
                    int temp = arr[k];
                    arr[k] = arr[j];
                    arr[j] = temp;
                }
                j++;
            }else if(arr[k]==0){
                if(arr[k]<arr[i]){
                    int temp = arr[k];
                    arr[k] = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
                i++;
                j++;
            }
        }
        return arr;
    }
    
    // public static int[][] findWord(char arr[][], String word){
    //     int dir[][] = new int[][]{{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}}; 
    //     int ans = new int[word.length][2];
    //     int m = arr.length, n = arr.length==0 ? 0 : arr[0].length;
    //     int k = word.length();
        
    //     for(int i=0;i<m;i++){
    //         for(int j=0;j<n;i++){
    //             int s = 0;
    //             boolean dirFound = false;
    //             ans = new int[word.length][2];
    //             if(arr[i][j]==word.charAt(s)){
    //                 //
    //                 ans[s] = new int[]{i,j};
    //                 s++;
    //                 int x=0;
    //                 if(!dirFound){
    //                     for(;x<dir.length;x++){
    //                         int r = i+dir[x][0], c = j+dir[x][1];
    //                         if(r>=0 && r<m && c>=0 && c<n && arr[r][c]==word.charAt(s)){
    //                             ans[s] = new int[]{r,c};
    //                             s++;
    //                         }
    //                     }
    //                 }else{
    //                     while(s<k){
    //                         int r = i+dir[x][0], c = j+dir[x][1];
    //                         if(r>=0 && r<m && c>=0 && c<n && arr[r][c]==word.charAt(s)){
    //                             ans[s] = new int[]{r,c};
    //                             s++;
    //                         }else{
    //                             break;
    //                         }
    //                         if()
    //                     }
    //                 }
                    
    //             }else if(arr[i][j]==word.charAt(k-1)){
    //                 //
    //             }   
    //         }
    //     }
    // }
    
    //public static int[][] 
    
    
}

//aaaab


round 2


/*
aaaaa
aaaaa
aaaaa
aaaaa
aaaaa
  0
a e s o r
e a a k a
a s r a a
a a o o a
a a s r s
a a e a a

r:2 c:2



[2,2,1,2,1,1,0,0,2,1]
[1,2,2,2,1,1,0,0,2,1] 
[1,1,2,2,2,1,0,0,2,1]
[1,1,1,2,2,2,0,0,2,1]
 i            
       j 
             k
             t


[0,1,1,1,2,2,2,0,2,1]
[0,0,1,1,1,2,2,2,2,1]
[0,0,1,1,1,1,2,2,2,2]
 i 
     j 
             k

0,0,1,1,1,2,0
    i
          j
            k
 


[2,2,1,2,1,1,0,0,2,1]
 i
 j
 k






//[0,0,1,1,1,1,2,2,2,2]










*/



/*


/*

service, endpoints



direct webhooks

kafka queue
- webhooks
- consumer(poll)

search service

log shipper - levels and other configurations


API interface:
accounts - subdomain -> abc.logservice.com
API token 

/token

roles

abc.logservice.com/push_log
abc.logservice.com/bulk_push_logs

periodic batching or threshold, fush the logs to service

retries with exponential backoff


      Load balancers

[kafka queue service]
 

 
[app servers, ...] poll the kafka queues

search service

relational database

redis


log shipper:

app config:

pushLogs()

publish({"options": {"mode": "single/bulk", "period": "5","units": "sec", //retry, thresholdCount, }}){
    
    
}



pushBulkLogs(){
    
}





package com.codility;

// you can also use imports, for example:
// import java.util.*;

public class Solution {


    public static void main(String [] args) {
        // you can write to stdout for debugging purposes, e.g.
        System.out.println("This is a debug message");
    }
}

class QueueNode{
    int val;
    QueueNode next;
    
    QueueNode(int val){
        this.val = val;
    }
}

// 1 2 3 4
// 1
// 4 5 6 7

class BQueue{
    int maxSize, size;
    QueueNode front, rear;
    
    BQueue(int maxSize){
        this.maxSize = maxSize;
        this.size = 0;
    }
    
    public void enqueue(int val){
        if(size==maxSize){
            //"wait until dequeued"
            enqueue(val);
        }else{
            QueueNode q = new QueueNode(val);
            if(front==null){
                front=rear=q;
            }else{
                rear.next = q;
                rear = q;
            }
            size++;
        }
    }
    
    public int dequeue(int val){
        if(size==0){
            "using a sleep()"
            
        }else{
            QueueNode q = front;
            if(front == rear){
                front = rear = null;
            }else
                front = front.next;
            } 
            return q.val;
        } 
    }
}




*/