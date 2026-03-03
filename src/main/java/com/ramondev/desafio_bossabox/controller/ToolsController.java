package com.ramondev.desafio_bossabox.controller;

import com.ramondev.desafio_bossabox.dtos.ToolsRequest;
import com.ramondev.desafio_bossabox.dtos.ToolsResponse;
import com.ramondev.desafio_bossabox.entity.Tools;
import com.ramondev.desafio_bossabox.mapper.ToolsMapper;
import com.ramondev.desafio_bossabox.service.ToolsService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/list")
    public ResponseEntity<List<ToolsResponse>> listtools(){
        List<Tools> toolsList = toolsService.findTools();
        List<ToolsResponse> newList = toolsList.stream().map(tools -> ToolsMapper.toConvertResponse(tools)).toList();
        return ResponseEntity.ok().body(newList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToolsResponse> findByTag(@PathVariable Long id){
        return toolsService.findById(id)
                .map(tools -> ResponseEntity.ok(ToolsMapper.toConvertResponse(tools)))
                .orElse(ResponseEntity.notFound().build());

    }
}


