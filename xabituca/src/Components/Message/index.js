import React from 'react';
import './styles.css'

function Message(props) {

    const cssClasses = {
        self : {
            messageBox : "self-message-box",
            messageContent : "self-message-content"
        },
        other : {
            messageBox : "other-message-box",
            messageContent : "other-message-content"
        }
    }

    function messageOwnerDiffer(user, messageUser) {
        if(user === messageUser){
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
        <div class="message-box">
            <div class= { messageOwnerDiffer(props.user, props.message.user).messageBox }>
                <div class={ messageOwnerDiffer(props.user, props.message.user).messageContent }>
                    { (props.user !== props.message.user ) && <div style = {{fontWeight:"bold"}} > { props.message.user } </div>}
                    <div> { props.message.text } </div>
                </div>
            </div>
        </div>
    )
}        
export default Message