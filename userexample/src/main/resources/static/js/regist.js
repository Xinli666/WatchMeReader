var varifycode="";



function registfunc(){
    var name=document.getElementById("username");
    var pass=document.getElementById("password");
    var chepass=document.getElementById("checkpassword");
    var phone=document.getElementById("phonenumber");
    var varifi=document.getElementById("varification");
   // var varibtn=document.getElementById("varifybtn");
   // var regibtn=document.getElementById("registbtn");
    var ale=document.getElementById("alertion");
    var alertion="";
    var reg1 = /^[0-9a-zA-Z]+$/;
    var reg2 = /^[0-9]+.?[0-9]*$/;
    if(name.value==""||pass.value==""||chepass.value==""||phone.value==""||varifi.value==""){
        alertion="请输入完整的用户信息";
        ale.innerHTML=alertion;
        return false;
    }
    else if(name.value.length>24){
        alertion="用户名过长";
        ale.innerHTML=alertion;
        return false;
    }
    else if(pass.value.length>15||pass.value.length<8||!reg1.test(pass.value)){
        alertion="密码必须是8~15位的英文加数字";
        ale.innerHTML=alertion;
        return false;
    }
    else if(!(chepass.value==pass.value)){
        alertion="两次密码不符";
        ale.innerHTML=alertion;
        return false;
    }
    else if(phone.value.length!=11||!reg2.test(phone.value)){
        alertion="请输入正确的手机号码";
        ale.innerHTML=alertion;
        return false;
    }
    else if(varifycode==""){
        alertion="请先获得验证码";
        ale.innerHTML=alertion;
        return false;
    }
    else if(!(varifycode==varifi.value)){
        alertion="验证码错误";
        ale.innerHTML=alertion;
        return false;
    }
    else{
        alertion="注册成功";
        ale.innerHTML=alertion;
        return true;
    }

}

function getvarifycode(){
    varifycode=(parseInt(Math.random()*9001+1000)).toString();
    document.getElementById("varify").value=varifycode;
    alert(varifycode);
}