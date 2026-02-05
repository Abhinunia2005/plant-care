package com.plantcare.plantcare;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  //tell this class handles HTTP requests, return values of this calls are http responses
public class HellowController {

    private Map<Integer , LocalDate> lastWateredMap = new HashMap<>();
    private static final int WATERING_INTERVAL_DAYS = 2;
    

    @GetMapping("/hello")  //maps HTTP GET requests to / endpoint
    public String hello() {
        return "Hello, PlantCare is alive!";
        
    }

    @PostMapping("/hello")
    public String postHello(){
        return "Hello received via POST";
    }

    @GetMapping("/plants/{id}")

    public String getPlant(@PathVariable int id)
    {
        LocalDate date = lastWateredMap.get(id);
        if(date == null )
        {
            return "Plant " + id + " has never been watered ";
        }

        LocalDate nextWatering = date.plusDays(WATERING_INTERVAL_DAYS);


        
        return "Plant " + id + " Last watered on "+ date + " Next watering due on " + nextWatering;
    }

    @PostMapping("plants/{id}/water")
    public String waterPlant(@PathVariable int id)
    {
        lastWateredMap.put(id,LocalDate.now());

        return "Plant with ID " + id + " has been watered!";
    }

    @Scheduled(cron = "0 0 9 * * *")
    public void dailyWateringCheck(){

        LocalDate today = LocalDate.now();
        
        lastWateredMap.forEach((id, LastWateredDate)->{
            LocalDate nextWateringDate = LastWateredDate.plusDays(WATERING_INTERVAL_DAYS);

            if(!nextWateringDate.isAfter(today))
            {
                System.out.println("Reminder: Plant with ID " + id + " needs watering today!");
            }
        });


    }

  
}
