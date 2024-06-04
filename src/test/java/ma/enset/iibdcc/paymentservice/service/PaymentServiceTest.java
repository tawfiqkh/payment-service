package ma.enset.iibdcc.paymentservice.service;

import ma.enset.iibdcc.paymentservice.entity.Payment;
import ma.enset.iibdcc.paymentservice.entity.PaymentStatus;
import static ma.enset.iibdcc.paymentservice.entity.PaymentType.CASH;
import ma.enset.iibdcc.paymentservice.exception.PaymentException.PaymentNotFound;
import ma.enset.iibdcc.paymentservice.repository.PaymentRepository;
import static ma.enset.iibdcc.paymentservice.utils.TestData.uuid1;
import static ma.enset.iibdcc.paymentservice.utils.TestData.payment1;
import static ma.enset.iibdcc.paymentservice.utils.TestData.payment2;
import static ma.enset.iibdcc.paymentservice.utils.TestData.payment3;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;
    @InjectMocks
    private PaymentService paymentService;
    final List<Payment> payments = List.of(payment1, payment2, payment3);

    @Test
    public void should_retrieve_all_payments(){

        Mockito.when(paymentRepository.findAll()).thenReturn(payments);
        assertThat(paymentService.getAllPayments()).isEqualTo(payments);
    }

    @Test
    public void should_find_payment_for_given_id() throws PaymentNotFound {

        Mockito.when(paymentRepository.findById(uuid1)).thenReturn(of(payment1));
        assertThat(paymentService.getPaymentById(uuid1)).isEqualTo(payment1);
    }

    @Test
    public void should_throw_payment_not_found_exception_for_given_id() throws PaymentNotFound {

        Mockito.when(paymentRepository.findById(uuid1)).thenReturn(empty());
        Exception exception = assertThrows(PaymentNotFound.class, ()-> paymentService.getPaymentById(uuid1));
        assertThat(exception.getMessage()).isEqualTo("payment with id: "+uuid1+" not found");
    }

    @Test
    public void should_find_payment_for_given_type() throws PaymentNotFound {

        Mockito.when(paymentRepository.findByType(CASH)).thenReturn(of(payments));
        List<Payment> results = paymentService.getPaymentByType(CASH);
        assertThat(results).containsAll(payments);
    }

    @Test
    public void should_throw_payment_not_found_exception_for_given_type() throws PaymentNotFound {

        Mockito.when(paymentRepository.findByType(CASH)).thenReturn(empty());
        Exception exception = assertThrows(PaymentNotFound.class, ()-> paymentService.getPaymentByType(CASH));
        assertThat(exception.getMessage()).isEqualTo("no payments with type: "+CASH+" found");
    }

    @Test
    public void should_find_payment_for_given_status() throws PaymentNotFound {

        Mockito.when(paymentRepository.findByStatus(PaymentStatus.CREATED)).thenReturn(of(payments));
        List<Payment> results = paymentService.getPaymentsByStatus(PaymentStatus.CREATED);
        assertThat(results).containsAll(payments);
    }

    @Test
    public void should_throw_payment_not_found_exception_for_given_status() throws PaymentNotFound {

        Mockito.when(paymentRepository.findByStatus(PaymentStatus.CREATED)).thenReturn(empty());
        Exception exception = assertThrows(PaymentNotFound.class, ()-> paymentService.getPaymentsByStatus(PaymentStatus.CREATED));
        assertThat(exception.getMessage()).isEqualTo("no payments with status: "+PaymentStatus.CREATED+" found");
    }

}
