<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>区域设置</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.ocupload-1.1.2.js"></script>
<!-- 导入easyui类库 -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/default.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
<script src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script type="text/javascript">
	function doAdd() {
		$('#addRegionWindow').window("open");
	}

	function doView() {
		var arr = $('#grid').datagrid("getSelections");
		if(arr.length==1){
			$('#updateRegionWindow').window("open");
			$('#updateRegionWindow').form("load", arr[0]);
			$('input[name="id"]').val(rowData.id);
		}else{
			$.messager.alert("提示信息", "请选择一列数据", "warning");
		}
	}

	function doDelete() {
		var arr = $('#grid').datagrid("getSelections");
		if(arr.length>0){
			 $.messager.confirm("提示窗口", "是否确认删除", function(r){
				 var arrr=new Array();
				 for(var i=0;i<arr.length;i++){
					    var staff = arr[i];
					    var id=staff.id;
		                arrr.push(id);
		            } 
				 var ids=arrr.join(",");
				 location.href = "regionAction_delete.action?ids=" + ids;
			 }
			 )
		}else{
			$.messager.alert("提示信息", "请选择数据", "warning");
		}
	}

	//工具栏
	var toolbar = [ {
		id : 'button-edit',
		text : '修改',
		iconCls : 'icon-edit',
		handler : doView
	}, {
		id : 'button-add',
		text : '增加',
		iconCls : 'icon-add',
		handler : doAdd
	}, {
		id : 'button-delete',
		text : '删除',
		iconCls : 'icon-cancel',
		handler : doDelete
	}, {
		id : 'button-import',
		text : '导入',
		iconCls : 'icon-redo'
	} ];
	// 定义列
	var columns = [ [ {
		field : 'id',
		checkbox : true,
	}, {
		field : 'province',
		title : '省',
		width : 120,
		align : 'center'
	}, {
		field : 'city',
		title : '市',
		width : 120,
		align : 'center'
	}, {
		field : 'district',
		title : '区',
		width : 120,
		align : 'center'
	}, {
		field : 'postcode',
		title : '邮编',
		width : 120,
		align : 'center'
	}, {
		field : 'shortcode',
		title : '简码',
		width : 120,
		align : 'center'
	}, {
		field : 'citycode',
		title : '城市编码',
		width : 200,
		align : 'center'
	} ] ];

	$(function() {
		// 先将body隐藏，再显示，不会出现页面刷新效果
		$("body").css({
			visibility : "visible"
		});

		// 收派标准数据表格
		$('#grid').datagrid({
			iconCls : 'icon-forward',
			fit : true,
			border : false,
			rownumbers : true,
			striped : true,
			pageList : [ 30, 50, 100 ],
			pagination : true,
			toolbar : toolbar,
			url : "regionAction_pageQuery.action",
			idField : 'id',
			columns : columns,
			onDblClickRow : doDblClickRow
		});
		
		// 添加文件上传
		$("#button-import").upload({
			action:'regionAction_uploadXml.action',
			name:'regionFile'
		});

		// 添加、修改区域窗口
		$('#addRegionWindow').window({
			title : '添加修改区域',
			width : 400,
			modal : true,
			shadow : true,
			closed : true,
			height : 400,
			resizable : false
		});
		
		$('#updateRegionWindow').window({
            title : '修改区域',
            width : 400,
            modal : true,
            shadow : true,
            closed : true,
            height : 400,
            resizable : false
        });

	});

	function doDblClickRow(rowIndex,rowData) {
		$('#updateRegionWindow').window("open");
		$('#updateRegionWindow').form("load", rowData);
		$('input[name="id"]').val(rowData.id);
	}
	
	
	
	
</script>
</head>
<body class="easyui-layout" style="visibility: hidden;">
	<div region="center" border="false">
		<table id="grid"></table>
	</div>
	<div class="easyui-window" title="区域添加修改" id="addRegionWindow" collapsible="false" minimizable="false" maximizable="false" style="top: 20px; left: 200px">
		<div region="north" style="height: 31px; overflow: hidden;" split="false" border="false">
			<div class="datagrid-toolbar">
				<a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true">保存</a>
			</div>
		</div>
		<script type="text/javascript">
			$("#save").click(function(){
		        var v = $("#addForm").form("validate");
		        if(v){
		            $("#addForm").submit();
		        }else{
		            $.messager.alert("提示信息", "请确认表格数据", "warning");
		        }
		    })
		</script>

		<div region="center" style="overflow: auto; padding: 5px;" border="false">
			<form id="addForm" action="regionAction_update.action" method="post">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">区域信息</td>
					</tr>
					<tr>
						<td>省</td>
						<td><input type="text" name="province" class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td>市</td>
						<td><input type="text" name="city" class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td>区</td>
						<td><input type="text" name="district" class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td>邮编</td>
						<td><input type="text" name="postcode" class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td>简码</td>
						<td><input type="text" name="shortcode" class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td>城市编码</td>
						<td><input type="text" name="citycode" class="easyui-validatebox" required="true" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
	
	   <div class="easyui-window" title="区域修改" id="updateRegionWindow" collapsible="false" minimizable="false" maximizable="false" style="top: 20px; left: 200px">
        <div region="north" style="height: 31px; overflow: hidden;" split="false" border="false">
            <div class="datagrid-toolbar">
                <a id="update" icon="icon-save" href="#" class="easyui-linkbutton" plain="true">保存</a>
            </div>
        </div>
        <script type="text/javascript">
            $("#update").click(function(){
                var v = $("#updateForm").form("validate");
                if(v){
                    $("#updateForm").submit();
                }else{
                    $.messager.alert("提示信息", "请确认表格数据", "warning");
                }
            })
        </script>

        <div region="center" style="overflow: auto; padding: 5px;" border="false">
            <form id="updateForm" action="regionAction_update.action" method="post">
                <table class="table-edit" width="80%" align="center">
                
                    <tr>
                        <td><input type="hidden" name="id"></td>
                    </tr>
                    <tr class="title">
                        <td colspan="2">区域信息</td>
                    </tr>
                    <tr>
                        <td>省</td>
                        <td><input type="text" name="province" class="easyui-validatebox" required="true" /></td>
                    </tr>
                    <tr>
                        <td>市</td>
                        <td><input type="text" name="city" class="easyui-validatebox" required="true" /></td>
                    </tr>
                    <tr>
                        <td>区</td>
                        <td><input type="text" name="district" class="easyui-validatebox" required="true" /></td>
                    </tr>
                    <tr>
                        <td>邮编</td>
                        <td><input type="text" name="postcode" class="easyui-validatebox" required="true" /></td>
                    </tr>
                    <tr>
                        <td>简码</td>
                        <td><input type="text" name="shortcode" class="easyui-validatebox" required="true" /></td>
                    </tr>
                    <tr>
                        <td>城市编码</td>
                        <td><input type="text" name="citycode" class="easyui-validatebox" required="true" /></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</body>
</html>