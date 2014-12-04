var page = {

	showPage : function(currentPage,countPage,changeMethod){
		var str = "";
		var countNum;
		//head
        if (currentPage == 1) {
        	str += "<em>首页</em>";
            str += "<em>上一页</em>";
        }
        else {
        	str += "<a href=\"javascript:void(0)\" onclick=\""+changeMethod+"(1)\">首页</a>";
            str += "<a href=\"javascript:void(0)\" onclick=\""+changeMethod+"("+(currentPage-1)+")\">上一页</a>";
        }
		
        //pageNum
		if(currentPage < 4||countPage < 6){
			countNum = 1;
		}else if(currentPage + 2 > countPage){
			countNum = countPage - 4;
		}else{
			countNum = currentPage - 2;
		}
		
		var countPage1 = parseInt(countPage)+1;
		var countNum1 = parseInt(countNum);
		//body
		for(var i = countNum1; i < countPage1; i++){
			
			if(i==currentPage){	
				str += "<b>"+i+"</b>";
			}else{
				str += "<a href=\"javascript:void(0)\" onclick=\""+changeMethod+"("+i+")\">"+i+"</a>";
			}
			
			if(i == countNum + 4){
				break;
			}
			
		}
        //end
        if (currentPage == countPage) {
        	str += "<em>下一页</em>";
            str += "<em>尾页</em>";
        }
        else {
            str += "<a href=\"javascript:void(0)\" onclick=\""+changeMethod+"("+(currentPage+1)+")\">下一页</a>";
            str += "<a href=\"javascript:void(0)\" onclick=\""+changeMethod+"("+(countPage1-1)+")\">尾页</a>";
        }
		if(countPage1>2){
			$(".pagination").empty().append(str);
		}
	}
}

var pageNew = {
	showPage : function(currentPage,countPage,changeMethod){
		var str = "";
		var countNum;
		//head
        if (currentPage == 1) {
        	str += "<a class=\"page-btn\">首页</a>";
            str += "<a class=\"page-btn\">&lt;&lt;上一页</a>";
        }
        else {
        	str += "<a href=\"javascript:void(0)\" onclick=\""+changeMethod+"(1)\"  class=\"page-btn\">首页</a>";
            str += "<a href=\"javascript:void(0)\" onclick=\""+changeMethod+"("+(currentPage-1)+")\" class=\"page-btn\">&lt;&lt;上一页</a>";
        }
		
        //pageNum
		if(currentPage < 4||countPage < 6){
			countNum = 1;
		}else if(currentPage + 2 > countPage){
			countNum = countPage - 4;
		}else{
			countNum = currentPage - 2;
		}
		
		var countPage1 = parseInt(countPage)+1;
		var countNum1 = parseInt(countNum);
		//body
		for(var i = countNum1; i < countPage1; i++){
			
			if(i==currentPage){	
				str += "<span class=\"cur\">"+i+"</span>";
			}else{
				str += "<a href=\"javascript:void(0)\" onclick=\""+changeMethod+"("+i+")\">"+i+"</a>";
			}
			
			if(i == countNum + 4){
				break;
			}
			
		}
        //end
        if (currentPage == countPage) {
        	str += "<a>&gt;</a>";
            str += "<a class=\"page-btn\">尾页</a>";
        }
        else {
            str += "<a href=\"javascript:void(0)\" onclick=\""+changeMethod+"("+(currentPage+1)+")\" class=\"page-btn\">下一页&gt;&gt;</a>";
            str += "<a href=\"javascript:void(0)\" onclick=\""+changeMethod+"("+(countPage1-1)+")\" class=\"page-btn\">尾页</a>";
        }
		if(countPage1>2){
			$(".page-new").empty().append(str);
		}
	}
}

