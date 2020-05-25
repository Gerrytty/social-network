package app.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Chat {

    private long id;

    private long chatId;

    private long senderId;
    private long receiverId;

    @Override
    public boolean equals(Object object) {

        if(!object.getClass().equals(getClass())) {
            return false;
        }

        Chat chat = (Chat) object;

        return (senderId == chat.senderId && receiverId == chat.receiverId)
                || (senderId == chat.receiverId && receiverId == chat.senderId);

    }

}
