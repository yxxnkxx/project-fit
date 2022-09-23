let data = JSON.parse(localStorage.getItem("json-data"));
let videoId = JSON.parse(localStorage.getItem("select-id")).id;
let videoObj = data.find((obj) => {
  return obj.id === videoId;
});

const videoBox = document.querySelector("#videoBox");
const iframeTag = document.createElement("iframe");
iframeTag.width = "420";
iframeTag.height = "315";
iframeTag.src = videoObj.url;
videoBox.appendChild(iframeTag);
console.log(data);
console.log(videoId);






const ID = JSON.parse(localStorage.getItem("select-id")).id;

const reviewTitle = document.querySelector("#InputTitle");
const reviewContent = document.querySelector("#InputContent");
const saveBtn = document.querySelector("#save");

loadReview();

function loadReview() {

  const reviews = JSON.parse(localStorage.getItem(ID));
  console.log(reviews)
  if (reviews !== null) {
    const tbodyTag = document.querySelector("#review-table");
    tbodyTag.innerHTML = "";

    for (let i = 0; i < reviews.length; i++) {
      let review = reviews[i];

      let trTag = document.createElement("tr");
      let numTag = document.createElement("th");
      numTag.innerHTML = i + 1;
      let titleTag = document.createElement("td");
      let aTag = document.createElement("a");
      aTag.id = review.title;
      aTag.href = "detail.html"
      aTag.innerHTML = review.title;
      titleTag.appendChild(aTag);

      let writerTag = document.createElement("td");
      writerTag.innerHTML = review.writer;

      let viewTag = document.createElement("td");
      viewTag.innerHTML = review.view;

      let timeTag = document.createElement("td");
      timeTag.innerHTML = review.time;

      trTag.appendChild(numTag);
      trTag.appendChild(titleTag);
      trTag.appendChild(writerTag);
      trTag.appendChild(viewTag);
      trTag.appendChild(timeTag);

      tbodyTag.appendChild(trTag);


      let selected = document.querySelectorAll("a");

      for (let i=0; i<selected.length; i++) {
        selected[i].addEventListener("click", () => {
    
          let selectReview = {
            index: i,
            title: selected[i].id,
          }
    
          localStorage.setItem("select-review", JSON.stringify(selectReview)); 
        })
      }


    }


  }
}






saveBtn.addEventListener("click", function () {
  let today = new Date();
  const review = {
    title: reviewTitle.value,
    content: reviewContent.value,
    writer: localStorage.getItem("user"),
    view: 0,
    time: today.toLocaleString()

  }

  let reviews = JSON.parse(localStorage.getItem(ID));
  if (reviews === null) {
    reviews = [];
  }
  reviews.push(review);
  localStorage.setItem(ID, JSON.stringify(reviews));

  reviewTitle.value="";
  reviewContent.value="";
  loadReview();
})




