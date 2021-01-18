<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${family != null}">
                <h2>家族データ　詳細ページ</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>家族データID</th>
                            <td><c:out value="${family.id}" /></td>
                        </tr>
                        <tr>
                            <th>生徒のユーザー番号</th>
                            <td><c:out value="${family.student}" /></td>
                        </tr>
                        <tr>
                            <th>保護者のユーザー番号</th>
                            <td><c:out value="${family.parent}" /></td>
                        </tr>
                        <tr>
                            <th>登録日時</th>
                            <td>
                                <fmt:formatDate value="${family.created_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                        <tr>
                            <th>更新日時</th>
                            <td>
                                <fmt:formatDate value="${family.updated_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                    </tbody>
                </table>

                <c:if test="${sessionScope.login_user.id == card.user.id}">
                    <p><a href="<c:url value="/familis/edit?id=${family.id}" />">この家族データを編集する</a></p>
                </c:if>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value="/families/index" />">一覧に戻る</a></p>
    </c:param>
</c:import>