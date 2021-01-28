<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${card != null}">
                <h2>カード　詳細ページ</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>氏名</th>
                            <td><c:out value="${card.user.name}" /></td>
                        </tr>
                        <tr>
                            <th>日付</th>
                            <td><fmt:formatDate value="${card.card_date}" pattern="yyyy年 M月 d日（E）" /></td>
                        </tr>
                        <tr>
                            <th>検温結果</th>
                            <td>
                                <pre><c:out value="${card.temperature}" /></pre>
                            </td>
                        </tr>
                        <tr>
                            <th>出席情報</th>
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
                        </tr>
                        <tr>
                            <th>コメント</th>
                            <td>
                                <pre><c:out value="${card.comment}" /></pre>
                            </td>
                        </tr>
                        <tr>
                            <th>登録日時</th>
                            <td>
                                <fmt:formatDate value="${card.created_at}" pattern="yyyy年 M月 d日（E） HH:mm:ss" />
                            </td>
                        </tr>
                        <tr>
                            <th>更新日時</th>
                            <td>
                                <fmt:formatDate value="${card.updated_at}" pattern="yyyy年 M月 d日（E） HH:mm:ss" />
                            </td>
                        </tr>
                    </tbody>
                </table>

                <c:if test="${sessionScope.login_user.id == card.user.id}">
                    <p><a href="<c:url value="/cards/edit?id=${card.id}" />">この日報を編集する</a></p>
                </c:if>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value="/cards/index" />">一覧に戻る</a></p>
    </c:param>
</c:import>