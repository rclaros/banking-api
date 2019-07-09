package banking.transactions.api.controller;

import banking.Translator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import banking.common.api.controller.ResponseHandler;
import banking.transactions.application.TransactionApplicationService;
import banking.transactions.application.dto.RequestBankTransferDto;

@RestController
@RequestMapping("api/transactions")
public class BankTransferController {

    @Autowired
    TransactionApplicationService transactionApplicationService;

    @Autowired
    ResponseHandler responseHandler;

    @RequestMapping(method = RequestMethod.POST, path = "/transfer", consumes = "application/json; charset=UTF-8", produces = "application/json; charset=UTF-8")
    public ResponseEntity<Object> performTransfer(@RequestBody RequestBankTransferDto requestBankTransferDto) throws Exception {
        try {
            transactionApplicationService.performTransfer(requestBankTransferDto);
            return this.responseHandler.getResponse(Translator.toLocale("message.transfer.done"), HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            return this.responseHandler.getAppExceptionResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/transfer_deposit", consumes = "application/json; charset=UTF-8", produces = "application/json; charset=UTF-8")
    public ResponseEntity<Object> performTransferDeposit(@RequestBody RequestBankTransferDto requestBankTransferDto) throws Exception {
        try {
            transactionApplicationService.performTransferDeposit(requestBankTransferDto);
            return this.responseHandler.getResponse(Translator.toLocale("message.transfer.done"), HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            return this.responseHandler.getAppExceptionResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/transfer_withdraw", consumes = "application/json; charset=UTF-8", produces = "application/json; charset=UTF-8")
    public ResponseEntity<Object> performTransferWithdraw(@RequestBody RequestBankTransferDto requestBankTransferDto) throws Exception {
        try {
            transactionApplicationService.performTransferWithdraw(requestBankTransferDto);
            return this.responseHandler.getResponse(Translator.toLocale("message.transfer.done"), HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            return this.responseHandler.getAppExceptionResponse();
        }
    }
}
