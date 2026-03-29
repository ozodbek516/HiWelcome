package uz.hiwelcome.hiwelcome.res;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class HotelResponse {

    private Long id;
    private String name;
    private Integer stars;
    private String location;
    private Double price;
    private List<String> images;
}