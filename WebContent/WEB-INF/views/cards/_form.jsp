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

<label for="tamperature">検温結果</label><br />
<input type="text" name="temperature" value="${card.temperature}" />
<br /><br />

<label for="attendance">出席</label><br />
<textarea name="attendance" rows="10" cols="50">${card.attendance}</textarea>
<br /><br />

<label for="comment">コメント</label><br />
<textarea name="comment" rows="10" cols="50">${card.comment}</textarea>
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">投稿</button>