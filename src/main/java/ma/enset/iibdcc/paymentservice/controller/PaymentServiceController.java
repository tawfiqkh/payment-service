package ma.enset.iibdcc.paymentservice.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.iibdcc.paymentservice.dto.paymentdto.PaymentCreateRequest;
import ma.enset.iibdcc.paymentservice.dto.paymentdto.PaymentResponse;
import ma.enset.iibdcc.paymentservice.entity.PaymentStatus;
import ma.enset.iibdcc.paymentservice.entity.PaymentType;
import ma.enset.iibdcc.paymentservice.exception.PaymentException.PaymentNotFound;
import ma.enset.iibdcc.paymentservice.service.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@Data
@RequestMapping(PaymentServiceController.ENDPOINT)
public class PaymentServiceController {

    public static final String ENDPOINT = "/payment";

    private PaymentService paymentService;
    private ModelMapper modelMapper;

    @PostMapping("new-payment")
    public ResponseEntity<HttpStatus>createPayment(@RequestBody PaymentCreateRequest paymentCreateRequest) throws Exception {
        paymentService.createPayment(paymentCreateRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/update-status/{id}")
    public  ResponseEntity<PaymentResponse> updateStatus(@RequestParam PaymentStatus status, @PathVariable UUID id) throws PaymentNotFound {
        paymentService.updatePaymentStatus(status, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all-payments")
    public ResponseEntity<List<PaymentResponse>> getAllPayments() {
        List<PaymentResponse> response = paymentService.getAllPayments()
                .stream()
                .map(it -> modelMapper.map(it, PaymentResponse.class))
                .toList();

        return ResponseEntity
                .ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> paymentById(@PathVariable("id") UUID id) throws PaymentNotFound {
        PaymentResponse paymentResponse = modelMapper.map(paymentService.getPaymentById(id), PaymentResponse.class);
        return ResponseEntity
                .ok(paymentResponse);
    }

    @GetMapping("/student/{code}/payments")
    public ResponseEntity<PaymentResponse> paymentByStudentCode(@PathVariable("code") String code) throws PaymentNotFound {
        PaymentResponse paymentResponse = modelMapper.map(paymentService.getPaymentsByStudentCode(code), PaymentResponse.class);
        return ResponseEntity
                .ok(paymentResponse);
    }

    @GetMapping("/type")
    public ResponseEntity<List<PaymentResponse>> paymentsByType(@RequestParam("payment_type")PaymentType paymentType) throws PaymentNotFound {
        List<PaymentResponse> paymentResponse = paymentService.getPaymentByType(paymentType)
                .stream()
                .map(it -> modelMapper.map(it, PaymentResponse.class))
                .toList();

        return ResponseEntity
                .ok(paymentResponse);
    }

    @GetMapping("/status")
    public ResponseEntity<List<PaymentResponse>> paymentsByStatus(@RequestParam("payment_status")PaymentStatus paymentStatus) throws PaymentNotFound {
        List<PaymentResponse> paymentResponse = paymentService.getPaymentsByStatus(paymentStatus)
                .stream()
                .map(it -> modelMapper.map(it, PaymentResponse.class))
                .toList();

        return ResponseEntity
                .ok(paymentResponse);
    }
}
