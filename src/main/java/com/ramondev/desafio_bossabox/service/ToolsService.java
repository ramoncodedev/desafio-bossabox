package com.ramondev.desafio_bossabox.service;


import com.ramondev.desafio_bossabox.entity.Tools;
import com.ramondev.desafio_bossabox.repository.ToolsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ToolsService {

    private final ToolsRepository toolsRepository;


    public Tools saveTools(Tools tools){

        return toolsRepository.save(tools);
    }

    public List<Tools> findTools(){
        return toolsRepository.findAll();
    }


    public Optional<Tools> findById(Long id){
        return toolsRepository.findById(id);
    }


    public void deleteBy(Long id){
        toolsRepository.deleteById(id);
    }




}
