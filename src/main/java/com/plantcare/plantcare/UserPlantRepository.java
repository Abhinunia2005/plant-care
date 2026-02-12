package com.plantcare.plantcare;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class UserPlantRepository {
    private Map<Integer, LocalDate> storage = new HashMap<>();

    public void setWater(int plantId , LocalDate date)
    {
         storage.put(plantId,date);

    }

    public LocalDate findLastWatered(int plantId)
    {
        return storage.get(plantId);
    }
    
    public Map<Integer,LocalDate> findAll()
    {
        return storage;
    }
}
