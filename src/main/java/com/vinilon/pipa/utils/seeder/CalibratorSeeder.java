package com.vinilon.pipa.utils.seeder;

import com.vinilon.pipa.model.Calibrator;
import com.vinilon.pipa.model.enums.Status;
import com.vinilon.pipa.repository.CalibratorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class CalibratorSeeder implements Seeder {

    private final CalibratorRepository calibratorRepository;

    @Override
    public void seed() {
        if (calibratorRepository.count() == 0) {
            Calibrator calibrator1 = Calibrator.builder()
                    .name("Calibrator A")
                    .calibratorStatus(Status.STAND_BY)
                    .thicknessMin(1)
                    .thicknessMax(5)
                    .build();

            Calibrator calibrator2 = Calibrator.builder()
                    .name("Calibrator B")
                    .calibratorStatus(Status.PRODUCTION)
                    .thicknessMin(2)
                    .thicknessMax(6)
                    .build();

            Calibrator calibrator3 = Calibrator.builder()
                    .name("Calibrator C")
                    .calibratorStatus(Status.STAND_BY)
                    .thicknessMin(3)
                    .thicknessMax(7)
                    .build();

            Calibrator calibrator4 = Calibrator.builder()
                    .name("Calibrator D")
                    .calibratorStatus(Status.PRODUCTION)
                    .thicknessMin(4)
                    .thicknessMax(8)
                    .build();

            Calibrator calibrator5 = Calibrator.builder()
                    .name("Calibrator E")
                    .calibratorStatus(Status.STAND_BY)
                    .thicknessMin(5)
                    .thicknessMax(9)
                    .build();

            Calibrator calibrator6 = Calibrator.builder()
                    .name("Calibrator F")
                    .calibratorStatus(Status.MAINTENANCE)
                    .thicknessMin(6)
                    .thicknessMax(10)
                    .build();

            Calibrator calibrator7 = Calibrator.builder()
                    .name("Calibrator G")
                    .calibratorStatus(Status.STAND_BY)
                    .thicknessMin(7)
                    .thicknessMax(11)
                    .build();

            Calibrator calibrator8 = Calibrator.builder()
                    .name("Calibrator H")
                    .calibratorStatus(Status.PRODUCTION)
                    .thicknessMin(8)
                    .thicknessMax(12)
                    .build();

            Calibrator calibrator9 = Calibrator.builder()
                    .name("Calibrator I")
                    .calibratorStatus(Status.STAND_BY)
                    .thicknessMin(9)
                    .thicknessMax(13)
                    .build();

            Calibrator calibrator10 = Calibrator.builder()
                    .name("Calibrator J")
                    .calibratorStatus(Status.PRODUCTION)
                    .thicknessMin(10)
                    .thicknessMax(14)
                    .build();

            calibratorRepository.saveAll(Arrays.asList(
                    calibrator1, calibrator2, calibrator3, calibrator4, calibrator5,
                    calibrator6, calibrator7, calibrator8, calibrator9, calibrator10
            ));
        }
    }
}
