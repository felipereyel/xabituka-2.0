import React from 'react';
import './styles.css'

function Message({ message, username }) {
    console.log(message)
    const { content, userGroup } = message
    const { user: messageUser } = userGroup

    const cssClasses = {
        self: {
            messageBox: "self-message-box",
            messageContent: "self-message-content",
            messageTime: "self-message-time"
        },
        other: {
            messageBox: "other-message-box",
            messageContent: "other-message-content",
            messageTime: "other-message-time"
        }
    }

    function messageOwnerDiffer(user, messageUser) {
        if (user === messageUser) {
            return cssClasses.self
        }
        return cssClasses.other
    }
    /* 
    Como será a Props?
        {
            user: "brilhante"
            text: "oi, tudo bem?"

        }
    */
    return (
        <div className={messageOwnerDiffer(username, messageUser.nickname).messageBox}>
            {(username !== messageUser.nickname) &&
                <div className="message-image-container">
                    <img src="https://static.turbosquid.com/Preview/001292/481/WV/_D.jpg" className="message-rounded-image" />
                </div>
            }
            <div className={messageOwnerDiffer(username, messageUser.nickname).messageContent}>
                {(username !== messageUser.nickname) &&
                    <div className="message-user-name">
                        {messageUser.fullName}
                    </div>
                }
                {content}
                <span className={messageOwnerDiffer(username, messageUser.nickname).messageTime}>8:40 AM, Today</span>
            </div>
        </div>
    )
}

export default Message

