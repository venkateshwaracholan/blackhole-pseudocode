/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package curated_lists.interviews.gameberrylabs;

import java.util.*;

//import lombok.AllArgsConstructor;

/**
 *
 * @author venka
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

Top most cuisine will be considered as a primary cuisine of the user and next 2 are considered as secondary. Similarly, the top most cost bracket will be considered as a primary cost bracket of the user and the next 2 are considered as secondary.


We want to sort all the restaurants available in the vicinity and show top 100 unique restaurants with the following logic:

Order
Condition
1
Featured restaurants of primary cuisine and primary cost bracket. If none, then all featured restaurants of primary cuisine, secondary cost and secondary cuisine, primary cost
2
All restaurants of Primary cuisine, primary cost bracket with rating >= 4
3
All restaurants of Primary cuisine, secondary cost bracket with rating >= 4.5
4
All restaurants of secondary cuisine, primary cost bracket with rating >= 4.5
5
Top 4 newly created restaurants by rating


6
All restaurants of Primary cuisine, primary cost bracket with rating < 4
7
All restaurants of Primary cuisine, secondary cost bracket with rating < 4.5
8
All restaurants of secondary cuisine, primary cost bracket with rating < 4.5
9
All restaurants of any cuisine, any cost bracket



Given the below classes. Implement the getRestaurantRecommendation function in any language of your choice:

Enum Cuisine {
	SouthIndian, NorthIndian, Chinese etc.
}

Class Restaurant {
	private string restaurantId
	private Cuisine cuisine
	private int costBracket
	private float rating
	private boolean isRecommended
	private Date onboardedTime
}

Class CuisineTracking {
	Private string type
	Private string noOfOrders
}

Class CostTracking {
	Private string type
	Private string noOfOrders
}

Class User {
	private CuisineTracking[]  cuisines
	private CostTracking[] costBracket
}

public string[] getRestaurantRecommendations(User user, Restaurant[] availableRestaurants){

	// Takes user and restaurant while returning back array of restaurant Ids in the right sorting order

}



Evaluation Criteria
Your solution will not only be evaluated on the correctness of your code but also the readability, maintainability and extensibility of your code. 
The code needs to be production quality. 
You can select any language of your choice to write code.
You can generate the code in any tool of your choice and share the git link for the same.


*/
enum Cuisine {
    SouthIndian, NorthIndian, Chinese
}


class Restaurant {
    static final Date twoDaysAgo = new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24 * 2);

    private String restaurantId;
    private Cuisine cuisine;
    private int costBracket;
    private float rating;
    private boolean isRecommended;
    private Date onboardedTime;
    private boolean isNew;

    Restaurant(String restaurantId, Cuisine cuisine, int costBracket, float rating, boolean isRecommended, Date onboardedTime){
        this.restaurantId = restaurantId;
        this.cuisine = cuisine;
        this.costBracket = costBracket;
        this.rating = rating;
        this.isRecommended = isRecommended;
        this.onboardedTime = onboardedTime;
        if(onboardedTime.before(twoDaysAgo))
            isNew = true;
    }
    
    public boolean isFeaturedPrimary(Cuisine cuisine, int costBracket){
        return isRecommended && this.cuisine == cuisine && this.costBracket == costBracket;
    }
}

class CuisineTracking implements Comparable<CuisineTracking>{
    private String type;
    private Cuisine cuisine;
    private int noOfOrders;
    CuisineTracking(Cuisine cuisine, int noOfOrders){
        this.cuisine = cuisine;
        this.noOfOrders = noOfOrders;
    }
    public Cuisine getCuisine(){
        return this.cuisine;
    }
    public int compareTo(CuisineTracking cuisine) {
        return cuisine.noOfOrders - this.noOfOrders;
    }	
    
}

class CostTracking implements Comparable<CostTracking>{
    private String type;
    private int costBracket;
    private int noOfOrders;
    CostTracking(int costBracket, int noOfOrders){
        this.costBracket = costBracket;
        this.noOfOrders = noOfOrders;
    }
    public int getCostBracket(){
        return this.costBracket;
    }
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
            
        return new String[1];
    }
    
    
    public static void main(String[] args){
        Restaurant r1 = new Restaurant("A", Cuisine.Chinese, 1, 5, false, new Date());
        Restaurant r2 = new Restaurant("A", Cuisine.NorthIndian, 5, 1, false, new Date());
        Restaurant r3 = new Restaurant("A", Cuisine.SouthIndian, 3, 3, false, new Date());
        Restaurant r4 = new Restaurant("B", Cuisine.Chinese, 5, 1, false, new Date());
        Restaurant r5 = new Restaurant("B", Cuisine.NorthIndian, 1, 5, false, new Date());
        Restaurant r6 = new Restaurant("B", Cuisine.SouthIndian, 2, 2, false, new Date());
        Restaurant[] r = new Restaurant[]{r1,r2,r3,r4,r5,r6};
        CuisineTracking cu1 = new CuisineTracking(Cuisine.Chinese, 10);
        CuisineTracking cu2 = new CuisineTracking(Cuisine.NorthIndian, 15);
        CuisineTracking cu3 = new CuisineTracking(Cuisine.SouthIndian, 20);
        CostTracking co1 = new CostTracking(1, 10);
        CostTracking co2 = new CostTracking(3, 15);
        CostTracking co3 = new CostTracking(5, 20);
        User u = new User(new CuisineTracking[]{cu1,cu2,cu3}, new CostTracking[]{co1,co2,co3});
        System.out.println(u.getPrimaryCuisine());
        for(Cuisine c: u.getSecondaryCuisine()){
            System.out.println(c);
        }
        System.out.println(u.getPrimaryCostBracket());
        for(int c: u.getSecondaryCostBracket()){
            System.out.println(c);
        }
        String[] ans = getRestaurantRecommendations(u, r);
        
        
    }
}
