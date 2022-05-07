select * from EventBooking;
select * from EventCategory;

delete from EventBooking where BookingID in (1,2,3);

delete from EventCategory where CategoryID in (1,2,3,4,5);