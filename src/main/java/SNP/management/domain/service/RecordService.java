package SNP.management.domain.service;

import SNP.management.domain.DTO.RecordDTO;
import SNP.management.domain.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;


    public RecordDTO findAllLast() {
        return null;
    }

    public List<RecordDTO> findAllByDay(int day) {
        return recordRepository.findAllByDay(day);
    }
}
