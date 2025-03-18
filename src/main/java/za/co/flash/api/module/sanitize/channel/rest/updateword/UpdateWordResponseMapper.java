package za.co.flash.api.module.sanitize.channel.rest.updateword;

import za.co.flash.api.module.sanitize.data.SensitiveWords;

public interface UpdateWordResponseMapper {
    UpdateWordResponseResource mapUpdateResponse(SensitiveWords entity);
}
