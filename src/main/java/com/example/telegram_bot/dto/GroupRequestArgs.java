package com.example.telegram_bot.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.nonNull;

@Builder
@Getter
public class GroupRequestArgs {
    private final String query;
    private final GroupInfo type;
    private final GroupFilter filter;


    private final Integer offset;

    private final Integer limit;

    public Map populateQueries(){
        Map querise=new HashMap<>();
        if(nonNull(query))
        {
            querise.put("query",query);
        }
        if(nonNull(type))
        {
            querise.put("type",type);
        }
        if(nonNull(filter))
        {
            querise.put("filter",filter);
        }if(nonNull(offset))
        {
            querise.put("offset",offset);
        }
        if(nonNull(limit))
        {
            querise.put("limit",limit);
        }
        return querise;
    }


}
