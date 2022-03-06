package com.edcm.backend.api.station;

import com.edcm.backend.core.mappers.StationMapper;
import com.edcm.backend.core.services.station.StationTransactionService;
import com.edcm.backend.core.shared.data.StationBasicInfoDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/station")
@AllArgsConstructor
public class StationController {
    private final StationTransactionService stationTransactionService;
    private final StationMapper mapper;

    @GetMapping()
    @ResponseBody
    public List<StationBasicInfoDto> findByName(@RequestParam String name) {
        var list = stationTransactionService.findByNameStartsWith(name);
        return list
            .stream()
            .map(mapper::projectionToStationBasicInfoDto)
            .collect(Collectors.toList());
    }
}
