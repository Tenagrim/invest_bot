package com.tenagrim.telegram.controller;

import com.tenagrim.telegram.bot.Bot;
import com.tenagrim.telegram.controller.base.SecuredRestController;
import com.tenagrim.telegram.dto.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
//@Secured({ "ROLE_VIEWER", "ROLE_EDITOR" })
//@SecurityRequirement(name = "bearerAuth")
public class AdminController implements SecuredRestController {

//    private final MessageService messageService;

    private final Bot bot;

    @PostMapping("/broadcast")
    public void broadcast(@RequestBody Message message){
//        List<SendMessage> messagesToSend = messageService.getBroadcastMessages(message.getText());

//        messagesToSend.forEach(bot::executeWithExceptionCheck);
    }

}

