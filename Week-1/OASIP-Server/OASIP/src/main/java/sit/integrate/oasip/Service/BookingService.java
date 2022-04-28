package sit.integrate.oasip.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sit.integrate.oasip.DTO.BookingDTO;
import sit.integrate.oasip.Entity.Booking;
import sit.integrate.oasip.Repository.BookingRepository;
import sit.integrate.oasip.Utils.ListMapper;

import java.util.List;

@Service
public class BookingService {
    @Autowired private BookingRepository repository;
    @Autowired private ModelMapper modelMapper;
    @Autowired private ListMapper listMapper;

    public List<BookingDTO> getBookings(){
        List<Booking> bookingList = repository.findAll();
        return listMapper.mapList(bookingList, BookingDTO.class, modelMapper);
    }

    public BookingDTO getBookingId(Integer bookingId){
        Booking booking = repository.findById(bookingId)
                .orElseThrow(()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND,  "Customer id "+ bookingId +"Does Not Exist !!!"));
        return modelMapper.map(booking, BookingDTO.class);
    }
}
