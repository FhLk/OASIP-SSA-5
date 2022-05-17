package sit.integrate.oasip.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sit.integrate.oasip.DTO.BookingDTO;
import sit.integrate.oasip.DTO.CategoryDTO;
import sit.integrate.oasip.DTO.SortListDayDTO;
import sit.integrate.oasip.Service.BookingService;

import javax.validation.Valid;
import java.time.LocalDateTime;
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
    public ResponseEntity<BookingDTO> getBooking(@PathVariable Integer BookingId){
        return ResponseEntity.ok(service.getBookingId(BookingId));
    }

    @GetMapping("/sortByCategory")
    public ResponseEntity<List<SortListDayDTO>> getBookingByCategory(@RequestParam CategoryDTO category){
        return ResponseEntity.ok(service.getBookingCategory(category));
    }

    @GetMapping("/sortByPast")
    public ResponseEntity<List<BookingDTO>> getAllBookingByPast(){
        return ResponseEntity.ok(service.getBookingSortPast(LocalDateTime.now()));
    }

    @GetMapping("/sortBySpecify")
    public ResponseEntity<List<BookingDTO>> getBookingBySpecify(@RequestParam String startDate,@RequestParam String endDate){
        return ResponseEntity.ok(service.getBookingWithSpecify(startDate,endDate));
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BookingDTO> AddBooking(@Valid @RequestBody BookingDTO newBooking){
        service.CreateBooking(newBooking);
        return new ResponseEntity<>(newBooking,HttpStatus.CREATED);
    }

    @PutMapping("/{BookingId}")
    public ResponseEntity<BookingDTO> update(@PathVariable Integer BookingId,@Valid @RequestBody BookingDTO updateBooking){
        ResponseEntity<BookingDTO> test = getBooking(BookingId);
        if(test.getBody().getBookingName()!=updateBooking.getBookingName()){
            return new ResponseEntity<>(updateBooking,HttpStatus.BAD_GATEWAY);
        }
        service.UpdateBooking(BookingId,updateBooking);
        return new ResponseEntity<>(updateBooking,HttpStatus.OK);
    }

    @DeleteMapping("/{bookingId}")
    public void deleteBooking(@PathVariable Integer bookingId){
        service.DeleteBooking(bookingId);
    }
}
