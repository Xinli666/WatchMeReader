var username="";
var varifycode="";
var linum=1;
var tagnum=1;

window.onload=function(){
    username=document.getElementById("loginusername").innerHTML;

    linum=parseInt(document.getElementById("linum").innerHTML);
    tagnum=parseInt(document.getElementById("tagnum").innerHTML);
    showlist(linum);
    showtag(tagnum);

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
            myinfbox[0].style.width="98%";
        }
    }

    if(username==""){
        document.getElementById("userinf").style.display="none";
        document.getElementById("login").style.display="block";
    }
    else{
        document.getElementById("login").style.display="none";
        document.getElementById("userinf").style.display="block";
    }

    var photonum=6;
    var othumbnail=document.getElementById("thumbnail");
    var nav1=document.getElementById("tab1");
    var nav2=document.getElementById("tab2");
    var nav3=document.getElementById("tab3");
    var nav4=document.getElementById("tab4");
    var nav5=document.getElementById("tab5");
    var nav6=document.getElementById("tab6");
    changephoto();
    function changephoto(){
        var num="photo";
        var tabnum="tab";
        var hidenum="";
        var activenum="";
        var hidetabnum="";
        var activetabnum="";
        if(photonum<6){
            hidenum=num+photonum.toString();
            activenum=num+(photonum+1).toString();
            hidetabnum=tabnum+photonum.toString();
            activetabnum=tabnum+(photonum+1).toString();
            var hidephoto=document.getElementById(hidenum);
            var activephoto=document.getElementById(activenum);
            hidephoto.className="slide-item";
            activephoto.className="slide-item slide-item-active";
            var hidetab=document.getElementById(hidetabnum);
            var activetab=document.getElementById(activetabnum);
            hidetab.className="slide-tab-item";
            activetab.className="slide-tab-item active";
            photonum++;
        }
        else if(photonum==6){
            var hidephoto=document.getElementById("photo6");
            var activephoto=document.getElementById("photo1");
            hidephoto.className="slide-item";
            activephoto.className="slide-item slide-item-active";
            var hidetab=document.getElementById("tab6");
            var activetab=document.getElementById("tab1");
            hidetab.className="slide-tab-item";
            activetab.className="slide-tab-item active";
            photonum=1;
        }
    }
    othumbnail.addEventListener('mouseover',function(){
        clearInterval(timer);
    });
    othumbnail.addEventListener('mouseout',function(){
        timer=setInterval(changephoto,5000);
    });
    nav1.addEventListener('mouseover',function(){
        photonum=6;
        for(var i=1;i<7;++i){
            document.getElementById("photo"+i.toString()).className="slide-item";
            document.getElementById("tab"+i.toString()).className="slide-tab-item";
        }
        changephoto();
    },false)
    nav2.addEventListener('mouseover',function(){
        photonum=1;
        for(var i=1;i<7;++i){
            document.getElementById("photo"+i.toString()).className="slide-item";
            document.getElementById("tab"+i.toString()).className="slide-tab-item";
        }
        changephoto();
    },false)
    nav3.addEventListener('mouseover',function(){
        photonum=2;
        for(var i=1;i<7;++i){
            document.getElementById("photo"+i.toString()).className="slide-item";
            document.getElementById("tab"+i.toString()).className="slide-tab-item";
        }
        changephoto();
    },false)
    nav4.addEventListener('mouseover',function(){
        photonum=3;
        for(var i=1;i<7;++i){
            document.getElementById("photo"+i.toString()).className="slide-item";
            document.getElementById("tab"+i.toString()).className="slide-tab-item";
        }
        changephoto();
    },false)
    nav5.addEventListener('mouseover',function(){
        photonum=4;
        for(var i=1;i<7;++i){
            document.getElementById("photo"+i.toString()).className="slide-item";
            document.getElementById("tab"+i.toString()).className="slide-tab-item";
        }
       changephoto();
    },false)
    nav6.addEventListener('mouseover',function(){
        photonum=5;
        for(var i=1;i<7;++i){
            document.getElementById("photo"+i.toString()).className="slide-item";
            document.getElementById("tab"+i.toString()).className="slide-tab-item";
        }
        changephoto();
    },false)

    var ul = document.getElementById("newscontent");
    var lis = ul.getElementsByTagName("li");

    /*for(var i=0;i<lis.length;i++){
        lis[i].onclick = function(){
            var abbreviate=this.getElementsByClassName("newsabbreviate");
            abbreviate[0].className="newsabbreviate active";
            this.onblur=function(){
                abbreviate[0].className="newsabbreviate";
            }
        }
    }*/
    var timer=setInterval(changephoto,5000);
    var timer1=setInterval(showtime,1000);
}

