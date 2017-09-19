<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理分区</title>
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
<script src="${pageContext.request.contextPath }/js/highcharts.js"></script>
<script src="${pageContext.request.contextPath }/js/modules/exporting.js"></script>
<script type="text/javascript">
	function doAdd() {
		$('#addSubareaWindow').window("open");
	}

	function doEdit() {
		var arr = $('#grid').datagrid("getSelections");
		if(arr.length==1){
			var subarea = arr[0].region.province+" "+arr[0].region.city+" "+arr[0].region.district;
	        $('#selectS').combobox("select",subarea);
	        $('#updateSubareaWindow').window("open");
	        $('#updateSubareaWindow').form("load", arr[0]);
			
		}else{
            $.messager.alert("提示信息", "请选择一条数据", "warning");
        }
	}

	function doDelete() {
		var arr = $('#grid').datagrid("getSelections");
		if(arr.length>0){
			 $.messager.confirm("提示窗口", "是否确认删除", function(r){
				 var array=new Array();
			     for(var i =0 ;i<arr.length;i++){
			    	 var staff = arr[i];
			    	 var id=staff.id;
			    	 array.push(id);
			     }	
			     var ids=array.join(",");
			     location.href = "subareaAction_delete.action?ids=" + ids;
			 })
		}else{
			$.messager.alert("提示信息", "请选择数据", "warning");
		}
	}

	function doSearch() {
		$('#searchWindow').window("open");
	}
	
	function doShow(){
		$('#showWindow').window("open");
		// 加载数据
		$.post("subareaAction_findSubareasGroupByProvince.action",function(data){
            $("#test").highcharts({
                title: {
                    text: '区域分区分布图'
                },
                series: [{
                    type: 'pie',
                    name: '分区数量',
                    data: data
                }]
            });
        });
	}

	// xls文件导出
	function doExport() {
		window.location.href="subareaAction_exportXls.action";
	}

	function doImport() {
		alert("导入");
	}
	
	   //定义一个工具方法，用于将指定的form表单中所有的输入项转为json数据{key:value,key:value}
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
    

	//工具栏
	var toolbar = [ {
		id : 'button-search',
		text : '查询',
		iconCls : 'icon-search',
		handler : doSearch
	}, {
		id : 'button-add',
		text : '增加',
		iconCls : 'icon-add',
		handler : doAdd
	}, {
		id : 'button-edit',
		text : '修改',
		iconCls : 'icon-edit',
		handler : doEdit
	}, {
		id : 'button-delete',
		text : '删除',
		iconCls : 'icon-cancel',
		handler : doDelete
	}, {
		id : 'button-import',
		text : '导入',
		iconCls : 'icon-redo',
		handler : doImport
	}, {
		id : 'button-export',
		text : '导出',
		iconCls : 'icon-undo',
		handler : doExport
	} ,{
        id : 'button-show',
        text : '区域分布图',
        iconCls : 'icon-search',
        handler : doShow
    }];
	// 定义列
	var columns = [ [ {
		field : 'id',
		checkbox : true,
	}, {
		field : 'showid',
		title : '分拣编号',
		width : 120,
		align : 'center',
		formatter : function(data, row, index) {
			return row.id;
		}
	}, {
		field : 'province',
		title : '省',
		width : 120,
		align : 'center',
		formatter : function(data, row, index) {
			return row.region.province;
		}
	}, {
		field : 'city',
		title : '市',
		width : 120,
		align : 'center',
		formatter : function(data, row, index) {
			return row.region.city;
		}
	}, {
		field : 'district',
		title : '区',
		width : 120,
		align : 'center',
		formatter : function(data, row, index) {
			return row.region.district;
		}
	}, {
		field : 'addresskey',
		title : '关键字',
		width : 120,
		align : 'center'
	}, {
		field : 'startnum',
		title : '起始号',
		width : 100,
		align : 'center'
	}, {
		field : 'endnum',
		title : '终止号',
		width : 100,
		align : 'center'
	}, {
		field : 'single',
		title : '单双号',
		width : 100,
		align : 'center'
	}, {
		field : 'position',
		title : '位置',
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
			border : true,
			rownumbers : true,
			striped : true,
			pageList : [ 30, 50, 100 ],
			pagination : true,
			toolbar : toolbar,
			url : "subareaAction_pageQuery.action",
			idField : 'id',
			columns : columns,
			onDblClickRow : doDblClickRow
		});

		// 添加、修改分区
		$('#addSubareaWindow').window({
			title : '添加修改分区',
			width : 600,
			modal : true,
			shadow : true,
			closed : true,
			height : 400,
			resizable : false
		});
		
		// 添加、修改分区
        $('#showWindow').window({
            title : '区域分布图',
            width : 600,
            modal : true,
            shadow : true,
            closed : true,
            height : 400,
            resizable : false
        });

		// 查询分区
		$('#searchWindow').window({
			title : '查询分区',
			width : 600,
			modal : true,
			shadow : true,
			closed : true,
			height : 400,
			resizable : false
		});
		
		// 查询分区
        $('#updateSubareaWindow').window({
            title : '查询分区',
            width : 600,
            modal : true,
            shadow : true,
            closed : true,
            height : 400,
            resizable : false
        });
		
		$("#btn").click(function() {
			// 点击一次 执行表格的load方法(自动提交ajax请求)
			var p=$("#searchForm").serializeJson();
			$("#grid").datagrid("load",p);
			// 关闭查询窗口
			$("#searchWindow").window("close");
			
		});

	});

	function doDblClickRow(rowIndex, rowData) {
		// 显示
		 var subarea = rowData.region.province+" "+rowData.region.city+" "+rowData.region.district;
		 $('#selectS').combobox("select",subarea);
        $('#updateSubareaWindow').window("open");
        $('#updateSubareaWindow').form("load", rowData);
       
	}
