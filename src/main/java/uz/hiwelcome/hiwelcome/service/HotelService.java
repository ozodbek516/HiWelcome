package uz.hiwelcome.hiwelcome.service;

import uz.hiwelcome.hiwelcome.req.HotelCreateRequest;
import uz.hiwelcome.hiwelcome.res.HotelResponse;

import java.util.List;

public interface HotelService {

    HotelResponse create(HotelCreateRequest request);

    HotelResponse update(Long id, HotelCreateRequest request);

    List<HotelResponse> getAll();

    HotelResponse getById(Long id);

    void delete(Long id);
}
