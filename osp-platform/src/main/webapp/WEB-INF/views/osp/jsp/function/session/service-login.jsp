<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/osp/jsp/common/init.jsp" %>

<!-- BEGIN LOGIN -->
<div id="login" class="extend"> 
	<!-- BEGIN LOGIN FORM -->
	<form id="loginform" class="form-vertical no-padding no-margin" onsubmit="return false;">
		<div class="row site-title">
    		<div class="col-md-12 text-center">
      			<h3>Operation Service Platform</h3>
    		</div>
  		</div>
  		<div class="row">
    		<div class="col-md-6" style='margin-left: 25%;'>
      			<div class="alert alert-error display-hide">
        			<button class="close" data-close="alert"></button>
        			<span> Enter any username and password. </span> 
        		</div>
      			<div class="form-group">
        			<label class="control-label visible-ie8 visible-ie9">帳號名稱</label>
        			<div class="input-icon"> <i class="fa fa-user"></i>
          				<input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="帳號名稱" id="ntaccount" name="ntaccount"/>
        			</div>
      			</div>
      			<div class="form-group">
        			<label class="control-label visible-ie8 visible-ie9">帳號密碼</label>
        			<div class="input-icon"> <i class="fa fa-lock"></i>
          				<input class="form-control placeholder-no-fix" type="password" autocomplete="off" placeholder="帳號密碼" id="password" name="password"/>
        			</div>
      			</div>
      			
      			<!-- 開發模式使用欄位 -->
      			<c:if test="${IS_DEV_MODE}">
	      			<div class="form-group">
	        			<label class="control-label visible-ie8 visible-ie9">員工編號</label>
	        			<div class="input-icon"> <i class="fa fa-lock"></i>
	          				<input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="員工編號" id="empNo" name="empNo" value="65196"/>
	        			</div>
	      			</div>
      			</c:if>
      			
      			<div class="form-group">
        			<div class="col-md-8">
          				<div class="form-actions right">
            				<button type="button" id="loginBtn" class="btn btn-primary"><i class="icon-lock"></i> 登入系統</button>
         				 </div>
        			</div>
      			</div>
    		</div>
  		</div>
	</form>
	<!-- END LOGIN FORM --> 
</div>
<!-- END LOGIN --> 

<script>
	require(['../../module/session/session-login-main']);
</script>