function login(){
    document.getElementById("login").style.display="none";
    document.getElementById("userinf").style.display="block";
}

function logout(){
    document.getElementById("userinf").style.display="none";
    document.getElementById("commenthis").style.display="none";
    document.getElementById("browsehis").style.display="none";
    document.getElementById("userinformation").style.display="none";
    document.getElementById("login").style.display="block";
}

function showchangeinf(){
    /* document.getElementById("userinf").style.display="none";*/
    document.getElementById("browsehis").style.display="none";
    document.getElementById("commenthis").style.display="none";
    document.getElementById("userinformation").style.display="block";
}

function showbrowsehistory(){
    document.getElementById("commenthis").style.display="none";
    document.getElementById("userinformation").style.display="none";
    document.getElementById("browsehis").style.display="block";
}

function showcommenthistory(){
    document.getElementById("userinformation").style.display="none";
    document.getElementById("browsehis").style.display="none";
    document.getElementById("commenthis").style.display="block";
}

function getvarifycode(){
    varifycode=(parseInt(Math.random()*9001+1000)).toString();
    //document.getElementById("varify").value=varifycode;
    alert(varifycode);
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

function changefunc(){
    var username=document.getElementById("changeusername");
    var password=document.getElementById("changepassword");
    var phonenum=document.getElementById("changephone");
    var varify=document.getElementById("changevarify");
    var reg1 = /^[0-9a-zA-Z]+$/;
    var reg2 = /^[0-9]+.?[0-9]*$/;
    if(username.value==""||password.value==""||phonenum.value==""||varify.value==""){
        alert("请输入完整的用户信息");
        return false;
    }
    else if(username.value.length>24){
        alert("用户名过长");
        return false;
    }
    else if(password.value.length>15||password.value.length<8||!reg1.test(password.value)){
        alert("密码必须是8~15位的英文加数字");
        return false;
    }
    else if(phonenum.value.length!=11||!reg2.test(phonenum.value)){
        alert("请输入正确的手机号码");
        return false;
    }
    else if(varify==""){
        alert("请先获得验证码");
        return false;
    }
    else if(!(varifycode==varify.value)){
        alert("验证码错误");
        return false;
    }
    else{
        alert("修改成功");
        return true;
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

function showtag(i){
    for(var j=1;j<9;j++) {
        document.getElementById("span"+j.toString()).className = "channel-item";
    }
    document.getElementById("span"+(i+1).toString()).className="channel-item active";
}

/*function changespan(i){
    for(var j=1;j<9;j++) {
        document.getElementById("span"+j.toString()).className = "channel-item";
    }
    document.getElementById("span"+i.toString()).className="channel-item active";
}*/

function showcomplaint(){
    document.getElementById("complaint").className="complaint active";
    document.getElementById("complain").className="complain active";
}

function closecomplain(){
    document.getElementById("complain").className="complain";
    document.getElementById("complaint").className="complaint";
}

function showtextarea(i){
    var myt=document.getElementsByName("textarea");
    myt[i-1].className="inputtextarea active";
}

function submitlike(){
    //alert(document.getElementById("commentName").innerText);
    //alert(document.getElementById("commentComment").innerText);
    document.getElementById("commentName2").value = document.getElementById("commentName").innerText;
    document.getElementById("commentComment2").value = document.getElementById("commentComment").innerText;
    document.getElementById("formcomment").submit();
}