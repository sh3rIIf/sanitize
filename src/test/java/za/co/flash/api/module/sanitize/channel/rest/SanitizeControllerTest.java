package za.co.flash.api.module.sanitize.channel.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import za.co.flash.api.module.sanitize.channel.rest.createword.CreateWordRequestResource;
import za.co.flash.api.module.sanitize.channel.rest.createword.CreateWordResponseResource;
import za.co.flash.api.module.sanitize.channel.rest.getword.GetWordResponseResource;
import za.co.flash.api.module.sanitize.channel.rest.updateword.UpdateWordRequestResource;
import za.co.flash.api.module.sanitize.channel.rest.updateword.UpdateWordResponseResource;
import za.co.flash.api.module.sanitize.data.SensitiveWords;
import za.co.flash.api.module.sanitize.data.SensitiveWordsDto;
import za.co.flash.api.module.sanitize.service.SanitizeService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

class SanitizeControllerTest {

    @InjectMocks
    private SanitizeController sanitizeController;

    @Mock
    private SanitizeService sanitizeService;

    List<SensitiveWords> sensitiveWordsList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        sensitiveWordsList.add(SensitiveWords.builder().id(1L).word("BEGIN").build());
        sensitiveWordsList.add(SensitiveWords.builder().id(2L).word("MIDDLE").build());
        sensitiveWordsList.add(SensitiveWords.builder().id(3L).word("END").build());
    }

    @Test
    void testStringSanitize() {
        given(sanitizeService.sanitizeString("BEGIN test this string")).willReturn("***** test this string");
        ResponseEntity<String> sanitizedString = sanitizeController.sanitizeString("BEGIN test this string");
        assertEquals(sanitizedString, ResponseEntity.ok("***** test this string"));
    }

    @Test
    void testGetWord() {
        given(sanitizeService.getWord(1L)).willReturn(sensitiveWordsList.get(0));
        ResponseEntity<GetWordResponseResource> response = sanitizeController.getWord(1L);
        assertEquals(response, ResponseEntity.ok(GetWordResponseResource.builder().id(1L).word("BEGIN").build()));
    }

    @Test
    void testAddWord() {
        SensitiveWords addedSensitiveWord = SensitiveWords.builder().id(99L).word("ADDED").build();
        given(sanitizeService.addWord(SensitiveWordsDto.builder().word("ADDED").build())).willReturn(addedSensitiveWord);
        ResponseEntity<CreateWordResponseResource> response = sanitizeController.addWord(CreateWordRequestResource.builder().word("ADDED").build());
        assertEquals(response, ResponseEntity.ok(CreateWordResponseResource.builder().id(99L).word("ADDED").build()));
    }

    @Test
    void testDeleteWord() {
        doNothing().when(sanitizeService).deleteWord(1L);
        ResponseEntity<?> response = sanitizeController.deleteWord(1L);
        assertEquals(response, ResponseEntity.ok(null));
    }

    @Test
    void testUpdateWord() {
        SensitiveWords updatedSensitiveWord = SensitiveWords.builder().id(99L).word("UPDATE").build();
        given(sanitizeService.updateWord(SensitiveWordsDto.builder().id(99L).word("UPDATE").build())).willReturn(updatedSensitiveWord);
        ResponseEntity<UpdateWordResponseResource> response = sanitizeController.updateWord(99L, UpdateWordRequestResource.builder().word("UPDATE").build());
        assertEquals(response, ResponseEntity.ok(UpdateWordResponseResource.builder().id(99L).word("UPDATE").build()));
    }
}
