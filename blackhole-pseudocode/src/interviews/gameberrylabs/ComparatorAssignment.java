/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interviews.gameberrylabs;

import java.text.ParseException;
import java.util.*;
import java.text.SimpleDateFormat;

/**
 *
 * @author venkateshwaran shanmugham
 */

/*
Restaurant Recommendation Engine
Assume that you are building a recommendation engine for your food delivery app and product team gives you the spec with following requirements.

Your algorithm considers following criterias
Cuisine of the restaurant : North Indian, Chinese, South Indian etc
Cost bracket: 1,2,3,4,5 (Increasing order from cheap to costly)
Featured restaurants: Restaurants which are officially tested by our app and recommended.
New restaurants: Restaurants which are onboarded in the last 48hrs.
Rating: Average user rating for the restaurant (from 0.0 - 5.0)

For every user that orders from the app we track following parameters:
Cuisine of the restaurant
Cost bracket

Top most cuisine will be considered as a primary cuisine of the user and next 2 are considered as secondary. Similarly, the top most cost bracket will be 
considered as a primary cost bracket of the user and the next 2 are considered as secondary.

We want to sort all the restaurants available in the vicinity and show top 100 unique restaurants with the following logic:

Order
Condition
1 Featured restaurants of primary cuisine and primary cost bracket. If none, then all featured restaurants of primary cuisine, secondary cost and secondary cuisine, 
primary cost
2 All restaurants of Primary cuisine, primary cost bracket with rating >= 4
3 All restaurants of Primary cuisine, secondary cost bracket with rating >= 4.5
4 All restaurants of secondary cuisine, primary cost bracket with rating >= 4.5
5 Top 4 newly created restaurants by rating
6 All restaurants of Primary cuisine, primary cost bracket with rating < 4
7 All restaurants of Primary cuisine, secondary cost bracket with rating < 4.5
8 All restaurants of secondary cuisine, primary cost bracket with rating < 4.5
9 All restaurants of any cuisine, any cost bracket


Evaluation Criteria
Your solution will not only be evaluated on the correctness of your code but also the readability, maintainability and extensibility of your code. 
The code needs to be production quality. 
You can select any language of your choice to write code.
You can generate the code in any tool of your choice and share the git link for the same.

*/
enum Cuisine {
    SouthIndian, NorthIndian, Chinese
}

class Restaurant{
    static final Date twoDaysAgo = new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24 * 2);

    private String restaurantId;
    private Cuisine cuisine;
    private int costBracket;
    private double rating;
    private boolean isRecommended;
    private Date onboardedTime;
    private boolean isNew;
    //java lombok library can help us avoid these getters,setters and constructors
    public String getRestaurantId(){
        return restaurantId;
    }
    public Date getOnboardedTime(){
        return onboardedTime;
    }
    public boolean isNew(){
        return isNew;
    }
    Restaurant(String restaurantId, Cuisine cuisine, int costBracket, double rating, boolean isRecommended, Date onboardedTime){
        this.restaurantId = restaurantId;
        this.cuisine = cuisine;
        this.costBracket = costBracket;
        this.rating = rating;
        this.isRecommended = isRecommended;
        this.onboardedTime = onboardedTime;
        if(onboardedTime.after(twoDaysAgo))
            isNew = true;
    }
    
    //actual useful methods
    public boolean isFeatured(){
        return isRecommended;
    }
    public boolean isPrimary(Cuisine cuisine, int costBracket){
        return this.cuisine == cuisine && this.costBracket == costBracket;
    }
    public boolean isPrimarySecondary(Cuisine cuisine, Set<Integer> costBracket){
        return this.cuisine == cuisine && costBracket.contains(this.costBracket);
    }
    public boolean isSecondaryPrimary(Set<Cuisine> cuisine, int costBracket){
        return cuisine.contains(this.cuisine) &&  this.costBracket == costBracket;
    }
    public double getRating(){
        return rating;
    }
}

class CuisineTracking implements Comparable<CuisineTracking>{
    private String type;
    private Cuisine cuisine;
    private int noOfOrders;
    //java lombok library can help us avoid these getters,setters and constructors
    CuisineTracking(Cuisine cuisine, int noOfOrders){
        this.cuisine = cuisine;
        this.noOfOrders = noOfOrders;
    }
    public Cuisine getCuisine(){
        return this.cuisine;
    }
    //for sorting based on order quantity
    public int compareTo(CuisineTracking cuisine) {
        return cuisine.noOfOrders - this.noOfOrders;
    }	
    
}

