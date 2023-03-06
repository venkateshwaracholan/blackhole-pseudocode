/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interviews.cleartrip;

/**
 *
 * @author venka
 */
public class Assignment {
    
}


/*
ClearFit - SDE4\nDesign a backend system for a new enterprise application that Cleartrip is launching, ClearFit. Cleartrip is partnering up with gyms across 
Bangalore to enter into the fitness space. For the Beta launch the requirements are as follows: \nFunctionality to onboard centers with details like centerName, 
center-timings, workout variations. \nEach center can have n possible workout variations - Weights, Cardio, Yoga, Swimming etc. There could be newer workouts added 
in the future. \n\nFunctionality to define workouts slots. \nEach center will define a workout slot during center timings. These slots would be defined by a center admin.
\nCurrent scope is that at one point of time for a center, there would be just one workout. \nThe number of seats in each workout slot for a given center is fixed. 
\nCurrent scope is that an admin would define these workout slots on a daily basis for the same day and only once. Update is out of scope\n\nEnd-User
/Customer can perform the following operations: \nOptional - Register onto the platform. In the current scope we don’t have to worry about authentication.\n
View the workout slots availability/unavailability for the day - filter by workout type (sorted by start time in ASC order).\nView the workout slots 
availability/unavailability for the day - filter by workout type and center name (sorted by seats available in ASC order).\nBook a workout slot for a user 
if seats are available at that time. \nCancel the booked workout slot. \nNotify me feature:  If a user is interested in some particular slot of a center and seats 
are unavailable he can choose to be part of the interest list for that slot. Once any other user cancels that particular slot the interested users can be notified. 
Notification will be pushed to all the interested users.  You can use logs to model the notification.
\n\n\nExample :-\n\nCenter Onboarding steps\nAddCentre(“Koramangala”);
\nAddCentretimings(“Koramangala”,List\u003cTimings\u003e timings);\n6 am to 9 am\n6 pm to 9 pm\n\nAddCentreActivities(“Koramangala”,List\u003cString\u003e activities);\n
Weights, Cardio, Yoga, Swimming\n\n\nAddCentre(“Bellandur”);\nAddCentretimings(“Bellandur”,List\u003cSlot\u003e slotList);\n7 am to 10 am\n7 pm to 10 pm
\n\nAddCentreActivities(“Bellandur”,List\u003cString\u003e activities);\nWeights, Cardio, Yoga\n\nAdmin Operation\nAdd Workout and seats to location\n
\naddWorkout(\u003ccenterName\u003e, \u003cworjour type\u003e, \u003cstart time\u003e, \u003cend time\u003e, \u003cavailable slots\u003e) 
\naddWorkout(“Koramangala”, “Weights”, 6, 7, 100) \naddWorkout(“Koramangala”, “Cardio”, 7, 8, 150) \naddWorkout(“Koramangala”, “Yoga”, 8, 9, 200) 
\n\naddWorkout(“Bellandur”, “Weights”, 18, 19, 100) // this should not be allowed because of time\naddWorkout(“Bellandur”, “Swimming”, 19, 20, 100) 
// not allowed because of workout type\naddWorkout(“Bellandur”, “Cardio”, 19, 20, 20)\naddWorkout(“Bellandur”, “Weights”, 20, 21, 100)
\naddWorkout(“Bellandur”, “Weights”, 21, 22, 100)\n\nUser Operations\nregister(“Vaibhav”)\n\nviewWorkoutAvailability(“Weights”)\n“Koramangala”, “Weights”, 6, 7, 100
\n“Bellandur”, “Weights”, 20, 21, 100\n“Bellandur”, “Weights”, 21, 22, 100\t\n\n\nbookASession\nbookSession(“Vaibhav”, “Koramangala”, “Weight”, 6, 7)
\n \n\nviewWorkoutAvailability(“Weights”)\n“Koramangala”, “Weights”, 6, 7, 99\n“Bellandur”, “Weights”, 19, 20, 100\n\nCancelSession
\ncancelSession(“Vaibhav”, “Koramangala”, “Weight”, 6, 7)\n\nviewWorkoutAvailability(“Weights”, “Koramangala”)\n“Koramangala”, “Weights”, 6, 7, 100\n
\nFunctional notes :-\nPlease handle concurrent scenarios like multiple users booking the same slot. \nFor simplicity, all the operations are performed for a day only. 
But keep your design extensible and moving operations across days should be done with minimal change.\nYou can take time as an integer since the existing scope is for a 
day only..\nThere would be a unique identifier of every entity- could be a name or a unique id. You can choose one and assume that it would be unique.
\nGuidelines\nTime: 120 mins \nMandatory programming language - Java. \nA driver program/main class/test case is needed to test out the code by the evaluator with 
multiple test cases. But do not spend too much time in the input parsing. Keep it as simple as possible. 
Evaluation criteria: Demoable \u0026 functionally correct code, Code readability, Proper Entity modeling, Modularity 
\u0026 Extensibility, Separation of concerns, Abstractions, Corner case handling. Use design patterns wherever applicable.\nYou are not allowed to use any 
external databases like MySQL. Use only in memory data structures. \nFunctionality doesn’t have to be defined as a rest api. You can expose them as methods 
also which can be invoked from the driver class.\nNo need to create any UX \nPlease focus on the Bonus Feature (if any) only after ensuring the required features 
are complete and demoable.\nMethod signatures defined are just to give an idea. You are free to change them to suit your requirements.\nUse of the internet is allowed 
to check syntax.\nUsing popular java frameworks like spring and dropwizard is allowed

fitness space


centers
    centername
    timing
    workout

no updates, just create and delete


actors 
    admin


workout slot(weights, cardio, swimming,yoga)


usecases
registration
see workout slots for the day
    - filter by workout time, sort by time
    - filter by workout time and center name, sort by number of seats
    
book a slot
cancel slot
notify me, get notification if someone cancels a slot



concurrrency
operation for today only







*/