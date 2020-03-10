import React from 'react';
import './styles.css'
import logo from '../../Assets/logo.png'

function ChatGroup(props) {

    return (
        <div className="chat-group-box">
            <div className="chat-group-img-wrapper">
                <img className="chat-group-img" src={props.chat.photo} alt={props.chat.groupName} />
            </div>

            <div className="chat-group-right-wrapper">
                <div className="chat-group-title">{props.chat.groupName}</div>
                <div className="chat-group-last-message">
                    {`${props.chat.lastMessage.user}: ${props.chat.lastMessage.message}`}
                </div>
                
            </div>

        </div>
    )

}

export default ChatGroup