/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.data_structures;

/**
 *
 * @author vshanmugham
 */



/*
hi




n 

urls

5

a,s,d,a,a,w,d


5
a,b,c,d,e,f,b

[b,c,e,f,d]
f a
r a

b null
c b
e c
f e
d f




public void url_cache(String arr[],int n){
    Queue<ListNode> q = new CustomLinkedList();
    Map<ListNode,ListNode> m = new HashMap();
    for(int i=0;i<arr.length;i++){
        ListNode nod = new ListNode(arr[i]);
        if(map.containKey(nod)){
            ListNode prev = map.get(nod);
            if(prev==null){
                q.poll();     
            }else if(q.peekLast()==nod){
                map.put(nod,prev);
            }
            else{
                map.put(prev.next.next,prev);
                
            }
        }else if(q.size>=n){
            nod = q.poll();
            map.delete(nod);
        }
        ListNode val = q.size()!=0? q.peekLast() : null ;
        map.put(nod,val);
        q.add(nod);
    }
}
  
O(n)

Queue + Map
[a,s,d,s]  O(n**2)

*/

public class LruCacheImpl {
  
}
