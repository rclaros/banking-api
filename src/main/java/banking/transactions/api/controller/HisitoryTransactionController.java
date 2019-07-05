/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banking.transactions.api.controller;

import banking.common.api.controller.ResponseHandler;
import banking.transactions.application.HistoryApplicationService;
import banking.transactions.application.dto.HistoryTransactionDto;
import banking.transactions.entity.HistoryTransaction;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author RCLAROS
 */
@RestController
@RequestMapping("api/history_transactions")
public class HisitoryTransactionController {

    @Autowired
    HistoryApplicationService transactionApplicationService;

    @Autowired
    ResponseHandler responseHandler;
    @Autowired
    private ModelMapper mapper;

    @RequestMapping(method = RequestMethod.GET, path = "", produces = "application/json; charset=UTF-8")
    @ResponseBody
    ResponseEntity<Object> getPaginated(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "100") int pageSize) {
        try {
            List<HistoryTransactionDto> transactions = transactionApplicationService.getTransactions(null, null, page, pageSize);
            List<HistoryTransactionDto> transactionsDto = mapper.map(transactions, new TypeToken<List<HistoryTransactionDto>>() {
            }.getType());
            return this.responseHandler.getDataResponse(transactionsDto, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            return this.responseHandler.getAppExceptionResponse();
        }
    }
}
