package uz.hiwelcome.hiwelcome.res;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class BookingResponse {

    private Long id;
    private String hotelName;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Integer totalDays;
    private Double totalPrice;
    private String status;
}
