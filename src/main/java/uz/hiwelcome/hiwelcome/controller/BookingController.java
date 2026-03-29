package uz.hiwelcome.hiwelcome.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.hiwelcome.hiwelcome.req.BookingCreateRequest;
import uz.hiwelcome.hiwelcome.res.BookingResponse;
import uz.hiwelcome.hiwelcome.service.BookingService;


import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public BookingResponse create(@RequestBody BookingCreateRequest request) throws AccessDeniedException {
        return bookingService.create(request);
    }

    @GetMapping
    public List<BookingResponse> getAll() {
        return bookingService.getAll();
    }

    @PutMapping("/{id}/confirm")
    public BookingResponse confirm(@PathVariable Long id) {
        return bookingService.confirm(id);
    }

    @PutMapping("/{id}/cancel")
    public BookingResponse cancel(@PathVariable Long id) {
        return bookingService.cancel(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookingService.delete(id);
    }
}
