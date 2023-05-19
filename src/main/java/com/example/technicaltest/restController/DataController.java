package com.example.technicaltest.restController;
import com.example.technicaltest.model.Data;
import com.example.technicaltest.service.DataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/data")
public class DataController {
    private final DataService dataService;

    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @PostMapping
    public ResponseEntity<?> insertData(@RequestBody Data data){
        return dataService.saveData(data);

    }


    @GetMapping("/{id}")
    public Data findDataById(@PathVariable String id){
        return dataService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteDataById(@PathVariable("id") String id){
       dataService.deleteDataById(id);
    }

    @PutMapping( "/{id}")
    public ResponseEntity<?> updateDataById(@PathVariable("id") Long id,@RequestBody Data data) {
        return dataService.updateData(id, data);
    }

    }
