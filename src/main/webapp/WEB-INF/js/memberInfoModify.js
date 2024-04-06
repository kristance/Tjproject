function memberSignOut(id) {
    let confirm1 = confirm("회원 탈퇴 하시겠습니까?",false)
    if (confirm1 == true) {
        fetch ("./memberSignOut?id="+ id, {
            method : "GET"
        }).then ((result) => {
            return result.text();
        }).then ((text) => {
            text = JSON.parse(text)
            console.log(text.processResult)
             if (text.processResult == 1) {
                alert("회원 탈퇴 되었습니다. 그동안 이용해주셔서 감사합니다.")
                location.href="./list"
             }
            
        }).catch ( (error) => {
            alert("회원탈퇴에 실패하였습니다.")
        })
   //     console.log(confirm1)
    }

    }


let uploadFile = document.querySelector("#uploadFile")
let imageUpload = document.querySelector("#imageUpload")
let imageTag = document.querySelector("#imageTag")
uploadFile.addEventListener("change", (event) => {
    console.log(event)
    const imageaddress = URL.createObjectURL(event.target.files[0])
    // let imgTag = document.createElement("img")
    // imgTag.src = imageaddress;
    // imgTag.width = 200
    // imgTag.height = 100
    imageTag.src = imageaddress;
    imageTag.width = 210
    imageTag.height = 300

    // imageUpload.appendChild(imgTag)
    imgTag.onload = URL.revokeObjectURL(this.src)
})

function uploadBtnClick() {
    let uploadFile = document.querySelector("#uploadFile")
    uploadFile.click();
}





// function memberInfoUpdateComplete() {
//     let memberInfoId = document.querySelector('#memberInfoId').value;
//     let updateName = document.querySelector('#updateName').value;
//     let updateNick = document.querySelector('#updateNick').value;
//     let updateEmail = document.querySelector('#updateEmail').value;
//     const formData = new FormData();
//     formData.append("image", imageUpload.files[0])
//     formData.append("name", encodeURIComponent(updateName))
//     formData.append("nick", encodeURIComponent(updateNick))
//     formData.append("email", encodeURIComponent(updateEmail))
//     console.log(memberInfoId)
    
//     const payload = new URLSearchParams(formData)
//     console.log(payload)

//     fetch ("./memberInfoUpdate", {
//         method : "POST",
//         headers : {
//             "Content-Type" : "application/x-www-form-urlencoded"},
//         body : payload
//     })
//     .then ( (result) => {
//         return result.text()
//     })
//     .then( (text) => {
//         //console.log("result ->" + text)
//         if (text >= 1) {
//             alert("수정되었습니다.")
//             location.href="./memberInfo?id=" + memberInfoId
//         } else {
//             alert("수정에 실패하였습니다.")
//             history.go(-1)
//         }
//     })
//     .catch ((error) => {
//         // console.log(error)
//     })

// }

