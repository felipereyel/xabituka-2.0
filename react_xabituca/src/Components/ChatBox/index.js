import React, { useState, useEffect } from 'react';
import './styles.css'
import Message from '../../Components/Message'


function ChatBox({ groupId }) {

    const user = "reyel"
    const [messageList, setMessageList] = useState([]);

    useEffect(() => {
        async function fetchMessageList() {
          const res = await fetch(`http://felipereyel.pythonanywhere.com/message-list/${groupId}`)
          const data = await res.json()
          console.log(data);
          setMessageList(data)
        }
    
        fetchMessageList()
      }, [groupId]);

    // const messageList = [
    //     {
    //         user: "brilhante",
    //         text: "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
    //     },

    //     {
    //         user: "reyel",
    //         text: "oi, tudo bem?"
    //     },

    //     {
    //         user: "brilhante",
    //         text: "oi, tudo bem?"
    //     }
    // ]

    return (
        <div className="chat-box">
            {
                messageList.map((message) => (
                    <Message message={message} user={user} />
                ))
            }
        </div>
    )
}

export default ChatBox;
