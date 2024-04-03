window.addEventListener('load',  () => {
	
	//alert(document.cookie)
	let cookie = document.cookie;
	
	
		
		let firstPeriod = cookie.indexOf('saveID');
		
		let secondPeriod = cookie.length;
		try {
			secondPeriod = cookie.indexOf(';', firstPeriod);
			
		} catch (error) {
			
		}
		let	thirdPeriod = cookie.substring(firstPeriod, secondPeriod);
		let	fourthPeriod = thirdPeriod.split('=');
		let	value = fourthPeriod[1];
		
	//	alert(value);
		
		if (value != null || value != '') {
			document.querySelector('#id').value = value;
			document.querySelector('#saveID').setAttribute('checked', 'checked');
		}
		
		
		
	})