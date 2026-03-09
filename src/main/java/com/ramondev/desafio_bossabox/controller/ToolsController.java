package com.ramondev.desafio_bossabox.controller;

import com.ramondev.desafio_bossabox.dtos.Request.ToolsRequest;
import com.ramondev.desafio_bossabox.dtos.Response.ToolsResponse;
import com.ramondev.desafio_bossabox.entity.Tools;
import com.ramondev.desafio_bossabox.mapper.ToolsMapper;
import com.ramondev.desafio_bossabox.service.ToolsService;
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
    public ResponseEntity<ToolsResponse> findById(@PathVariable Long id){
        return toolsService.findById(id)
                .map(tools -> ResponseEntity.ok(ToolsMapper.toConvertResponse(tools)))
                .orElse(ResponseEntity.notFound().build());

    }




    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletebyid(@PathVariable Long id){
        toolsService.deleteBy(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}


