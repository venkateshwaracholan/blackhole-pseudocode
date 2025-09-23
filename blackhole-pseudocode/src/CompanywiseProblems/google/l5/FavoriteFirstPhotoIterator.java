package CompanywiseProblems.google.l5;

import java.util.*;

/*
Problem Name: FavoriteFirstPhotoIterator

Description:
Given a list of photo IDs (Strings) and a subset of favorite photo IDs (no duplicates in either list),
implement an iterator that returns all favorite photo IDs first (in order of appearance in favorites),
then the remaining photos (in order of appearance in the photos list), **without duplicates**.

Edge Cases:
- Empty photos list → return empty
- Empty favorites → return photos as-is
- All photos are favorites → output same as favorites
- Duplicates between photos and favorites → favorites take precedence
- Unusual IDs (strings, numbers, mixed, special characters)
- Max/min size lists

Examples:

Photos:     [p10, p2, p3, p4, p5, p6, p7, p8]  
Favorites:  [p8, p4, p10]  

Output:     [p8, p4, p10, p2, p3, p5, p6, p7]

Constraints:
- No duplicates within each list
- IDs are Strings
- Order must be preserved: favorites first, then remaining photos
*/

public class FavoriteFirstPhotoIterator implements Iterator<String>{

    // ----------------- BRUTE FORCE -----------------
    /**
     * Brute-force approach using a HashSet to skip duplicates.
     * 1. Add all favorites to result.
     * 2. Iterate over photos, skip any already in favorites.
     *
     * Time: O(F + P) → F = #favorites, P = #photos
     * Space: O(F) for set
     */
    public static List<String> getPhotosBrute(List<String> photos, List<String> favorites) {
        var res = new ArrayList<String>();
        var seen = new HashSet<String>();

        for (var fav : favorites) {
            res.add(fav);
            seen.add(fav);
        }

        for (var photo : photos) {
            if (!seen.contains(photo)) {
                res.add(photo);
            }
        }

        return res;
    }

    // ----------------- OPTIMAL TWO-POINTER -----------------
    /**
     * Optimal two-pointer approach without extra set.
     * Logic:
     * 1. Yield all favorites first (preserves order).
     * 2. Iterate photos and skip any photo already added as favorite.
     *
     * Time: O(P + F)  
     * Space: O(P + F) for result only (no extra HashSet)
     *
     * Edge Cases handled:
     * - Empty photos or favorites
     * - All photos are favorites
     * - Preserves input order
     */
    public static List<String> orderPhotosTwoPointers(List<String> photos, List<String> favourites) {
        var result = new ArrayList<String>(photos.size());

        // 1. Add favourites first
        result.addAll(favourites);

        // 2. Two-pointer iteration over photos and favourites
        int favIndex = 0;
        for (String photo : photos) {
            // Skip favourites that are already added
            if (favIndex < favourites.size() && photo.equals(favourites.get(favIndex))) {
                favIndex++;
                continue;
            }
            result.add(photo);
        }

        return result;
    }

    /*
    Follow-up / Discussion Points:
    - Brute-force is simple and safe for unsorted input.
    - Two-pointer avoids extra space, preserves input order.
    - Edge cases: empty lists, all photos in favorites, no favorites.
    - Normalization: optionally handle case-insensitive IDs or trimmed strings.
    - Can extend to streaming or iterator pattern for lazy evaluation.
    */

    private final List<String> photos;
    private final List<String> favourites;
    private int favIndex = 0;
    private int favYeildIndex = 0;
    private int photoIndex = 0;

    public FavoriteFirstPhotoIterator(List<String> photos, List<String> favourites) {
        this.photos = photos;
        this.favourites = favourites;
    }

    @Override
    public boolean hasNext() {
        while (photoIndex < photos.size() && favIndex < favourites.size()
                && photos.get(photoIndex).equals(favourites.get(favIndex))) {
            photoIndex++;
            favIndex++;
        }
        return favYeildIndex < favourites.size() || photoIndex < photos.size();
    }

