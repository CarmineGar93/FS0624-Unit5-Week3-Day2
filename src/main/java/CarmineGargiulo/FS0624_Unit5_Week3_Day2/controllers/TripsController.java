package CarmineGargiulo.FS0624_Unit5_Week3_Day2.controllers;

import CarmineGargiulo.FS0624_Unit5_Week3_Day2.dto.StatusDTO;
import CarmineGargiulo.FS0624_Unit5_Week3_Day2.dto.TripDTO;
import CarmineGargiulo.FS0624_Unit5_Week3_Day2.entities.Trip;
import CarmineGargiulo.FS0624_Unit5_Week3_Day2.exceptions.BadRequestException;
import CarmineGargiulo.FS0624_Unit5_Week3_Day2.services.TripsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/trips")
public class TripsController {
    @Autowired
    private TripsService tripsService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Trip> getAllTrips(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "date") String sortBy) {
        return tripsService.findAllTrips(page, size, sortBy);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Trip saveTrip(@RequestBody @Validated TripDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", ")));
        return tripsService.saveTrip(body);
    }

    @PatchMapping("/{tripId}/status")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Trip modifyStatus(@PathVariable UUID tripId, @RequestBody @Validated StatusDTO body,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", ")));
        return tripsService.findByIdAndUpdateStatus(tripId, body);
    }

    @GetMapping("/{tripId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Trip getSingleTrip(@PathVariable UUID tripId) {
        return tripsService.findTripById(tripId);
    }

    @PutMapping("/{tripId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Trip modifyTrip(@PathVariable UUID tripId, @RequestBody @Validated TripDTO body,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", ")));
        return tripsService.findTripByIdAndUpdate(tripId, body);
    }

    @DeleteMapping("/{tripId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrip(@PathVariable UUID tripId) {
        tripsService.findTripByIdAndDelete(tripId);
    }

}
