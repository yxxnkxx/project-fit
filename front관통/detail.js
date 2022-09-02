let data = JSON.parse(localStorage.getItem("json-data"));
let videoId = JSON.parse(localStorage.getItem("select-id")).id; 
let videoObj = data.find((obj)=>{
  return obj.id === videoId;
});
let reviewList = JSON.parse(localStorage.getItem(videoId));


const review = reviewList.find((obj)=>{
  return obj.title === JSON.parse(localStorage.getItem("select-review")).title;
});
console.log(review);
review.view++; // 조회수 증가
const divTag = document.querySelector("#review");
const h2Tag = document.createElement("h2");
h2Tag.innerHTML = review.title;
const spanTag = document.createElement("span");
spanTag.innerHTML = "<br> 작성자 : " + review.writer +
"<br>" + "작성일 : " + review.time + 
"<br>" + "조회수 : " + review.view +
"<br>" +
"<hr>" + review.content + "<br>";
divTag.appendChild(h2Tag);
divTag.appendChild(spanTag);


let modifyTag = document.querySelector("#save")
let listTag = document.querySelector("#list");




const index = JSON.parse(localStorage.getItem("select-review")).index;
function saveReview() {
  reviewList[index] = review;
  localStorage.setItem(videoId, JSON.stringify(reviewList));
}



listTag.addEventListener("click", () => {
  saveReview();
  window.location.href="list.html";
})



loadCurrentReview();

function loadCurrentReview() {
  const divTag = document.querySelector("#review");
  divTag.innerHTML="";
  const h2Tag = document.createElement("h2");
  h2Tag.innerHTML = review.title;
  const spanTag = document.createElement("span");
  spanTag.innerHTML = "<br> 작성자 : " + review.writer +
  "<br>" + "작성일 : " + review.time + 
  "<br>" + "조회수 : " + review.view +
  "<br>" +
  "<hr>" + review.content + "<br>";
  divTag.appendChild(h2Tag);
  divTag.appendChild(spanTag);
}

const modiBtn = document.querySelector("#modi-btn");
modiBtn.addEventListener("click", ()=>{
  document.querySelector("#InputTitle").value = review.title;
  document.querySelector("#InputContent").value = review.content;
});

const saveBtn = document.querySelector("#save");
saveBtn.addEventListener("click", ()=>{
  review.title = document.querySelector("#InputTitle").value;
  review.content = document.querySelector("#InputContent").value;
  saveReview();
  loadCurrentReview();
});


const deleteBtn = document.querySelector("#delete");
deleteBtn.addEventListener("click", () => {

  let conf = confirm("삭제하시겠습니까?");
  if (conf===true) {
    reviewList = reviewList.filter((data)=> {
      return data.title!==review.title;
    })
  } 
  
  console.log(reviewList)
  localStorage.setItem(videoId, JSON.stringify(reviewList));
  window.location.href="list.html";

}) 