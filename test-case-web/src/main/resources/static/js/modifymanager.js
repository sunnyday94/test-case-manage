 //修改用户弹窗
  function modifyManager(){
    $('#modifyManagerDiv').modal('show');
  }
  
  //检查原密码
  function checkPassword(){
		var originPassword = $("#originPassword").val();
		$.ajax({
			type : "POST",
			url : "checkPassword",
			data : "password=" + originPassword,
			async : false,
			success : function(message) {
				if(message == "1"){
					$("#newPassword").removeAttr("readOnly");
					$("#newPasswordAgain").removeAttr("readOnly");
				}else{
					$("#newPassword").attr("readOnly","readOnly");
					$("#newPasswordAgain").attr("readOnly","readOnly");
				}
			}
		});
	}
  
	//修改用户密码
	function saveModification(){
		var newPassword = $("#newPassword").val();
		var newPasswordAgain = $("#newPasswordAgain").val();
		if(newPassword.length<6){
			$("#warn").text("密码需要六位以上有效数字或字符");
			return;
		}
		if(newPassword != newPasswordAgain){
			$("#warn").text("密码两次输入不一致");
			return;
		}
		layer.confirm('确认修改用户密码？', {
			  btn: ['确认','取消'] //按钮
			}, function(){  
				$.ajax({
					type : "POST",
					url : "updateUserPassword",
					data : "password=" + newPassword,
					async : false,
					success : function(message) {
						if(message==1){
							$('#modifyManagerDiv').modal('hide');
							layer.msg('密码修改成功,请重新登录!',{time:1800});
							//跳转登录页
							setTimeout(function(){window.location.href="goToLogin";},1800);
						}else{
							layer.msg('密码修改失败!',{time:1000});
						}
					}
				});
			}, function(){
			   
			});
	}