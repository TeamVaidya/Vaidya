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
