// 비밀번호 일치 확인 함수

function passwordCheckFunction() {
    let pw = $('#pw').val();
    let pw2 = $('#pw2').val();
    
    if (pw !== pw2) {
        $('#passwordCheckMessage').html('비밀번호가 일치하지 않습니다.');
    } else {
        $('#passwordCheckMessage').html('비밀번호가 일치합니다.');
		let b = document.getElementById('registerb').value
		console.log(b)
    }
}

