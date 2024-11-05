package CarmineGargiulo.FS0624_Unit5_Week3_Day2.controllers;

import CarmineGargiulo.FS0624_Unit5_Week3_Day2.dto.BookingDTO;
import CarmineGargiulo.FS0624_Unit5_Week3_Day2.entities.Booking;
import CarmineGargiulo.FS0624_Unit5_Week3_Day2.entities.Employee;
import CarmineGargiulo.FS0624_Unit5_Week3_Day2.exceptions.BadRequestException;
import CarmineGargiulo.FS0624_Unit5_Week3_Day2.services.BookingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bookings")
public class BookingsController {
    @Autowired
    private BookingsService bookingsService;


    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Booking> getAllBookings(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size,
                                        @RequestParam(defaultValue = "trip.date") String sortBy,
                                        @RequestParam(required = false) UUID employeeId) {
        return bookingsService.findAllBookings(page, size, sortBy, employeeId);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Booking saveBooking(@RequestBody @Validated BookingDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String message =
                    bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", "));
            throw new BadRequestException(message);
        }
        return bookingsService.saveBooking(body);
    }

    @GetMapping("/{bookingId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Booking getSingleBooking(@PathVariable UUID bookingId) {
        return bookingsService.findBookingById(bookingId);
    }

    @DeleteMapping("/{bookingId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBooking(@PathVariable UUID bookingId) {
        bookingsService.findBookingByIdAndDelete(bookingId);
    }

    @GetMapping("/mybookings")
    public Page<Booking> getBookingsByCurrentEmployee(@AuthenticationPrincipal Employee current,
                                                      @RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size,
                                                      @RequestParam(defaultValue = "trip.date") String sortBy) {
        return bookingsService.findAllBookings(page, size, sortBy, current.getEmployeeId());
    }
}
