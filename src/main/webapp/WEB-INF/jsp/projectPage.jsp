<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="header.jsp"%>
<div class="container-fluid">
	<div class="row title-lable">
		<div class="col-lg-12">
			<span type="button" class="btn btn-success">项目信息</span>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div id="toolbar">
				<button id="btn_add" type="button" class="btn btn-default" data-toggle="modal" data-target="#exampleModal"
				 data-whatever="@mdo"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>项目新增</button>
				<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="exampleModalLabel">项目新增</h4>
							</div>
							<form action="/project/insert" method="post">
								<div class="modal-body">
									<div class="form-group">
										<label for="recipient-name" class="control-label">请输入项目名称</label>
										<input type="text" class="form-control" id="recipient-name" name="projectName">
									</div>
									<div class="form-group">
										<label for="recipient-name" class="control-label">请输入SO</label>
										<input type="text" class="form-control" id="recipient-name" name="projectCode">
									</div>									
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
									<button type="submit" class="btn btn-primary" on>保存</button>
								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="modal fade" id="ModifyProject" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="exampleModalLabel">项目修改</h4>
							</div>
							<form action="/project/update" method="post">
								<div class="modal-body">
									<input id="ModifyProjectID" name="projectID" value="" hidden="true" />
									<div class="form-group">
										<label for="recipient-name" class="control-label">请输入项目名称</label>
										<input type="text" class="form-control" id="ModifyProjectName" name="projectName">
									</div>
									<div class="form-group">
										<label for="recipient-name" class="control-label">请输入SO</label>
										<input type="text" class="form-control" id="ModifyProjectCode" name="projectCode">
									</div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
									<button type="submit" class="btn btn-primary" on>保存</button>
								</div>
							</form>
						</div>
					</div>
				</div>
				<!-- <button onclick="releaseWorkOrder()" class="btn btn-default"><span class="glyphicon glyphicon-file" aria-hidden="true"></span>工单下发</button> -->
			</div>
			<table id="table"></table>
		</div>
	</div>
</div>
<script>
	var navLabel = document.getElementById("nav_project");
	navLabel.classList.add("active");
	$('#table').bootstrapTable({
		url: '/projectRest/findList',
		method: 'get', //请求方式（*）
		contentType: "application/x-www-form-urlencoded", //必须要有！！！！
		toolbar: '#toolbar', //工具按钮用哪个容器
		striped: true, //是否显示行间隔色
		cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination: true, //是否显示分页（*）
		search: true,
		showRefresh: true,
		sidePagination: "client", //分页方式：client客户端分页，server服务端分页（*）
		pageNumber: 1, //初始化加载第一页，默认第一页
		pageSize: 8, //每页的记录行数（*）
		pageList: [8, 50, 100],
		buttonsAlign: 'left',
		searchAlign: 'left',
		toolbarAlign: 'right',
		showFooter: false,
		showColumns: true,
		detailView: false,
		columns: [{
			field: 'projectID',
			title: '序号',
			align: 'center',
		}, {
			field: 'projectName',
			title: '项目名称',
			align: 'center',
		}, {
			field: 'projectCode',
			title: 'SO号',
			align: 'center',
		}, {
			field: '',
			title: '操作',
			align: 'center',
			formatter: function(value, row, index) {
				return ['<button onclick="modifyProject(' + row.projectID +
					')" class="btn btn-success"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改</button>'
				].join('');
			}
		}]
	})
	function modifyProject(projectID) {
		$.ajax({
			url: '/projectRest/find?projectID=' + projectID,
			type: 'GET',
			success: function(data) {
				row = data;
				$("#ModifyProjectID").val(row.projectID);
				$("#ModifyProjectName").val(row.projectName);
				$("#ModifyProjectCode").val(row.projectCode);
				$('#ModifyProject').modal('show');
			}
		});
	}
</script>
<%@include file="footer.jsp"%>
