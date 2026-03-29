package uz.hiwelcome.hiwelcome.service;

import uz.hiwelcome.hiwelcome.req.BookingCreateRequest;
import uz.hiwelcome.hiwelcome.res.BookingResponse;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface BookingService {

    BookingResponse create(BookingCreateRequest request) throws AccessDeniedException;

    List<BookingResponse> getAll();

    BookingResponse confirm(Long id);

    BookingResponse cancel(Long id);

    void delete(Long id);


}