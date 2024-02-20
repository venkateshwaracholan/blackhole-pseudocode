/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author venka
 */
import java.util.*;

public class GoldCast {

    public static int findPeak(List<List<Integer>> events){
        if(events.isEmpty()) return 0;
        List<List<Integer>> ans = new ArrayList();
        Queue<List<Integer>> heap = new PriorityQueue<>((a,b) -> a.get(2)-b.get(2));
        Collections.sort(events, (a,b) ->  a.get(0)-b.get(0));
        ans.add(events.get(0));
        for(int i=1;i<events.size();i++){
            List<Integer> last = ans.get(ans.size()-1);
            List<Integer> newIn = events.get(i);
            if(newIn.get(1)<last.get(0) || newIn.get(0)>last.get(1)){
                ans.add(newIn);
            }else{
                last.set(0, Math.max(last.get(0), newIn.get(0)));
                last.set(1, Math.min(last.get(1), newIn.get(1)));
                last.set(2, last.get(2)+ newIn.get(2));
            }
        }
        for(List<Integer> event: ans){
            heap.add(event);
            if(heap.size()>1)
                heap.poll();
        }
        return heap.poll().get(2);
    }
    /*
     3      8(5)
  1    5  6    10
    */
    public static void main(String[] args){
        // Test case 1: Non-overlapping events
        List<List<Integer>> input1 = new ArrayList<>();
        input1.add(Arrays.asList(1, 2, 10));
        input1.add(Arrays.asList(3, 4, 20));
        input1.add(Arrays.asList(5, 6, 15));
        int peak1 = findPeak(input1); // Expected output: 20
        System.out.println("testcase 1: Expected output: 20 Actual: " + peak1);

        // Test case 2: Two overlapping events
        List<List<Integer>> input2 = new ArrayList<>();
        input2.add(Arrays.asList(1, 4, 10));
        input2.add(Arrays.asList(3, 6, 20));
        int peak2 = findPeak(input2); // Expected output: 30
        System.out.println("testcase 2: Expected output: 30 Actual: " + peak2);

        // Test case 3: Two non-overlapping events
        List<List<Integer>> input3 = new ArrayList<>();
        input3.add(Arrays.asList(1, 2, 10));
        input3.add(Arrays.asList(3, 4, 20));
        int peak3 = findPeak(input3); // Expected output: 20
        System.out.println("testcase 3: Expected output: 20 Actual: " + peak3);

        // Test case 4: Events with the same start time
        List<List<Integer>> input4 = new ArrayList<>();
        input4.add(Arrays.asList(1, 2, 10));
        input4.add(Arrays.asList(1, 3, 20));
        int peak4 = findPeak(input4); // Expected output: 30
        System.out.println("testcase 4: Expected output: 30 Actual: " + peak4);

        // Test case 5: Events with the same end time
        List<List<Integer>> input5 = new ArrayList<>();
        input5.add(Arrays.asList(1, 3, 10));
        input5.add(Arrays.asList(2, 3, 20));
        int peak5 = findPeak(input5); // Expected output: 30
        System.out.println("testcase 5: Expected output: 30 Actual: " + peak5);

        // Test case 6: One event
        List<List<Integer>> input6 = new ArrayList<>();
        input6.add(Arrays.asList(1, 2, 10));
        int peak6 = findPeak(input6); // Expected output: 10
        System.out.println("testcase 6: Expected output: 10 Actual: " + peak6);

        // Test case 7: Empty list
        List<List<Integer>> input7 = new ArrayList<>();
        int peak7 = findPeak(input7); // Expected output: 0
        System.out.println("testcase 7: Expected output: 0 Actual: " + peak7);
        
    }
}
