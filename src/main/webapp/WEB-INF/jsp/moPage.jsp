<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="header.jsp"%>
<div class="container-fluid">
	<div class="row title-lable">
		<div class="col-lg-12">
			<span type="button" class="btn btn-success">MO信息</span>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div id="toolbar">
				<button id="btn_add" type="button" class="btn btn-default" data-toggle="modal" data-target="#exampleModal"
				 data-whatever="@mdo"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>MO新增</button>
				<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="exampleModalLabel">MO新增</h4>
							</div>
							<form action="/mo/upload" method="post" enctype="multipart/form-data">
								<div class="modal-body">
									<div class="form-group">
									    <label for="exampleInputFile">文件上传：</label>
									    <input name="file" type="file" id="InputFile">
									    <p class="help-block">请确认文件模板正确！</p>
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
				<div class="modal fade" id="ModifyBatch" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="exampleModalLabel">Batch修改</h4>
							</div>
							<form action="/batch/update" method="post">
								<div class="modal-body">
									<input id="ModifyBatchID" name="batchID" value="" hidden="true" />
									<div class="form-group">
										<select id="ModifyProjectID" class="form-control" name="projectID">
											<option value="">请选择项目</option>
											<c:forEach items="${projects}" var="project">
												<option value="${project.projectID}">${project.projectName}-${project.projectCode}</option>
											</c:forEach>
										</select>
									</div>
									<div class="form-group">
										<label for="recipient-name" class="control-label">请输入Batch名称</label>
										<input type="text" class="form-control" id="ModifyBatchName" name="BatchName">
									</div>
									<div class="form-group">
										<label for="recipient-name" class="control-label">请输入WBS</label>
										<input type="text" class="form-control" id="ModifyBatchCode" name="BatchCode">
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
				<a href="/mo/download" id="btn_add" type="button" class="btn btn-default">
					<span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span>导出
				</a>
			</div>
			<table id="table"></table>
		</div>
	</div>
</div>
<script>
	var navLabel = document.getElementById("nav_project");
	navLabel.classList.add("active");
	$('#table').bootstrapTable({
		url: '/moTableRest/list',
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
			field: 'moID',
			title: '序号',
			align: 'center',
		}, {
			field: 'projectName',
			title: '项目名称',
			align: 'center',
		}, {
			field: 'so',
			title: 'SO号',
			align: 'center',
		},{
			field: 'wbs',
			title: 'WBS号',
			align: 'center',
		},{
			field: 'mo',
			title: 'MO号',
			align: 'center',
		},{
			field: 'productClassifyName',
			title: '类型',
			align: 'center',
		},{
			field: 'productModelName',
			title: '型号',
			align: 'center',
		},{
			field: 'materialDate',
			title: '到料日期',
			align: 'center',
			formatter: function(value, row, index) {
				return changeDateFormat(value, 0)
			}
		},{
			field: 'moStartDate',
			title: '排产日期',
			align: 'center',
			formatter: function(value, row, index) {
				return changeDateFormat(value, 0)
			}
		},{
			field: 'processName',
			title: '产线',
			align: 'center',
		},{
			field: 'lt',
			title: 'LT(D)',
			align: 'center',
		},{
			field: 'availableDays',
			title: '可用天数',
			align: 'center',
		},{
			field: 'deliveryDate',
			title: '发货日期',
			align: 'center',
			formatter: function(value, row, index) {
				return changeDateFormat(value, 0)
			}
		},{
			field: '',
			title: '操作',
			align: 'center',
			formatter: function(value, row, index) {
				return ['<button disabled="true" onclick="modifyBatch(' + row.batchID +
					')" class="btn btn-success"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改</button>'
				].join('');
			}
		}]
	})
	function modifyBatch(batchID) {
		$.ajax({
			url: '/batchRest/find?batchID=' + batchID,
			type: 'GET',
			success: function(data) {
				row = data;
				$("#ModifyBatchID").val(row.batchID);
				$("#ModifyProjectID").val(row.projectID);
				$("#ModifyBatchName").val(row.batchName);
				$("#ModifyBatchCode").val(row.batchCode);
				$('#ModifyBatch').modal('show');
			}
		});
	}
</script>
<%@include file="footer.jsp"%>
