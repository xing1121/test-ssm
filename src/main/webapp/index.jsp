<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>TEST-SSM</title>
		<!-- 引入css -->
		<%@include file="/WEB-INF/include/css-file.jsp" %>
		<!-- 引入js -->
		<%@include file="/WEB-INF/include/js-file.jsp" %>
	</head>
	<body>
		<!-- 测试连接 -->
		<a href="${ctp }/hello">Hello</a>
		
		<!-- 展示数据 -->
		<table id="datagrid_01" class="easyui-datagrid"></table>  
		
		<!-- 对话框弹框，默认关闭，当点击添加或编辑时弹出 -->
		<div id="dialog_01" class="easyui-dialog" title="弹窗" style="width:400px;height:200px;text-align: center;padding: 15px;"   
		        data-options="iconCls:'icon-save', modal:true, closed:true"> 
		    <!-- 表单 -->  
			<form id="form_01" method="post">
					<input class="easyui-validatebox" type="hidden" name="id"/>  
			    <div>   
			        <label for="name">姓名:</label>   
			        <input class="easyui-validatebox" type="text" name="name" data-options="required:true,validType:'length[0,10]'" />   
			    </div> 
			    <br/> 
   			    <div>   
			        <label for="age">年龄:</label>   
			        <input class="easyui-validatebox" type="text" name="age" data-options="required:true,validType:'length[0,3]'" />   
			    </div>  
			    <br/> 
			    <div>   
			        <label for="email">邮箱:</label>   
			        <input class="easyui-validatebox" type="text" name="email" data-options="required:true,validType:'email'" />   
			    </div> 
			    <br/> 
			    <div>   
			        <input id="button_01" type="button" value="提交" />   
			    </div> 
			</form> 
		</div>  
	</body>
	<script type="text/javascript">
		$(function(){
			
			// 表格初始化
			$('#datagrid_01').datagrid({    
			    url:'${ctp}/person/queryAll', 
			    autoRowHeight:true,
			    singleSelect:true,
			    fitColumns:true,
			    columns:[[    
			        {field:'id',title:'Id',width:100,align:'center'},    
			        {field:'name',title:'Name',width:100,align:'center'},    
			        {field:'age',title:'Age',width:100,align:'center'},
			        {field:'email',title:'Email',width:100,align:'center'}  
			    ]],
				toolbar: [{
					iconCls: 'icon-add',
					handler: function(){
						addFunction();
					}
				},'-',{
					iconCls: 'icon-edit',
					handler: function(){
						updateFunction();
					}
				},'-',{
					iconCls: 'icon-remove',
					handler: function(){
						deleteFuntion();
					}
				}]
			});  
			
			// 删除函数
			function deleteFuntion(){
				// 获取当前选中的数据，selectedRow是一个js对象
				var selectedRow = $("#datagrid_01").datagrid("getSelected");
				// 数据为空提示
				if (selectedRow == null || $.trim(selectedRow) == '') {
					$.messager.alert('警告','请先选择一条数据！');
					return;
				}
				// 数据非空弹出对话框，是否确认删除
				$.messager.confirm('确认','您确认想要删除id为'+selectedRow.id+'的记录吗？',function(yn){    
				    if (yn){    
						// 确认删除，发送ajax请求，删除
						var url = "${ctp}/person/deleteById";
						var id = selectedRow.id; 
						$.ajax({
							url:url,
							type:"post",
							dataType:"text",
							data:{
								id:id
							},
							success:function(res){
								// 删除成功，提示
								$.messager.alert('提示',res);
								// 刷新表格
								$('#datagrid_01').datagrid("reload");
							},
							error:function(res){
								$.messager.alert('提示',res);
							}
						});
				    }    
				});  
			}
			
			// 添加按钮
			function addFunction(){
				// 清空表单
				$("#form_01").form('clear');
				// 修改表单的提交地址
				$("#form_01").form({
					url:'${ctp}/person/insert',
					success:function(msg){
						$.messager.alert('提示',msg);
						$("#datagrid_01").datagrid('reload');
					}
				});
				// 修改对话框的主题并弹出
				$("#dialog_01").dialog({
					title:'添加',
					closed:false
				});
			}
			
			// 更新按钮
			function updateFunction(){
				// 清空表单
				$("#form_01").form('clear');
				// 修改表单的提交地址
				$("#form_01").form({
					url:'${ctp}/person/updateById',
					success:function(msg){
						$.messager.alert('提示',msg);
						$("#datagrid_01").datagrid('reload');
					}
				});
				// 获取当前选中对象
				// 获取当前选中的数据，selectedRow是一个js对象
				var selectedRow = $("#datagrid_01").datagrid("getSelected");
				// 数据为空提示
				if (selectedRow == null || $.trim(selectedRow) == '') {
					$.messager.alert('警告','请先选择一条数据！');
					return;
				}
				// 填充表单内容
				$("input[name='id']").val(selectedRow.id);
				$("input[name='name']").val(selectedRow.name);
				$("input[name='age']").val(selectedRow.age);
				$("input[name='email']").val(selectedRow.email);
				// 修改对话框的主题并弹出
				$("#dialog_01").dialog({
					title:'更新',
					closed:false
				});
			}
			
			// 提交按钮
			$("#button_01").click(function(){
				// 提交表单
				$("#form_01").submit();
				// 关闭对话框
				$('#dialog_01').dialog("close")
			});
		});
	</script>
</html>