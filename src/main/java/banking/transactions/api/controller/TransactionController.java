/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banking.transactions.api.controller;

import banking.common.api.controller.ResponseHandler;
import banking.transactions.application.HistoryApplicationService;
import banking.transactions.application.dto.TransactionDto;
import banking.utils.DateUtils;
import java.util.Date;
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

@RestController
@RequestMapping("api/history_transactions")
public class TransactionController {

    @Autowired
    HistoryApplicationService transactionApplicationService;

    @Autowired
    ResponseHandler responseHandler;
    @Autowired
    private ModelMapper mapper;

    @RequestMapping(method = RequestMethod.GET, path = "/all", produces = "application/json; charset=UTF-8")
    @ResponseBody
    ResponseEntity<Object> getTransactions(
            @RequestParam(value = "fromTime", required = false) Long fromDate,
            @RequestParam(value = "toTime", required = false) Long toDate,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "100") int pageSize) {
        try {
            Date from_date = DateUtils.getToDate(fromDate);
            Date to_date = DateUtils.getToDate(toDate);
            List<TransactionDto> transactions = transactionApplicationService.getTransactions(from_date, to_date, page, pageSize);
            List<TransactionDto> transactionsDto = mapper.map(transactions, new TypeToken<List<TransactionDto>>() {
            }.getType());
            return this.responseHandler.getDataResponse(transactionsDto, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            return this.responseHandler.getAppExceptionResponse();
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/all_account", produces = "application/json; charset=UTF-8")
    @ResponseBody
    ResponseEntity<Object> getTransactionsByAccount(
            @RequestParam(value = "fromTime", required = false) Long fromDate,
            @RequestParam(value = "toTime", required = false) Long toDate,
            @RequestParam(value = "accountId", required = false) Long accountId,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "100") int pageSize) {
        try {
            Date from_date = DateUtils.getToDate(fromDate);
            Date to_date = DateUtils.getToDate(toDate);
            List<TransactionDto> transactions = transactionApplicationService.getTransactionsByAccount(from_date, to_date, accountId, page, pageSize);
            List<TransactionDto> transactionsDto = mapper.map(transactions, new TypeToken<List<TransactionDto>>() {
            }.getType());
            return this.responseHandler.getDataResponse(transactionsDto, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            return this.responseHandler.getAppExceptionResponse();
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/all_customer", produces = "application/json; charset=UTF-8")
    @ResponseBody
    ResponseEntity<Object> getTransactionsByCustomer(
            @RequestParam(value = "fromTime", required = false) Long fromDate,
            @RequestParam(value = "toTime", required = false) Long toDate,
            @RequestParam(value = "customerId", required = false) Long customerId,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "100") int pageSize) {
        try {
            Date from_date = DateUtils.getToDate(fromDate);
            Date to_date = DateUtils.getToDate(toDate);
            List<TransactionDto> transactions = transactionApplicationService.getTransactionsByCustomer(from_date, to_date, customerId, page, pageSize);
            List<TransactionDto> transactionsDto = mapper.map(transactions, new TypeToken<List<TransactionDto>>() {
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
