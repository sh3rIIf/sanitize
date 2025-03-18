package za.co.flash.api.module.sanitize.channel.rest;

import za.co.flash.api.module.sanitize.channel.rest.createword.CreateWordRequestMapper;
import za.co.flash.api.module.sanitize.channel.rest.createword.CreateWordRequestResource;
import za.co.flash.api.module.sanitize.channel.rest.updateword.UpdateWordRequestMapper;
import za.co.flash.api.module.sanitize.channel.rest.updateword.UpdateWordRequestResource;
import za.co.flash.api.module.sanitize.data.SensitiveWordsDto;

public class RequestMapper implements CreateWordRequestMapper, UpdateWordRequestMapper {

    @Override
    public SensitiveWordsDto mapCreateRequest(CreateWordRequestResource resource) {
        return SensitiveWordsDto.builder().word(resource.getWord()).build();
    }

    @Override
    public SensitiveWordsDto mapUpdateRequest(Long id, UpdateWordRequestResource resource) {
        return SensitiveWordsDto.builder().id(id).word(resource.getWord()).build();
    }
}
