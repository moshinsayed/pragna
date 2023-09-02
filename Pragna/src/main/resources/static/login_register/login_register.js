/*global $, document, window, setTimeout, navigator, console, location*/
$(document).ready(function() {
	"use strict";

	var usernameError = true,
		emailError = true,
		phoneError = true,
		passwordError = true,
		passConfirm = true;

	// Detect browser for css purpose
	if (navigator.userAgent.toLowerCase().indexOf("firefox") > -1) {
		$(".form form label").addClass("fontSwitch");
	}

	// Label effect
	$("input").focus(function() {
		$(this).siblings("label").addClass("active");
	});

	// Form validation
	$("input").blur(function() {
		// User Name
		if ($(this).hasClass("name")) {
			if ($(this).val().length === 0) {
				$(this)
					.siblings("span.error")
					.text("Please type your full name")
					.fadeIn()
					.parent(".form-group")
					.addClass("hasError");
				usernameError = true;
			} else if ($(this).val().length > 1 && $(this).val().length <= 6) {
				$(this)
					.siblings("span.error")
					.text("Please type at least 6 characters")
					.fadeIn()
					.parent(".form-group")
					.addClass("hasError");
				usernameError = true;
			} else {
				$(this)
					.siblings(".error")
					.text("")
					.fadeOut()
					.parent(".form-group")
					.removeClass("hasError");
				usernameError = false;
			}
		}
		// Email
		if ($(this).hasClass("email")) {
			if ($(this).val().length == "") {
				$(this)
					.siblings("span.error")
					.text("Please type your email address")
					.fadeIn()
					.parent(".form-group")
					.addClass("hasError");
				emailError = true;
			} else {
				$(this)
					.siblings(".error")
					.text("")
					.fadeOut()
					.parent(".form-group")
					.removeClass("hasError");
				emailError = false;
			}
		}

		//phone 
		if ($(this).hasClass("phone")) {
			if ($(this).val().length < 10) {
				$(this)
					.siblings("span.error")
					.text("Please type valid whatsapp phone number")
					.fadeIn()
					.parent(".form-group")
					.addClass("hasError");
				phoneError = true;
			} else {
				$(this)
					.siblings(".error")
					.text("")
					.fadeOut()
					.parent(".form-group")
					.removeClass("hasError");
				phoneError = false;
			}
		}

		// PassWord
		if ($(this).hasClass("pass")) {
			if ($(this).val().length < 8) {
				$(this)
					.siblings("span.error")
					.text("Please type at least 8 charcters")
					.fadeIn()
					.parent(".form-group")
					.addClass("hasError");
				passwordError = true;
			} else {
				$(this)
					.siblings(".error")
					.text("")
					.fadeOut()
					.parent(".form-group")
					.removeClass("hasError");
				passwordError = false;
			}
		}

		// PassWord confirmation
		if ($(".pass").val() !== $(".passConfirm").val()) {
			$(".passConfirm")
				.siblings(".error")
				.text("Passwords don't match")
				.fadeIn()
				.parent(".form-group")
				.addClass("hasError");
			passConfirm = false;
		} else {
			$(".passConfirm")
				.siblings(".error")
				.text("")
				.fadeOut()
				.parent(".form-group")
				.removeClass("hasError");
			passConfirm = false;
		}

		// label effect
		if ($(this).val().length > 0) {
			$(this).siblings("label").addClass("active");
		} else {
			$(this).siblings("label").removeClass("active");
		}
	});

	// form switch
	$("a.switch").click(function(e) {
		$(this).toggleClass("active");
		e.preventDefault();

		if ($("a.switch").hasClass("active")) {
			$(this)
				.parents(".form-peice")
				.addClass("switched")
				.siblings(".form-peice")
				.removeClass("switched");
		} else {
			$(this)
				.parents(".form-peice")
				.removeClass("switched")
				.siblings(".form-peice")
				.addClass("switched");
		}
	});

var path = new URL(window.location.href).pathname;

	var url = "/pragna_dev";
	//var url = "/pragna_qa";
	var id=path.split("/")[3];
	
	
	//var url = "";
	//var id=path.split("/")[2];
	

	// Form submit
	$("form.signup-form").submit(function(event) {
		event.preventDefault();
		if (
			usernameError == true ||
			emailError == true ||
			passwordError == true ||
			passConfirm == true ||
			phoneError == true
		) {
			
			$(".name, .email, .phone, .pass, .passConfirm").blur();
		} else {
			$(".signup, .login").addClass("switched");
			$("#overlay").fadeIn();
			var formData = new FormData();
			formData.append("userName", $("#name").val());
			formData.append("userEmail", $("#email").val());
			formData.append("userPhone", selectedCountryCode+" "+$("#phone").val());
			formData.append("userPassword", $("#password").val());

			$.ajax({
				type: "POST",
				url: url + "/user/addUser",
				data: formData,
				processData: false,
				contentType: false,
				success: function(response) {
					console.log(response);
					$("#overlay").fadeOut();
					setTimeout(function() {
						$(".brand").addClass("active");
					}, 300);
					setTimeout(function() {
						$(".heading").addClass("active").css("margin-left", "94px");
					}, 600);
					setTimeout(function() {
						$(".success-msg p").addClass("active");
					}, 900);
					setTimeout(function() {
						$(".success-msg a").addClass("active");
					}, 1050);
					setTimeout(function() {
						$(".form").hide();
					}, 700);
				},
				error: function(xhr, status, error) {
					console.error("Error:", error);
				}
			});

			setTimeout(function() {
				$(".signup, .login").hide();
			}, 700);

		}
	});

	$("#login").click(function() {

		var formData = new FormData();
		formData.append("loginEmail", $("#loginemail").val());
		formData.append("loginPassword", $("#loginPassword").val());

		if (id == undefined) {
		    $.ajax({
			type: "POST",
			url: url + "/user/loginUser",
			data: formData,
			processData: false,
			contentType: false,
			success: function(response) {
				console.log(response);
				if(response == "")
				{
					alert("Plase check username/password");
				}
				if (response.verify == false) {
					alert("Please verify your identity...!");
				}
				if (response.verify == true) {
					window.open(url+"/add_child/"+response.parentId, "_self");
				}
			},
			error: function(xhr, status, error) {
				console.error("Error:", error);
			}
		});
		} else {
			if(id == $("#loginemail").val())
			{
			$.ajax({
			type: "POST",
			url: url + "/user/loginWithVerifyUser",
			data: formData,
			processData: false,
			contentType: false,
			success: function(response) {
				console.log(response);
				if(response == "")
				{
					alert("Plase check username/password");
				}
				if (response.verify == true) {
					window.open(url+"/add_child/"+response.parentId, "_self");
				}
			},
			error: function(xhr, status, error) {
				console.error("Error:", error);
			}
		});
		}
		else{
			$.ajax({
			type: "POST",
			url: url + "/user/loginUser",
			data: formData,
			processData: false,
			contentType: false,
			success: function(response) {
				console.log(response);
				if(response == "")
				{
					alert("Plase check username/password");
				}
				if (response.verify == false) {
					alert("Please verify your identity...!");
				}
				if (response.verify == true) {
					window.open(url+"/add_child/"+response.parentId, "_self");
				}
			},
			error: function(xhr, status, error) {
				console.error("Error:", error);
			}
		});
		}
		}
		
	})

	
});
