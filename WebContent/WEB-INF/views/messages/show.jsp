<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${message != null}">
                <h2>メッセージ　詳細ページ</h2>

                <table class="message_show_table">
                    <tbody>
                        <tr>
                            <th>メッセージID</th>
                            <td><c:out value="${message.id}" /></td>
                        </tr>
                        <tr>
                            <th>作成者</th>
                            <td><c:out value="${message.user.name}" /></td>
                        </tr>
                        <tr>
                            <th>日付</th>
                            <td><fmt:formatDate value="${message.message_date}" pattern="yyyy-MM-dd" /></td>
                        </tr>
                        <tr>
                            <th>送信先グループ</th>
                            <td><c:out value="${message.group.group_name}" /></td>
                        </tr>
                        <tr>
                            <th>件名</th>
                            <td><c:out value="${message.title}" /></td>
                        </tr>
                        <tr>
                            <th>メッセージ内容</th>
                            <td><c:out value="${message.message}" /></td>
                        </tr>
                        <tr>
                            <th>登録日時</th>
                            <td>
                                <fmt:formatDate value="${message.created_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                        <tr>
                            <th>更新日時</th>
                            <td>
                                <fmt:formatDate value="${message.updated_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                    </tbody>
                </table>

                <c:if test="${sessionScope.login_user.id == message.user.id}">
                    <p><a href="<c:url value="/messages/edit?id=${message.id}" />">このメッセージを編集する</a></p>
                </c:if>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value="/messages/index" />">一覧に戻る</a></p>
    </c:param>
</c:import>