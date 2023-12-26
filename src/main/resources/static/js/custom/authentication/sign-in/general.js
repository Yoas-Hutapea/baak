"use strict";

var KTSigninGeneral = function () {
    var form, submitButton, formValidation;
    return {
        init: function () {
            form = document.querySelector("#kt_sign_in_form");
            submitButton = document.querySelector("#kt_sign_in_submit");
            formValidation = FormValidation.formValidation(form, {
                fields: {
                    username: {
                        validators: {
                            notEmpty: {
                                message: "Username is required"
                            }
                        }
                    },
                    password: {
                        validators: {
                            notEmpty: {
                                message: "The password is required"
                            }
                        }
                    }
                },
                plugins: {
                    trigger: new FormValidation.plugins.Trigger,
                    bootstrap: new FormValidation.plugins.Bootstrap5({
                        rowSelector: ".fv-row"
                    })
                }
            });

            console.log("Form:", form);
            console.log("Submit Button:", submitButton);
            console.log("Form Validation:", formValidation);

            submitButton.addEventListener("click", async function (event) {
                event.preventDefault();

                console.log("Submit Button Clicked");

                const status = await formValidation.validate();

                if (status === "Valid") {
                    try {
                        const response = await fetch('/login', {
                            method: 'POST',
                            body: new FormData(form)
                        });

                        if (response.ok) {
                            // Handle success response
                            const responseBody = await response.text();
                            console.log("Success Response Body:", responseBody);

                            Swal.fire({
                                text: "You have successfully logged in!",
                                icon: "success",
                                buttonsStyling: !1,
                                confirmButtonText: "Ok, got it!",
                                customClass: {
                                    confirmButton: "btn btn-primary"
                                }
                            }).then((function (result) {
                                if (result.isConfirmed) {
                                    // Redirect to the appropriate page
                                    if (responseBody === "redirect:/") {
                                        window.location.href = "/";  // Change to the appropriate URL
                                    } else if (responseBody === "redirect:/kaosuser") {
                                        window.location.href = "/kaosuser";  // Change to the appropriate URL
                                    }
                                }
                            }));
                        } else {
                            // Handle error response
                            Swal.fire({
                                text: "Sorry, looks like there are some errors detected, please try again.",
                                icon: "error",
                                buttonsStyling: !1,
                                confirmButtonText: "Ok, got it!",
                                customClass: {
                                    confirmButton: "btn btn-primary"
                                }
                            });
                        }
                    } catch (error) {
                        // Handle network or server error
                        console.error("Error:", error);
                    }
                } else {
                    // Handle form validation error
                    Swal.fire({
                        text: "Sorry, looks like there are some errors detected, please try again.",
                        icon: "error",
                        buttonsStyling: !1,
                        confirmButtonText: "Ok, got it!",
                        customClass: {
                            confirmButton: "btn btn-primary"
                        }
                    });
                }
            });
        }
    };
}();

KTUtil.onDOMContentLoaded((function () {
    KTSigninGeneral.init();
}));
