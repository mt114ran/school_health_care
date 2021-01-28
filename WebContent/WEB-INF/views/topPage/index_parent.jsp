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
        <h2>SCHOOL HEALTH CARE　トップページ</h2>



        <%-- 紐づいている生徒アカウントを表示する --%>
        <h3>【登録している家族アカウント】</h3>

        <div  style="margin-bottom:100px">
            <c:choose>
                <c:when test="${family_users != null}">
                    <table id="family_user_list">
                        <tbody>
                            <tr>
                                <th>ユーザー番号</th>
                                <th>氏名</th>
                                <th>操作</th>
                            </tr>
                            <c:forEach var="family_user" items="${family_users}" varStatus="status">
                                <tr class="row${status.count % 2}">
                                    <td><c:out value="${family_user.code}" /></td>
                                    <td><c:out value="${family_user.name}" /></td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${family_user.delete_flag == 1}">
                                                （削除済み）
                                            </c:when>
                                            <c:otherwise>
                                                <a href="<c:url value='/users/show?id=${family_user.id}' />">詳細を表示</a>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <h4>登録している家族アカウントはありません。</h4>
                </c:otherwise>
            </c:choose>
        </div>



        <%-- 子供に届いているメッセージを保護者アカウントのトップページに表示 --%>
        <h3>【先生のメッセージ】</h3>

        <div  style="margin-bottom:100px">
            <c:choose>
                <c:when test="${messages != null}">
                    <table id="message_list">
                        <tbody>
                            <tr>
                                <th class="message_date">日付</th>
                                <th class="message_group">グループ</th>
                                <th class="user_name">担当教員</th>
                                <th class="title">件名</th>
                                <th class="action">操作</th>
                            </tr>
                            <c:forEach var="message" items="${messages}" varStatus="status">
                                <tr class="row${status.count % 2}">
                                    <td class="message_date"><fmt:formatDate value='${message.message_date}' pattern='yyyy年 M月 d日（E）' /></td>
                                    <td class="message_group"><c:out value="${message.group.group_name}" /></td>
                                    <td class="user_name"><c:out value="${message.user.name}" /></td>
                                    <td class="message_title">${message.title}</td>
                                    <td class="action"><a href="<c:url value='/messages/show?id=${message.id}' />">詳細を見る</a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <h4>近日７日間のメッセージは届いていません。</h4>
                </c:otherwise>
            </c:choose>
        </div>



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