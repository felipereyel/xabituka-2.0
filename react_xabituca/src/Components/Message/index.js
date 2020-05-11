import React from 'react';
import './styles.css'

function Message({ message, username }) {
    // console.log(message)
    const { content, userGroup, createdAt } = message
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

    function timeConverter(timestamp){
        var a = new Date(timestamp);
        var months = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
        var year = a.getFullYear();
        var month = months[a.getMonth()];
        var date = a.getDate();
        var hour = a.getHours();
        var min = a.getMinutes();
        var sec = a.getSeconds();
        var time = date + ' ' + month + ' ' + year + ' ' + hour + ':' + min + ':' + sec ;
        return time;
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
                    <img src={messageUser.photo} className="message-rounded-image" />
                </div>
            }
            <div className={messageOwnerDiffer(username, messageUser.nickname).messageContent}>
                {(username !== messageUser.nickname) &&
                    <div className="message-user-name">
                        {messageUser.fullName}
                    </div>
                }
                {content}
                <span className={messageOwnerDiffer(username, messageUser.nickname).messageTime}>{timeConverter(createdAt)}</span>
            </div>
        </div>
    )
}

export default Message

