package za.co.flash.api.module.sanitize.channel.rest.updateword;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateWordRequestResource {
    @Schema(description = "Sensitive Word", example = "BEGIN")
    private String word;
}
