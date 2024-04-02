window.addEventListener('load', function () {
//	alert(document.cookie + "  <- 123.js");
	let cookie = document.cookie;
	let firstPeriod = cookie.indexOf('saveID');
	let secondPeriod = cookie.indexOf(';', firstPeriod);
	let thirdPeriod = cookie.substring(firstPeriod, secondPeriod);
	let fourthPeriod = thirdPeriod.split('=');
//	let value = fourthPeriod[2];
	
	
	if (secondPeriod == -1) {
		secondPeriod = cookie.length
	}
	alert(fourthPeriod);
		
})