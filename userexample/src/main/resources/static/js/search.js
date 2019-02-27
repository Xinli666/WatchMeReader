/*window.onload=function() {
    var ul = document.getElementById("newscontent");
    var lis = ul.getElementsByTagName("li");
    alert(lis.length);
    for (var i = 0; i < lis.length; i++) {
        lis[i].onclick = function () {
            var abbreviate = this.getElementsByClassName("newsabbreviate");
            abbreviate[0].className = "newsabbreviate active";
            this.onblur = function () {
                abbreviate[0].className = "newsabbreviate";
            }
        }
    }
}*/

var page=1;
var totalpage=1;
var linum=1

window.onload=function(){

    showtime();
    function showtime() {
        var today = new Date();
        var year = today.getFullYear();
        var month = today.getMonth() + 1;
        var day = today.getDate();
        var hours = today.getHours();
        var minutes = today.getMinutes();
        var seconds = today.getSeconds();
        month = month < 10 ? "0" + month : month;
        day = day < 10 ? "0" + day : day;
        hours = hours < 10 ? "0" + hours : hours;
        minutes = minutes < 10 ? "0" + minutes : minutes;
        seconds = seconds < 10 ? "0" + seconds : seconds;
        var str = year + "年" + month + "月" + day + "日 " + hours + ":" + minutes + ":" + seconds;
        document.getElementById("time").innerHTML=str;
    }

    page=parseInt(document.getElementById("pagenum").innerHTML);
    totalpage=parseInt(document.getElementById("totalpage").innerHTML);
    linum=parseInt(document.getElementById("linum").innerHTML);

    var images="";
    for(var i=0;i<linum;i++){
        images=document.getElementById("myimage"+(i+1).toString()).innerHTML;
        //alert(images);
        if(images.length==0){
            //alert(i);
            var mylis=document.getElementById("news"+(i+1).toString());
            var myimage=mylis.getElementsByClassName("newsimage");
            myimage[0].style.display="none";
            var myinfbox=mylis.getElementsByClassName("newsinfbox");
            myinfbox[0].style.width="100%";
        }
    }

    showlist(linum);
    var timer1=setInterval(showtime,1000);
    //document.getElementById("pagePrevious").value=page;
    //document.getElementById("pageNext").value=page;
}

function showlist(linum){
    //alert(linum);
    if(linum<10)
    {
        for(var i=10;i>linum;i--){
            document.getElementById("news"+i.toString()).style.display="none";
        }
    }
}


function choosenews(i) {
    var title = document.getElementById("title" + i.toString()).innerHTML;
    var myli = document.getElementById("news" + i.toString());
    var abbreviate = document.getElementById("abbreviate" + i.toString());
    abbreviate.className = "newsabbreviate active";
    myli.onblur = function () {
        abbreviate.className = "newsabbreviate";
    };
    document.getElementById("choose" + i.toString()).value = title;
    document.getElementById("form" + i.toString()).submit();
}

function submitcomment(i){
    //document.getElementById('form'+i.toString()).removeAttribute("action","localhost:8080/list");
    //document.getElementById('form'+i.toString()).setAttribute("action","localhost:8080/comment");
    var title = document.getElementById("title" + i.toString()).innerHTML;
    document.getElementById("newstitle" + i.toString()).value = title;
    document.getElementById("formtext"+i.toString()).submit();
    //document.getElementById();
}

function pageprevious(){
    if(page>1){
        page--;
    }
    document.getElementById("pagePrevious").value = page.toString();
}

/*function pageNext(i){
    alert(i);
    if(page<i){
        page++;
    }
    alert(page);
    document.getElementById("pageNext").value = page.toString();
}*/

function pagenext(){
    if(page<totalpage){
        page++;
    }
    document.getElementById("pageNext").value = page.toString();
}

