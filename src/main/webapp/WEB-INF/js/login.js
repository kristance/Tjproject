window.addEventListener('load',  () => {
	
	//alert(document.cookie)
	let cookie = document.cookie;
	
	
		
		let firstPeriod = cookie.indexOf('saveID');
		
		let secondPeriod = cookie.indexOf(';', firstPeriod);
		if (secondPeriod == -1) {
			secondPeriod = cookie.length;
		}

		let	thirdPeriod = cookie.substring(firstPeriod, secondPeriod);
		let	fourthPeriod = thirdPeriod.split('=');
		let	value = fourthPeriod[1];
		
		//alert(value);
		
		if (value != null || value != '' && value != undefined) {
			document.querySelector('#id').value = value;
			document.querySelector('#saveID').setAttribute('checked', 'checked');
		}	

		let firstPeriodAutoLogin = cookie.indexOf("autoLogin");
		let secondPeriodAutoLogin = cookie.indexOf(";", firstPeriodAutoL/ogin);
		if (secondPeriodAutoLogin == -1) {
			secondPeriodAutoLogin = cookie.length
		}
		let thirdPeriodAutoLogin = cookie.substring(firstPeriodAutoLogin, secondPeriodAutoLogin);
		let valueAutoLogin = fourthPeriod[1];
		
		let cookieAutoLogin = cookie.indexOf("autoLogin");
		if (cookieAutoLogin != -1 ) {
			document.querySelector("#autoLogin").setAttribute("checked", "checked");
		} else {
			document.querySelector("#autoLogin").removeAttribute("checked");
			let date = new date();
			cookie = "autoLogin=" + valueAutoLogin + ";expires=" + date.toUTCString() + ";";
		}

		
		
	})
	
function categorySortFn() {
		let category = document.querySelector('#category')
		let categorySelectedValue = category.options[category.selectedIndex].text;
		console.log('categoryvalue -> ' + categorySelectedValue)
		document.querySelector('#categoryOptionValue').value = categorySelectedValue;
		document.querySelector('#categoryOptionValueSubmit').submit();
	
}