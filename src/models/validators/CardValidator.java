package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Card;

public class CardValidator {
    public static List<String> validate(Card c) {
        List<String> errors = new ArrayList<String>();

        String temperature_error = _validateTempareture(c.getTemperature());
        if(!temperature_error.equals("")) {
            errors.add(temperature_error);
        }

        String attendance_error = _validateAttendance(c.getAttendance());
        if(!attendance_error.equals("")) {
            errors.add(attendance_error);
        }

//        String comment_error = _validateComment(c.getComment());
//        if(!comment_error.equals("")) {
//            errors.add(comment_error);
//        }

        return errors;
    }

    private static String _validateTempareture(Double tem) {
        if(tem == null || tem.equals("")) {
            return "検温結果を選択して下さい。";
            }

        return "";
    }

    private static String _validateAttendance(Integer integer) {
        if(integer == null || integer.equals("")) {
            return "出席情報を選択して下さい。";
            }

        return "";
    }

//    private static String _validateComment(String string) {
//        if(string == null || string.equals("")) {
//            return "内容を入力してください。";
//            }
//
//        return "";
//    }
}