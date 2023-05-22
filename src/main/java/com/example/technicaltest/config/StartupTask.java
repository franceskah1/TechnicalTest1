package com.example.technicaltest.config;
import com.example.technicaltest.service.DataService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class StartupTask implements ApplicationRunner {
    private final DataService dataService;

   // is used to execute incrementQty method when the application starts up
    @Override
    public void run(ApplicationArguments args) throws Exception {
        dataService.incrementQty();

    }
}