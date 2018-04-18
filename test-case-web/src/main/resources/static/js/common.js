function siderBar(id){
	 var ele = $('#'+id);
	 ele.addClass("active");
	 ele.parents("ul").addClass("in");
	 ele.parents("li.treeview").addClass("active");
	 
	 var lis = ele.parents("li.treeview").clone();
	 lis.removeClass();
	 var bread = $(".content-header>.breadcrumb");
	 $("li:gt(0)", bread).remove();
	 for(var i = lis.length - 1; i >= 0; --i) {
	  var li = $(lis[i]);
	  $("ul", li).remove();
	  $("a span:last", li).remove();
	  bread.append(li);
	 }
	 bread.append(ele.clone());
}

/* 提示条配置
toastr.options = {
	"closeButton" : true,
	"debug" : false,
	"progressBar" : true,
	"preventDuplicates" : false,
	"positionClass" : "toast-top-right",
	"onclick" : null,
	"showDuration" : "400",
	"hideDuration" : "1000",
	"timeOut" : "7000",
	"extendedTimeOut" : "1000",
	"showEasing" : "swing",
	"hideEasing" : "linear",
	"showMethod" : "fadeIn",
	"hideMethod" : "fadeOut"
}
*/