</script>
</head>
<body class="easyui-layout" style="visibility: hidden;">
	<div region="center" border="false">
		<table id="grid"></table>
	</div>
	<!-- 添加 修改分区 -->
	<div class="easyui-window" title="分区添加" id="addSubareaWindow" collapsible="false" minimizable="false" maximizable="false" style="top: 20px; left: 200px">
		<div style="height: 31px; overflow: hidden;" split="false" border="false">
			<div class="datagrid-toolbar">
				<a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true">保存</a>
				<script type="text/javascript">
				    $(function(){
				    	$("#save").click(function(){
				    		var r = $("#saveForm").form('validate');
				    		if(r){
				    			$("#saveForm").submit();
				    		}
				    	})
				    })
				</script>
			</div>
		</div>

		<div style="overflow: auto; padding: 5px;" border="false">
			<form id="saveForm"  method="post" action="subareaAction_save.action">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">分区信息</td>
					</tr>
					<tr>
						<td>选择区域</td>
						<td><input class="easyui-combobox" name="region.id" 
						data-options="valueField:'id',textField:'name',mode:'remote',
						url:'regionAction_listajax.action'" /></td>
					</tr>
					<tr>
						<td>关键字</td>
						<td><input type="text" name="addresskey" class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td>起始号</td>
						<td><input type="text" name="startnum" class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td>终止号</td>
						<td><input type="text" name="endnum" class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td>单双号</td>
						<td><select class="easyui-combobox" name="single" style="width: 150px;">
								<option value="0">单双号</option>
								<option value="1">单号</option>
								<option value="2">双号</option>
						</select></td>
					</tr>
					<tr>
						<td>位置信息</td>
						<td><input type="text" name="position" class="easyui-validatebox" required="true" style="width: 250px;" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	 <!-- 修改  -->
	<div class="easyui-window" title="分区修改" id="updateSubareaWindow" collapsible="false" minimizable="false" maximizable="false" style="top: 20px; left: 200px">
        <div style="height: 31px; overflow: hidden;" split="false" border="false">
            <div class="datagrid-toolbar">
                <a id="update" icon="icon-save" href="#" class="easyui-linkbutton" plain="true">保存</a>
                <script type="text/javascript">
                    $(function(){
                        $("#update").click(function(){
                            var r = $("#updateForm").form('validate');
                            if(r){
                                $("#updateForm").submit();
                            }
                        })
                    })
                </script>
            </div>
        </div>
       
        <div style="overflow: auto; padding: 5px;" border="false">
            <form id="updateForm"  method="post" action="subareaAction_update.action">
                <input type="hidden" name="id" >
                <table class="table-edit" width="80%" align="center">
                    <tr class="title">
                        <td colspan="2">分区信息</td>
                    </tr>
                    <tr>
                        <td>选择区域</td>
                        <td><input id="selectS" class="easyui-combobox" name="region.id" 
                        data-options="valueField:'id',textField:'name',mode:'remote',
                        url:'regionAction_listajax.action'" /></td>
                    </tr>
                    <tr>
                        <td>关键字</td>
                        <td><input type="text" name="addresskey" class="easyui-validatebox" required="true" /></td>
                    </tr>
                    <tr>
                        <td>起始号</td>
                        <td><input type="text" name="startnum" class="easyui-validatebox" required="true" /></td>
                    </tr>
                    <tr>
                        <td>终止号</td>
                        <td><input type="text" name="endnum" class="easyui-validatebox" required="true" /></td>
                    </tr>
                    <tr>
                        <td>单双号</td>
                        <td><select class="easyui-combobox" name="single" style="width: 150px;">
                                <option value="0">单双号</option>
                                <option value="1">单号</option>
                                <option value="2">双号</option>
                        </select></td>
                    </tr>
                    <tr>
                        <td>位置信息</td>
                        <td><input type="text" name="position" class="easyui-validatebox" required="true" style="width: 250px;" /></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
	<!-- 查询分区 -->
	<div class="easyui-window" title="查询分区窗口" id="searchWindow" collapsible="false" minimizable="false" maximizable="false" style="top: 20px; left: 200px">
		<div style="overflow: auto; padding: 5px;" border="false">
			<form id="searchForm" >
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">查询条件</td>
					</tr>
					<tr>
						<td>省</td>
						<td><input type="text" name="region.province" /></td>
					</tr>
					<tr>
						<td>市</td>
						<td><input type="text" name="region.city" /></td>
					</tr>
					<tr>
						<td>区（县）</td>
						<td><input type="text" name="region.district" /></td>
					</tr>
					<tr>
						<td>关键字</td>
						<td><input type="text" name="addresskey" /></td>
					</tr>
					<tr>
						<td colspan="2"><a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
	<!-- 显示分区数据 -->
	<div class="easyui-window" title="区域分布图" id="showWindow" collapsible="false" minimizable="false" maximizable="false" style="top: 20px; left: 200px">
           <div id="test"></div>
    </div>
</body>
</html>