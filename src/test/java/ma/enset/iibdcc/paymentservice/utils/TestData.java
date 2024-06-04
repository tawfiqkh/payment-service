package ma.enset.iibdcc.paymentservice.utils;

import ma.enset.iibdcc.paymentservice.entity.Payment;
import ma.enset.iibdcc.paymentservice.entity.PaymentStatus;
import ma.enset.iibdcc.paymentservice.entity.PaymentType;
import ma.enset.iibdcc.paymentservice.entity.Student;

import java.util.Date;
import java.util.UUID;

public final class TestData {

    public static final UUID uuid1 = UUID.fromString("550e8400-e29b-41d4-a716-446655440000");
    public static final UUID uuid2 = UUID.fromString("550e8400-e29b-41d4-a716-446655440001");
    public static final UUID uuid3 = UUID.fromString("550e8400-e29b-41d4-a716-446655440002");
    public static final UUID uuid4 = UUID.fromString("550e8400-e29b-41d4-a716-446655440003");
    public static Student student1 = Student.builder()
            .id(uuid1)
            .sector("IT")
            .firstName("firstname")
            .lastName("lastname")
            .picture("/picture.jpeg")
            .payments(null)
            .build();

    public static Student student2 = Student.builder()
            .id(uuid2)
            .sector("IT")
            .firstName("student2")
            .lastName("st1")
            .picture("/picture1.jpeg")
            .payments(null)
            .build();

    public static Student student3 = Student.builder()
            .id(uuid3)
            .sector("ECO")
            .firstName("student3")
            .lastName("st2")
            .picture("/picture2.jpeg")
            .payments(null)
            .build();

    public static Student student4 = Student.builder()
            .id(uuid4)
            .sector("FR")
            .firstName("student4")
            .lastName("st4")
            .picture("/picture4.jpeg")
            .payments(null)
            .build();

    public static Payment payment1 = Payment.builder()
            .id(uuid1)
            .date(new Date())
            .file("/file.pdf")
            .student(student1)
            .type(PaymentType.CASH)
            .status(PaymentStatus.CREATED)
            .build();

    public static Payment payment2 = Payment.builder()
            .id(uuid2)
            .date(new Date())
            .file("/file.pdf")
            .student(student2)
            .type(PaymentType.TRANSFER)
            .status(PaymentStatus.REJECTED)
            .build();

    public static Payment payment3 = Payment.builder()
            .id(uuid3)
            .date(new Date())
            .file("/file.pdf")
            .student(student3)
            .type(PaymentType.CHECK)
            .status(PaymentStatus.VALIDATED)
            .build();
}