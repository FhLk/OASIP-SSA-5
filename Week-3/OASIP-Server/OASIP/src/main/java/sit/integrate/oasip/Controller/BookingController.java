package sit.integrate.oasip.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sit.integrate.oasip.DTO.BookingDTO;
import sit.integrate.oasip.Service.BookingService;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://localhost:3000/",allowedHeaders = "*",allowCredentials = "true",
        methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
public class BookingController {
    @Autowired
    private BookingService service;

    @GetMapping("")
    public List<BookingDTO> getAllBooking(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "bookingName") String sortBy){
        return service.getBookings(page,pageSize,sortBy);
    }

    @GetMapping("/{BookingId}")
    public BookingDTO getAllBooking(@PathVariable Integer BookingId){
        return service.getBookingId(BookingId);
    }

    @GetMapping("/sortBy/{field}")
    public List<BookingDTO> getBookingWithSort(@PathVariable String field){
        return service.getBookingWithSorting(field);
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
