package com.example.technicaltest.service;
import com.example.technicaltest.model.Data;
import com.example.technicaltest.repository.DataRepository;
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
            }catch (NumberFormatException e){
                throw new NumberFormatException("Data id: \"" + id + "\" can't be parsed!");
            }
            dataRepository.deleteById(parseId);
        }


    public Data findById(String id){
        long parseId;
        try {
            parseId = Long.parseLong(id);
        }catch (NumberFormatException e){
            throw new NumberFormatException("Data id: \"" + id + "\" can't be parsed!");
        }

        return dataRepository.findById(parseId).get();
    }

    public void saveData(Data data){
        Data data1=new Data();
        data1.setId(data.getId());
        data1.setTitle(data.getTitle());
        data1.setQty(data.getQty());
        data1.setDate1(data.getDate1());
        dataRepository.save(data1);

    }

}
