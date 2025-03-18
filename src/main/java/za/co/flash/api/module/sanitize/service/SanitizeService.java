package za.co.flash.api.module.sanitize.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.flash.api.module.sanitize.data.SanitizeRepository;
import za.co.flash.api.module.sanitize.data.SensitiveWords;
import za.co.flash.api.module.sanitize.data.SensitiveWordsDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class SanitizeService {

    @Getter
    public class WordMap
    {
        public String word;
        public Integer position;

        public WordMap(String word, Integer position)
        {
            this.word = word;
            this.position = position;
        }
    }

    private SanitizeRepository sanitizeRepository;

    @Autowired
    public SanitizeService(SanitizeRepository sanitizeRepository) {
        this.sanitizeRepository = sanitizeRepository;
    }

    public String sanitizeString(String unsanitizedString) {

        List<SensitiveWords> sensitiveWordsList = sanitizeRepository.findAll();

        List<WordMap> wordMaps = new ArrayList<>();
        for (SensitiveWords sensitiveWord : sensitiveWordsList) {
            int i = unsanitizedString.indexOf(sensitiveWord.getWord());
            while (i >= 0) {
                wordMaps.add(new WordMap(sensitiveWord.getWord(), i));
                i = unsanitizedString.indexOf(sensitiveWord.getWord(), i + 1);
            }
        }

        for (WordMap wordMap : wordMaps) {
            int i1 = wordMap.getPosition();
            int i2 = wordMap.getWord().length();
            unsanitizedString = unsanitizedString.substring(0, i1) + "*".repeat(wordMap.getWord().length()) + unsanitizedString.substring(i1 + i2);
        }

        return unsanitizedString;
    }

    public SensitiveWords getWord(Long id) {
        return sanitizeRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public SensitiveWords addWord(SensitiveWordsDto dto) {
        return sanitizeRepository.save(SensitiveWords.builder().word(dto.getWord()).build());
    }

    public void deleteWord(Long id) {
        sanitizeRepository.deleteById(id);
    }

    public SensitiveWords updateWord(SensitiveWordsDto dto) {
        sanitizeRepository.findById(dto.getId()).orElseThrow(IllegalArgumentException::new);
        return sanitizeRepository.save(SensitiveWords.builder().id(dto.getId()).word(dto.getWord()).build());
    }

}
