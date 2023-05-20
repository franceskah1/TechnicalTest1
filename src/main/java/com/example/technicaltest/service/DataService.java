package com.example.technicaltest.service;
import com.example.technicaltest.exception.NotFoundException;
import com.example.technicaltest.model.Data;
import com.example.technicaltest.repository.DataRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
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

    public void saveData(Data data){
            Data data1=new Data();
            data1.setId(data.getId());
            data1.setTitle(data.getTitle());
            data1.setQty(data.getQty());
            data1.setDate1(data.getDate1());
            dataRepository.save(data1);

    }
    public void updateData(Long id,Data data) {
        Data data2 = dataRepository.findById(id).orElseThrow(() -> new NotFoundException("Data with id: " + id + " not found!"));
        data2.setDate1(data.getDate1());
        data2.setTitle(data.getTitle());
        data2.setQty(data.getQty());
        dataRepository.save(data);
    }
//increment the "qty" field by one, every fifth minute of every hour
    @Scheduled(cron = "0 */5 * * * *") // Run every 5 minutes
    public void incrementQty() {
        Data data = dataRepository.findById(1L).get();
        data.setQty(data.getQty() + 1);
        dataRepository.save(data);
    }
    }