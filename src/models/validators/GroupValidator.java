package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.Group;
import utils.DBUtil;

public class GroupValidator {
    public static List<String> validate(Group g, Boolean codeDuplicateCheckFlag, Boolean passwordCheckFlag) {
        List<String> errors = new ArrayList<String>();

        String group_name_error = validateGroupName(g.getGroup_name(), codeDuplicateCheckFlag);
        if(!group_name_error.equals("")) {
            errors.add(group_name_error);
        }

        return errors;
    }

    // グループの名前の確認
    private static String validateGroupName(String group_name, Boolean codeDuplicateCheckFlag) {
        // 必須入力チェック
        if(group_name == null || group_name.equals("")) {
            return "新規グループの名前を入力してください。";
        }

        // すでに登録されているユーザー番号との重複チェック
        if(codeDuplicateCheckFlag) {
            EntityManager em = DBUtil.createEntityManager();
            long groups_count = (long)em.createNamedQuery("getNameGroupsCount", Long.class)
                                           .setParameter("group_name",group_name )
                                           .getSingleResult();
            em.close();
            if(groups_count > 0) {
                return "同じ名前のグループが既に存在しています。";
            }
        }

        return "";
    }


}