package sit.integrate.oasip.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sit.integrate.oasip.DTO.BookingDTO;
import sit.integrate.oasip.Entity.EventBooking;
import sit.integrate.oasip.Repository.BookingRepository;
import sit.integrate.oasip.Utils.ListMapper;

import java.util.List;

@Service
public class BookingService {
    @Autowired private BookingRepository repository;
    @Autowired private ModelMapper modelMapper;
    @Autowired private ListMapper listMapper;

    public List<BookingDTO> getBookings(){
        List<EventBooking> bookingList = repository.findAll();
        return listMapper.mapList(bookingList, BookingDTO.class, modelMapper);
    }

    public BookingDTO getBookingId(Integer bookingId){
        EventBooking booking = repository.findById(bookingId)
                .orElseThrow(()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND,  "Booking id "+ bookingId +"Does Not Exist !!!"));
        return modelMapper.map(booking, BookingDTO.class);
    }

    public EventBooking CreateBooking(BookingDTO newBooking){
        EventBooking booking = modelMapper.map(newBooking,EventBooking.class);
        return repository.saveAndFlush(booking);
    }

    public EventBooking updateBooking(Integer bookingId,BookingDTO updateBooking){
        EventBooking booking = repository.findById(bookingId).map(b->mapBooking(modelMapper.map(b,BookingDTO.class),updateBooking))
                .orElseGet(()->{
                    updateBooking.setId(bookingId);
                    return modelMapper.map(updateBooking,EventBooking.class);
                });
        return repository.saveAndFlush(booking);
    }

    private EventBooking mapBooking(BookingDTO oldBooking,BookingDTO newBooking){
        oldBooking=newBooking;
        return modelMapper.map(oldBooking,EventBooking.class);
    }
}
