import React from 'react';
import './styles.css'
import logo from '../../Assets/logo.png'

function ChatGroup({ chat, setSelectedGroup }) {

    return (
        <div className="chat-group-box" onClick={() => setSelectedGroup(chat)}>
            <div className="chat-group-img-wrapper">
                <img className="chat-group-img" src={chat.photo} alt=" " />
            </div>

            <div className="chat-group-right-wrapper">
                <div className="chat-group-title">{chat.name}</div>
                <div className="chat-group-last-message">
                    {`${chat.lastMessage.userName}: ${chat.lastMessage.content}`}
                </div>
                
            </div>

        </div>
    )

}

export default ChatGroup