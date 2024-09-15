import { navigate, handlePopState } from './navigate.js';
import { closePopup, openPopup } from './dialogue-boxes-utils.js';
import { html, render } from 'https://unpkg.com/lit@2?module';

document.addEventListener('DOMContentLoaded', (event) => {
    window.addEventListener('popstate', handlePopState);
    
    let currentUser;
    
    const usernameInput = document.querySelector('input[name=username]');
    const enterChatButton = document.getElementById('enter-chat-btn');
    const sendMessageButton = document.getElementById('send-msg-btn');
    const messageInput = document.querySelector('.type-message input');
    const chatBox = document.querySelector('.content');
    const dialogCloseButton = document.getElementById('close-btn');

    let ws = new WebSocket("ws://" + location.hostname + ":" + location.port + "/chat");
    
    
    ws.onopen = function() {
        console.log('Connected!');
    };
    
    
    const messages = [];
    ws.onmessage = function(event) {
        const data = JSON.parse(event.data);
        const type = data.type;
        const username = data.username;
        const message = data.message;
        console.log(data);
        
        let messageTemplate;
        if (type === 'error') {
            openPopup();
            dialogCloseButton.addEventListener('click', closePopup);
        } else {
            if (type === 'system') {
                navigate('/chat');
                messageTemplate = (username, message) => html`
                <p class="user-update">${username} ${message}!</p>
                 `;
            } else {
                const className = currentUser === username ? 'sent' : 'received';
                
                messageTemplate = (username, message) => html`
                    <div class="message ${className}">
                        <p class="username">${username}</p>
                        <p>${message}</p>
                    </div>`;
            }

            messages.push(messageTemplate(username, message));
        
            render(html`${messages}`, chatBox);
        } 
        
       
    };
    
    enterChatButton.addEventListener('click', () => {
        currentUser = usernameInput.value;
        
        if (!usernameInput.value) {
            triggerErrorTransition(usernameInput);
        } else {
            ws.send(JSON.stringify({ type: "username", username: currentUser }));
        }
    });
    
    
    
    sendMessageButton.addEventListener('click', (e) => sendMessage(e));
    
    function sendMessage(event) {
        console.log(event);
        
        const message = messageInput.value;
        
        if (message && event.type === 'click' || event.key === 'Enter') {
            ws.send(JSON.stringify({ type: "message", message: message}));
            messageInput.value = ''; 
        } else {
            triggerErrorTransition(messageInput);
        }
        
    }
    
    ping(ws);
});

function ping(ws) {
    setInterval(() => {
        if (ws.readyState === WebSocket.OPEN) {
            ws.send(JSON.stringify({ type: 'ping' }));
        }
    }, 30000);
}

function triggerErrorTransition(input) {
    input.style.transition = 'border-color 1s ease';
    input.style.border = '0.2em solid red';
    setTimeout(() => {
        input.style.borderColor = '';
    }, 1000);
}




