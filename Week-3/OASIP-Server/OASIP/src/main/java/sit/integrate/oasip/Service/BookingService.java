package sit.integrate.oasip.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.modelmapper.ModelMapper;
<<<<<<< HEAD
=======
import org.springframework.data.domain.PageRequest;
>>>>>>> localhost
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sit.integrate.oasip.DTO.BookingDTO;
import sit.integrate.oasip.DTO.CategoryDTO;
import sit.integrate.oasip.DTO.SortListDayDTO;
import sit.integrate.oasip.Entity.EventBooking;
import sit.integrate.oasip.Entity.EventCategory;
import sit.integrate.oasip.Repository.BookingRepository;
import sit.integrate.oasip.Utils.ListMapper;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {

    @Autowired private BookingRepository repository;
    @Autowired private ModelMapper modelMapper;
    @Autowired private ListMapper listMapper;

    public List<BookingDTO> getBookings(int page, int pageSize, String sort){
        List<EventBooking> bookingList = repository.findAll(PageRequest.of(page,pageSize,Sort.by(Sort.Direction.DESC,sort))).getContent();
        return listMapper.mapList(bookingList, BookingDTO.class, modelMapper);
    }

<<<<<<< HEAD
=======
    public BookingDTO getBookingId(Integer bookingId){
        EventBooking booking = repository.findById(bookingId)
                .orElseThrow(()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND,  "Booking id "+ bookingId +" Does Not Exist !!!"));
        return modelMapper.map(booking, BookingDTO.class);
    }

    public List<BookingDTO> getBookingCategory(
            int page,
            int pageSize,
            EventCategory category){
        List<EventBooking> bookingList = repository.findAllByCategoryOrderByStartTimeDesc(PageRequest.of(page,pageSize),category);
        return listMapper.mapList(bookingList, BookingDTO.class,modelMapper);
    }

    public List<BookingDTO> getBookingSortPast(int page, int pageSize,LocalDateTime localDateTime){
        List<EventBooking> bookingList = repository.findAllByStartTimeLessThanOrderByStartTimeDesc(PageRequest.of(page,pageSize),localDateTime);
        return listMapper.mapList(bookingList, BookingDTO.class, modelMapper);
    }

    public List<SortListDayDTO> getBookingWithSpecify(
            int page,
            int pageSize,
            String startdate,
            String enddate){
        List<EventBooking> bookingList = repository.findAllByStartTimeBetweenOrderByStartTimeAsc(
                PageRequest.of(page,pageSize),
                LocalDateTime.parse(startdate),
                LocalDateTime.parse(enddate));
        return listMapper.mapList(bookingList, SortListDayDTO.class,modelMapper);
    }

>>>>>>> localhost
    public EventBooking CreateBooking(BookingDTO newBooking){
        EventBooking booking = modelMapper.map(newBooking,EventBooking.class);
        return repository.saveAndFlush(booking);
    }
<<<<<<< HEAD

    public BookingDTO getBookingId(Integer bookingId){
        EventBooking booking = repository.findById(bookingId)
                .orElseThrow(()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND,  "Booking id "+ bookingId +" Does Not Exist !!!"));
        return modelMapper.map(booking, BookingDTO.class);
    }

    public EventBooking updateBooking(Integer bookingId,BookingDTO updateBooking){
=======
    public EventBooking UpdateBooking(Integer bookingId,BookingDTO updateBooking){
>>>>>>> localhost
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

<<<<<<< HEAD
    public void CancelBooking(Integer BookingId){
=======
    public void DeleteBooking(Integer BookingId){
>>>>>>> localhost
        repository.deleteById(BookingId);
    }
}