class CostTracking implements Comparable<CostTracking>{
    private String type;
    private int costBracket;
    private int noOfOrders;
    //java lombok library can help us avoid these getters,setters and constructors
    CostTracking(int costBracket, int noOfOrders){
        this.costBracket = costBracket;
        this.noOfOrders = noOfOrders;
    }
    public int getCostBracket(){
        return this.costBracket;
    }
    //for sorting based on order quantity
    public int compareTo(CostTracking costTracking) {
        return costTracking.noOfOrders - this.noOfOrders;
    }	
}

class User {
    private CuisineTracking[]  cuisines;
    private CostTracking[] costBrackets;
    private Cuisine primaryCuisine;
    private Set<Cuisine> secondaryCuisine = new HashSet();
    private int primaryCostBracket;
    private Set<Integer> secondaryCostBracket = new HashSet();
    //java lombok library can help us avoid these getters,setters and constructors
    User(CuisineTracking[]  cuisines, CostTracking[] costBrackets){
        this.cuisines = cuisines;
        this.costBrackets = costBrackets;
        computePrimarySecondary();
    }
    public Cuisine getPrimaryCuisine(){
        return this.primaryCuisine;
    }
    public Set<Cuisine> getSecondaryCuisine(){
        return this.secondaryCuisine;
    }
    public int getPrimaryCostBracket (){
        return this.primaryCostBracket;
    }
    public Set<Integer> getSecondaryCostBracket (){
        return this.secondaryCostBracket;
    }
    
    public void computePrimarySecondary(){
        Arrays.sort(cuisines);
        if(cuisines.length>0) primaryCuisine = cuisines[0].getCuisine();
        if(cuisines.length>1) secondaryCuisine.add(cuisines[1].getCuisine());
        if(cuisines.length>2) secondaryCuisine.add(cuisines[2].getCuisine());
        Arrays.sort(costBrackets);
        if(costBrackets.length>0) primaryCostBracket = costBrackets[0].getCostBracket();
        if(costBrackets.length>1) secondaryCostBracket.add(costBrackets[1].getCostBracket());
        if(costBrackets.length>2) secondaryCostBracket.add(costBrackets[2].getCostBracket());
    }
}

public class ComparatorAssignment {
    

    public static String[] getRestaurantRecommendations(User user, Restaurant[] availableRestaurants){

            // Takes user and restaurant while returning back array of restaurant Ids in the right sorting order
        Cuisine primaryCuisine = user.getPrimaryCuisine();
        Set<Cuisine> secondaryCuisine = user.getSecondaryCuisine();
        int primaryCostBracket = user.getPrimaryCostBracket();
        Set<Integer> secondaryCostBracket = user.getSecondaryCostBracket();
        sortRestaurants(availableRestaurants, primaryCuisine, secondaryCuisine, primaryCostBracket, secondaryCostBracket);
        String[] recommendations = new String[availableRestaurants.length]; 
        for(int idx=0;idx < availableRestaurants.length;idx++){
            recommendations[idx] = availableRestaurants[idx].getRestaurantId();
        }
        return recommendations;
    }
    //topk new restaurants by rating
    public static Set<Restaurant> getTopKNewlyCreatedRestaurantsByRating(Restaurant[] restaurants, int k){
        
        //filtering new restaurants
        Restaurant[] newRestaurants = Arrays.stream(restaurants).filter(r -> r.isNew()).toArray(Restaurant[]::new);
        //finding Top k using min Heap
        PriorityQueue<Restaurant> heap = new PriorityQueue<Restaurant>(new Comparator<Restaurant>() {    
            @Override
            public int compare(Restaurant r1, Restaurant r2)
            {
                Double rating1 = r1.getRating();
                Double rating2 = r2.getRating();
                return rating1.compareTo(rating2);
            }
        });
        for(Restaurant r: restaurants){
            heap.add(r);
            if(heap.size()>k) heap.poll();
        }
        Set<Restaurant> topKnewRestaurants = new HashSet();
        while(k-->0)
            topKnewRestaurants.add(heap.poll());
        return topKnewRestaurants;
    }
    
