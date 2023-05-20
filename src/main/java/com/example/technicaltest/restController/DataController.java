package com.example.technicaltest.restController;
import com.example.technicaltest.exception.NotFoundException;
import com.example.technicaltest.model.Data;
import com.example.technicaltest.repository.DataRepository;
import com.example.technicaltest.service.DataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/data")
public class DataController {
    private final DataService dataService;
    public DataController(DataService dataService, DataRepository dataRepository) {
        this.dataService = dataService;

    }

    @PostMapping
    public ResponseEntity<?> insertData(@RequestBody Data data){
        try {
            dataService.saveData(data);
            return new ResponseEntity<>("Data inserted successfully!",HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

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
    public ResponseEntity<?> updateById( @PathVariable Long id,@RequestBody Data data) {
        try {
            dataService.updateData(id,data);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch  (NotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping
    public ResponseEntity<?> incrementQty(){
        try {
            dataService.incrementQty();;
            return new ResponseEntity<>(HttpStatus.OK);
        }  catch (Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }

    }
}

    
