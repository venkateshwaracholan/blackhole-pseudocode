/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.design_data_structure;

/**
 *
 * @author vshanmugham
 */

/*  #google
You are developing a music player, with shuffle feature. Which randomise the songs played from a playlist.
There’s a cool down number, c, meaning you should not repeat last  c recently played songs. The songs should be played completely randomised .

*/
import java.util.*;

// ArrayList + queue
public class RandomizedKSongs {
  int k;
  Random random = new Random();
  List<String> songs;
  Queue<String> coolDownQueue;
  RandomizedKSongs(List<String> songs, int k){
    this.songs = songs;
    this.k = k;
    this.coolDownQueue = new LinkedList();
  }
//  Time: O(1) space: O(1) 
//  Core Idea:ArrayList + queue
//  Init songs list and queue and a random generator
//  in shuffle, first generate a random number within the arraylist size
//  get and the song from random index
//  There are 2 case: 
//  coolDownQueue < k:
//  in this case, to avoid O(n time complexity of remove in arrayList),
//  we swap with last index, so we set the last index's value at rand and remove the last Index
//  and add the string in variable to queue
//  case 2 : coolDownQueue >= k
//  in this case, instead of setting the last index's value, we set the first value from queue
//  the polled value, and no need for removal as one song from queu has to come back to list
  
  
  public String shuffle(){
    int n = songs.size();
    int rand = random.nextInt(n);
    String randSong = songs.get(rand);
    if(coolDownQueue.size()<k){
      songs.set(rand,songs.get(n-1));
      songs.remove(n-1);
    }else
      songs.set(rand,coolDownQueue.poll());
    coolDownQueue.add(randSong);
    System.out.println(randSong);
    return randSong;
  }
  
  
  public static void main(String args[]){
    List<String> songs = new ArrayList(Arrays.asList("a","b","c","d","e"));
    RandomizedKSongs playlist = new RandomizedKSongs(songs, 3);
    playlist.shuffle();
    playlist.shuffle();
    playlist.shuffle();
    playlist.shuffle();
    playlist.shuffle();
    playlist.shuffle();
    playlist.shuffle();
    
    
  }
}
