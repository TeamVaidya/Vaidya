document.getElementById('sidebar-toggle').addEventListener('click', function() {
    const sidebar = document.getElementById('sidebar');
    const mainContainer = document.getElementById('main-container');
    const slotsContainer = document.getElementById('slots-container');
    const calendarContainer = document.getElementById('calendar-container');

    if (sidebar.style.width === '200px') {
        sidebar.style.width = '0';
        mainContainer.style.marginLeft = '0';
        slotsContainer.style.width = '100%';
        calendarContainer.style.width = '100%';
    } else {
        sidebar.style.width = '200px';
        mainContainer.style.marginLeft = '200px';
        slotsContainer.style.width = 'calc(100% - 200px)';
        calendarContainer.style.width = 'calc(100% - 200px)';
    }
});

// Initialize Flatpickr Calendar
flatpickr("#calendar", {
    inline: true,
    onChange: function(selectedDates, dateStr, instance) {
        fetchAppointments(dateStr);
    }
});

// Load appointments for today's date by default
const today = new Date();
fetchAppointments(today.toISOString().split('T')[0]);

function fetchAppointments(selectedDate) {
    const doctor = JSON.parse(localStorage.getItem('doctor'));

    if (!doctor || !doctor.userId) {
        alert("Please log in to view your appointments.");
        return;
    }

    const apiUrl = `http://localhost:8082/api/slots/search?date=${selectedDate}&userId=${doctor.userId}`;

    fetch(apiUrl)
        .then(response => response.json())
        .then(data => {
            const slotsContainer = document.getElementById('slots-container');
            const appointmentsHeader = document.getElementById('appointments-header');
            
            appointmentsHeader.textContent = `Appointments, ${new Date(selectedDate).toDateString()}`;
            slotsContainer.innerHTML = '';

            if (data.length === 0) {
                slotsContainer.innerHTML = '<h4 id="no-slots">Please create slots for the selected date.</h4>';
            } else {
                data.forEach(slot => {
                    const slotElement = document.createElement('div');
                    slotElement.classList.add('slot');
                    
                    if (slot.status === "yes") {
                        slotElement.classList.add('green');
                        slotElement.addEventListener('click', function() {
                            showSearchForm(slot); 
                        });
                    } else {
                        slotElement.classList.add('red');
                        slotElement.addEventListener('click', function() {
                            showModifyForm(slot); 
                        });
                    }

                    slotElement.innerHTML = `
                        <p><strong>Slot:</strong> ${slot.slotId}</p> 
                        <p><strong>Time:</strong> ${slot.startTime}</p>
                    `;

                    slotsContainer.appendChild(slotElement);
                });
            }
        })
        .catch(error => console.error('Error fetching slots:', error));
}


function showSearchForm(slot) {
    const modal = document.getElementById("patient-search-modal");
    modal.style.display = "block";
    document.body.classList.add("modal-open");

    
    document.getElementById("patient-form").reset(); 
    document.getElementById("mobile-input").value = ''; 
    document.getElementById("patient-names-list").innerHTML = ''; 

   
    document.getElementById("dateTime").value = `${slot.date} ${slot.startTime}`; 
    document.getElementById("dateTime").readOnly = true; 

    modal.dataset.slotInfo = JSON.stringify(slot); 
}

function showModifyForm(slot) {
    const modal = document.getElementById("patient-search-modal");
    modal.style.display = "block";
    document.body.classList.add("modal-open");

    // Clear form fields
    document.getElementById("patient-form").reset(); // Clear the patient form
    document.getElementById("mobile-input").value = ''; 
    document.getElementById("patient-names-list").innerHTML = ''; 

    // Fill in date and time
    document.getElementById("dateTime").value = `${slot.date} ${slot.startTime}`; 
    document.getElementById("dateTime").readOnly = true; 

    
    modal.dataset.slotInfo = JSON.stringify(slot); 

   
    document.getElementById("book-button").textContent = "Modify";
}

