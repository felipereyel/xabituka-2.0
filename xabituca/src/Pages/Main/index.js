import React, { useState, useEffect } from 'react';
import unicorn from '../../Assets/unicorn.gif'
import send from '../../Assets/send.png'
import clip from '../../Assets/clip.png'
import './styles.css'
import logo from '../../Assets/logo.png'
import ChatGroup from '../../Components/ChatGroup'
import ChatBox from '../../Components/ChatBox'

function MainPage() {
  const [selectedGroup, setSelectedGroup] = useState(null);
  const [chatList, setChatList] = useState([]);

  useEffect(() => {
    async function fetchChatList() {
      const res = await fetch("http://felipereyel.pythonanywhere.com/chat-list")
      const data = await res.json()
      console.log(data);
      setChatList(data)
    }

    fetchChatList()
  }, []);

  // const chatList = [
  //   {
  //     groupName: "LABPROG1",
  //     lastMessage: {
  //       message: "reyel é gay",
  //       user: "brilhante"
  //     },
  //     photo: logo
  //   },
  //   {
  //     groupName: "vai dar bom",
  //     lastMessage: {
  //       message: "q q isso aqui",
  //       user: "reyel"
  //     },
  //     photo: logo
  //   },

  //   {
  //     groupName: "eita eita",
  //     lastMessage: {
  //       message: "deram o gás",
  //       user: "gabilu"
  //     },
  //     photo: logo
  //   }
  // ]

  return (
    <div className="main-wrapper">
      <div className="white-box-main">
        <div className="main-chat-list">
          {
            chatList.map(chat => (
              <ChatGroup key={chat.groupId} chat={chat} setSelectedGroup={setSelectedGroup} />
            ))
          }
        </div>
        <div className="main-chat">
          {(selectedGroup !== null)
            ? (
              <>
                <div className="main-group-wrapper">
                  <div className="main-group-name">
                    {selectedGroup.name}
                  </div>
                </div>
                <ChatBox groupId={selectedGroup.id} />
                <div className="main-input-wrapper">
                  <img className="main-clip-button" src={clip} alt="Anexar" />
                  <input className="main-input-message" />
                  <img className="main-send-button" src={send} alt="Enviar" />
                </div>
              </>
            )
            : (
              <img className="main-no-group-selected" src={unicorn} alt="Escolha um chat" />
            )
          }
        </div>
      </div>
    </div>
  );
}

export default MainPage;
