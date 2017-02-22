<%@tag pageEncoding="UTF-8"%>
<%@ attribute name="page" type="org.springframework.data.domain.Page" required="true"%>
<%@ attribute name="paginationSize" type="java.lang.Integer" required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
int current =  page.getNumber() + 1;
int begin = Math.max(1, current - paginationSize/2);
int end = Math.min(begin + (paginationSize - 1), page.getTotalPages());

request.setAttribute("current", current);
request.setAttribute("begin", begin);
request.setAttribute("end", end);
request.setAttribute("totalPage", page.getTotalPages());
request.setAttribute("totalElements", page.getTotalElements());
%>
	<% if (page.hasPreviousPage()){%>
             <a style="cursor:pointer;"  href="###"  onclick="CardListChange('${searchParams}',${current-1})" class="but_up"></a>
      <%}else{%>
             <a class="but_up_on"></a>
      <%} %>
   
    <% if (page.hasNextPage()){%>
            <a style="cursor:pointer;" href="###" onclick="CardListChange('${searchParams}',${current+1})" class="but_down"></a>		
      <%}else{%>
            <a class="but_down_on"></a>		
      <%} %>
