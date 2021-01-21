<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${group != null}">
                <h2>グループ情報　詳細ページ</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>グループID</th>
                            <td><c:out value="${group.id}" /></td>
                        </tr>
                        <tr>
                            <th>グループ名</th>
                            <td><c:out value="${group.group_name}" /></td>
                        </tr>
                        <tr>
                            <th>登録日時</th>
                            <td>
                                <fmt:formatDate value="${group.created_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                        <tr>
                            <th>更新日時</th>
                            <td>
                                <fmt:formatDate value="${group.updated_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                    </tbody>
                </table>

                <p><a href="<c:url value="/groups/edit?id=${group.id}" />">グループ情報を編集する</a></p>

            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <h2>参加メンバー</h2>
        <table id="member_list">
            <tbody>
                <tr>
                    <th>ユーザー番号</th>
                    <th>氏名</th>
                    <th>アカウント種類</th>
                    <th>操作</th>
                </tr>
                <c:forEach var="member" items="${members}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td><c:out value="${member.code}" /></td>
                        <td><c:out value="${member.name}" /></td>
                        <td>
                            <c:choose>
                                <c:when test="${member.acc_inf == 0}">先生</c:when>
                                <c:when test="${member.acc_inf == 1}">生徒</c:when>
                                <c:otherwise>保護者</c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${member.delete_flag == 1}">
                                    （削除済み）
                                </c:when>
                                <c:otherwise>
                                    <a href="<c:url value='/users/show?id=${user.id}' />">詳細を表示</a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>


        <p><a href="<c:url value="/group_members/edit" />">グループメンバーの登録・削除</a></p>
        <p><a href="<c:url value="/groups/index" />">グループ情報一覧に戻る</a></p>
    </c:param>
</c:import>
