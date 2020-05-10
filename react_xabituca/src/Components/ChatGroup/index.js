import React from 'react';
import './styles.css'
import logo from '../../Assets/logo.png'

function ChatGroup({ group, setSelectedGroup }) {
    const { photo, name, lastMessage } = group

    return (
        <div className="chat-group-box" onClick={() => setSelectedGroup(group)}>
            <div className="chat-group-img-wrapper">
                <img className="chat-group-img" src={photo} alt=" " />
            </div>

            <div className="chat-group-right-wrapper">
                <div className="chat-group-title">{name}</div>
                <div className="chat-group-last-message">
                    {`${lastMessage.userGroup.user.nickname}: ${lastMessage.content}`}
                </div>

            </div>

        </div>
    )

}

export default ChatGroup