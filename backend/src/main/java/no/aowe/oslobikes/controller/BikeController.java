package no.aowe.oslobikes.controller;

import no.aowe.oslobikes.domain.BikeResponse;
import no.aowe.oslobikes.domain.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("api")
public class BikeController {

    @Autowired
    private WebClient webClient;

    @GetMapping("bikes")
    public List<Station> getBikes() {
       return Stream.of(getStationStatus(), getStationInformation())
               .flatMap(Collection::stream)
               .collect(Collectors.toList());
    }

    public List<Station> getStationInformation() {
        return getFromService("/station_information.json");
    }

    public List<Station> getStationStatus() {
        return getFromService("/station_status.json");
    }

    private List<Station> getFromService(final String serviceUri) {
        BikeResponse bikeResponse = webClient.get()
                .uri(serviceUri)
                .retrieve()
                .bodyToMono(BikeResponse.class)
                .block();

        if (bikeResponse == null
                || bikeResponse.getData() == null
                || bikeResponse.getData().getStations() == null) {
            return Collections.emptyList();
        }

        return bikeResponse.getData().getStations();
    }
}
