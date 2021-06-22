$(document).ready(function() {

	/* On clicking the "sign up" button*/
	$("#signup-btn").click(function() {

		/* Taking all the form paramters */
		let fname = $("#signup-cmp-fname-id").val();
		let lname = $("#signup-cmp-lname-id").val();
		let email = $("#signup-cmp-email-id").val();
		let uname = $("#signup-cmp-user-id").val();
		let pass = $("#signup-cmp-pass-id").val();
		let cpass = $("#signup-cmp-cnf-pass-id").val();
		let country = $("#signup-cmp-country-id").val();
		let gender = $("#signup-cmp-gender-id").val();
		/* Ends here*/

		/*Sign up validation*/
		if (checkFname(fname) === false || checkLname(lname) === false || checkEmail(email) === false || checkUname(uname) === false || checkPass(pass) === false || checkCpass(pass, cpass) === false || checkCountry(country) === false || checkGender(gender) === false) {
			$("#alert-danger").append("Form is not successfully submitted!");
		} else {
			$("#alert-success").append("Form is successfully submitted!");
		}
		/*Ends here */
	});
	/* Ends here */
});

/*Regular expressions */
let textRegex = /^[a-zA-Z]*$/;
/* Ends here */

/* Independent validation functions */
function checkFname(fname) {
	if (!textRegex.test(fname) || fname === "") {
		return false;
	}
	return true;
}

function checkLname(lname) {
	if (!textRegex.test(lname) || lname === "") {
		return false;
	}
	return true;
}

function checkEmail(email) {
	return true;
}

function checkUname(uname) {
	if (!textRegex.test(uname) || uname === "") {
		return false;
	}
	return true;
}

function checkPass(pass) {
	return true;
}

function checkCpass(pass, cpass) {
	if (pass !== cpass) {
		return false;
	}
	return true;
}

function checkCountry(country) {
	if (country === "Open this select menu") {
		return false;
	}
	return true;
}

function checkGender(gender) {
	if (gender === "Choose") {
		return false;
	}
	return true;
}