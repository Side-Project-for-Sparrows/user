package com.sparrows.user.user.adapter.out;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparrows.user.kafka.outbox.OutboxEvent;
import com.sparrows.user.kafka.outbox.OutboxEventRepository;
import com.sparrows.user.kafka.payload.user.UserEventEnum;
import com.sparrows.user.kafka.properties.KafkaProperties;
import com.sparrows.user.user.model.enums.UserType;
import com.sparrows.user.kafka.payload.user.UserCreatedPayload;
import com.sparrows.user.user.port.out.UserEventPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class KafkaUserEventAdapter implements UserEventPort {
    private final KafkaProperties kafkaProperties;
    private final ObjectMapper objectMapper;
    private final OutboxEventRepository outboxEventRepository;

    @Transactional
    public void publishUserCreatedEvent(Long userId, Integer schoolId, UserType userType) {
        UserCreatedPayload payload = new UserCreatedPayload();
        payload.setSchoolId(schoolId);
        payload.setUserId(userId);
        payload.setUserType(userType);

        OutboxEvent outboxEvent = new OutboxEvent();
        outboxEvent.setAggregateType(kafkaProperties.getAggregateType().getUser());
        outboxEvent.setAggregateId(String.valueOf(userId));
        outboxEvent.setEventType(UserEventEnum.UserCreated.toString());
        outboxEvent.setTopic(kafkaProperties.getTopic().getUser().getCreate());
        outboxEvent.setKey(String.valueOf(userId));
        try {
            String json = objectMapper.writeValueAsString(payload);
            outboxEvent.setPayload(json); // Object → JSON 문자열
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON 직렬화 실패", e);
        }
        outboxEvent.setStatus("PENDING");

        outboxEventRepository.save(outboxEvent);
    }
}
