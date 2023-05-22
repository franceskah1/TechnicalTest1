package com.example.technicaltest.restController;
import com.example.technicaltest.exception.NotFoundException;
import com.example.technicaltest.model.Data;
import com.example.technicaltest.repository.DataRepository;
import com.example.technicaltest.service.DataService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1/data")
public class DataController {
    private final DataService dataService;
    public DataController(DataService dataService) {
        this.dataService = dataService;

    }

    @PostMapping
    public ResponseEntity<?> insertData(@RequestBody Data data) {
        try {
            dataService.saveData(data);
            return new ResponseEntity<>("Data inserted successfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }


        @PutMapping( "/{id}")
        public ResponseEntity<?> updateById( @PathVariable Long id,@RequestBody Data data) {
            try {
                Optional<?> updatedDataOptional = dataService.updateData1(id, data);
                return updatedDataOptional
                        .map(ResponseEntity::ok)
                        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
            }

        }
        @GetMapping("/{id}")
        public ResponseEntity<Data> getDataById(@PathVariable String id) {
            Optional<Data> dataOptional = dataService.getDataById(id);
            return dataOptional
                    .map(ResponseEntity::ok)
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDataById(@PathVariable Long id) {
        boolean isDeleted = dataService.deleteDataById(id);
        if (isDeleted) {
            return ResponseEntity.ok("Data deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    @GetMapping
//    public ResponseEntity<?> incrementQty(){
//        try {
//            dataService.incrementQty();;
//            return new ResponseEntity<>(HttpStatus.OK);
//        }  catch (Exception e) {
//        e.printStackTrace();
//        return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
//    }
//
//    }
}

    
