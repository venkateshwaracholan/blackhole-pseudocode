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
public class paypal_sep_2020 {
  
}


/*

Hi!


// hi



class SingletonExample{

   private static SingletonExample obj;
    
    SingletonExample(){
        //return getInstance();
    }
    
    public static SingletonExample getInstance(){
        
        if(obj==null){
            synchronized(obj){
                if(obj==null){
                    obj = new SingletonExample();
                }
            }
        }
        return obj;
    }
    
    public static void main(String args[]){
        SingletonExample test = new SingletonExample();
    }
}


// t1
// t2



binary tree



     3
   1   4
   
   
        10
     5    
   3   6
  1   7 11

public static boolean checkBST(TreeNode root){
    if(root==null) return true;
    
    if(root.left!=null){
        if(root.val<root.left.val){
            return false;
        }
    }
    if(root.right!=null){
        if(root.right.val<root.val){
            return false;
        }
    }
    return checkBST(root.left) && checkBST(root.right);
}



public static boolean checkBST(TreeNode root){
    return checkBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
}

public static boolean checkBST(TreeNode root, int L, int R){
    if(root==null) return true;
    if(root.val < L || root.val > R) return false; 
    return checkBST(root.left, L, root.val) && checkBST(root.right, root.val, R);
}

-inf          10                inf
               

              10
       5               20
   3   .   9       12 .  21
1 . 11    6 . 11

       L    R
5 =>  -inf, 10
3 =>  -inf, 5
1 =>  -inf, 3
11 =>    3, 5



ArrayList
LinkedList


arraylist.add(0,"");

HashMap


10

%3

1 1
2 2
3 0
4 1
5 2 
6 0
7 1





customer
id name


orders
id cust_id 

select c.id, c.name, c.count() from customers c left join orders o on c.id = o.cust_id group by c.id, c.name;



*/


/*



ArrayList ---


class ArrayList implements List{
    Object arr[];
    int size, maxSize;
    final int loadfactor = 2;
    
    public void insert(Object val){
        if(size<maxSize){
            arr[size++] = val;
        }else{
            maxsize = maxSize*loadfactor;
            int newArr[] = new Object[maxsize];
            for(int )
            
        }
    }

}


[1,2,3]

[              ]



0123456789
6210001000

1 2 1 0

len=4

0: 1
1: 2
2: 1

3 -> 0
2 -> 1
1 -> 2
0 -> 1


public static boolean autoBio(int number){
    int temp = number;
    Map<Integer> freqMap = new HashMap();
    int len = 0;
    while(temp>0){
        len++;
        int r = temp%10;
        freqMap.put(freqMap.getOrDefault(r,0)+1);
        temp = temp/10;
    }
    temp = number;
    while(temp>0){
        int r = temp%10;
        int mapVal = freqMap.contains(len-1) ? freqMap.get(len) : 0 ;
        len--;
        temp = temp/10;
        if(mapVal!=r){
            return false;
        }
    }
    return true;
}





















badmiton reservation


very arena 4 courts


users - 
customer
admin - configure the application

several locations


/users- crud



roles

user

arenas
courts



models:

users
id name email role_id
roles
arenas
courts
reservations
id slot_id court_id
slots
id start_time, end_time, type, start_date, end_date, price

9:00 am to 9 pm
slot 30 mins

periodic break






*/



/*



/*

account
id name email role_id

Roles
admin / user

products
id, name, price

1
2


cartItems
id acc_id prod_id qty

1   1       1      1
2   1       2      1




API Interface;
/accounts  CRUD
/products CRUD

POST /add_to_cart   - {prod_id: 1}  // account_id can be fetched from cookies.
Get /
Delete /remove_from_cart/{id}




class CartsController{
    
    public void add_to_cart(Request req){
        // ORM to create 
        
    }
    
    public void add_items_to_cart(List<Products> prod){
        
        
    }
    
}



Heap


class Account{
    CartItem cart;
}


class Products{
    private id, name, price;
    
//    public boolean equals(Object o){
//        if(o==null) return 
//    }
    
}

class ProductComparator implements Comparable<Product>{
    
    public int compare(Product a, Product b){
        return b.price-a.price;
    }
    
}

class CartItems{
    Queue<Product> maxHeap = new PriorityQueue<>(new ProductComparator());
    public addItem(Product p){
        maxHeap.add(p);
    }
    
    public removeItem(Product p){
        maxHeap.remove(p);
    }
}




                10
                
            4        6
         2     1   1    5  
        
   

//                10


parent tables
child tables
query tables
charts    // views
summary    // views
pivots

       A     
       
   B       c








*/