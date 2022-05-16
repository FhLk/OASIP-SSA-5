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
    public ResponseEntity<List<BookingDTO>> getAllBooking(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "startTime") String sort){
        return ResponseEntity.ok(service.getBookings(page,pageSize,sort));
    }

    @GetMapping("/{BookingId}")
    public ResponseEntity<BookingDTO> getAllBooking(@PathVariable Integer BookingId){
        return ResponseEntity.ok(service.getBookingId(BookingId));
    }

    @GetMapping("/sortBypast")
    public ResponseEntity<List<BookingDTO>> getAllBookingByPast(){
        return ResponseEntity.ok(service.getBookingSortPast(LocalDateTime.now()));
    }

    @GetMapping("/sortByupcomming")
    public ResponseEntity<List<BookingDTO>> getAllBookingByUpcomming(){
        return ResponseEntity.ok(service.getBookingSortUpcomming(LocalDateTime.now()));
    }

    @GetMapping("/sortBySpecify")
    public ResponseEntity<List<BookingDTO>> getBookingBySpecify(@RequestParam String startdate,@RequestParam String enddate){
        return ResponseEntity.ok(service.getBookingWithSpecify(startdate,enddate));
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BookingDTO> AddBooking(@Valid @RequestBody BookingDTO newBooking){
        service.CreateBooking(newBooking);
        return new ResponseEntity<>(newBooking,HttpStatus.CREATED);
    }

    @PutMapping("/{BookingId}")
    public ResponseEntity<BookingDTO> update(@PathVariable Integer BookingId,@Valid @RequestBody BookingDTO updateBooking){
        service.updateBooking(BookingId,updateBooking);
        return new ResponseEntity<>(updateBooking,HttpStatus.OK);
    }

    @DeleteMapping("/{bookingId}")
    public void deleteBooking(@PathVariable Integer bookingId){
        service.CancelBooking(bookingId);
    }
}
