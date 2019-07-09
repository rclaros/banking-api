/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banking.transactions.application.dto.deserializer;

import banking.common.application.enumeration.RequestBodyType;
import banking.transactions.application.dto.OperationDto;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.math.BigDecimal;

public class RequestBankOperationDtoDeserializer extends JsonDeserializer<OperationDto> {

    @Override
    public OperationDto deserialize(JsonParser jsonParser, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        OperationDto requestBankTransferDto = null;
        try {
            ObjectCodec objectCodec = jsonParser.getCodec();
            JsonNode node = objectCodec.readTree(jsonParser);
            String accountNumber = node.get("accountNumber").asText();
            BigDecimal amount = new BigDecimal(node.get("amount").asText());
            requestBankTransferDto = new OperationDto(accountNumber, amount);
        } catch (Exception ex) {
            requestBankTransferDto = new OperationDto(RequestBodyType.INVALID.toString(), null);
        }
        return requestBankTransferDto;
    }
}
