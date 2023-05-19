package com.example.technicaltest.service;
import com.example.technicaltest.exception.NotFoundException;
import com.example.technicaltest.model.Data;
import com.example.technicaltest.repository.DataRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DataService {
    private final DataRepository dataRepository;

    public DataService(DataRepository dataRepository) {

        this.dataRepository = dataRepository;
    }


        public void deleteDataById(String id){
            long parseId;
            try {
                parseId = Long.parseLong(id);
                dataRepository.deleteById(parseId);
            }catch (NumberFormatException e){
                throw new NumberFormatException("Data id: \"" + id + "\" can't be parsed!");
            }catch (EmptyResultDataAccessException e){
                throw new NotFoundException("Data with id: " + id + " doesn't exist!");
            }

        }


    public Data findById(String id){
        long parseId;
        try {
            parseId = Long.parseLong(id);
        }catch (NumberFormatException e) {
            throw new NumberFormatException("Data id: \"" + id + "\" can't be parsed!");
        }

        return dataRepository.findById(parseId).orElseThrow(()->new NotFoundException("Data with id: " + id + " not found!"));
    }

    public ResponseEntity<?> saveData(Data data){
        try {
            Data data1=new Data();
            data1.setId(data.getId());
            data1.setTitle(data.getTitle());
            data1.setQty(data.getQty());
            data1.setDate1(data.getDate1());
            dataRepository.save(data1);
          return   new ResponseEntity<>("Data register successfully!",HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
           return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    public ResponseEntity<?> updateData(Long id,Data data) {

        Data data2 = dataRepository.findById(id).orElseThrow(() -> new NotFoundException("Data with id: " + id + " not found!"));
        data2.setDate1(data.getDate1());
        data2.setTitle(data.getTitle());
        data2.setQty(data.getQty());
        return new ResponseEntity<>("Data register successfully!", HttpStatus.OK);
    }
    }