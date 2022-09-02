const nameTag = document.querySelector("#userName");
const idTag = document.querySelector("#userId");
const pwTag = document.querySelector("#userPassword");
const reBtn = document.querySelector("#registBtn");
 
let userlist = JSON.parse(localStorage.getItem("users"));
let user = {};
reBtn.addEventListener("click", ()=>{
  if(!nameTag.value || !idTag.value || !pwTag.value) {
    alert("세가지를 모두 입력해주세요.");
  } else {
    if(!userlist){
      userlist = [];
    }  
      let dbuser = userlist.find((obj)=>{
        return obj.id === idTag.value;
      });
      if(dbuser) {
        alert("이미 사용중인 아이디입니다.");
      } else {
        user.id = idTag.value;
        user.password = pwTag.value;
        user.name = nameTag.value;
        console.log(user);
        userlist.push(user);
        localStorage.setItem("users", JSON.stringify(userlist));
        alert("회원가입이 완료되었습니다.");
        location.replace("login.html");
        window.open("login.html")
      }
  }
});