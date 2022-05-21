package sit.integrate.oasip.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sit.integrate.oasip.DTO.BookingDTO;
import sit.integrate.oasip.DTO.SortListDayDTO;
import sit.integrate.oasip.Entity.EventCategory;
import sit.integrate.oasip.Service.BookingService;
import sit.integrate.oasip.exeption.BookingNotFoundException;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bookings")
//@CrossOrigin(origins = "http://localhost:3000/",allowedHeaders = "*",allowCredentials = "true",
//         methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
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
    public ResponseEntity<List<BookingDTO>> getBookingByCategory(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam EventCategory category){
        return ResponseEntity.ok(service.getBookingCategory(page,pageSize,category));
    }

    @GetMapping("/sortByPast")
    public ResponseEntity<List<BookingDTO>> getAllBookingByPast(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int pageSize
    ){
        return ResponseEntity.ok(service.getBookingSortPast(page,pageSize,LocalDateTime.now()));
    }

    @GetMapping("/sortBySpecify")
    public ResponseEntity<List<BookingDTO>> getBookingBySpecify(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam String date){
        return ResponseEntity.ok(service.getBookingWithSpecify(
                page,
                pageSize,
                date+"T00:00",
                date+"T23:59"
        ));
    }


    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BookingDTO> AddBooking(@Valid @RequestBody BookingDTO newBooking){
        newBooking.setBookingName(newBooking.getBookingName().trim());
        newBooking.setBookingEmail(newBooking.getBookingEmail().trim());
        newBooking.setEventNote(newBooking.getEventNote().trim());
        service.CreateBooking(newBooking);
        return new ResponseEntity<>(newBooking,HttpStatus.CREATED);
    }

    @PutMapping("/{BookingId}")
    public ResponseEntity<BookingDTO> update(@PathVariable Integer BookingId,@Valid @RequestBody BookingDTO updateBooking) throws BookingNotFoundException {
        ResponseEntity<BookingDTO> test = getBooking(BookingId);
        List errors = new ArrayList();
        if(!test.getBody().getBookingName().equals(updateBooking.getBookingName())){
            errors.add("The bookingName can't change");
            if(!test.getBody().getBookingEmail().equals(updateBooking.getBookingEmail())){
                errors.add("The bookingEmail can't change");
            }
            throw new BookingNotFoundException(errors.toString());
        }
        service.UpdateBooking(BookingId,updateBooking);
        return new ResponseEntity<>(updateBooking,HttpStatus.OK);
    }

    @DeleteMapping("/{bookingId}")
    public void deleteBooking(@PathVariable Integer bookingId){
        service.DeleteBooking(bookingId);
    }
}
