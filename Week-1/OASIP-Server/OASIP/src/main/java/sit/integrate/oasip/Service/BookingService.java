package sit.integrate.oasip.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
}
