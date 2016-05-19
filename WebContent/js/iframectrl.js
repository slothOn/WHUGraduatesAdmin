var fram1 = document.getElementById("mainframe");  

function go_back(){  
	fram1.contentWindow.history.go(-1);  
}  
      
function go_for(){  
    fram1.contentWindow.history.go(1);  
}  