package uz.hiwelcome.hiwelcome.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.hiwelcome.hiwelcome.req.HotelCreateRequest;
import uz.hiwelcome.hiwelcome.res.HotelResponse;
import uz.hiwelcome.hiwelcome.service.HotelService;

import java.util.List;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @PostMapping
    public HotelResponse create(@Valid @RequestBody HotelCreateRequest request) {
        return hotelService.create(request);
    }

    @PutMapping("/{id}")
    public HotelResponse update(@PathVariable Long id,
                                @Valid @RequestBody HotelCreateRequest request) {
        return hotelService.update(id, request);
    }

    @GetMapping
    public List<HotelResponse> getAll() {
        return hotelService.getAll();
    }

    @GetMapping("/{id}")
    public HotelResponse getById(@PathVariable Long id) {
        return hotelService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        hotelService.delete(id);
    }
}
