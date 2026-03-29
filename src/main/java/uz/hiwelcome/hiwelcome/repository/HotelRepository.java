package uz.hiwelcome.hiwelcome.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.hiwelcome.hiwelcome.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
