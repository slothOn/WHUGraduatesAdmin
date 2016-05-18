function ajaxAddSchool(){
    var $form = $("form:last");
    var $postdata = $form.serializeArray();    
    $.get('Student_schooladd.action',$postdata,function(data){
        //插入成功,增加下一行
        if(data == '0'){
            var input_vs = document.getElementsByClassName("input_v");
            for(var i = 0; i < input_vs.length; i++){
                input_vs[i].value='';
            }
            var $html = "<tr class='list record'><td>" +
                $postdata[1]['value'] + "</td><td>" + $postdata[2]['value'] + "</td><td>" + $postdata[3]['value'] + "</td><td>"
                 + $postdata[4]['value'] + "</td><td>修改&nbsp;删除</td></tr>";
            $(".record:last").after($html);
            alert("插入成功");
        }
        //插入失败,弹出提示
        else{
            alert("插入失败");
        }
    },"html");
}