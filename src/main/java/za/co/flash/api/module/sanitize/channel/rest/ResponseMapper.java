package za.co.flash.api.module.sanitize.channel.rest;

import za.co.flash.api.module.sanitize.channel.rest.createword.CreateWordResponseMapper;
import za.co.flash.api.module.sanitize.channel.rest.createword.CreateWordResponseResource;
import za.co.flash.api.module.sanitize.channel.rest.getword.GetWordResponseMapper;
import za.co.flash.api.module.sanitize.channel.rest.getword.GetWordResponseResource;
import za.co.flash.api.module.sanitize.channel.rest.updateword.UpdateWordResponseMapper;
import za.co.flash.api.module.sanitize.channel.rest.updateword.UpdateWordResponseResource;
import za.co.flash.api.module.sanitize.data.SensitiveWords;

public class ResponseMapper implements CreateWordResponseMapper, UpdateWordResponseMapper, GetWordResponseMapper {

    @Override
    public CreateWordResponseResource mapCreateResponse(SensitiveWords entity) {
        return CreateWordResponseResource.builder().id(entity.getId()).word(entity.getWord()).build();
    }

    @Override
    public UpdateWordResponseResource mapUpdateResponse(SensitiveWords entity) {
        return UpdateWordResponseResource.builder().id(entity.getId()).word(entity.getWord()).build();
    }

    @Override
    public GetWordResponseResource mapGetResponse(SensitiveWords entity) {
        return GetWordResponseResource.builder().id(entity.getId()).word(entity.getWord()).build();
    }
}
