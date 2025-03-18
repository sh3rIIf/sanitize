package za.co.flash.api.module.sanitize.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import za.co.flash.api.module.sanitize.data.SanitizeRepository;
import za.co.flash.api.module.sanitize.data.SensitiveWords;
import za.co.flash.api.module.sanitize.data.SensitiveWordsDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

class SanitizeServiceTest {

    @InjectMocks
    private SanitizeService sanitizeService;

    @Mock
    private SanitizeRepository sanitizeRepository;

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
        given(sanitizeRepository.findAll()).willReturn(sensitiveWordsList);
        String sanitizedString = sanitizeService.sanitizeString("BEGIN test this string");
        assertEquals(sanitizedString, "***** test this string");
    }

    @Test
    void testGetWord() {
        given(sanitizeRepository.findById(1L)).willReturn(Optional.ofNullable(sensitiveWordsList.get(0)));
        SensitiveWords sensitiveWords = sanitizeService.getWord(1L);
        assertEquals(sensitiveWords, sensitiveWordsList.get(0));
    }

    @Test
    void testAddWord() {
        SensitiveWords addedSensitiveWord = SensitiveWords.builder().id(99L).word("ADD").build();
        given(sanitizeRepository.save(SensitiveWords.builder().word("ADD").build())).willReturn(addedSensitiveWord);
        SensitiveWords sensitiveWords = sanitizeService.addWord(SensitiveWordsDto.builder().word("ADD").build());
        assertEquals(sensitiveWords, addedSensitiveWord);
    }

    @Test
    void testUpdateWord() {
        SensitiveWords updatedSensitiveWord = SensitiveWords.builder().id(99L).word("UPDATE").build();
        given(sanitizeRepository.findById(99L)).willReturn(Optional.ofNullable(updatedSensitiveWord));
        given(sanitizeRepository.save(SensitiveWords.builder().id(99L).word("UPDATE").build())).willReturn(updatedSensitiveWord);
        SensitiveWords sensitiveWords = sanitizeService.updateWord(SensitiveWordsDto.builder().id(99L).word("UPDATE").build());
        assertEquals(sensitiveWords, updatedSensitiveWord);
    }
}
