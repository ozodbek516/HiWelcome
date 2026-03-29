package uz.hiwelcome.hiwelcome.req;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingCreateRequest {

    @NotNull
    private Long hotelId;

    @NotNull
    private LocalDate checkInDate;

    @NotNull
    private LocalDate checkOutDate;

    @NotNull
    private Integer peopleCount;

    @NotNull
    private String phoneNumber;
}
