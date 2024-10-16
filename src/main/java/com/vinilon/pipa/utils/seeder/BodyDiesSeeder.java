package com.vinilon.pipa.utils.seeder;

import com.vinilon.pipa.model.BodyDies;
import com.vinilon.pipa.model.Machine;
import com.vinilon.pipa.model.enums.BodyDiesStatus;
import com.vinilon.pipa.repository.BodyDiesRepository;
import com.vinilon.pipa.repository.MachineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BodyDiesSeeder implements Seeder {

    private final BodyDiesRepository bodyDiesRepository;
    private final MachineRepository machineRepository;

    @Override
    public void seed() {
        if (bodyDiesRepository.count() == 0) {

            BodyDies bodyDies1 = BodyDies.builder()
                    .name("BodyDies A")
                    .bodyDiesStatus(BodyDiesStatus.READY)
                    .machines(Arrays.asList("Machine A","Machine B"))
                    .build();

            BodyDies bodyDies2 = BodyDies.builder()
                    .name("BodyDies B")
                    .bodyDiesStatus(BodyDiesStatus.NOT_READY)
                    .machines(Arrays.asList("Machine C","Machine D"))
                    .build();

            BodyDies bodyDies3 = BodyDies.builder()
                    .name("BodyDies C")
                    .bodyDiesStatus(BodyDiesStatus.NOT_READY)
                    .machines(Arrays.asList("Machine E","Machine F"))
                    .build();

            BodyDies bodyDies4 = BodyDies.builder()
                    .name("BodyDies D")
                    .bodyDiesStatus(BodyDiesStatus.READY)
                    .machines(Arrays.asList("Machine G","Machine H"))
                    .build();

            BodyDies bodyDies5 = BodyDies.builder()
                    .name("BodyDies E")
                    .bodyDiesStatus(BodyDiesStatus.READY)
                    .machines(Arrays.asList("Machine I","Machine J"))
                    .build();

            BodyDies bodyDies6 = BodyDies.builder()
                    .name("BodyDies F")
                    .bodyDiesStatus(BodyDiesStatus.NOT_READY)
                    .machines(Arrays.asList("Machine A","Machine B"))
                    .build();

            BodyDies bodyDies7 = BodyDies.builder()
                    .name("BodyDies G")
                    .bodyDiesStatus(BodyDiesStatus.NOT_READY)
                    .machines(Arrays.asList("Machine C","Machine D"))
                    .build();

            BodyDies bodyDies8 = BodyDies.builder()
                    .name("BodyDies H")
                    .bodyDiesStatus(BodyDiesStatus.READY)
                    .machines(Arrays.asList("Machine E","Machine F"))
                    .build();

            BodyDies bodyDies9 = BodyDies.builder()
                    .name("BodyDies I")
                    .bodyDiesStatus(BodyDiesStatus.READY)
                    .machines(Arrays.asList("Machine G","Machine H"))
                    .build();

            BodyDies bodyDies10 = BodyDies.builder()
                    .name("BodyDies J")
                    .bodyDiesStatus(BodyDiesStatus.READY)
                    .machines(Arrays.asList("Machine I","Machine J"))
                    .build();

            bodyDiesRepository.saveAll(Arrays.asList(
                    bodyDies1, bodyDies2, bodyDies3, bodyDies4, bodyDies5,
                    bodyDies6, bodyDies7, bodyDies8, bodyDies9, bodyDies10
            ));
        }
    }
}

