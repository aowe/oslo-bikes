package no.aowe.oslobikes.controller;

import no.aowe.oslobikes.domain.Station;
import no.aowe.oslobikes.logic.BikeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class BikeController {

    @Autowired
    private BikeImpl bikeImpl;

    @GetMapping("bikes")
    public List<Station> getBikes() {
        return bikeImpl.getBikes();
    }
}
