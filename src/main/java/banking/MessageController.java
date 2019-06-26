/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banking;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/translation")
public class MessageController {

    @RequestMapping(method = RequestMethod.GET, path = "/message", produces = "application/json; charset=UTF-8")
    public String welcome() throws Exception {
        return Translator.toLocale("mensaje");
    }
}
