document.getElementById('sidebar-toggle').addEventListener('click', function() {
    const sidebar = document.getElementById('sidebar');
    const content = document.getElementById('content');
    
    if (sidebar.style.width === '200px') {
        sidebar.style.width = '0';
        content.style.marginLeft = '0';
    } else {
        sidebar.style.width = '200px';
        content.style.marginLeft = '200px';
    }
});

function closeSidebar() {
    const sidebar = document.getElementById('sidebar');
    const content = document.getElementById('content');
    sidebar.style.width = '0';
    content.style.marginLeft = '0';
}

function closeSidebar() {
    const sidebar = document.getElementById('sidebar');
    const content = document.getElementById('content');

    sidebar.style.width = '0'; 
    content.style.marginLeft = '0'; 
}


document.getElementById('appointments-link').addEventListener('click', function(event) {
    event.preventDefault();
    console.log("Appointments link clicked");

    
    const currentDate = new Date().toISOString().split('T')[0]; 

    
    const doctor = JSON.parse(localStorage.getItem('doctor'));
    
    if (!doctor || !doctor.userId) {
        console.error("Doctor not logged in or userId not available.");
        alert("Please log in to view your appointments.");
        return;
    }

    // Use the doctor.userId in the API call
    const apiUrl = `http://localhost:8082/api/slots/search?date=${currentDate}&userId=${doctor.userId}`;

    fetch(apiUrl)
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            console.log("API response received:", data);
            const slotsContainer = document.getElementById('slots-container');

            slotsContainer.innerHTML = '';                          
            const header = document.createElement('h2');
            header.textContent = "Appointments";
            slotsContainer.appendChild(header);

            if (data.length === 0) {
                slotsContainer.innerHTML += '<p>No slots available for the selected date.</p>';
            } else {
                data.forEach((slot, index) => {
                    // Conditionally render "Book" or "Modify" button based on slot status
                    const buttonHtml = slot.status === "yes"
                        ? '<button class="book">Book</button>'
                        : '<button class="modify">Modify</button>';

                    
                    const slotElement = document.createElement('div');
                    slotElement.classList.add('slot');
                    slotElement.innerHTML = `
                        <div>
                            <p><strong>SlotId:</strong> ${slot.slotId}</p>
                            <p><strong>Time:</strong> ${slot.startTime}</p>
                        </div>
                        <div>
                            ${buttonHtml}
                        </div>
                    `;
                    slotsContainer.appendChild(slotElement);
                });
            }
        })
        .catch(error => {
            console.error('Error fetching slots:', error);
        });
});


