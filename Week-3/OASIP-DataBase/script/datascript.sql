insert into `EventCategory` (`CategoryID`,`CategoryName`,`Description`,`Duration`) values 
(1, 'Project Management Clinic','ตารางนัดหมายนี้ใช้สำหรับนัดหมาย project management clinic ในวิชา INT221 integrated project I ให้นักศึกษาเตรียมเอกสารที่เกี่ยวข้องเพื่อแสดง EventBooking ระหว่างขอคำปรึกษา', 30 ),
(2,'DevOps/Infra Clinic','Use this event category for DevOps/Infra clinic.',20),
(3,'Database Clinic','ตารางนัดหมายนี้ใช้สำหรับนัดหมาย database clinic ในวิชา INT221 integrated project I',15),
(4,'Client-side Clinic','ตารางนัดหมายนี้ใช้สำหรับนัดหมาย client-side clinic ในวิชา INT221 integrated project',30),
(5,'Server-side Clinic','',30);

insert into `EventBooking` (`BookingID`,`BookingName`,`BookingEmail`,`Category`,`StartTime`,`BookingDuration`,`EventNote`) values
(1,'Somchai Jaidee (OR-7)','somchai.jai@mail.kmutt.ac.th',2,'2022-05-23 13:00',30,''),
(2,'Somsri Rakdee (SJ-3)','somsri.rak@mail.kmutt.ac.th',1,'2022-04-27 09:30',30,' ขอปรึกษาเรื่องเพื่อนไม่ช่วยงาน '),
(3,'สมเกียรติ ขยันเรียน กลุ่ม TT-4','somkiat.kay@kmutt.ac.th',3,'2022-05-23 16:30',15,'');

insert into `EventBooking` (`BookingID`,`BookingName`,`BookingEmail`,`Category`,`StartTime`,`BookingDuration`,`EventNote`) values
(4,'INFRA-26-11:45','INFRA-26-11:15@kmutt.ac.th',2,'2022-05-26 11:15',20,''),
(5,'INFRA-26-09:20','INFRA-26-09:20@kmutt.ac.th',2,'2022-05-26 09:20',30,''),
(6,'INFRA-26-10:40','INFRA-26-10:40@kmutt.ac.th',2,'2022-05-26 10:40',20,''),
(7,"INFRA-26-12:00","INFRA-26-12:00@kmutt.ac.th",2,'2022-05-26 12:00',30,''),
(8,"DB-26-10:20","DB-26-10:20@kmutt.ac.th",3,'2022-05-26 10:20',15,''),
(9,"INFRA-27-10:20","INFRA-27-10:20@kmutt.ac.th",2,'2022-05-27 10:20',20,''),
(10,"INFRA-26-10:00","INFRA-26-10:00@kmutt.ac.th",2,'2022-05-26 10:00',20,''),
(11,"INFRA-26-11:45","INFRA-26-11:45@kmutt.ac.th",2,'2022-05-26 11:45',10,'');












