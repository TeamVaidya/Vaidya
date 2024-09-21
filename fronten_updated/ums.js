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

document.addEventListener('DOMContentLoaded', function() {
    fetchUsers();
    setupPaginationControls(); // Attach event listeners only once when the page is loaded
});

let currentPage = 0;
const pageSize = 1;
let totalPages = 10;

function fetchUsers(page = currentPage, size = pageSize) {
    fetch(`http://localhost:8081/users/pagination?page=${page}&size=${size}`)
        .then(response => response.json())
        .then(data => {
            const userTableBody = document.getElementById('user-table-body');
            userTableBody.innerHTML = ''; 

            data.users.forEach(user => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td><input type="checkbox" class="user-checkbox" data-user-id="${user.userId}"></td>
                    <td>${user.userId}</td>
                    <td>${user.fullName}</td>
                    <td>${user.userEmail}</td>
                    <td>${user.address}</td>
                    <td>${user.roleId}</td>
                `;
                userTableBody.appendChild(row);
            });

            const checkboxes = document.querySelectorAll('.user-checkbox');
            checkboxes.forEach(checkbox => {
                checkbox.addEventListener('change', function() {
                    if (this.checked) {
                        const userId = this.getAttribute('data-user-id');
                        //fetchUserDetails(userId);
                    }
                });
            });

            totalPages = data.totalPages;
            updatePaginationControls();
        })
        .catch(error => console.error('Error fetching users:', error));
}

function setupPaginationControls() {
    // Attach event listeners once when the page is loaded
    document.getElementById('prev-page').addEventListener('click', () => {
        if (currentPage > 0) {
            currentPage--;
            fetchUsers(currentPage);
        }
    });

    document.getElementById('next-page').addEventListener('click', () => {
        if (currentPage < totalPages - 1) {
            currentPage++;
            fetchUsers(currentPage);
        }
    });
}

function updatePaginationControls() {
    // Only update the button states and page info here
    document.getElementById('prev-page').disabled = currentPage === 0;
    document.getElementById('next-page').disabled = currentPage >= totalPages - 1;

    document.getElementById('page-info').textContent = `Page ${currentPage + 1} of ${totalPages}`;
}

function fetchUserDetails(userId) {
    fetch(`http://localhost:8081/users/${userId}`)
        .then(response => response.json())
        .then(user => {
            showUserDetails(user);
        })
        .catch(error => console.error('Error fetching user details:', error));
}

function showUserDetails(user) {
    document.body.classList.add('blurred');

    const userDetailsModal = document.createElement('div');
    userDetailsModal.className = 'user-details-modal';
    
    userDetailsModal.innerHTML = `
    <div class="modal-content">
        <span class="close-modal" onclick="closeUserDetailsModal()">&times;</span>
        <h2>User Details</h2>
        <div class="modal-form">
            <div class="form-row">
                <div class="form-group">
                    <label for="userId"><strong>User ID:</strong></label>
                    <input type="text" id="userId" value="${user.userId}" readonly>
                </div>
                <div class="form-group">
                    <label for="fullName"><strong>Full Name:</strong></label>
                    <input type="text" id="fullName" value="${user.fullName}" readonly>
                </div>
                <div class="form-group">
                    <label for="email"><strong>Email:</strong></label>
                    <input type="email" id="email" value="${user.userEmail}" readonly>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group">
                    <label for="specialization"><strong>Specialization:</strong></label>
                    <input type="text" id="specialization" value="${user.specialization || 'N/A'}" readonly>
                </div>
                <div class="form-group">
                    <label for="qualification"><strong>Qualification:</strong></label>
                    <input type="text" id="qualification" value="${user.qualification || 'N/A'}" readonly>
                </div>
                <div class="form-group">
                    <label for="experience"><strong>Experience:</strong></label>
                    <input type="text" id="experience" value="${user.experience || 'N/A'}" readonly>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group">
                    <label for="address"><strong>Address:</strong></label>
                    <input type="text" id="address" value="${user.address}" readonly>
                </div>
                <div class="form-group">
                    <label for="diseases"><strong>Diseases:</strong></label>
                    <input type="text" id="diseases" value="${user.diseases || 'N/A'}" readonly>
                </div>
                <div class="form-group">
                    <label for="roleId"><strong>Role ID:</strong></label>
                    <input type="text" id="roleId" value="${user.roleId}" readonly>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group">
                    <label for="aadharNo"><strong>Aadhar No:</strong></label>
                    <input type="text" id="aadharNo" value="${user.aadharNo || 'N/A'}" readonly>
                </div>
                <div class="form-group">
                    <label for="enabled"><strong>Enabled:</strong></label>
                    <input type="text" id="enabled" value="${user.enabled ? 'Yes' : 'No'}" readonly>
                </div>
            </div>
            <div class="button-container">
                <button id="editButton">Edit</button>
                <button id="updateButton" style="display:none;">Update</button>
                <button id="deleteButton">Delete</button>
            </div>
        </div>
    </div>
`;

    document.body.appendChild(userDetailsModal);
    enableEditing();


    //document.getElementById('edit-button').addEventListener('click', enableEditing);
    document.getElementById('updateButton').addEventListener('click', () => updateUserDetails(user.userId));
    document.getElementById('deleteButton').addEventListener('click', () => deleteUser(user.userId));
}
document.getElementById('delete-button').addEventListener('click', () => deleteUser(user.userId));

function enableEditing() {
    
    document.querySelectorAll('.modal-form input').forEach(input => {
        if (input.id !== 'userId' && input.id !== 'roleId') {
            input.removeAttribute('readonly');
        }
    });

    // Show the update button and hide the edit button
    document.getElementById('editButton').style.display = 'none';
    document.getElementById('updateButton').style.display = 'inline-block';
}

function updateUserDetails(userId) {
    const updatedUser = {
        fullName: document.getElementById('fullName').value,
        userEmail: document.getElementById('email').value,
        specialization: document.getElementById('specialization').value,
        qualification: document.getElementById('qualification').value,
        experience: document.getElementById('experience').value,
        address: document.getElementById('address').value,
        diseases: document.getElementById('diseases').value,
        aadharNo: document.getElementById('aadharNo').value,
        enabled: document.getElementById('enabled').value === 'Yes'
    };

    fetch(`http://localhost:8081/users/${userId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(updatedUser)
    })
    .then(response => {
        if (response.ok) {
            alert('User updated successfully');
            closeUserDetailsModal();
            fetchUsers(); 
        } else {
            alert('Error updating user');
        }
    })
    .catch(error => console.error('Error updating user details:', error));
}
function deleteUsers(userIds) {
    if (userIds.length === 0) {
        alert('No users selected for deletion.');
        return;
    }

    if (confirm('Are you sure you want to delete the selected users?')) {
       
        const deletePromises = userIds.map(userId =>
            fetch(`http://localhost:8081/users/${userId}`, {
                method: 'DELETE'
            })
        );

        // Execute all delete promises
        Promise.all(deletePromises)
            .then(responses => {
                if (responses.every(response => response.ok)) {
                    alert('Users deleted successfully');
                    fetchUsers(); // Refresh the user list
                } else {
                    alert('Error deleting some users');
                }
            })
            .catch(error => console.error('Error deleting users:', error));
    }
}


