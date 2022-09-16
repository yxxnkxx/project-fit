const idTag = document.querySelector("#userId");
const pwTag = document.querySelector("#userPassword");

const userlist = JSON.parse(localStorage.getItem("users"));

window.addEventListener("load", () => {
  const user = localStorage.getItem("user");
  if (user !== "guest") {
    alert("로그아웃 되었습니다.");
    localStorage.setItem("user", "guest");
    location.replace("index.html");
    window.open("index.html")
  }

})



const liBtn = document.querySelector("#loginBtn");
liBtn.addEventListener("click", () => {
  console.log(idTag.value);
  if (!idTag.value || !pwTag.value) {
    alert("ID 와 비밀번호를 모두 입력하세요.");
  } else {


    if (userlist !== null) { 
    let user = userlist.find((obj) => {
      return obj.id === idTag.value && obj.password === pwTag.value;
    });
    if (!user) {
      alert("올바른 ID와 비밀번호를 입력하세요.")
    } else {
      alert("로그인 되었습니다.");
      localStorage.setItem("user", user.name);
      location.replace("index.html");
      window.open("index.html")
    }
  } else {
    alert("올바른 ID와 비밀번호를 입력하세요.")
  }
  }
});

const reBtn = document.querySelector("#registBtn");
reBtn.addEventListener("click", () => {
  location.replace("regist.html");
  window.open("regist.html");
});