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


window.viewPrescription = function(patientId) {
    const apiUrl = `http://localhost:8083/api/patients/${patientId}`; 

    fetch(apiUrl)
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json(); 
        })
        .then(data => {
            
            document.getElementById('clinic-name').textContent = data.doctor.clinicName;
            document.getElementById('timings').textContent = `${data.slot.startTime} - ${data.slot.endTime}`;
            document.getElementById('phone-number').textContent = data.doctor.phoneNumber || 'N/A';
            document.getElementById('patient-name').textContent = data.patientName;
            document.getElementById('patient-id').textContent = data.patientId;
            document.getElementById('gender').textContent = data.doctor.gender;
            document.getElementById('age').textContent = data.age;
            document.getElementById('mobile').textContent = data.mobileNo;
            // Fill in other symptoms as required
            document.getElementById('fever').textContent = 'N/A'; 
            document.getElementById('bp').textContent = 'N/A';
            document.getElementById('weight').textContent = 'N/A';
            document.getElementById('sugar').textContent = 'N/A'; 
            document.getElementById('history').textContent = 'N/A'; 
            document.getElementById('test').textContent = 'N/A';
            document.getElementById('medicine').textContent = 'N/A'; 
            
            // Show the modal
            document.getElementById('patient-detail-modal').style.display = "block";
        })
        .catch(error => {
            console.error('Error fetching patient data:', error);
        });
};

window.closeModal = function() {
    document.getElementById('patient-detail-modal').style.display = "none";
};

document.addEventListener('DOMContentLoaded', () => {
    // Using static API URL for now
    const apiUrl = `http://localhost:8083/api/patients/doctor/7/date/2024-09-22`;

    function fetchPrescriptionData() {
        fetch(apiUrl)
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! Status: ${response.status}`);
                }
                return response.json(); // Parse the response as JSON
            })
            .then(data => {
                populatePrescriptionTable(data);
            })
            .catch(error => {
                console.error('Error fetching prescription data:', error);
            });
    }

    function populatePrescriptionTable(prescriptions) {
        const tableBody = document.querySelector('#user-table-body');
        tableBody.innerHTML = ''; // Clear existing rows

        if (prescriptions.length === 0) {
            const emptyRow = document.createElement('tr');
            emptyRow.innerHTML = '<td colspan="5">No prescriptions found for the selected date.</td>';
            tableBody.appendChild(emptyRow);
        } else {
            prescriptions.forEach(prescription => {
                const row = document.createElement('tr');

                row.innerHTML = `
                    <td>${prescription.patientName}</td>
                    <td>${prescription.mobileNo}</td>
                    <td>Dr. ${prescription.doctor.fullName}</td>
                    <td>${prescription.dateTime.split('T')[0]}</td>
                    <td><button onclick="viewPrescription(${prescription.patientId})">View</button></td>
                `;

                tableBody.appendChild(row);
            });
        }
    }

    fetchPrescriptionData();
});
