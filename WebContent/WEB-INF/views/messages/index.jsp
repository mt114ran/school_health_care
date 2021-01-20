<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>メッセージ　一覧</h2>
        <table id="message_list">
            <tbody>
                <tr>
                    <th class="message_id">メッセージID</th>
                    <th class="user_name">ユーザー名</th>
                    <th class="message_date">日付</th>
                    <th class="message_group">メッセージ先グループ</th>
                    <th class="message">メッセージ</th>
                    <th class="action">操作</th>
                </tr>
                <c:forEach var="message" items="${messages}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="message_id"><c:out value="${message.id}" /></td>
                        <td class="user_name"><c:out value="${message.user.name}" /></td>
                        <td class="message_date"><fmt:formatDate value='${message.message_date}' pattern='yyyy-MM-dd' /></td>
                        <td class="message_group"><c:out value="${message.group.group_name}" /></td>
                        <td class="message_comment">${message.message}</td>
                        <td class="action"><a href="<c:url value='/cards/show?id=${message.id}' />">詳細を見る</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${messages_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((messages_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/messsages/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="<c:url value='/messages/new' />">メッセージの登録</a></p>

    </c:param>
</c:import>l>