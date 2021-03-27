package es.udc.paproject.backend.rest.controllers;


import es.udc.paproject.backend.model.entities.Province;
import es.udc.paproject.backend.model.entities.SportTest;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.services.Block;
import es.udc.paproject.backend.model.services.SportTestService;
import es.udc.paproject.backend.rest.dtos.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static es.udc.paproject.backend.rest.dtos.ProvinceConversor.toProvinceDtos;
import static es.udc.paproject.backend.rest.dtos.SportTestConversor.toSportTestDto;
import static es.udc.paproject.backend.rest.dtos.SportTestConversor.toSportTestSummaryDtos;
import static es.udc.paproject.backend.rest.dtos.SportTestTypeConversor.toSportTestTypeDtos;

@RestController
@RequestMapping("/sportTests")
public class SportTestController {

    @Autowired
    private SportTestService sportTestService;


    @GetMapping("")
    public BlockDto<SportTestSummaryDto> findSportTests(
            @RequestParam(required = false) Long provinceId,
            @RequestParam(required = false) Long typeId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate finishDate,
            @RequestParam(defaultValue="0") int page) {

        Block<SportTest> sportTestBlock = sportTestService.findSportTests(provinceId, typeId, startDate,
                finishDate, page, 2);

        return new BlockDto<>(toSportTestSummaryDtos(sportTestBlock.getItems()), sportTestBlock.getExistMoreItems());
    }

    @GetMapping("/{testId}")
    public SportTestDto findSportTest(@PathVariable Long testId) throws InstanceNotFoundException {
                return toSportTestDto(sportTestService.findSportTestById(testId));
    }

    @GetMapping("/provinces")
    public List<ProvinceDto> findAllProvinces() {
        return toProvinceDtos(sportTestService.findAllProvinces());
    }

    @GetMapping("/types")
    public List<SportTestTypeDto> findAllSportTests() {
        return toSportTestTypeDtos(sportTestService.findAllSportTestTypes());
    }





}
