package com.example.technicaltest.restController;
import com.example.technicaltest.model.Data;
import com.example.technicaltest.service.DataService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/data")
public class DataController {
    private final DataService dataService;

    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @PostMapping
    public void insertData(@RequestBody Data data) {
        dataService.saveData(data);
    }
    @GetMapping("/{id}")
    public Data findCenterById(@PathVariable String id){
        return dataService.findById(id);
    }


    }
