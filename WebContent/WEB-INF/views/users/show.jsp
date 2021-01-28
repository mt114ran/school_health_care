<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${user != null}">
                <h2>id : ${user.id} のユーザー情報　詳細ページ</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>ユーザー番号</th>
                            <td><c:out value="${user.code}" /></td>
                        </tr>
                        <tr>
                            <th>氏名</th>
                            <td><c:out value="${user.name}" /></td>
                        </tr>
                        <tr>
                            <th>アカウント情報</th>
                            <td>
                                <c:choose>
                                    <c:when test="${user.acc_inf == 0}">先生</c:when>
                                    <c:when test="${user.acc_inf == 1}">生徒</c:when>
                                    <c:otherwise>保護者</c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <th>登録日時</th>
                            <td>
                                <fmt:formatDate value="${user.created_at}" pattern="yyyy年 M月 d日（E） HH:mm:ss" />
                            </td>
                        </tr>
                        <tr>
                            <th>更新日時</th>
                            <td>
                                <fmt:formatDate value="${user.updated_at}" pattern="yyyy年 M月 d日（E） HH:mm:ss" />
                            </td>
                        </tr>
                    </tbody>
                </table>

                <p><a href="<c:url value='/users/edit?id=${user.id}' />">このユーザー情報を編集する</a></p>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value='/users/index' />">一覧に戻る</a></p>
    </c:param>
</c:import>