package za.co.flash.api.module.sanitize.channel.rest.getword;

import za.co.flash.api.module.sanitize.data.SensitiveWords;

public interface GetWordResponseMapper {
    GetWordResponseResource mapGetResponse(SensitiveWords entity);
}
