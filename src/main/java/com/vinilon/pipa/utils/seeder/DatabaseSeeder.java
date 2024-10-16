package com.vinilon.pipa.utils.seeder;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "app.db.run-seeders", havingValue = "true")
public class DatabaseSeeder implements CommandLineRunner {

    private final MachineSeeder machineSeeder;
    private final BodyDiesSeeder bodyDiesSeeder;
    private final PinSeeder pinSeeder;
    private final CalibratorSeeder calibratorSeeder;

    @Override
    public void run(String... args) {
        machineSeeder.seed();
        bodyDiesSeeder.seed();
        pinSeeder.seed();
        calibratorSeeder.seed();
    }
}
