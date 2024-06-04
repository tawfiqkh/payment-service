package ma.enset.iibdcc.paymentservice.controller;

import java.util.List;
import ma.enset.iibdcc.paymentservice.service.PaymentService;
import ma.enset.iibdcc.paymentservice.utils.TestData;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(PaymentServiceController.class)
class PaymentServiceControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    ModelMapper modelMapper;
    @MockBean
    public PaymentService paymentService;

    @Test
    void getAllPayments() throws Exception {

        when(paymentService.getAllPayments()).thenReturn(List.of(TestData.payment1, TestData.payment1 ));

        this.mockMvc.perform(get("/payment/all-payments"))
                .andExpect(status().isOk());
    }
}
