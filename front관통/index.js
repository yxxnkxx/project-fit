
function selectVideo(partList) {
  const selectTag = document.querySelector("#select-video");
  selectTag.innerHTML = "";
  partList.forEach((obj) => {
    console.log(obj.title);

    let thumbTag = document.createElement("div");
    thumbTag.setAttribute("class", "thumbnail")
    let aTag = document.createElement("a");
    aTag.setAttribute("id", obj.id);
    aTag.href = "list.html";
    let imgTag = document.createElement("img");
    imgTag.setAttribute("class", "img-thumbnail");
    imgTag.src = "https://img.youtube.com/vi/" + obj.id + "/hqdefault.jpg";
    aTag.appendChild(imgTag);

    thumbTag.appendChild(aTag);
    let caption = document.createElement("div");
    caption.setAttribute("class", "caption");
    let title = document.createElement("p");
    title.setAttribute("class", "video-title");
    title.innerHTML = obj.title;

    let position = document.createElement("span");
    position.setAttribute("class", "badge text-bg-primary");
    position.innerHTML = obj.part;

    let channel = document.createElement("p");
    channel.setAttribute("class", "video-channel");
    channel.innerHTML = obj.channelName;


    caption.appendChild(title);
    caption.appendChild(position);
    caption.appendChild(channel);

    thumbTag.appendChild(caption);
    selectTag.appendChild(thumbTag);


    
  })

  let thumb = document.querySelectorAll("a");

  for (let i=0; i<thumb.length; i++) {
    thumb[i].addEventListener("click", () => {

      let selectId = {
        id: thumb[i].id,
      }

      console.log(thumb[i].id);
      localStorage.setItem("select-id", JSON.stringify(selectId)); 
    })
  }
  
}



window.addEventListener("load", function () {
  axios({
    url: '/data/video.json',
    method: "GET",
  })
    .then((res) => {
      if(!this.localStorage.getItem("user")) {
        this.localStorage.setItem("user", "guest");
      }
      const login = this.document.querySelector("#check-login");
      if (this.localStorage.getItem("user")==="guest") {
        login.innerHTML="<div class=\"p-2\"><i class=\"bi bi-box-arrow-in-left\"></i></div>"
      } else {
        login.innerHTML="<div class=\"p-2\"><i class=\"bi bi-box-arrow-right\"></i>"; 
        
      }
      this.localStorage.setItem("json-data", JSON.stringify(res.data));
      const list = res.data;
      let partList;
      console.log(list);
      const wholeBtn = document.querySelector("#whole");
      wholeBtn.addEventListener("click", function () {
        partList = list.filter((obj) => {
          return obj.part === wholeBtn.innerText;
        })
        selectVideo(partList);
      })


      const upBtn = document.querySelector("#up");
      upBtn.addEventListener("click", function () {
        partList = list.filter((obj) => {
          return obj.part === upBtn.innerText;
        })
        selectVideo(partList);
      })

      const stomachBtn = document.querySelector("#stomach");
      stomachBtn.addEventListener("click", function () {
        partList = list.filter((obj) => {
          return obj.part === stomachBtn.innerText;
        })
        selectVideo(partList);

      })


      const downBtn = document.querySelector("#down");
      downBtn.addEventListener("click", function () {
        partList = list.filter((obj) => {
          return obj.part === downBtn.innerText;
        })
        selectVideo(partList);

      })
      return partList

    })
    

});



