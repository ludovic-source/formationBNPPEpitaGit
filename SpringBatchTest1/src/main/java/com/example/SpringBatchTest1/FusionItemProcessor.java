package com.example.SpringBatchTest1;

import org.springframework.batch.item.ItemProcessor;

public class FusionItemProcessor implements ItemProcessor<MessageInput1DTO, MessageOutputDTO> {

    private int nbreLignes = 0;

    @Override
    public MessageOutputDTO process(MessageInput1DTO messageInput1DTO) throws Exception {
        nbreLignes += 1;
        MessageOutputDTO messageResult = new MessageOutputDTO();
        messageResult.setMessageInput1(messageInput1DTO.getMessageImput1());
        messageResult.setMessageInput2("non renseigné");
        messageResult.setTypeInput2("non renseigné");
        messageResult.setMarqueVoitureInput1(messageInput1DTO.getMarqueVoitureInput1().toUpperCase());

        System.out.println(nbreLignes);
        System.out.println(messageResult.getMessageInput1() + ";" + messageResult.getMessageInput2()
                + ";" + messageResult.getMarqueVoitureInput1() + ";" + messageResult.getTypeInput2());

        return messageResult;
    }
}
