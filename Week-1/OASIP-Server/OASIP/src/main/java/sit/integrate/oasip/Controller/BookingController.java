package sit.integrate.oasip.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sit.integrate.oasip.DTO.BookingDTO;
import sit.integrate.oasip.Entity.Booking;
import sit.integrate.oasip.Repository.BookingRepository;
import sit.integrate.oasip.Service.BookingService;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
    @Autowired
    private BookingService service;

    @GetMapping("")
    public List<BookingDTO> getAllBooking(){ return service.getBookings();}
}
