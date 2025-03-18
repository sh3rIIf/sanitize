package za.co.flash.api.module.sanitize.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SensitiveWordsDto {
    private Long id;
    private String word;
}
