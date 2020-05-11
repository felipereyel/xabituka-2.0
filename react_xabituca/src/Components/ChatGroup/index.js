import React, { useEffect } from 'react';
import './styles.css'
import logo from '../../Assets/logo.png'

function ChatGroup({ group, setSelectedGroup }) {

    const { photo, name, lastMessage: lastMessageAsArray } = group
    let lastMessage = {
        userGroup: {
            user: {
                nickname: ""
            }
        },
        content: "Nenhuma mensagem enviada."
    }

    if (lastMessageAsArray.length > 0) {
        lastMessage = lastMessageAsArray[0]
    }

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