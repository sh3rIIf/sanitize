package za.co.flash.api.module.sanitize.channel.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.flash.api.module.sanitize.channel.rest.createword.CreateWordRequestResource;
import za.co.flash.api.module.sanitize.channel.rest.createword.CreateWordResponseResource;
import za.co.flash.api.module.sanitize.channel.rest.getword.GetWordResponseResource;
import za.co.flash.api.module.sanitize.channel.rest.updateword.UpdateWordRequestResource;
import za.co.flash.api.module.sanitize.channel.rest.updateword.UpdateWordResponseResource;
import za.co.flash.api.module.sanitize.service.SanitizeService;

@RestController
@RequestMapping(value = "/sanitize")
public class SanitizeController {

    private SanitizeService sanitizeService;
    private RequestMapper requestMapper;
    private ResponseMapper responseMapper;

    @Autowired
    public SanitizeController(SanitizeService sanitizeService) {

        this.sanitizeService = sanitizeService;
        this.requestMapper = new RequestMapper();
        this.responseMapper = new ResponseMapper();
    }

    @Operation(summary = "Sanitize a string", description = "Sanitizes the given string by replacing sensitive words")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping(
            value = "/string",
            produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<String> sanitizeString(@RequestParam @Parameter(name = "unsanitizedString", description = "The string to be sanitized", example = "This is an EXAMPLE string") String unsanitizedString) {
        return ResponseEntity.ok(sanitizeService.sanitizeString(unsanitizedString));
    }

    @Operation(summary = "Get a sensitive word by id", description = "Returns a sensitive word as per the id of the word")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping(
            value = "/{id}",
            produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<GetWordResponseResource> getWord(@PathVariable @Parameter(name = "id", description = "Sensitive word id", example = "1") Long id) {
        return ResponseEntity.ok(responseMapper.mapGetResponse(sanitizeService.getWord(id)));
    }

    @Operation(summary = "Add a word to sensitive list", description = "Adds a word to the sensitive word list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @PostMapping(
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<CreateWordResponseResource> addWord(@RequestBody CreateWordRequestResource resource) {
        return ResponseEntity.ok(responseMapper.mapCreateResponse(sanitizeService.addWord(requestMapper.mapCreateRequest(resource))));
    }

    @Operation(summary = "Remove a word from sensitive list", description = "Removes a word from the sensitive word list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @DeleteMapping(
            value = "/{id}"
    )
    public ResponseEntity<?> deleteWord(@PathVariable @Parameter(name = "id", description = "Sensitive word id", example = "1") Long id) {
        sanitizeService.deleteWord(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Update a word in sensitive list", description = "Updates a word in the sensitive word list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @PutMapping(
            value = "/{id}",
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UpdateWordResponseResource> updateWord(@PathVariable @Parameter(name = "id", description = "Sensitive word id", example = "1") Long id, @RequestBody UpdateWordRequestResource resource) {
        return ResponseEntity.ok(responseMapper.mapUpdateResponse(sanitizeService.updateWord(requestMapper.mapUpdateRequest(id, resource))));
    }
}
