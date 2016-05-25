/**
 * Created by zxc on 16/5/24.
 */
function postparamform(){
    var pageform = document.getElementById("pageform");
    var pageval = document.getElementById("page").value;
    var formurl = pageform.action + "&page=" + pageval;
    window.location = formurl;
    return false;
}