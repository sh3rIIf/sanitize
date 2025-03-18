package za.co.flash.api.module.sanitize.channel.rest.createword;

import za.co.flash.api.module.sanitize.data.SensitiveWords;

public interface CreateWordResponseMapper {
    CreateWordResponseResource mapCreateResponse(SensitiveWords entity);
}
