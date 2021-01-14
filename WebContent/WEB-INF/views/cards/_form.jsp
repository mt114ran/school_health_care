<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>
<label for="card_date">日付</label><br />
<input type="date" name="card_date" value="<fmt:formatDate value='${card.card_date}' pattern='yyyy-MM-dd' />" />
<br /><br />

<label for="name">氏名</label><br />
<c:out value="${sessionScope.login_user.name}" />
<br /><br />

<label for="temperature">検温結果</label><br />
<select name="temperature">
    <option value="36.0"<c:if test="${user.temperature == 36.0}"> selected</c:if>>36.0</option>
    <option value="36.1"<c:if test="${user.temperature == 36.1}"> selected</c:if>>36.1</option>
    <option value="36.2"<c:if test="${user.temperature == 36.2}"> selected</c:if>>36.2</option>
    <option value="36.3"<c:if test="${user.temperature == 36.3}"> selected</c:if>>36.3</option>
    <option value="36.4"<c:if test="${user.temperature == 36.4}"> selected</c:if>>36.4</option>
    <option value="36.5"<c:if test="${user.temperature == 36.5}"> selected</c:if>>36.5</option>
    <option value="36.6"<c:if test="${user.temperature == 36.6}"> selected</c:if>>36.6</option>
    <option value="36.7"<c:if test="${user.temperature == 36.7}"> selected</c:if>>36.7</option>
    <option value="36.8"<c:if test="${user.temperature == 36.8}"> selected</c:if>>36.8</option>
    <option value="36.9"<c:if test="${user.temperature == 36.9}"> selected</c:if>>36.9</option>
    <option value="37.0"<c:if test="${user.temperature == 37.0}"> selected</c:if>>37.0</option>
    <option value="37.1"<c:if test="${user.temperature == 37.1}"> selected</c:if>>37.1</option>
    <option value="37.2"<c:if test="${user.temperature == 37.2}"> selected</c:if>>37.2</option>
    <option value="37.3"<c:if test="${user.temperature == 37.3}"> selected</c:if>>37.3</option>
    <option value="37.4"<c:if test="${user.temperature == 37.4}"> selected</c:if>>37.4</option>
    <option value="37.5"<c:if test="${user.temperature == 37.5}"> selected</c:if>>37.5</option>
    <option value="37.6"<c:if test="${user.temperature == 37.6}"> selected</c:if>>37.6</option>
    <option value="37.7"<c:if test="${user.temperature == 37.7}"> selected</c:if>>37.7</option>
    <option value="37.8"<c:if test="${user.temperature == 37.8}"> selected</c:if>>37.8</option>
    <option value="37.9"<c:if test="${user.temperature == 37.9}"> selected</c:if>>37.9</option>
    <option value="38.0"<c:if test="${user.temperature == 38.0}"> selected</c:if>>38.0</option>
    <option value="38.1"<c:if test="${user.temperature == 38.1}"> selected</c:if>>38.1</option>
    <option value="38.2"<c:if test="${user.temperature == 38.2}"> selected</c:if>>38.2</option>
    <option value="38.3"<c:if test="${user.temperature == 38.3}"> selected</c:if>>38.3</option>
</select>
<br /><br />

<label for="attendance">欠課情報</label><br />
<select name="attendance">
    <option value="0"<c:if test="${user.acc_inf == 0}"> selected</c:if>>出席</option>
    <option value="1"<c:if test="${user.acc_inf == 1}"> selected</c:if>>遅刻（登校時刻の目安をコメントに記入）</option>
    <option value="2"<c:if test="${user.acc_inf == 2}"> selected</c:if>>欠席</option>
    <option value="3"<c:if test="${user.acc_inf == 3}"> selected</c:if>>忌引</option>
    <option value="4"<c:if test="${user.acc_inf == 4}"> selected</c:if>>出席停止</option>
    <option value="5"<c:if test="${user.acc_inf == 5}"> selected</c:if>>その他（詳細はコメントに記入）</option>
</select>
<br /><br />

<label for="comment">コメント</label><br />
<textarea name="comment" rows="10" cols="50">${card.comment}</textarea>
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">投稿</button>