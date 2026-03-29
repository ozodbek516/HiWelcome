package uz.hiwelcome.hiwelcome.req;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
public class HotelCreateRequest {

    @NotBlank(message = "Hotel name is required")
    private String name;

    @NotNull(message = "Stars is required")
    @Min(value = 1, message = "Stars must be at least 1")
    @Max(value = 5, message = "Stars must be at most 5")
    private Integer stars;

    @NotBlank(message = "Region is required")
    private String region;

    @NotBlank(message = "City is required")
    private String city;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than 0")
    private Double pricePerNight;

    private String description;

    private List<String> images;
}