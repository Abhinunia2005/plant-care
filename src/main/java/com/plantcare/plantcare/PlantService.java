package com.plantcare.plantcare;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class PlantService {
        private static final int WATERING_INTERVAL_DAYS = 2;


        private Map<Integer , LocalDate> lastWateredMap = new HashMap<>();
    
    public String waterPlant(int id)
    {
        lastWateredMap.put(id,LocalDate.now());
         return "Plant with ID " + id + " has been watered!";

    }

    public LocalDate getLastWatered(int id)
    {
        return lastWateredMap.get(id);
    }


   public String getPlantStatus(@PathVariable int id)
    {
        LocalDate date = getLastWatered(id);
        if(date == null )
        {
            return "Plant " + id + " has never been watered ";
        }

        LocalDate nextWatering = date.plusDays(WATERING_INTERVAL_DAYS);


        
        return "Plant " + id + " Last watered on "+ date + " Next watering due on " + nextWatering;
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
