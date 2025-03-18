package za.co.flash.api.module.sanitize.data;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class SensitiveWords {
    @Id
    private Long id;

    private String word;
}
