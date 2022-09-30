package fr.m2i.apichat.mappers;

import fr.m2i.apichat.dtos.MessageDto;
import fr.m2i.apichat.model.Message;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface MessageMapper {
    Message messageDtoToMessage(MessageDto messageDto);

    MessageDto messageToMessageDto(Message message);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Message updateMessageFromMessageDto(MessageDto messageDto, @MappingTarget Message message);
}
