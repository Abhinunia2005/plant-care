package com.plantcare.plantcare;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  //tell this class handles HTTP requests, return values of this calls are http responses
    public class HellowController {

        private final PlantService plantService;

        public HellowController(PlantService plantService)
        {
            this.plantService = plantService;
        }    

    @GetMapping("/plants/{id}")

    public String getPlant(@PathVariable int id)
    {
           return plantService.getPlantStatus(id);
    }

    @PostMapping("plants/{id}/water")
    public String waterPlant(@PathVariable int id)
    {
        return plantService.waterPlant(id);
    }



  
}
