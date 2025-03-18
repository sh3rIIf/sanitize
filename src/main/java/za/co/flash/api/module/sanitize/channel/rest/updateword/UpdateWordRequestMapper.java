package za.co.flash.api.module.sanitize.channel.rest.updateword;

import za.co.flash.api.module.sanitize.data.SensitiveWordsDto;

public interface UpdateWordRequestMapper {
    SensitiveWordsDto mapUpdateRequest(Long id, UpdateWordRequestResource resource);
}
