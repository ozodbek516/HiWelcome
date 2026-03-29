package uz.hiwelcome.hiwelcome.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.hiwelcome.hiwelcome.req.HotelCreateRequest;
import uz.hiwelcome.hiwelcome.res.HotelResponse;
import uz.hiwelcome.hiwelcome.entity.Hotel;
import uz.hiwelcome.hiwelcome.service.HotelService;
import uz.hiwelcome.hiwelcome.entity.HotelImage;
import uz.hiwelcome.hiwelcome.repository.HotelImageRepository;
import uz.hiwelcome.hiwelcome.repository.HotelRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final HotelImageRepository imageRepository;

    @Override
    public HotelResponse create(HotelCreateRequest request) {

        Hotel hotel = Hotel.builder()
                .name(request.getName())
                .stars(request.getStars())
                .region(request.getRegion())
                .city(request.getCity())
                .pricePerNight(request.getPricePerNight())
                .description(request.getDescription())
                .build();

        hotelRepository.save(hotel);

        if (request.getImages() != null) {
            for (String img : request.getImages()) {
                HotelImage image = HotelImage.builder()
                        .imageUrl(img)
                        .hotel(hotel)
                        .build();
                imageRepository.save(image);
            }
        }

        return mapToResponse(hotel);
    }

    @Override
    public HotelResponse update(Long id, HotelCreateRequest request) {

        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        hotel.setName(request.getName());
        hotel.setStars(request.getStars());
        hotel.setRegion(request.getRegion());
        hotel.setCity(request.getCity());
        hotel.setPricePerNight(request.getPricePerNight());
        hotel.setDescription(request.getDescription());

        hotelRepository.save(hotel);

        return mapToResponse(hotel);
    }

    @Override
    public List<HotelResponse> getAll() {
        return hotelRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public HotelResponse getById(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));
        return mapToResponse(hotel);
    }

    @Override
    public void delete(Long id) {
        hotelRepository.deleteById(id);
    }

    private HotelResponse mapToResponse(Hotel hotel) {
        return HotelResponse.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .stars(hotel.getStars())
                .location(hotel.getRegion() + ", " + hotel.getCity())
                .price(hotel.getPricePerNight())
                .images(
                        hotel.getImages() == null ? List.of() :
                                hotel.getImages()
                                        .stream()
                                        .map(HotelImage::getImageUrl)
                                        .collect(Collectors.toList())
                )
                .build();
    }
}