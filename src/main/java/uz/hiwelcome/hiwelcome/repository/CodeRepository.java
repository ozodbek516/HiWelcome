package uz.hiwelcome.hiwelcome.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.hiwelcome.hiwelcome.authEntity.Code;


import java.util.Optional;

@Repository
public interface CodeRepository extends JpaRepository<Code, Long> {

    Optional<Code> findByEmail(String email);
}