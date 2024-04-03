function memberSignOut(id) {
    let confirm1 = confirm("회원 탈퇴 하시겠습니까?",false)
    if (confirm1 == true) {
        fetch ("./memberSignOut?id="+ id, {
            method : "GET"
        }).then ((result) => {
            return result.text();
        }).then ((text) => {
            let processResult = text;
            console.log(processResult.);
            
        }).catch ( (error) => {
            alert("회원탈퇴에 실패하였습니다.")
        })
        console.log(confirm1)
    }

    }