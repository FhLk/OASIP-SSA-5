package sit.integrate.oasip.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sit.integrate.oasip.DTO.BookingDTO;
import sit.integrate.oasip.Entity.EventBooking;
import sit.integrate.oasip.Service.BookingService;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    @Autowired
    private BookingService service;

    @GetMapping("")
    public List<BookingDTO> getAllBooking(){
        return service.getBookings();
    }

    @GetMapping("/{BookingId}")
    public BookingDTO getAllBooking(@PathVariable Integer BookingId){
        return service.getBookingId(BookingId);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public BookingDTO AddBooking(@RequestBody BookingDTO newBooking){
        service.CreateBooking(newBooking);
        return newBooking;
    }

    @PutMapping("/{BookingId}")
    public BookingDTO update(@PathVariable Integer BookingId,@RequestBody BookingDTO updateBooking){
        service.updateBooking(BookingId,updateBooking);
        return updateBooking;
    }

    @DeleteMapping("/{bookingId}")
    public void deleteBooking(@PathVariable Integer bookingId){
        service.CancelBooking(bookingId);
    }
}
