package no.aowe.oslobikes.logic;

import no.aowe.oslobikes.domain.BikeResponse;
import no.aowe.oslobikes.domain.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class BikeImpl {

    @Autowired
    private WebClient webClient;

    public List<Station> getBikes() {
        Map<String, Station> stationStatusMap = getStationStatus().stream()
                .collect(Collectors.toMap(Station::getStation_id, station -> station));

        return getStationInformation()
                .stream()
                .map(station -> {
                    Station status = stationStatusMap.get(station.getStation_id());
                    if (status != null) {
                        station.setNum_bikes_available(status.getNum_bikes_available());
                        station.setNum_docks_available(status.getNum_docks_available());
                    }
                    return station;
                }).collect(Collectors.toList());
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