    public static Restaurant[] sortRestaurants(Restaurant[] restaurants, Cuisine primaryCuisine, Set<Cuisine> secondaryCuisines,
                                                    int primaryCostBracket, Set<Integer> secondaryCostBrackets) {
        //getting top4 new restaurants
        Set<Restaurant> top4newRestaurants = getTopKNewlyCreatedRestaurantsByRating(restaurants, 4);
        //comparator Logic
        Comparator<Restaurant> comparator = new Comparator<Restaurant>() {
		@Override
		public int compare(Restaurant r1, Restaurant r2) {
                    Boolean b1 =  r1.isFeatured() && r1.isPrimary(primaryCuisine, primaryCostBracket);
                    Boolean b2 =  r2.isFeatured() && r2.isPrimary(primaryCuisine, primaryCostBracket);
                    return b2.compareTo(b1);
		}
	};
        // chained thenComparing for lexographic ordering for all the listed orders
        comparator = comparator.thenComparing((r1)  -> {
            return r1.isFeatured() && r1.isPrimarySecondary(primaryCuisine, secondaryCostBrackets);
        },Comparator.reverseOrder())        
        .thenComparing((r1)  -> {
            return r1.isFeatured() && r1.isSecondaryPrimary(secondaryCuisines, primaryCostBracket);
        },Comparator.reverseOrder())
        .thenComparing((r1)  -> {
            return r1.isPrimary(primaryCuisine, primaryCostBracket) && r1.getRating()>=4;
        },Comparator.reverseOrder())
        .thenComparing((r1)  -> {
            return r1.isPrimarySecondary(primaryCuisine, secondaryCostBrackets) && r1.getRating()>=4.5;
        },Comparator.reverseOrder())
        .thenComparing((r1)  -> {
            return r1.isSecondaryPrimary(secondaryCuisines, primaryCostBracket) && r1.getRating()>=4.5;
        },Comparator.reverseOrder())
        .thenComparing((r1)  -> {
            return top4newRestaurants.contains(r1);
        },Comparator.reverseOrder())
        .thenComparing((r1)  -> {
            return r1.isPrimary(primaryCuisine, primaryCostBracket) && r1.getRating()<4;
        },Comparator.reverseOrder())
        .thenComparing((r1)  -> {
            return r1.isPrimarySecondary(primaryCuisine, secondaryCostBrackets) && r1.getRating()<4.5;
        },Comparator.reverseOrder())
        .thenComparing((r1)  -> {
            return r1.isSecondaryPrimary(secondaryCuisines, primaryCostBracket) && r1.getRating()<4.5;
        },Comparator.reverseOrder());
        Arrays.sort(restaurants, comparator);
        return restaurants;
    }

    //date helpers 
    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
    
    public static void main(String[] args){
        //Test cases
        Restaurant r1 = new Restaurant("A", Cuisine.Chinese, 1, 5, false, parseDate("2022-03-25"));
        Restaurant r2 = new Restaurant("B", Cuisine.NorthIndian, 5, 4.5, false, new Date());
        Restaurant r3 = new Restaurant("C", Cuisine.SouthIndian, 3, 3.5, false, new Date(System.currentTimeMillis() - 1*3600 * 1000));
        Restaurant r4 = new Restaurant("D", Cuisine.Chinese, 5, 2, false, new Date(System.currentTimeMillis() - 2*3600 * 1000));
        Restaurant r5 = new Restaurant("E", Cuisine.NorthIndian, 1, 1, false, new Date(System.currentTimeMillis() - 3*3600 * 1000));
        Restaurant r6 = new Restaurant("F", Cuisine.SouthIndian, 5, 4, true, new Date(System.currentTimeMillis() - 4*3600 * 1000));
        Restaurant[] restaurants = new Restaurant[]{r1,r2,r3,r4,r5,r6};
        CuisineTracking cu1 = new CuisineTracking(Cuisine.Chinese, 10);
        CuisineTracking cu2 = new CuisineTracking(Cuisine.NorthIndian, 15);
        CuisineTracking cu3 = new CuisineTracking(Cuisine.SouthIndian, 20);
        CostTracking co1 = new CostTracking(1, 10);
        CostTracking co2 = new CostTracking(3, 15);
        CostTracking co3 = new CostTracking(5, 20);
        User u = new User(new CuisineTracking[]{cu1,cu2,cu3}, new CostTracking[]{co1,co2,co3});
        //outputs
        System.out.println("Primary cuisine");
        System.out.println(u.getPrimaryCuisine());
        System.out.println("\nSecondary cuisine");
        for(Cuisine c: u.getSecondaryCuisine()){
            System.out.println(c);
        }
        System.out.println("\nPrimary Cost bracket");
        System.out.println(u.getPrimaryCostBracket());
        System.out.println("\nSecondary Cost bracket");
        for(int c: u.getSecondaryCostBracket()){
            System.out.println(c);
        }
        System.out.println("\nRecommendations");
        String[] recommendations = getRestaurantRecommendations(u, restaurants);
        for(String r: recommendations){
            System.out.println(r);
        }
        
        /*
        output order explanation: 
        
        F - featured
        B - sec, pri && rat>=4.5
        C - pri, sec && rat<4  
        A - sec,sec, rat5
        D - sec, pri && rat=2
        E - sec,sec. rat=1
        (ADE) sorted this way coz of top 4 new restaurants by rating
        */
    }
}
