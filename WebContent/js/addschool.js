function ajaxAddSchool(){
    var $form = $(".addschool:last");
    var $postdata = $form.serializeArray();    
    $.get('Student_schooladd.action',$postdata,function(data){
        //插入成功,增加下一行
        if(data != '0'){
            var input_vs = document.getElementsByClassName("input_v");
            for(var i = 0; i < input_vs.length; i++){
                input_vs[i].value='';
            }
            var $html = "<tr class='list record' id='" + data + "'><td>" +
                $postdata[2]['value'] + "</td><td>" + $postdata[3]['value'] + "</td><td>" + $postdata[4]['value'] + "</td><td>"
                 + $postdata[5]['value'] + "</td><td>" + 
                 "<a onclick=\"javascript:if(confirm('真的要删除吗？')) ajaxDelSchool(" + data + ");\">删除</a>"
                 +"</td></tr>";
            $(".record:last").after($html);
            //alert("插入成功");
        }
        //插入失败,弹出提示
        else{
            alert("插入失败");
        }
    },"html");
}

function ajaxAddJob(){
    var $form = $(".addjob:last");
    var $postdata = $form.serializeArray();    
    $.get('Student_jobadd.action',$postdata,function(data){
        //插入成功,增加下一行
        if(data != '0'){
            var input_vs = document.getElementsByClassName("input_v2");
            for(var i = 0; i < input_vs.length; i++){
                input_vs[i].value='';
            }
            var $html = "<tr class='list jobrecord' id='" + data + "'><td>" +
                $postdata[2]['value'] + "</td><td>" + $postdata[3]['value'] + "</td><td>" + $postdata[4]['value'] + "</td><td>"
                 + $postdata[5]['value'] + "</td><td>" + $postdata[6]['value'] +"</td><td>" + 
                 "<a onclick=\"javascript:if(confirm('真的要删除吗？')) ajaxDelJob(" + data + ");\">删除</a>"
                 + "</td></tr>";
            $(".jobrecord:last").after($html);
            //alert("插入成功");
        }
        //插入失败,弹出提示
        else{
            alert("插入失败");
        }
    },"html");
}

function ajaxDelSchool(id){
	$.get('Student_schoolajaxdelete.action?ssid='+id, id, function(data){
		//alert("ssid:"+id);
		if(data == '0'){
			$("#"+id).empty();	        
		}else{
			alert("删除失败");
		}
	},'html');
}

function ajaxDelJob(id){
	$.get('Student_jobajaxdelete.action?sjid='+id, id, function(data){
		//alert("sjid:"+id);
		if(data == '0'){
			$("#"+id).empty();	        
		}else{
			alert("删除失败");
		}
	},'html');
}

function initialAddSchool(){
    var studentid = $("#studentid").val();
    if(!studentid) {
        alert("请先输入学号");
        return false;
    }
    var studentname = $("#studentname").val();
    if(!studentname){
        alert("请先输入学生姓名");
        return false;
    }
	var schidclass = $(".schidclass:last");
    schidclass.val(studentid);
    var schnameclass = $(".schnameclass:last");
    schnameclass.val(studentname);
    ajaxAddSchool();
}

function initialAddJob(){
	var studentid = $("#studentid").val();
    if(!studentid) {
        alert("请先输入学号");
        return false;
    }
    var studentname = $("#studentname").val();
    if(!studentname){
        alert("请先输入学生姓名");
        return false;
    }
    var jobidclass = $(".jobidclass:last");
    jobidclass.val(studentid);
    var jobnameclass = $(".jobnameclass:last");
    jobnameclass.val(studentname);
    ajaxAddJob();
}

function addStudentValidation(){
    var studentid = $("#studentid").val();
    if(!studentid) {
        alert("请先输入学号");
        return false;
    }
    var studentname = $("#studentname").val();
    if(!studentname){
        alert("请先输入学生姓名");
        return false;
    }
    window.onbeforeunload=function(){};
    var addform = document.getElementsByName("addForm")[0];
    addform.submit();
}