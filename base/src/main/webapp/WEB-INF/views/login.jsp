<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>前台收银-登录</title>
<link rel="icon" href="${ctx}/static/images/favicon.ico" type="image/x-icon" /> 
<link rel="shortcut icon" href="${ctx}/static/images/favicon.ico" type="image/x-icon" />  
<link href="${ctx}/static/css/login.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/css/toastr.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/static/css/select.css" rel="stylesheet" type="text/css" />

<script src="${ctx}/static/jquery/jquery-1.9.1.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/static/jquery/jquery.cookie.js"></script>
<script src="${ctx}/static/js/toastr.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/static/js/select.js"></script>
<script type="text/javascript" src="${ctx}/static/js/keyboard.js"></script>

<script type="text/javascript" src="${ctx}/static/js/ProgressbarUtil.js"></script>

<script type="text/javascript" src="${ctx}/static/js/login.js"></script>


	<!--[if IE 6]>
	<script src="${ctx}/static/js/DD_belatedPNG_0.0.8a.js"></script>
	<script> DD_belatedPNG.fix('.login_header_l,.login_header_r,.but_login,.login_jianpan,.small_delete,span,div,a');</script>
	<![endif]-->
<script type="text/javascript">
$(document).ready(function(){
	//TODO 测试用
	var binfo = "";
	var Sys = {}; 
    var ua = navigator.userAgent.toLowerCase(); 
    var s; 
    (s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] : 
    (s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] : 
    (s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] : 
    (s = ua.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] : 
    (s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;
        //以下进行测试
        if(Sys.ie) binfo = 'IE: '+Sys.ie;
        if(Sys.firefox) binfo = 'Firefox: '+Sys.firefox;
        if(Sys.chrome) binfo = 'Chrome: '+Sys.chrome;
        if(Sys.opera) binfo = 'Opera: '+Sys.opera;
        if(Sys.safari) binfo = 'Safari: '+Sys.safari;
        
    //toastr.info("(浏览器版本："+binfo+")");
    var site_url = window.location.href.toLowerCase();	
    if(site_url.indexOf("relogin=1") > 0)
    {
    	toastr.error('长时间没有登录，请重新登录！');
    }
        
});

</script>

</head>

<body id="body">
<form:form id="loginForm"  action="${ctx}/login" method="post">

	<div class="login_wrap">
		<div class="login_header">
			<div class="login_header_l"></div>
			<div class="login_header_c">
				<h3>
				前台收银 ${sysVersion.verNumber}
				</h3>
			</div>
			<div class="login_header_r"></div>
		</div>
		<div class="login_body">
			<div class="login_cont">
				<h4>
					<c:if test="${fn:length(restaurants) == 1 }">
					<c:if test="${restName != null && restName != ''}">
						<c:if test="${fn:length(restName) <= 3 }">
							&nbsp;&nbsp;&nbsp;&nbsp;${restName}&nbsp;&nbsp;&nbsp;&nbsp;
						</c:if>
						<c:if test="${fn:length(restName)>3 && fn:length(restName) < 15}">
							${restName}
						</c:if>
						<c:if test="${fn:length(restName)>=15}">
							<span style="margin-top: -20px;display: block;">${restName}</span>
						</c:if>
					</c:if>
					<c:if test="${restName == null || restName == ''}">
					前台收银
					</c:if>
					</c:if>
					
					<c:if test="${fn:length(restaurants) > 1 }">
						<div class="system_select">
							<div class="uboxstyle">
								<select name="restId" id="restId">
									<c:forEach items="${restaurants}" var="restaurant" varStatus="status">
										<option value="${restaurant.restId}" <c:if test="${restId==restaurant.restId || status.index == 0}">selected="selected"</c:if>>${restaurant.restName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</c:if>
					
						
				</h4>
				<div class="login">
					<span>账号</span>
					<input id="uname" name="uname" value="${username}" type="text">
					
					<input id="username" name="username" value="" type="hidden">
				</div>
				<div class="login">
					<span>密码</span>
					<input id="password" name="password" type="password" type="password" >
					<input type="checkbox" id="rememberMe" name="rememberMe" style="display: none;" checked="checked"/>
				</div>
				<div class=login_hint>
					<span>
						<%
					String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
					if(error != null){
					%>
					帐号或密码错误 (默认帐号：01 &nbsp;&nbsp;&nbsp; 默认密码：01 )
					<%
					}
					else
					{
					%>
					默认帐号：01 &nbsp;&nbsp;&nbsp; 默认密码：01
					<%
					}
					%>
					</span>
				</div>
				<div class="login">
					<a class="but_login" href="###" style="cursor:pointer;" onclick="login();"></a>
				</div>
			</div>
		</div>
	</div>
	
	<div class="login_jianpan" id="keyboard">
		<a style="cursor:pointer;" id="49" value="1" href="###" class="shuzi ml_7">1</a>
		<a style="cursor:pointer;" id="50" value="2" href="###" class="shuzi">2</a>
		<a style="cursor:pointer;" id="51" value="3" href="###" class="shuzi">3</a>
		<a style="cursor:pointer;" id="52" value="4" href="###" class="shuzi">4</a>
		<a style="cursor:pointer;" id="53" value="5" href="###" class="shuzi">5</a>
		<a style="cursor:pointer;" id="54" value="6" href="###" class="shuzi">6</a>
		<a style="cursor:pointer;" id="55" value="7" href="###" class="shuzi">7</a>
		<a style="cursor:pointer;" id="56" value="8" href="###" class="shuzi">8</a>
		<a style="cursor:pointer;" id="57" value="9" href="###" class="shuzi">9</a>
		<a style="cursor:pointer;" id="48" value="0" href="###" class="shuzi">0</a>
		<a style="cursor:pointer;" id="81" value="Q" href="###" class="zimu ml_37">q</a>
		<a style="cursor:pointer;" id="87" value="W" href="###" class="zimu">w</a>
		<a style="cursor:pointer;" id="69" value="E" href="###" class="zimu">e</a>
		<a style="cursor:pointer;" id="82" value="R" href="###" class="zimu">r</a>
		<a style="cursor:pointer;" id="84" value="T" href="###" class="zimu">t</a>
		<a style="cursor:pointer;" id="89" value="Y" href="###" class="zimu">y</a>
		<a style="cursor:pointer;" id="85" value="U" href="###" class="zimu">u</a>
		<a style="cursor:pointer;" id="73" value="I" href="###" class="zimu">i</a>
		<a style="cursor:pointer;" id="79" value="O" href="###" class="zimu">o</a>
		<a style="cursor:pointer;" id="80" value="P" href="###" class="zimu">p</a>
		<a style="cursor:pointer;" id="8" value="del" href="###" class="jp_delete"></a>
		<a style="cursor:pointer;" id="65" value="A" class="zimu ml_57" href="###">a</a>
		<a style="cursor:pointer;" id="83" value="S" href="###" class="zimu">s</a>
		<a style="cursor:pointer;" id="68" value="D" href="###" class="zimu">d</a>
		<a style="cursor:pointer;" id="70" value="F" href="###" class="zimu">f</a>
		<a style="cursor:pointer;" id="71" value="G" href="###" class="zimu">g</a>
		<a style="cursor:pointer;" id="72" value="H" href="###" class="zimu">h</a>
		<a style="cursor:pointer;" id="74" value="J" href="###" class="zimu">j</a>
		<a style="cursor:pointer;" id="75" value="K" href="###" class="zimu">k</a>
		<a style="cursor:pointer;" id="76" value="L" href="###" class="zimu">l</a>
		<a style="cursor:pointer;" href="###" id="13" value="ok" class="jp_queding"></a>
		<a style="cursor:pointer;" href="###" value="shift" class="jp_daxie ml_57"></a>
		<a style="cursor:pointer;" id="90" value="Z" href="###" class="zimu">z</a>
		<a style="cursor:pointer;" id="88" value="X" href="###" class="zimu">x</a>
		<a style="cursor:pointer;" id="67" value="C" href="###" class="zimu">c</a>
		<a style="cursor:pointer;" id="86" value="V" href="###" class="zimu">v</a>
		<a style="cursor:pointer;" id="66" value="B" href="###" class="zimu">b</a>
		<a style="cursor:pointer;" id="78" value="N" href="###" class="zimu">n</a>
		<a style="cursor:pointer;" id="77" value="M" href="###" class="zimu">m</a>
		<a style="cursor:pointer;" href="###"  value="shift" class="jp_daxie"></a>
	</div>
	
	</form:form>
</body>
</html>
