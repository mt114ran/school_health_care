package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.Family;
import models.User;
import utils.DBUtil;

public class FamilyValidator {
    public static List<String> validate(Family f, Boolean codeDuplicateCheckFlag, Boolean passwordCheckFlag) {
        List<String> errors = new ArrayList<String>();

        String student_code_error = validateStudentCode(f.getStudent(), codeDuplicateCheckFlag);
        if(!student_code_error.equals("")) {
            errors.add(student_code_error);
        }

        String parent_code_error = validateParentCode(f.getParent(), codeDuplicateCheckFlag);
        if(!parent_code_error.equals("")) {
            errors.add(parent_code_error);
        }

        return errors;
    }

    // 生徒のユーザー番号の確認
    private static String validateStudentCode(User student, Boolean codeDuplicateCheckFlag) {
        // 必須入力チェック
        if(student.getCode() == null || student.getCode().equals("")) {
            return "生徒のユーザー番号を入力してください。";
        }

        // すでに登録されているユーザー番号との重複チェック
        if(codeDuplicateCheckFlag) {
            EntityManager em = DBUtil.createEntityManager();
            long users_count = (long)em.createNamedQuery("checkRegisteredCode", Long.class)
                                           .setParameter("code", student.getCode())
                                           .getSingleResult();
            em.close();
            if(users_count == 0) {
                return "入力されたユーザー番号を持つ生徒が存在しません。";
            }
        }

        return "";
    }

    // 保護者のユーザー番号の確認
    private static String validateParentCode(User parent, Boolean codeDuplicateCheckFlag) {
        // 必須入力チェック
        if(parent.getCode() == null || parent.getCode().equals("")) {
            return "保護者のユーザー番号を入力してください。";
        }

        // すでに登録されているユーザー番号との重複チェック
        if(codeDuplicateCheckFlag) {
            EntityManager em = DBUtil.createEntityManager();
            long users_count = (long)em.createNamedQuery("checkRegisteredCode", Long.class)
                                           .setParameter("code", parent.getCode())
                                           .getSingleResult();
            em.close();
            if(users_count == 0) {
                return "入力されたユーザー番号を持つ保護者が存在しません。";
            }
        }

        return "";
    }


}