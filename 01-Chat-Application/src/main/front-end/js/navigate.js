export function navigate(path) {
    const welcomePage = document.querySelector('.start-page');
    const chatPage = document.querySelector('.chat-page');
    const welcomePageButtons = document.querySelector('.buttons');

    if (path === '/chat') {
        welcomePage.style.display = 'none';
        chatPage.style.display = 'block';
        history.pushState({}, '', '/chat');
    } else {
        chatPage.style.display = 'none';
        welcomePage.style.display = 'block';        
        history.pushState({}, '', '/');
        
    }
}

export function handlePopState() {
    const path = window.location.pathname;
    navigate(path);
}
