<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
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
		//alert("增加...");
		$('#addStaffWindow').window("open");
	}
	
	function doView() {
		$('#searchStaffWindow').window("open");
	}

	function doRestore(){
		var rows = $("#grid").datagrid("getSelections");
		if (rows.length > 0) {
            $.messager.confirm("确认还原", "确定要还原所选的取派员吗", function(r) {
                var array = new Array();
                if (r) {
                    for (var i = 0; i < rows.length; i++) {
                        var staff = rows[i];
                        var id = staff.id;
                        array.push(id);
                    }
                    var ids = array.join(",");
                    location.href = "staffAction_restore.action?ids=" + ids;
                }
            });
        } else {
            $.messager.alert("提示信息", "请选择需要删除的取派员", "warning");
        }
	}
	function doDelete() {
		var rows = $("#grid").datagrid("getSelections");
		if (rows.length > 0) {
			$.messager.confirm("确认删除", "你确定要删除所选择的取派员吗", function(r) {
				var array = new Array();
				if (r) {
					for (var i = 0; i < rows.length; i++) {
						var staff = rows[i];
						var id = staff.id;
						array.push(id);
					}
					var ids = array.join(",");
					location.href = "staffAction_delete.action?ids=" + ids;
				}
			});
		} else {
			$.messager.alert("提示信息", "请选择需要删除的取派员", "warning");
		}
	}

	//工具栏
	var toolbar = [ {
		id : 'button-view',
		text : '查询',
		iconCls : 'icon-search',
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
        id : 'button-restore',
        text : '还原',
        iconCls : 'icon-back',
        handler : doRestore
    },  ];
	// 定义列
	var columns = [ [ {
		field : 'id',
		checkbox : true,
	}, {
		field : 'name',
		title : '姓名',
		width : 120,
		align : 'center'
	}, {
		field : 'telephone',
		title : '手机号',
		width : 120,
		align : 'center'
	}, {
		field : 'haspda',
		title : '是否有PDA',
		width : 120,
		align : 'center',
		formatter : function(data, row, index) {
			if (data == "1") {
				return "有";
			} else {
				return "无";
			}
		}
	}, {
		field : 'deltag',
		title : '是否作废',
		width : 120,
		align : 'center',
		formatter : function(data, row, index) {
			if (data == "0") {
				return "正常使用"
			} else {
				return "已作废";
			}
		}
	}, {
		field : 'standard',
		title : '取派标准',
		width : 120,
		align : 'center'
	}, {
		field : 'station',
		title : '所谓单位',
		width : 200,
		align : 'center'
	} ] ];
	
	 $.fn.serializeJson=function(){  
	        var serializeObj={};  
	        var array=this.serializeArray();
	        $(array).each(function(){  
	            if(serializeObj[this.name]){  
	                if($.isArray(serializeObj[this.name])){  
	                    serializeObj[this.name].push(this.value);  
	                }else{  
	                    serializeObj[this.name]=[serializeObj[this.name],this.value];  
	                }  
	            }else{  
	                serializeObj[this.name]=this.value;   
	            }  
	        });  
	        return serializeObj;  
	    }; 

	$(function() {
		// 先将body隐藏，再显示，不会出现页面刷新效果
		$("body").css({
			visibility : "visible"
		});

		// 取派员信息表格
		$('#grid').datagrid({
			iconCls : 'icon-forward',
			fit : true,
			border : false,
			rownumbers : true,
			striped : true,
			pageList : [ 5, 10, 20 ],
			pagination : true,
			toolbar : toolbar,
			url : "staffAction_pageQuery.action",
			idField : 'id',
			columns : columns,
			onDblClickRow : doDblClickRow
		});

		// 添加取派员窗口
		$('#addStaffWindow').window({
			title : '添加取派员',
			width : 400,
			modal : true,
			shadow : true,
			closed : true,
			height : 400,
			resizable : false
		});
		
		$('#editStaffWindow').window({
			title : '修改取派员',
			width : 400,
			modal : true, // 遮盖
			shadow : true, // 阴影
			closed : true, // 默认关闭
			height : 400,
			resizable : false
		// 不可调节
		});
		
		$('#searchStaffWindow').window({
            title : '修改取派员',
            width : 400,
            modal : true, // 遮盖
            shadow : true, // 阴影
            closed : true, // 默认关闭
            height : 400,
            resizable : false
        // 不可调节
        });

	});

	function doDblClickRow(rowIndex, rowData) {
		// 显示
		$('#editStaffWindow').window("open");
		// 回显
		$('#editStaffWindow').form("load", rowData);

	}
