package com.vinilon.pipa.utils.seeder;

import com.vinilon.pipa.model.Machine;
import com.vinilon.pipa.model.enums.Status;
import com.vinilon.pipa.repository.MachineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MachineSeeder implements Seeder {
    private final MachineRepository machineRepository;

    @Override
    public void seed() {
        if (machineRepository.count() == 0) {
            List<Machine> machines = Arrays.asList(
                    Machine.builder()
                            .name("Machine A")
                            .machineStatus(Status.STAND_BY)
                            .diameterMin(50)
                            .diameterMax(250)
                            .build(),
                    Machine.builder()
                            .name("Machine B")
                            .machineStatus(Status.STAND_BY)
                            .diameterMin(300)
                            .diameterMax(1200)
                            .build(),
                    Machine.builder()
                            .name("Machine C")
                            .machineStatus(Status.PRODUCTION)
                            .diameterMin(70)
                            .diameterMax(200)
                            .build(),
                    Machine.builder()
                            .name("Machine D")
                            .machineStatus(Status.MAINTENANCE)
                            .diameterMin(400)
                            .diameterMax(1880)
                            .build(),
                    Machine.builder()
                            .name("Machine E")
                            .machineStatus(Status.STAND_BY)
                            .diameterMin(60)
                            .diameterMax(220)
                            .build(),
                    Machine.builder()
                            .name("Machine F")
                            .machineStatus(Status.MAINTENANCE)
                            .diameterMin(45)
                            .diameterMax(1600)
                            .build(),
                    Machine.builder()
                            .name("Machine G")
                            .machineStatus(Status.STAND_BY)
                            .diameterMin(550)
                            .diameterMax(1700)
                            .build(),
                    Machine.builder()
                            .name("Machine H")
                            .machineStatus(Status.MAINTENANCE)
                            .diameterMin(350)
                            .diameterMax(1300)
                            .build(),
                    Machine.builder()
                            .name("Machine I")
                            .machineStatus(Status.STAND_BY)
                            .diameterMin(65)
                            .diameterMax(190)
                            .build(),
                    Machine.builder()
                            .name("Machine J")
                            .machineStatus(Status.PRODUCTION)
                            .diameterMin(25)
                            .diameterMax(110)
                            .build()
            );

            machineRepository.saveAll(machines);
        }
    }
}
