/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system_design.system_design_interview.notification_service;

/**
 *
 * @author venka
 */

// Distributed Notification service - KAFKA,  - msg is sent from publisher to subscribers subscribed to a topic


public class NotificationService {
    
    /*
    use case examples
        when credit card exceed limit, service monitoring system alerts, 
    
    limits of sync communication
        producer calls each sub in order and waits for response
        hard to scale when num of subs and num of msg grow
        hard to extend for diff types of subs
        
    */
    
}
