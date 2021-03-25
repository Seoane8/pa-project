package es.udc.paproject.backend.rest.controllers;


import es.udc.paproject.backend.model.entities.SportTest;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.services.Block;
import es.udc.paproject.backend.model.services.SportTestService;
import es.udc.paproject.backend.rest.dtos.BlockDto;
import es.udc.paproject.backend.rest.dtos.SportTestDto;
import es.udc.paproject.backend.rest.dtos.SportTestSummaryDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static es.udc.paproject.backend.rest.dtos.SportTestConversor.toSportTestDto;
import static es.udc.paproject.backend.rest.dtos.SportTestConversor.toSportTestSummaryDtos;

@RestController
@RequestMapping("/sporttest")
public class SportTestController {

    @Autowired
    private SportTestService sportTestService;


    @GetMapping("/tests")
    public BlockDto<SportTestSummaryDto> findSportTests(
            @RequestParam(required = false) Long provinceId,
            @RequestParam(required = false) Long typeId,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String finishDate,
            @RequestParam(defaultValue="0") int page) {

        LocalDate startDateParsed = null;
        LocalDate finishDateParsed = null;
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if (startDate != null) {
            startDateParsed = LocalDate.parse(startDate, dtf);
        }

        if (finishDate != null) {
            finishDateParsed = LocalDate.parse(finishDate, dtf);
        }

        Block<SportTest> sportTestBlock = sportTestService.findSportTests(provinceId, typeId, startDateParsed,
                finishDateParsed, page, 2);

        return new BlockDto<>(toSportTestSummaryDtos(sportTestBlock.getItems()), sportTestBlock.getExistMoreItems());
    }

    @GetMapping("/tests/{testId}")
    public SportTestDto findSportTest(@PathVariable Long testId) throws InstanceNotFoundException {
                return toSportTestDto(sportTestService.findSportTestById(testId));
            }

}
