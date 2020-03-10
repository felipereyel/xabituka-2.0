import React from 'react';
import './styles.css'
import logo from '../../Assets/logo.png'

function ChatGroup({ chat }) {

    return (
        <div className="chat-group-box">
            <div className="chat-group-img-wrapper">
                <img className="chat-group-img" src={chat.photo} alt={chat.groupName} />
            </div>

            <div className="chat-group-right-wrapper">
                <div className="chat-group-title">{chat.groupName}</div>
                <div className="chat-group-last-message">
                    {`${chat.lastMessage.user}: ${chat.lastMessage.message}`}
                </div>
                
            </div>

        </div>
    )

}

export default ChatGroup