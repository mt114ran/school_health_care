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


<c:choose>
    <c:when test="${students_count == 0}">
       <p>※生徒のアカウントデータがありません。<br /><a href="<c:url value="/users/new" />">こちらから生徒アカウントを作成してください。</a></p>
    </c:when>
    <c:otherwise>
        <label for="student_code">生徒のユーザー番号を選択して下さい</label><br />
        <select name="student_code">
            <c:forEach var="student" items="${students}" varStatus="status">
                <option value="${student.code}"<c:if test="${student.id == 0}"> selected</c:if>>${student.name}</option>
            </c:forEach>
        </select>
    </c:otherwise>
</c:choose>

<br><br>

<c:choose>
    <c:when test="${parents_count == 0}">
       <p>※保護者のアカウントデータがありません。<br /><a href="<c:url value="/users/new" />">こちらから保護者アカウントを作成してください。</a></p>
    </c:when>
    <c:otherwise>
        <label for="parent_code">保護者のユーザー番号を選択して下さい</label><br />
        <select name="parent_code">
            <c:forEach var="parent" items="${parents}" varStatus="status">
                <option value="${parent.code}"<c:if test="${parent.id == 0}"> selected</c:if>>${parent.name}</option>
            </c:forEach>
        </select>
    </c:otherwise>
</c:choose>

<br><br>

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">家族情報を登録</button>