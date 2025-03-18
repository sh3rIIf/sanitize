package za.co.flash.api.module.sanitize.channel.rest.getword;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetWordResponseResource {
    @Schema(description = "Sensitive Word ID", example = "1")
    private Long id;
    @Schema(description = "Sensitive Word", example = "BEGIN")
    private String word;
}