document.addEventListener("DOMContentLoaded", function() {
    
    document.querySelector(".close").addEventListener("click", function() {
        document.getElementById("patient-search-modal").style.display = "none";
        document.body.classList.remove("modal-open");
    });
  
    // Search patients by mobile number
    document.getElementById("search-button").addEventListener("click", function() {
        const mobileNumber = document.getElementById("mobile-input").value;

        fetch(`http://localhost:8083/api/patients/search1?mobileNo=${mobileNumber}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! Status: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                const patientList = document.getElementById("patient-names-list");
                patientList.innerHTML = ""; // Clear previous results

                if (Array.isArray(data)) {
                    data.forEach(patient => {
                        const patientElement = document.createElement("div");
                        patientElement.textContent = patient.patientName;
                        patientElement.classList.add("patient-name");

                        patientElement.addEventListener("click", function() {
                            const modal = document.getElementById("patient-search-modal");
                            const slot = JSON.parse(modal.dataset.slotInfo); 
                            populateForm(patient, slot); 
                        });

                        patientList.appendChild(patientElement);
                    });
                } else {
                    console.error("Unexpected response format:", data);
                }
            })
            .catch(error => {
                console.error("Error:", error);
                alert("An error occurred while fetching patient data. Please try again.");
            });
    });

    function populateForm(patient) {
        const modal = document.getElementById("patient-search-modal");
        const slot = JSON.parse(modal.dataset.slotInfo); // Retrieve the slot info
        
        document.getElementById("patientName").value = patient.patientName;
        document.getElementById("mobileNo").value = patient.mobileNo;
        document.getElementById("email").value = patient.email;
        document.getElementById("aadharNo").value = patient.aadharNo;
        document.getElementById("age").value = patient.age;

        // Fill in date and time if the slot info is available
        if (slot) {
            document.getElementById("dateTime").value = `${slot.date} ${slot.startTime}`; // Combine date and startTime
            document.getElementById("dateTime").readOnly = true; // Make dateTime readonly
        }
    
        document.getElementById("address").value = patient.address;
    }
    
    document.getElementById("book-button").addEventListener("click", function() {
        const modal = document.getElementById("patient-search-modal");
        const slot = JSON.parse(modal.dataset.slotInfo); // Retrieve the slot info
        const doctor = JSON.parse(localStorage.getItem('doctor')); // Get doctor info from localStorage
    
        const formData = {
            patientName: document.getElementById("patientName").value,
            mobileNo: document.getElementById("mobileNo").value,
            email: document.getElementById("email").value,
            aadharNo: document.getElementById("aadharNo").value,
            age: document.getElementById("age").value,
            dateTime: document.getElementById("dateTime").value,
            address: document.getElementById("address").value,
            roleId: 1, // Default roleId
            doctor: {
                userId: doctor.userId // Use the doctor userId from localStorage
            },
            slot: {
                slotId: slot.slotId 
            }
        };
    
        if (this.textContent === "Modify") {
            const slotId = slot.slotId; 
            const updateStatusUrl = `http://localhost:8082/api/slots/${slotId}?status=no`;
    
            fetch(updateStatusUrl, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json"
                }
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error("Failed to update slot status");
                }
                alert("Status updated. Please refresh the page.");
                document.getElementById("patient-search-modal").style.display = "none";
                document.body.classList.remove("modal-open");
            })
            .catch(error => {
                console.error("Error:", error);
                alert("Error occurred: " + error.message);
            });
        } else {
            // Send booking request
            fetch("http://localhost:8083/api/patients", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(formData)
            })
            .then(response => response.json())
            .then(data => {
                console.log("Booking Successful:", data);
                alert("Booking Successful");
    
                
                const slotId = slot.slotId; // Get the slot ID from the slot object
                const updateStatusUrl = `http://localhost:8082/api/slots/${slotId}?status=no`;
    
                return fetch(updateStatusUrl, {
                    method: "PUT",
                    headers: {
                        "Content-Type": "application/json"
                    }
                });
            })
            .then(updateResponse => {
                if (!updateResponse.ok) {
                    throw new Error("Failed to update slot status");
                }
                alert("Status updated. Please refresh the page.");
                document.getElementById("patient-search-modal").style.display = "none";
                document.body.classList.remove("modal-open");
            })
            .catch(error => {
                console.error("Error:", error);
                alert("Error occurred: " + error.message);
            });
        }
    });
});
document.getElementById("create-slots-button").addEventListener("click", function() {
    document.getElementById("create-slots-modal").style.display = "block";
    document.body.classList.add("modal-open");
});

function closeCreateSlotsModal() {
    document.getElementById("create-slots-modal").style.display = "none";
    document.body.classList.remove("modal-open");
}


document.getElementById("create-slot-button").addEventListener("click", function() {
    const startTime = document.getElementById("startTime").value;
    const endTime = document.getElementById("endTime").value;
    const slotRange = document.getElementById("slotRange").value;
    const date = document.getElementById("date").value;
    const userId = JSON.parse(localStorage.getItem("doctor")).userId; 

    const slotData = {
        startTime: startTime,
        endTime: endTime,
        slotRange: slotRange,
        userId: userId,
        date: date
    };

    fetch("http://localhost:8082/api/slots", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(slotData)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Failed to create slot");
        }
        return response.json();
    })
    .then(data => {
        alert("Slot created successfully!");
        closeCreateSlotsModal(); // Close modal after successful creation
        fetchAppointments(new Date(date).toISOString().split('T')[0]);
    })
    .catch(error => {
        console.error("Error:", error);
        alert("Error occurred: " + error.message);
    });
});
