package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Message;

public class MessageValidator {
    public static List<String> validate(Message m) {
        List<String> errors = new ArrayList<String>();

        String message_error = _validateMessage(m.getMessage());
        if(!message_error.equals("")) {
            errors.add(message_error);
        }

        return errors;
    }

    private static String _validateMessage(String string) {
        if(string == null || string.equals("")) {
            return "メッセージを入力してください。";
            }

        return "";
    }
}