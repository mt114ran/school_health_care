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
<input type="date" name="message_date" value="<fmt:formatDate value='${message.message_date}' pattern='yyyy-MM-dd' />" />
<br /><br />

<label for="name">氏名</label><br />
<c:out value="${sessionScope.login_user.name}" />
<br /><br />

<c:choose>
    <c:when test="${groups_count == 0}">
       <p>※参加しているグループがありません。<br /><a href="<c:url value="/groups/index" />">コチラからグループの作成または参加を行ってください。</a></p>
    </c:when>
    <c:otherwise>
        <label for="group">メッセージ先のグループ</label><br />
        <select name="group">
            <c:forEach var="group" items="${groups}" varStatus="status">
                <option value="${group.id}"<c:if test="${card.attendance == 0}"> selected</c:if>>${group.group_name}</option>
            </c:forEach>
        </select>
    </c:otherwise>
</c:choose>

<br /><br />

<label for="title">件名</label><br />
<input type="text" name="title" value="${message.title}" />
<br /><br />

<label for="message">メッセージ</label><br />
<textarea name="message" rows="10" cols="50">${message.message}</textarea>
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">登録</button>