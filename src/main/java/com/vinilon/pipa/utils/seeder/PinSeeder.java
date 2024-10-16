package com.vinilon.pipa.utils.seeder;

import com.vinilon.pipa.model.BodyDies;
import com.vinilon.pipa.model.Pin;
import com.vinilon.pipa.model.enums.Status;
import com.vinilon.pipa.repository.BodyDiesRepository;
import com.vinilon.pipa.repository.PinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PinSeeder implements Seeder {

    private final PinRepository pinRepository;

    @Override
    public void seed() {
        if (pinRepository.count() == 0) {

            Pin pin1 = Pin.builder()
                    .name("Pin A")
                    .pinStatus(Status.STAND_BY)
                    .bodyDies(Arrays.asList("BodyDies A", "BodyDies B"))
                    .build();

            Pin pin2 = Pin.builder()
                    .name("Pin B")
                    .pinStatus(Status.MAINTENANCE)
                    .bodyDies(Arrays.asList("BodyDies B", "BodyDies C"))
                    .build();

            Pin pin3 = Pin.builder()
                    .name("Pin C")
                    .pinStatus(Status.PRODUCTION)
                    .bodyDies(Arrays.asList("BodyDies D", "BodyDies E"))
                    .build();

            Pin pin4 = Pin.builder()
                    .name("Pin D")
                    .pinStatus(Status.STAND_BY)
                    .bodyDies(Arrays.asList("BodyDies F", "BodyDies G"))
                    .build();

            Pin pin5 = Pin.builder()
                    .name("Pin E")
                    .pinStatus(Status.STAND_BY)
                    .bodyDies(Arrays.asList("BodyDies H", "BodyDies I"))
                    .build();

            Pin pin6 = Pin.builder()
                    .name("Pin F")
                    .pinStatus(Status.PRODUCTION)
                    .bodyDies(Arrays.asList("BodyDies A", "BodyDies J"))
                    .build();

            Pin pin7 = Pin.builder()
                    .name("Pin G")
                    .pinStatus(Status.PRODUCTION)
                    .bodyDies(Arrays.asList("BodyDies I", "BodyDies C"))
                    .build();

            Pin pin8 = Pin.builder()
                    .name("Pin H")
                    .pinStatus(Status.STAND_BY)
                    .bodyDies(Arrays.asList("BodyDies E", "BodyDies F"))
                    .build();

            Pin pin9 = Pin.builder()
                    .name("Pin I")
                    .pinStatus(Status.STAND_BY)
                    .bodyDies(Arrays.asList("BodyDies G", "BodyDies J"))
                    .build();

            Pin pin10 = Pin.builder()
                    .name("Pin J")
                    .pinStatus(Status.STAND_BY)
                    .bodyDies(Arrays.asList("BodyDies D", "BodyDies F"))
                    .build();

            pinRepository.saveAll(Arrays.asList(
                    pin1, pin2, pin3, pin4, pin5,
                    pin6, pin7, pin8, pin9, pin10
            ));
        }
    }
}


