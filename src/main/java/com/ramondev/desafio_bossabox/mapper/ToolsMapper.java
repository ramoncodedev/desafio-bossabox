package com.ramondev.desafio_bossabox.mapper;


import com.ramondev.desafio_bossabox.dtos.Request.ToolsRequest;
import com.ramondev.desafio_bossabox.dtos.Response.ToolsResponse;
import com.ramondev.desafio_bossabox.entity.Tools;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ToolsMapper {

    public Tools toConvert(ToolsRequest request){
        return Tools.builder()
                .title(request.title())
                .link(request.link())
                .tags(request.tags())
                .build();
    }


    public ToolsResponse toConvertResponse(Tools tools){
        return ToolsResponse.builder()
                .id(tools.getId())
                .title(tools.getTitle())
                .link(tools.getLink())
                .tags(tools.getTags())
                .build();
    }


}
