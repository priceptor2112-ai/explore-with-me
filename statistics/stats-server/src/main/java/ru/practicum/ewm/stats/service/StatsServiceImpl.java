package ru.practicum.ewm.stats.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.stats.dto.EndpointHitDto;
import ru.practicum.ewm.stats.dto.ViewStatsDto;
import ru.practicum.ewm.stats.mapper.StatsMapper;
import ru.practicum.ewm.stats.repository.StatsRepository;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {
    private final StatsRepository statsRepository;
    private final StatsMapper statsMapper;

    @Override
    public EndpointHitDto saveHit(EndpointHitDto hitDto) {
        log.info("Saving hit: app={}, uri={}, ip={}", hitDto.getApp(), hitDto.getUri(), hitDto.getIp());
        var hit = statsMapper.toEntity(hitDto);
        var saved = statsRepository.save(hit);
        return statsMapper.toDto(saved);
    }

    @Override
    public List<ViewStatsDto> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique) {
        log.info("Getting stats from {} to {}, uris={}, unique={}", start, end, uris, unique);
        
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Start date must be before end date");
        }

        if (Boolean.TRUE.equals(unique)) {
            return statsRepository.findUniqueStats(start, end, uris);
        }
        return statsRepository.findStats(start, end, uris);
    }
}
