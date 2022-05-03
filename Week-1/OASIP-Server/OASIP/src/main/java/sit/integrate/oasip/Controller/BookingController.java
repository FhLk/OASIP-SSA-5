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
//@CrossOrigin(origins = "http://10.4.56.111",
//        allowedHeaders = "*",
//        methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT},
//        allowCredentials = "true")
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
    public EventBooking AddBooking(@RequestBody BookingDTO newBooking){
        return service.CreateBooking(newBooking);
    }

    @PutMapping("/{BookingId}")
    public EventBooking update(@PathVariable Integer BookingId,@RequestBody BookingDTO updateBooking){
        return service.updateBooking(BookingId,updateBooking);
    }


}
