<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>
<label for="code">ユーザー番号</label><br />
<input type="text" name="code" value="${user.code}" />
<br /><br />

<label for="name">氏名</label><br />
<input type="text" name="name" value="${user.name}" />
<br /><br />

<label for="password">パスワード</label><br />
<input type="password" name="password" />
<br /><br />

<label for="acc_inf">アカウント情報</label><br />
<select name="acc_inf">
    <option value="0"<c:if test="${user.acc_inf == 0}"> selected</c:if>>先生</option>
    <option value="1"<c:if test="${user.acc_inf == 1}"> selected</c:if>>生徒</option>
    <option value="2"<c:if test="${user.acc_inf == 2}"> selected</c:if>>保護者</option>
</select>
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">投稿</button>