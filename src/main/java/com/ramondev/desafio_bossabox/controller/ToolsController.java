package com.ramondev.desafio_bossabox.controller;

import com.ramondev.desafio_bossabox.dtos.ToolsRequest;
import com.ramondev.desafio_bossabox.dtos.ToolsResponse;
import com.ramondev.desafio_bossabox.entity.Tools;
import com.ramondev.desafio_bossabox.mapper.ToolsMapper;
import com.ramondev.desafio_bossabox.service.ToolsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tools")
public class ToolsController {

    private final ToolsService toolsService;

    @PostMapping
    public ResponseEntity<ToolsResponse> createTools(@RequestBody ToolsRequest toolsRequest){
        Tools tools = ToolsMapper.toConvert(toolsRequest);
        Tools newTools = toolsService.saveTools(tools);

        return ResponseEntity.status(HttpStatus.CREATED).body(ToolsMapper.toConvertResponse(newTools));
    }
}
