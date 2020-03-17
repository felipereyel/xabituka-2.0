import React from 'react';
import './styles.css'

function Message(props) {

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
        <div className={messageOwnerDiffer(props.user, props.message.userName).messageBox}>
            {(props.user !== props.message.userName) &&
                <div className="message-image-container">
                    <img src="https://static.turbosquid.com/Preview/001292/481/WV/_D.jpg" className="message-rounded-image" />
                </div>
            }
            <div className={messageOwnerDiffer(props.user, props.message.userName).messageContent}>
                { (props.user !== props.message.userName) &&
                    <div className="message-user-name">
                        {props.message.userName}
                    </div>
                }
                {props.message.content}
                <span className={messageOwnerDiffer(props.user, props.message.userName).messageTime}>8:40 AM, Today</span>
            </div>
        </div>
    )
}

export default Message

