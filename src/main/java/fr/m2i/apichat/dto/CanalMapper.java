package fr.m2i.apichat.dto;

import fr.m2i.apichat.model.Canal;

public class CanalMapper {

    public static Canal copy(Canal canal, Canal content){
        if(canal == null || content == null){
            return null;
        }

        if(content.getName()!= null && !"".equals(content.getName())){
            canal.setName(content.getName());
        }
      // a voir comment gerer les updates des canaux !! ou que le renommage

        return canal;
    }
}
