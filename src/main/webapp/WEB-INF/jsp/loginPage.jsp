<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="header.jsp"%>
<div class="container-fluid">
	<div class="row">
		<div class="col-xs-9">
			<img class="img-responsive" src="image/login.jpg">
		</div>
		<div class="col-xs-3 loginbackground">
			<br>
			<form id="defaultForm" action="login" method="post" class="form-horizontal">
				<div class="form-group">
					<div class="col-sm-offset-3 col-sm-9">
						<br /><br /><br />
						<h4 class="text-danger">${error}</h4>
					</div>
					<label for="inputSESAID" class="col-sm-3 control-label">账号:</label>
					<div class="col-sm-9">
						<input name="userName" type="SESAID" class="form-control" id="SESAIDs" placeholder="SESAID">
						<span class="help-block" id="sesaid" />
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-3 control-label">密码:</label>
					<div class="col-sm-9">
						<input name="password" type="password" class="form-control" id="inputPassword3" placeholder="Password">
						<span class="help-block" id="password" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-3 col-sm-2">
						<button type="submit" class="btn btn-default">登录</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<%@include file="footer.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		$('#defaultForm').bootstrapValidator({
			message: 'This value is not valid',
			feedbackIcons: {
				valid: 'glyphicon glyphicon-ok',
				invalid: 'glyphicon glyphicon-remove',
				validating: 'glyphicon glyphicon-refresh'
			},
			fields: {
				'staff.sesaid': {
					container: '#sesaid',
					validators: {
						notEmpty: {
							message: 'The SESAID is required and cannot be empty'
						}
					}
				},
				'staff.password': {
					container: '#password',
					validators: {
						notEmpty: {
							message: 'The Password is required and cannot be empty'
						}
					}
				}
			}
		});
	});
</script>
</body>
</html>
