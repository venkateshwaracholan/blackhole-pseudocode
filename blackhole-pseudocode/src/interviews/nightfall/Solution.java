/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interviews.nightfall;

/**
 *
 * @author venka
 */
// Our goal is to implement and demonstrate a service for connecting vaccine patients with providers. Patients should be able to browse available appointments, make or cancel a reservation, and review the details of their reservation. Providers need to manage their available appointments and review their daily appointment reservations.

// Patients may only have one reservation in total at any given time (not one per provider).

// All vaccination appointments last 30 minutes and start on the hour or half hour. Providers may otherwise create appointments at any time of day.

// The desired API is provided, and may be adapted to your preferred programming language.

import java.io.*;
import java.util.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

class PatientAppointment {
    public LocalDateTime appointmentTime;
    public String providerID;
    
    public PatientAppointment(LocalDateTime appointmentTime, String providerID){
      this.appointmentTime = appointmentTime;
      this.providerID = providerID;
    }
}

class ProviderAppointment {
    public LocalDateTime appointmentTime;
    public String patientID;

    public ProviderAppointment(LocalDateTime appointmentTime){
      this.appointmentTime = appointmentTime;
    }
}

class VaccineScheduler {

    Map<String, PatientAppointment> patientAppointments = new HashMap<>();
    Map<String, Map<LocalDateTime, Map<LocalDateTime, ProviderAppointment>>> providerAppointments = new HashMap<>();
    

    /**
     * Reserve a patient appointment with this provider and appointment time
     * @param patientID A unique patient ID
     * @param providerID A unique provider ID
     * @param appointmentTime Appointment date and time
     */
    public void scheduleAppointment(String patientID, String providerID, LocalDateTime appointmentTime)
    {
        PatientAppointment patientAppointment = patientAppointments.get(patientID);
        if(patientAppointment!=null) return;
        
        LocalDateTime startOfDay = appointmentTime.with(LocalTime.MIN);;
        if(providerAppointments.get(providerID)==null) {
            return;
        }
        Map<LocalDateTime, Map<LocalDateTime, ProviderAppointment>> perProviderAppointments = providerAppointments.get(providerID);

        if(perProviderAppointments.get(startOfDay)==null) {
            return;
        }
        Map<LocalDateTime, ProviderAppointment> perDayAppointments =  perProviderAppointments.get(startOfDay);
        ProviderAppointment appointment = perDayAppointments.get(appointmentTime);
        if(appointment==null || appointment.patientID!=null) return;
        patientAppointments.put(patientID, new PatientAppointment(appointmentTime, providerID));
        appointment.patientID = patientID;
    }

    /**
     * Cancel an existing appointment for a patient. If this patient has no
     * appointment, do nothing.
     * @param patientID A unique patient ID
     */
    public void cancelAppointment(String patientID)
    {
    }

    /**
     * Get this patient's appointment information
     * @param patientID A unique patient ID
     * @return A PatientAppointment with this patient's appointment information, or
     *         null if this patient has no appointment reserved
     */
    public PatientAppointment getPatientAppointment(String patientID)
    {
        return null;
    }

    /**
     * Get open appointments on this day for patients to browse
     * @param day A calendar date
     * @return A mapping of appointment time to list of provider IDs
     *         indicating which providers have available appointments for each
     *         appointment time on this day
     */
    public Map<LocalDateTime, List<String>> getAvailableAppointments(LocalDate day)
    {
        LocalDateTime startOfday = day.atStartOfDay();
        Map<LocalDateTime, List<String>> result = new HashMap();
        for(String providerID: providerAppointments.keySet()){
            if(providerAppointments.get(providerID)==null) {
                return result;
            }
            Map<LocalDateTime, Map<LocalDateTime, ProviderAppointment>> perProviderAppointments = providerAppointments.get(providerID);
            if(perProviderAppointments.get(startOfday)==null) {
                return result;
            }
            Map<LocalDateTime, ProviderAppointment> perDayAppointments =  perProviderAppointments.get(startOfday);
            List<String> providers = new ArrayList();
            for(LocalDateTime appointmentTime: perDayAppointments.keySet()){
                ProviderAppointment appointment = perDayAppointments.get(appointmentTime);
                LocalDateTime time = appointment.appointmentTime;
                if(result.get(time)==null) result.put(time, new ArrayList());
                result.get(time).add(providerID);
            }
        }
        return result;
    }

    /**
     * Make a new appointment with this provider available
     * @param providerID A unique provider ID
     * @param appointmentTime Appointment date and time
     */
    public void addAppointment(String providerID, LocalDateTime appointmentTime)
    {
        LocalDateTime startOfDay = appointmentTime.with(LocalTime.MIN);
        if(providerAppointments.get(providerID)==null) {
            providerAppointments.put(providerID, new HashMap());
        }
        Map<LocalDateTime, Map<LocalDateTime, ProviderAppointment>> perProviderAppointments = providerAppointments.get(providerID);

        if(perProviderAppointments.get(startOfDay)==null) {
            perProviderAppointments.put(startOfDay, new HashMap());
        }
        Map<LocalDateTime, ProviderAppointment> perDayAppointments =  perProviderAppointments.get(startOfDay);
        ProviderAppointment appointment = perDayAppointments.get(appointmentTime);
        if(appointment!=null) return;
        perDayAppointments.put(appointmentTime, new ProviderAppointment(appointmentTime));
    }

    /**
     * Remove an available appointment for a provider at this time. If this
     * provider has no appointment at this time, do nothing.
     * @param providerID A unique provider ID
     * @param appointmentTime Appointment date and time
     */
    public void removeAppointment(String providerID, LocalDateTime appointmentTime)
    {
        LocalDateTime startOfDay = appointmentTime.with(LocalTime.MIN);
        if(providerAppointments.get(providerID)==null) {
            return;
        }
        Map<LocalDateTime, Map<LocalDateTime, ProviderAppointment>> perProviderAppointments = providerAppointments.get(providerID);

        if(perProviderAppointments.get(startOfDay)==null) {
            return;
        }
        Map<LocalDateTime, ProviderAppointment> perDayAppointments =  perProviderAppointments.get(startOfDay);
        ProviderAppointment appointment = perDayAppointments.get(appointmentTime);
        if(appointment==null) return;
        perDayAppointments.remove(appointmentTime);
    }

    /**
     * Get this provider's patient schedule for this day
     * @param providerID A unique provider ID
     * @param day A calendar date
     * @return A list of ProviderAppointment objects (containing appointment
     *         times and patient IDs), sorted by appointment time, which
     *         represents the patient schedule for this provider on this day
     */
    public AbstractCollection<ProviderAppointment> getProviderSchedule(String providerID, LocalDate day)
    {
        return null;
    }

}

public class Solution {
    public static void main(String[] args) {
        VaccineScheduler vc = new VaccineScheduler();
        LocalDateTime appointmentTime = LocalDateTime.now();
        
        LocalDate startOfDay = appointmentTime.toLocalDate();
        vc.addAppointment("provider1", appointmentTime);
        LocalDateTime appointmentTime2 = LocalDateTime.now();
        vc.addAppointment("provider2", appointmentTime);
        vc.addAppointment("provider3", appointmentTime2);
        //vc.removeAppointment("provider1", appointmentTime);
        vc.scheduleAppointment("patient1", "provider1", appointmentTime);
        vc.getAvailableAppointments(startOfDay);
        System.out.println("hello");
    }
}