function closeUserDetailsModal() {
    document.querySelector('.user-details-modal').remove();
    document.body.classList.remove('blurred');
}


function closeUserDetailsModal() {
   
    document.body.classList.remove('blurred');
    
    const modal = document.querySelector('.user-details-modal');
    if (modal) {
        modal.remove();
    }
}
document.getElementById("remove-filters-button").style.display = "none";

document.getElementById('filter-button').addEventListener('click', function() {
    const filterButton = document.getElementById('filter-button');
    const userTable = document.getElementById('user-table');
    const filterRowId = 'filter-row';

    // Check if the filter row already exists
    if (!document.getElementById(filterRowId)) {
        const filterRow = document.createElement('tr');
        filterRow.id = filterRowId;

        filterRow.innerHTML = `
            <td></td>
            <td><input type="text" id="filter-userId" placeholder="Filter by User ID"></td>
            <td><input type="text" id="filter-fullName" placeholder="Filter by Full Name"></td>
            <td><input type="text" id="filter-email" placeholder="Filter by Email"></td>
            <td><input type="text" id="filter-address" placeholder="Filter by Address"></td>
            <td><input type="text" id="filter-roleId" placeholder="Filter by Role ID"></td>
        `;

        userTable.querySelector('thead').appendChild(filterRow);
        filterButton.textContent = 'Apply'; 

        document.getElementById("remove-filters-button").style.display = "block";

        
        filterButton.addEventListener('click', function applyFilter() {
            applyFilters(); 
          
        });
    }
});
function applyFilters() {
    
    const userId = document.getElementById('filter-userId').value.trim();
    const fullName = document.getElementById('filter-fullName').value.trim();
    const userEmail = document.getElementById('filter-email').value.trim();
    const address = document.getElementById('filter-address').value.trim();
    const roleId = document.getElementById('filter-roleId').value.trim();

    
    const queryParams = {};

    if (userId) queryParams.userId = userId;
    if (fullName) queryParams.fullName = fullName;
    if (userEmail) queryParams.userEmail = userEmail;
    if (address) queryParams.address = address;
    if (roleId) queryParams.roleId = roleId;

   
    if (Object.keys(queryParams).length === 0) {
        alert('Please provide at least one filter value');
        return;
    }

    const queryString = new URLSearchParams(queryParams).toString();

    const apiUrl = `http://localhost:8081/users/filterUsers?${queryString}`;


    fetch(apiUrl)
        .then(response => response.json())
        .then(data => {
            
            const tableBody = document.getElementById('user-table-body');
            tableBody.innerHTML = '';

            // Check if any users were returned
            if (data.length === 0) {
                const noUsersRow = document.createElement('tr');
                noUsersRow.innerHTML = `
                    <td colspan="6" style="text-align: center;">0 users found</td>
                `;
                tableBody.appendChild(noUsersRow);
            } else {
               
                data.forEach(user => {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td><input type="checkbox" /></td>
                        <td>${user.userId}</td>
                        <td>${user.fullName}</td>
                        <td>${user.userEmail}</td>
                        <td>${user.address}</td>
                        <td>${user.roleId}</td>
                    `;
                    tableBody.appendChild(row);
                });
            }
        })
        .catch(error => console.error('Error fetching data:', error));
}

document.getElementById("remove-filters-button").addEventListener("click", function() {
    
    window.location.href = "ums.html";  
});


document.addEventListener('DOMContentLoaded', function() {
    const userTableBody = document.getElementById('user-table-body');
    const actionButtons = document.getElementById('action-buttons');
    const editButton = document.getElementById('edit-button');
    const deleteButton = document.getElementById('delete-button');

    // Event listener for checkboxes
    userTableBody.addEventListener('change', (e) => {
        // Make sure to use '.user-checkbox' class
        const selectedUsers = document.querySelectorAll('.user-checkbox:checked');

        if (selectedUsers.length > 0) {
            actionButtons.style.display = 'block';

            if (selectedUsers.length === 1) {
                editButton.style.display = 'inline-block';
                deleteButton.style.display = 'inline-block';
            } 
          
            else if (selectedUsers.length > 1) {
                editButton.style.display = 'none';
                deleteButton.style.display = 'inline-block';
            }
        } else {
            // Hide buttons if no users are selected
            actionButtons.style.display = 'none';
        }
    });
    editButton.addEventListener('click', () => {
        
        const selectedCheckbox = document.querySelector('.user-checkbox:checked');
        if (selectedCheckbox) {
            const userId = selectedCheckbox.getAttribute('data-user-id');
            
            fetchUserDetails(userId);
        } else {
            console.log('No user selected');
        }
    });

deleteButton.addEventListener('click', () => {
    const selectedCheckboxes = document.querySelectorAll('.user-checkbox:checked');
    const userIds = Array.from(selectedCheckboxes).map(checkbox => checkbox.getAttribute('data-user-id'));
    deleteUsers(userIds);
});

});
