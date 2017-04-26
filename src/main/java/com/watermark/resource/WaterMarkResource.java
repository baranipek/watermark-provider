package com.watermark.resource;

import com.watermark.model.domain.Book;
import com.watermark.model.domain.Journal;
import com.watermark.model.entity.Document;
import com.watermark.model.response.TicketIdResponse;
import com.watermark.service.WaterMarkService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@Log4j
@RequestMapping("/watermark")
public class WaterMarkResource {
    private final WaterMarkService waterMarkService;

    @Autowired
    public WaterMarkResource(WaterMarkService waterMarkService) {this.waterMarkService = waterMarkService;}

    @RequestMapping(value = "/journal",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create new WaterMarkResponseDto", notes = "Creates new WaterMarkResponseDto")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 201, message = "") })
    public ResponseEntity<TicketIdResponse> createJournalWatermark(@RequestBody Journal requestDto) {
        log.info("Create a new watermark via post for document ");
        return new ResponseEntity<>(this.waterMarkService.createJournalWatermark(requestDto),HttpStatus.CREATED);

    }

    @RequestMapping(value = "/book",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create new WaterMarkResponseDto", notes = "Creates new WaterMarkResponseDto")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 201, message = "") })
    public ResponseEntity<TicketIdResponse> createBookWatermark(@RequestBody Book requestDto) {log.info("Create a new watermark via post for document ");
        return new ResponseEntity<>(this.waterMarkService.createJournalWatermark(requestDto),HttpStatus.CREATED);

    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Document By ticketId", notes = "Get Document By ticketId")
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Bad Request"), @ApiResponse(code = 200, message = "") })
    public ResponseEntity<Document> getDocument(@PathVariable ("id") Long id) {
        return new ResponseEntity<>(this.waterMarkService.getWatermarkByTicketId(id),HttpStatus.OK);
    }
}
