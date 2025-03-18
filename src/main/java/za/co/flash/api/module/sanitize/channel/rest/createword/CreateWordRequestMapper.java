package za.co.flash.api.module.sanitize.channel.rest.createword;

import za.co.flash.api.module.sanitize.data.SensitiveWordsDto;

public interface CreateWordRequestMapper {
    SensitiveWordsDto mapCreateRequest(CreateWordRequestResource resource);
}
