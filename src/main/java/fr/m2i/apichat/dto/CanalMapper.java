package fr.m2i.apichat.dto;

import fr.m2i.apichat.model.Canal;
import fr.m2i.apichat.model.Message;
import fr.m2i.apichat.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CanalMapper {

    private Long id;
    private  String name;
    private String description;
    private Date createdAt;
    private LocalDateTime updatedAt;
    private List<UserDTO> users=new ArrayList<>();
    private List<MessageDTO> messages=new ArrayList<>();

    public static CanalDTO buildCanalDTO(Canal canal){
        if(canal == null){
            return new CanalDTO();
        }

        List<UserDTO> usersDTO = new ArrayList<>();
        // users not needed
        //if(canal.getUsers() != null ){
        //}

        List<MessageDTO> messagesDTO = new ArrayList<>();
        if(canal.getMessages() != null ){
            for(Message m: canal.getMessages()){
                messagesDTO.add(MessageMapper.buildMessageDTO(m));
            }
        }

        CanalDTO  canalDTO = new CanalDTO();
        canalDTO.setId(canal.getId());
        canalDTO.setName(canal.getName());
        canalDTO.setDescription(canal.getDescription());
        canalDTO.setCreatedAt(canal.getCreatedAt());
        canalDTO.setUpdatedAt(canal.getUpdatedAt());
        canalDTO.setMessages(messagesDTO);

        return canalDTO;

    }
    public static List<CanalDTO> buidListCanalDTO(List<Canal> orders){

        List<CanalDTO> dtos = new ArrayList<>();
        for(Canal c:orders){
            CanalDTO dto = CanalMapper.buildCanalDTO(c);
            dtos.add(dto);
        }
        return dtos;
    }

    public static Canal buildCanal(CanalDTO canalDto){
        if(canalDto == null){
            return null;
        }

        List<User> users = new ArrayList<>();
        List<Message> messages = new ArrayList<>();

        Canal  canal = new Canal();
        canal.setName(canalDto.getName());
        canal.setDescription(canalDto.getDescription());
        if(canalDto.getCreatedAt() != null) {
            canal.setCreatedAt(canalDto.getCreatedAt());
        }
        if(canalDto.getUpdatedAt()!= null) {
            canal.setUpdatedAt(canalDto.getUpdatedAt());
        }
        canal.setMessages(messages);

        // to do: add user that create canal (list user must be not null )

        return canal;

    }


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
