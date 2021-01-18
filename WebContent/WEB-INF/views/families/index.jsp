<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>家族関係　一覧</h2>
        <table id="families_list">

            <tbody>
                <tr>
                    <th>id</th>
                    <th>生徒ID</th>
                    <th>保護者ID</th>
                    <th>操作</th>
                </tr>
                <c:forEach var="family" items="${families}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td><c:out value="${family.id}" /></td>
                        <td><c:out value="${family.student}" /></td>
                        <td><c:out value="${family.parent}" /></td>
                        <td><a href="<c:url value='/families/show?id=${family.id}' />">詳細を見る</a></td>
                    </tr>
                </c:forEach>
            </tbody>

        </table>

        <div id="pagination">
            （全 ${families_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((families_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/families/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="<c:url value='/families/new' />">家族関係の登録</a></p>

    </c:param>
</c:import>