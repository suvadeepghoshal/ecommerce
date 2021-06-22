document.getElementById("btn-submit").addEventListener("click", function() {
	let email = document.getElementById("email-id").value;
	let pass = document.getElementById("pass-id").value;
	if (checkEmail(email) === false || checkPass(pass) === false) {
		console.log("Can not submit empty form");
	} else {
		let xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				console.log(this.responseText);
				document.location.href = this.responseText;
			}
		};
		let data = email + "," + pass;
		xhr.open('POST', '/bin/signin', true);
		xhr.send(data);
	}
});

function checkEmail(email) {
	if (email === "") {
		return false;
	}
	return true;
}

function checkPass(pass) {
	let passRegex = /(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z])(?=.*[!@#$%^&*])([^\s]).{8,}/;
	if (!passRegex.test(pass)) {
		return false;
	}
	return true;
}