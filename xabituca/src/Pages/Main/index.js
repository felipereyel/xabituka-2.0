import React, { useState } from 'react';
import unicorn from '../../Assets/unicorn.gif'
import './styles.css'
import logo from '../../Assets/logo.png'
import ChatGroup from '../../Components/ChatGroup'
import ChatBox from '../../Components/ChatBox'

function MainPage() {
  const [selectedGroup, setSelectedGroup] = useState(null);
  const chatList = [
    {
      groupName: "LABPROG1",
      lastMessage: {
        message: "reyel é gay",
        user: "brilhante"
      },
      photo: logo
    },
    {
      groupName: "vai dar bom",
      lastMessage: {
        message: "q q isso aqui",
        user: "reyel"
      },
      photo: logo
    },

    {
      groupName: "eita eita",
      lastMessage: {
        message: "deram o gás",
        user: "gabilu"
      },
      photo: logo
    }
  ]

  return (
    <div className="main-wrapper">
      <div className="white-box-main">
        <div className="main-chat-list">
          {
            chatList.map(chat => (
              <ChatGroup chat={chat} setSelectedGroup={setSelectedGroup} />
            ))
          }
        </div>
        <div className="main-chat">
          {(selectedGroup !== null)
            ? (
              <>
                <div className="main-group-wrapper">
                  <div className="main-group-name">
                    {selectedGroup.groupName}
                  </div>
                </div>
                <ChatBox />
                <div className="main-input-wrapper">
                  <input className="main-input-message" />
                </div>
              </>
            ) 
            :(
              <img className="main-no-group-selected" src= {unicorn} alt="Escolha um chat"/>
            )
         }
        </div>
      </div>
    </div>
  );
}

export default MainPage;