</script>
</head>
<body class="easyui-layout" style="visibility: hidden;">
	<div region="center" border="false">
		<table id="grid"></table>
	</div>
	<div class="easyui-window" title="对收派员进行添加或者修改" id="addStaffWindow" collapsible="false" minimizable="false" maximizable="false" style="top: 20px; left: 200px">
		<div region="north" style="height: 31px; overflow: hidden;" split="false" border="false">
			<div class="datagrid-toolbar">
				<a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true">保存</a>
			</div>
		</div>

		<div region="center" style="overflow: auto; padding: 5px;" border="false">
			<form id="addStaff" action="staffAction_add.action" method="post">

				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">收派员信息</td>
					</tr>
					<!-- TODO 这里完善收派员添加 table -->
					<tr>
						<td>姓名</td>
						<td><input type="text" name="name" class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td>手机</td>
						<td><script type="text/javascript">
							// 保存按钮保定事件
							$("#save").click(function() {
								var v = $("#addStaff").form("validate");
								if (v) {
									$("#addStaff").submit();
								}
							})
							var reg = /^1[0-9]{10}$/;
							$.extend($.fn.validatebox.defaults.rules, {
								telephone : {
									validator : function(value, param) {
										return reg.test(value);
									},
									message : '手机号输入错误,请输入正确的手机号'

								}

							});
						</script> <input type="text" data-options="validType:'telephone'" name="telephone" class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td>单位</td>
						<td><input type="text" name="station" class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td colspan="2"><input type="checkbox" name="haspda" value="1" /> 是否有PDA</td>
					</tr>
					<tr>
						<td>取派标准</td>
						<td><input type="text" name="standard" class="easyui-validatebox" required="true" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>

	<div class="easyui-window" title="修改取派员" id="editStaffWindow" collapsible="false" minimizable="false" maximizable="false" style="top: 20px; left: 200px">
		<div region="north" style="height: 31px; overflow: hidden;" split="false" border="false">
			<div class="datagrid-toolbar">
				<a id="edit" icon="icon-save" href="#" class="easyui-linkbutton" plain="true">保存</a>
			</div>
		</div>

		<div region="center" style="overflow: auto; padding: 5px;" border="false">
			<form id="editStaff" action="staffAction_update.action" method="post">
				<input name="id" type="hidden">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">收派员信息</td>
					</tr>
					<!-- TODO 这里完善收派员添加 table -->
					<tr>
						<td>姓名</td>
						<td><input type="text" name="name" class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td>手机</td>
						<td><script type="text/javascript">
							// 保存按钮保定事件
							$("#edit").click(function() {
								var v = $("#editStaff").form("validate");
								if (v) {
									$("#editStaff").submit();
								}
							})
							var reg = /^1[0-9]{10}$/;
							$.extend($.fn.validatebox.defaults.rules, {

								telephone : {

									validator : function(value, param) {

										return reg.test(value);

									},

									message : '手机号输入错误,请输入正确的手机号'

								}

							});
						</script> <input type="text" data-options="validType:'telephone'" name="telephone" class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td>单位</td>
						<td><input type="text" name="station" class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td colspan="2"><input type="checkbox" name="haspda" value="1" /> 是否有PDA</td>
					</tr>
					<tr>
						<td>取派标准</td>
						<td><input type="text" name="standard" class="easyui-validatebox" required="true" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
	<div class="easyui-window" title="查询" id="searchStaffWindow" collapsible="false" minimizable="false" maximizable="false" style="top: 20px; left: 200px">
        <div region="north" style="height: 31px; overflow: hidden;" split="false" border="false">
            <div class="datagrid-toolbar">
                <a id="search" icon="icon-save" href="#" class="easyui-linkbutton" plain="true">查询</a>
            </div>
        </div>

        <div region="center" style="overflow: auto; padding: 5px;" border="false">
            <form id="searchForm" action="staffAction_search.action" method="post">
                <input name="id" type="hidden">
                <table class="table-edit" width="80%" align="center">
                    <tr class="title">
                        <td colspan="2">收派员信息</td>
                    </tr>
                    <!-- TODO 这里完善收派员添加 table -->
                    <tr>
                        <td>姓名</td>
                        <td><input type="text" name="name" id="" class="easyui-validatebox"  /></td>
                    </tr>
                    <tr>
                        <td>手机</td>
                        <td><script type="text/javascript">
                            // 查询按钮提交保存事件
                            $("#search").click(function() {
                            	if(!$("#deltag").is(':checked')){
                            		$("#deltag").val('0');
                            		$("#deltag").attr("checked",'true');
                            	}
                            	var p=$("#searchForm").serializeJson();
                                $("#grid").datagrid("load",p);
                                $("#searchStaffWindow").window("close");
                            })
                            
                            var reg = /^1[0-9]{10}$/;
                            $.extend($.fn.validatebox.defaults.rules, {

                                telephone : {

                                    validator : function(value, param) {

                                        return reg.test(value);

                                    },
                                    message : '手机号输入错误,请输入正确的手机号'
                                }

                            });
                        </script> <input type="text" data-options="validType:'telephone'" name="telephone" class="easyui-validatebox"  /></td>
                    </tr>
                    <tr>
                        <td>单位</td>
                        <td><input type="text" name="station" class="easyui-validatebox" /></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="checkbox" name="haspda" value="1" /> 是否有PDA</td>
                    </tr>
                     <tr>
                        <td colspan="2"><input type="checkbox" id="deltag" name="deltag" value="1" /> 是否作废</td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    
    
</body>
</html>
