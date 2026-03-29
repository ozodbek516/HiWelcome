package uz.hiwelcome.hiwelcome.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.hiwelcome.hiwelcome.authEntity.User;
import uz.hiwelcome.hiwelcome.repository.BookingRepository;
import uz.hiwelcome.hiwelcome.req.BookingCreateRequest;
import uz.hiwelcome.hiwelcome.res.BookingResponse;
import uz.hiwelcome.hiwelcome.entity.*;
import uz.hiwelcome.hiwelcome.service.BookingService;
import uz.hiwelcome.hiwelcome.repository.HotelRepository;
import uz.hiwelcome.hiwelcome.utils.UserGetService;

import java.nio.file.AccessDeniedException;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final HotelRepository hotelRepository;

    @Override
    public BookingResponse create(BookingCreateRequest request) throws AccessDeniedException {

        User user = UserGetService.getUser();

        Hotel hotel = hotelRepository.findById(request.getHotelId())
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        long days = ChronoUnit.DAYS.between(
                request.getCheckInDate(),
                request.getCheckOutDate()
        );

        if (days <= 0) {
            throw new RuntimeException("Invalid date range");
        }

        double totalPrice = days * hotel.getPricePerNight();

        Booking booking = Booking.builder()
                .checkInDate(request.getCheckInDate())
                .checkOutDate(request.getCheckOutDate())
                .peopleCount(request.getPeopleCount())
                .phoneNumber(request.getPhoneNumber())
                .totalDays((int) days)
                .totalPrice(totalPrice)
                .status(BookingStatus.PENDING)
                .hotel(hotel)
                .user(user)
                .build();

        bookingRepository.save(booking);

        return mapToResponse(booking);
    }

    @Override
    public List<BookingResponse> getAll() {

        User user;
        try {
            user = UserGetService.getUser();
        } catch (AccessDeniedException e) {
            throw new RuntimeException("Unauthorized");
        }

        return bookingRepository.findByUserId(user.getId())
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public BookingResponse confirm(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setStatus(BookingStatus.CONFIRMED);
        bookingRepository.save(booking);

        return mapToResponse(booking);
    }

    @Override
    public BookingResponse cancel(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);

        return mapToResponse(booking);
    }

    @Override
    public void delete(Long id) {
        if (!bookingRepository.existsById(id)) {
            throw new RuntimeException("Booking not found");
        }
        bookingRepository.deleteById(id);
    }

    private BookingResponse mapToResponse(Booking booking) {
        return BookingResponse.builder()
                .id(booking.getId())
                .hotelName(booking.getHotel().getName())
                .checkInDate(booking.getCheckInDate())
                .checkOutDate(booking.getCheckOutDate())
                .totalDays(booking.getTotalDays())
                .totalPrice(booking.getTotalPrice())
                .status(booking.getStatus().name())
                .build();
    }
}