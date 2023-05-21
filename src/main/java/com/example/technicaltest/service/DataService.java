package com.example.technicaltest.service;
import com.example.technicaltest.exception.NotFoundException;
import com.example.technicaltest.model.Data;
import com.example.technicaltest.repository.DataRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DataService {
    private final DataRepository dataRepository;

    public DataService(DataRepository dataRepository) {

        this.dataRepository = dataRepository;
    }




    public boolean deleteDataById(Long id) {
        Optional<Data> dataOptional = dataRepository.findById(id);
        if (dataOptional.isPresent()) {
            dataRepository.delete(dataOptional.get());
            return true;
        }
        return false;
    }


    public Optional<Data> getDataById(String id) {
        long parseId;
        try {
            parseId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Data id: \"" + id + "\" can't be parsed!");
    }
        return dataRepository.findById(parseId);
}


    public Data saveData(Data data) {
        Data data1 = new Data();
        data1.setId(data.getId());
        data1.setTitle(data.getTitle());
        data1.setQty(data.getQty());
        data1.setDate1(data.getDate1());
      return   dataRepository.save(data1);

    }


        public Optional<?> updateData1(Long id, Data updatedData) {
            Optional<Data> dataOptional = dataRepository.findById(id);
            if (dataOptional.isPresent()) {
                Data data = dataOptional.get();
                // Update the fields
                data.setTitle(updatedData.getTitle());
                data.setDate1(updatedData.getDate1());
                data.setQty(updatedData.getQty());
                // Save the updated data
                dataRepository.save(data);
            }
            return dataOptional;
}

    //increment the "qty" field by one, every fifth minute of every hour
    @Scheduled(cron = "0 */5 * * * *")// Run every 5 minutes
    //@Scheduled(cron = "cron0 0 8-10 * * *")
    public ResponseEntity<?> incrementQty() {
        try {
            List<Data> data = dataRepository.findAll();//Get all records in database
            List<Data> dataList = new ArrayList<>();
            for (Data record : data) {
                int qty = record.getQty() + 1;   // Update quantity increasing +1
                record.setQty(qty);
                dataList.add(record);
            }
            dataRepository.saveAll(dataList);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);

        }
    }

}