package com.copmap.tracking;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tracking")
public class TrackingController {

    private final TrackingService trackingService;

    public TrackingController(TrackingService trackingService) {
        this.trackingService = trackingService;
    }

    @PostMapping("/location")
    public void updateLocation(@RequestBody OfficerLocation location) {
        trackingService.saveLocation(location);
    }
}
