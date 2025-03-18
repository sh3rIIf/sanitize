package za.co.flash.api.module.sanitize.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SanitizeRepository extends CrudRepository<SensitiveWords, Long> {

    List<SensitiveWords> findAll();
    Optional<SensitiveWords> findById(Long id);
    SensitiveWords save(SensitiveWords sensitiveWords);
    void deleteById(Long id);
}
