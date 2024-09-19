document.addEventListener('DOMContentLoaded', () => {
    const sidebarToggle = document.getElementById('sidebar-toggle');
    const sidebar = document.querySelector('.sidebar');
    const content = document.getElementById('content');
    const closeSidebarButton = document.querySelector('.closebtn');
    
    if (sidebarToggle) {
        sidebarToggle.addEventListener('click', function() {
            if (sidebar.style.width === '200px') {
                closeSidebar();
            } else {
                sidebar.style.width = '200px';
                if (content) content.style.marginLeft = '200px';
            }
        });
    }

    function closeSidebar() {
        if (sidebar) sidebar.style.width = '0';
        if (content) content.style.marginLeft = '0';
    }

    if (closeSidebarButton) {
        closeSidebarButton.addEventListener('click', closeSidebar);
    }

    const doctorRegistrationForm = document.getElementById('doctor-registration');
    const userRegistrationForm = document.getElementById('user-registration');
    const loginForm = document.getElementById('login-form');
    const doctorDetailsEl = document.getElementById('doctor-details');
    const logoutBtn = document.getElementById('logout-btn');
    const registerDoctorBtn = document.getElementById('register-doctor-btn');
    const registerUserBtn = document.getElementById('register-user-btn');
    const loginBtn = document.getElementById('login-btn');

    function showForm(formId) {
        if (doctorRegistrationForm) doctorRegistrationForm.style.display = 'none';
        if (userRegistrationForm) userRegistrationForm.style.display = 'none';
        if (loginForm) loginForm.style.display = 'none';

        if (formId === 'doctor-registration' && doctorRegistrationForm) {
            doctorRegistrationForm.style.display = 'block';
        } else if (formId === 'user-registration' && userRegistrationForm) {
            userRegistrationForm.style.display = 'block';
        } else if (formId === 'login-form' && loginForm) {
            loginForm.style.display = 'block';
        }
    }

    function updateUIAfterLogin(data) {
        if (doctorDetailsEl) {
            doctorDetailsEl.innerHTML = `
                <h2>Welcome, ${data.fullName}</h2>
                <p><strong>Email:</strong> ${data.userEmail}</p>
                <p><strong>Specialization:</strong> ${data.specialization}</p>
                <p><strong>Qualification:</strong> ${data.qualification}</p>
                <p><strong>Experience:</strong> ${data.experience} years</p>
                <p><strong>Address:</strong> ${data.address}</p>
            `;
        }

        // Hide registration and login buttons and forms
        if (registerDoctorBtn) registerDoctorBtn.style.display = 'none';
        if (registerUserBtn) registerUserBtn.style.display = 'none';
        if (loginBtn) loginBtn.style.display = 'none';
        if (doctorRegistrationForm) doctorRegistrationForm.style.display = 'none';
        if (userRegistrationForm) userRegistrationForm.style.display = 'none';
        if (loginForm) loginForm.style.display = 'none';

        // Show the logout button
        if (logoutBtn) logoutBtn.style.display = 'block';
    }

    if (registerDoctorBtn) {
        registerDoctorBtn.addEventListener('click', () => showForm('doctor-registration'));
    }

    if (registerUserBtn) {
        registerUserBtn.addEventListener('click', () => showForm('user-registration'));
    }

    if (loginBtn) {
        loginBtn.addEventListener('click', () => showForm('login-form'));
    }

    const doctorRegistrationFormEl = document.getElementById('doctor-registration-form');
    const userRegistrationFormEl = document.getElementById('user-registration-form');
    const loginFormEl = document.getElementById('login-form');

    if (doctorRegistrationFormEl) {
        doctorRegistrationFormEl.addEventListener('submit', (e) => {
            e.preventDefault();
            const doctorData = {
                fullName: document.getElementById('doctor-fullName').value,
                userEmail: document.getElementById('doctor-userEmail').value,
                aadharNo: document.getElementById('user-aadharNo').value,
                specialization: document.getElementById('doctor-specialization').value,
                qualification: document.getElementById('doctor-qualification').value,
                experience: parseInt(document.getElementById('doctor-experience').value),
                address: document.getElementById('doctor-address').value,
                phoneNo: document.getElementById('doctor-phoneNo').value,
                gender: document.getElementById('doctor-gender').value,
                password: document.getElementById('doctor-password').value

            };

            const messageContainer = document.getElementById('message-container');
            const messageText = document.getElementById('message-text');
            const loadingText = document.getElementById('loading-text');

            // Show loading message
            messageContainer.style.display = 'block';
            loadingText.style.display = 'block';
            messageText.style.display = 'none';

            fetch('http://localhost:8080/doctor/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(doctorData)
            })
            .then(response => response.text())
            .then(text => {
                loadingText.style.display = 'none'; // Hide loading message

                
                if (text.includes("Verify email by the link sent to your email address")) {
                    alert("Verify email by the link sent to your email address");
                    showForm('login-form'); // Show login form after registration
                } else {
                    messageText.textContent = text;
                    messageText.style.display = 'block';
                }
            })
            .catch(error => {
                loadingText.style.display = 'none'; // Hide loading message
                messageText.textContent = 'Error: ' + error.message;
                messageText.style.display = 'block';
            });
        });
    }

   

    if (loginFormEl) {
        loginFormEl.addEventListener('submit', (e) => {
            e.preventDefault();
            const loginData = {
                email: document.getElementById('login-email').value,
                password: document.getElementById('login-password').value
            };

            fetch('http://localhost:8080/login/doctor', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(loginData)
            })
            .then(response => response.text())
            .then(text => {
                if (text.includes("Doctor login successful")) {
                    const email = loginData.email;

                    fetch(`http://localhost:8080/doctor/email?email=${email}`)
                        .then(response => response.json())
                        .then(data => {
                            // Save login state and doctor details
                            localStorage.setItem('doctor', JSON.stringify(data));

                            // Update UI after login
                            updateUIAfterLogin(data);
                        })
                        .catch(error => {
                            console.error('Error fetching doctor details:', error);
                            alert('Error fetching doctor details');
                        });
                } else {
                    alert("Login API Response: " + text);
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Error: ' + error.message);
            });
        });
    }

    if (logoutBtn) {
        logoutBtn.addEventListener('click', () => {
            localStorage.removeItem('doctor');
            if (doctorDetailsEl) doctorDetailsEl.innerHTML = '';

            // Show forms and hide logout button after logging out
            if (registerDoctorBtn) registerDoctorBtn.style.display = 'block';
            if (registerUserBtn) registerUserBtn.style.display = 'block';
            if (loginBtn) loginBtn.style.display = 'block';
            if (logoutBtn) logoutBtn.style.display = 'none';
        });
    }

    const doctor = JSON.parse(localStorage.getItem('doctor'));
    if (doctor) {
        updateUIAfterLogin(doctor);
    } else {
        showForm('login-form'); // Show login form by default
    }
});
