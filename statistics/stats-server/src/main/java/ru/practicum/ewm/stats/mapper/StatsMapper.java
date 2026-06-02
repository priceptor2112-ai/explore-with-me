package ru.practicum.ewm.stats.mapper;

import org.springframework.stereotype.Component;
import ru.practicum.ewm.stats.dto.EndpointHitDto;
import ru.practicum.ewm.stats.model.EndpointHit;

@Component
public class StatsMapper {

    public EndpointHitDto toDto(EndpointHit hit) {
        if (hit == null) return null;
        return EndpointHitDto.builder()
                .id(hit.getId())
                .app(hit.getApp())
                .uri(hit.getUri())
                .ip(hit.getIp())
                .timestamp(hit.getTimestamp())
                .build();
    }

    public EndpointHit toEntity(EndpointHitDto dto) {
        if (dto == null) return null;
        return EndpointHit.builder()
                .id(dto.getId())
                .app(dto.getApp())
                .uri(dto.getUri())
                .ip(dto.getIp())
                .timestamp(dto.getTimestamp())
                .build();
    }
}
