package es.udc.paproject.backend.rest.controllers;


import es.udc.paproject.backend.model.entities.SportTest;
import es.udc.paproject.backend.model.services.Block;
import es.udc.paproject.backend.model.services.SportTestService;
import es.udc.paproject.backend.rest.dtos.BlockDto;
import es.udc.paproject.backend.rest.dtos.SportTestSummaryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

import static es.udc.paproject.backend.rest.dtos.SportTestConversor.toSportTestSummaryDtos;

@RestController
@RequestMapping("/sporttest")
public class SportTestController {

    @Autowired
    private SportTestService sportTestService;


    @GetMapping("/sporttests")
    public BlockDto<SportTestSummaryDto> findSportTests(
            @RequestParam(required = false) Long provinceId,
            @RequestParam(required = false) Long typeId,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate finishDate,
            @RequestParam(defaultValue="0") int page) {

        Block<SportTest> sportTestBlock = sportTestService.findSportTests(provinceId, typeId, startDate,
                finishDate, page, 10);

        return new BlockDto<>(toSportTestSummaryDtos(sportTestBlock.getItems()), sportTestBlock.getExistMoreItems());
    }

}
