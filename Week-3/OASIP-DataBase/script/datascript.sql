insert into `EventCategory` (`CategoryID`,`CategoryName`,`Description`,`Duration`) values 
(1, 'Project Management Clinic','ตารางนัดหมายนี้ใช้สำหรับนัดหมาย project management clinic ในวิชา INT221 integrated project I ให้นักศึกษาเตรียมเอกสารที่เกี่ยวข้องเพื่อแสดง EventBooking ระหว่างขอคำปรึกษา', 30 ),
(2,'DevOps/Infra Clinic','Use this event category for DevOps/Infra clinic.',20),
(3,'Database Clinic','ตารางนัดหมายนี้ใช้สำหรับนัดหมาย database clinic ในวิชา INT221 integrated project I',15),
(4,'Client-side Clinic','ตารางนัดหมายนี้ใช้สำหรับนัดหมาย client-side clinic ในวิชา INT221 integrated project',30),
(5,'Server-side Clinic',' ',30);

insert into `EventBooking` (`BookingID`,`BookingName`,`BookingEmail`,`Category`,`StartTime`,`BookingDuration`,`EventNote`) values 
(1,'Somchai Jaidee (OR-7)','somchai.jai@mail.kmutt.ac.th',2,'2022-05-23 13:00',30,' '),
(2,'Somsri Rakdee (SJ-3)','somsri.rak@mail.kmutt.ac.th',1,'2022-04-27 09:30',30,' ขอปรึกษาเรื่องเพื่อนไม่ช่วยงาน '),
<<<<<<< HEAD
(3,'สมเกียรติ ขยันเรียน กลุ่ม TT-4','somkiat.kay@kmutt.ac.th',3,'2022-05-23 16:30',15,' ');
=======
(3,'สมเกียรติ ขยันเรียน กลุ่ม TT-4','somkiat.kay@kmutt.ac.th',3,'2022-05-23 16:30',15,' '),
(4,'Somchai Jaidee (OR-7)','somchai.jai@mail.kmutt.ac.th',2,'2022-05-24 13:00',30,' '),
(5,'Somsri Rakdee (SJ-3)','somsri.rak@mail.kmutt.ac.th',1,'2022-04-25 09:30',30,' ขอปรึกษาเรื่องเพื่อนไม่ช่วยงาน '),
(6,'สมเกียรติ ขยันเรียน กลุ่ม TT-4','somkiat.kay@kmutt.ac.th',3,'2022-05-26 16:30',15,' ');


>>>>>>> localhost