    //handles duplicates if we have further followups
    // @Override
    // public boolean hasNext() {
    //     while (photoIndex < photos.size() && favIndex < favourites.size()) {
    //         int photoNum = Integer.parseInt(photos.get(photoIndex).replaceAll("\\D+", ""));
    //         int favNum = Integer.parseInt(favourites.get(favIndex).replaceAll("\\D+", ""));
    //         int cmp = Integer.compare(photoNum, favNum);
    //         if (cmp == 0) {
    //             photoIndex++;  // skip duplicate photo
    //         } else if (cmp > 0) {
    //             favIndex++;    // skip already yielded favourite
    //         } else {
    //             break;         // photo < fav → yield this photo
    //         }
    //     }
    //     return favYeildIndex < favourites.size() || photoIndex < photos.size();
    // }

    @Override
    public String next() {
        // 1. Yield all favourites first
        if (favYeildIndex < favourites.size()) {
            // System.out.println(favourites.get(favYeildIndex));
            return favourites.get(favYeildIndex++);
        }

        // System.out.println(photos.get(photoIndex));

        return photos.get(photoIndex++);
    }

    public static void main(String[] args) {
        var photos = List.of("p10", "p2", "p3", "p4", "p5", "p6", "p7", "p8");
        var favorites = List.of("p8", "p4", "p10");

        // --- Brute force approach ---
        System.out.println(getPhotosBrute(photos, favorites));
        // Expected: [p8, p4, p10, p2, p3, p5, p6, p7]

        // Edge case: empty favorites
        System.out.println(getPhotosBrute(photos, List.of()));
        // Expected: [p10, p2, p3, p4, p5, p6, p7, p8]

        // Edge case: empty photos
        System.out.println(getPhotosBrute(List.of(), favorites));
        // Expected: [p8, p4, p10]

        // --- Optimized two-pointer approach (assumes sorted input) ---
        var sortedPhotos = List.of("p2","p3","p4","p5","p6","p7","p8","p10");
        var sortedFavorites = List.of("p4","p8","p10");

        System.out.println("sorted optimized");

        System.out.println(orderPhotosTwoPointers(sortedPhotos, sortedFavorites));
        // Expected: [p4, p8, p10, p2, p3, p5, p6, p7]

        // Edge case: empty favorites
        System.out.println(orderPhotosTwoPointers(sortedPhotos, List.of()));
        // Expected: [p2, p3, p4, p5, p6, p7, p8, p10]

        // Edge case: empty photos
        System.out.println(orderPhotosTwoPointers(List.of(), sortedFavorites));
        // Expected: [p4, p8, p10]


        System.out.println("Lazy Iterator output:");
        var lazyIter = new FavoriteFirstPhotoIterator(sortedPhotos, sortedFavorites);
        var resultLazy = new ArrayList<String>();
        while (lazyIter.hasNext()) {
            resultLazy.add(lazyIter.next());
        }
        System.out.println(resultLazy);

        System.out.println("--- Lazy Iterator Edge Cases ---");

        // Case 1: Normal
        var lazyIter1 = new FavoriteFirstPhotoIterator(
            List.of("p2","p3","p4","p5","p6","p7","p8","p10"),
            List.of("p4","p8","p10")
        );
        var result1 = new ArrayList<String>();
        while(lazyIter1.hasNext()) result1.add(lazyIter1.next());
        System.out.println(result1);
        // Expected: [p4, p8, p10, p2, p3, p5, p6, p7]

        // Case 2: No favorites
        var lazyIter2 = new FavoriteFirstPhotoIterator(
            List.of("p2","p3","p4","p5","p6","p7","p8","p10"),
            List.of()
        );
        var result2 = new ArrayList<String>();
        while(lazyIter2.hasNext()) result2.add(lazyIter2.next());
        System.out.println(result2);
        // Expected: [p2, p3, p4, p5, p6, p7, p8, p10]

        // Case 3: Empty photos
        var lazyIter3 = new FavoriteFirstPhotoIterator(
            List.of(),
            List.of("p4","p8","p10")
        );
        var result3 = new ArrayList<String>();
        while(lazyIter3.hasNext()) result3.add(lazyIter3.next());
        System.out.println(result3);
        // Expected: [p4, p8, p10]

        // Case 4: Both empty
        var lazyIter4 = new FavoriteFirstPhotoIterator(List.of(), List.of());
        var result4 = new ArrayList<String>();
        while(lazyIter4.hasNext()) result4.add(lazyIter4.next());
        System.out.println(result4);
        // Expected: []

        // Case 5: All photos are favorites
        var lazyIter5 = new FavoriteFirstPhotoIterator(
            List.of("p4","p8","p10"),
            List.of("p4","p8","p10")
        );
        var result5 = new ArrayList<String>();
        while(lazyIter5.hasNext()) result5.add(lazyIter5.next());
        System.out.println(result5);
        // Expected: [p4, p8, p10]

        // Case 6: Photos contain duplicates of favorites
        var lazyIter6 = new FavoriteFirstPhotoIterator(
            List.of("p2","p3","p4","p4","p5","p6","p7","p8","p10","p10"),
            List.of("p4","p8","p10")
        );
        var result6 = new ArrayList<String>();
        while(lazyIter6.hasNext()) result6.add(lazyIter6.next());
        System.out.println(result6);
        // Expected: [p4, p8, p10, p2, p3, p5, p6, p7]

        // Case 7: Favorites not in photos
        var lazyIter7 = new FavoriteFirstPhotoIterator(
            List.of("p2","p3","p5","p6","p7"),
            List.of("p4","p8","p10")
        );
        var result7 = new ArrayList<String>();
        while(lazyIter7.hasNext()) result7.add(lazyIter7.next());
        System.out.println(result7);
        // Expected: [p4, p8, p10, p2, p3, p5, p6, p7]

        // Case 8: Favorites entirely outside photos
        var lazyIter8 = new FavoriteFirstPhotoIterator(
            List.of("p2","p3","p4","p5"),
            List.of("p6","p7","p8")
        );
        var result8 = new ArrayList<String>();
        while(lazyIter8.hasNext()) result8.add(lazyIter8.next());
        System.out.println(result8);
        // Expected: [p6, p7, p8, p2, p3, p4, p5]

        // Case 9: Single element, same
        var lazyIter9 = new FavoriteFirstPhotoIterator(
            List.of("p2"),
            List.of("p2")
        );
        var result9 = new ArrayList<String>();
        while(lazyIter9.hasNext()) result9.add(lazyIter9.next());
        System.out.println(result9);
        // Expected: [p2]

        // Case 10: Single photo, favorite not in photos
        var lazyIter10 = new FavoriteFirstPhotoIterator(
            List.of("p2"),
            List.of("p3")
        );
        var result10 = new ArrayList<String>();
        while(lazyIter10.hasNext()) result10.add(lazyIter10.next());
        System.out.println(result10);
        // Expected: [p3, p2]
    }
}

/*
| Approach                  | Time Complexity | Space Complexity | When Best                             | Notes / Google Discussion Points                   |
| ------------------------- | --------------- | ----------------| -------------------------------------| -------------------------------------------------- |
| Brute (HashSet)           | O(F + P)        | O(F)             | Unsorted lists                        | Simple, easy to implement in 60-min interview    |
| Two-pointer (no extra set)| O(F + P)        | O(P + F) output  | Preserves order, avoids extra space   | Optimal, elegant, precise for Google interviews  |
| Edge Cases                | - Empty lists   | - All photos in favorites | - No favorites                        | Must be considered for correctness               |
| Normalization             | Optional        | -                | Case-insensitive / trimmed strings    | Real-world data handling                         |
*/
