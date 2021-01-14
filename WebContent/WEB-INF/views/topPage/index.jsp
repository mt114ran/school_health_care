<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>SCHOOL HEALTH CAREへようこそ</h2>
        <h3>【自分のカード　一覧】</h3>
        <table id="card_list">
            <tbody>
                <tr>
                    <th class="user_name">ユーザー名</th>
                    <th class="card_date">日付</th>
                    <th class="temperature">検温結果</th>
                    <th class="attendance">出席</th>
                    <th class="action">操作</th>
                </tr>
                <c:forEach var="card" items="${cards}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="user_name"><c:out value="${card.user.name}" /></td>
                        <td class="card_date"><fmt:formatDate value='${card.card_date}' pattern='yyyy-MM-dd' /></td>
                        <td class="temperature">${card.temperature}</td>
                        <td>
                            <c:choose>
                                <c:when test="${card.attendance == 0}">出席</c:when>
                                <c:when test="${card.attendance == 1}">遅刻</c:when>
                                <c:when test="${card.attendance == 2}">欠席</c:when>
                                <c:when test="${card.attendance == 3}">忌引き</c:when>
                                <c:when test="${card.attendance == 4}">出席停止</c:when>
                                <c:otherwise>その他</c:otherwise>
                            </c:choose>
                        </td>
                        <td class="action"><a href="<c:url value='/cards/show?id=${card.id}' />">詳細を見る</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${cards_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((cards_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="<c:url value='/cards/new' />">新規カードの登録</a></p>
    </c:param>
</c:import>