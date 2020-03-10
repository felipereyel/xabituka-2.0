import React from 'react';
import './styles.css'
import logo from '../../Assets/logo.png'
import ChatGroup from '../../Components/ChatGroup'

function MainPage() {
  const chatList = [
    {
      groupName: "labprog",
      lastMessage: {
        message: "deram o gás",
        user: "gabilu"
      },
      photo: logo
    },
    {
      groupName: "labprog",
      lastMessage: {
        message: "deram o gás",
        user: "gabilu"
      },
      photo: logo
    },

    {
      groupName: "labprog",
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
              <ChatGroup chat={chat} />
            ))
          }
        </div>
        <div className="main-chat"></div>
      </div>
    </div>
  );
}

export default MainPage;
