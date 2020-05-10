import React, { useState, useEffect } from 'react'
import unicorn from '../../Assets/unicorn.gif'
import './styles.css'
import logo from '../../Assets/logo.png'
import ChatGroup from '../../Components/ChatGroup'
import ChatBox from '../../Components/ChatBox'
import api from '../../Services/api'

function MainPage() {
  const [selectedGroup, setSelectedGroup] = useState(null);
  const [chatList, setChatList] = useState([]);

  // useEffect(() => {
  //   async function fetchChatList() {
  //     console.log(localStorage.getItem('token'))

  //     const res = await api.get("/groups", {
  //       headers: {
  //         Authorization: localStorage.getItem('token')
  //       }
  //     })

  //     const data = res.data
  //     console.log(data)
  //     setChatList(data)
  //   }

  //   fetchChatList()
  // }, []);

  return (
    <div className="main-wrapper">
      <div className="white-box-main">
        <div className="main-chat-list-wrapper">
          <div className="main-chat-list">
            {
              chatList.map(chat => (
                <ChatGroup key={chat.id} chat={chat} setSelectedGroup={setSelectedGroup} />
              ))
            }
          </div>
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
                <ChatBox groupId={selectedGroup.id} unselectGroup={() => setSelectedGroup(null)} />
              </>
            )
            : (
              <>
                {/* <img className="main-no-group-selected" src={unicorn} alt="Escolha um chat" /> */}
                <div className="main-zoom-unicorn-wrapper">
                  <div className="main-zoom-unicorn">
                  </div>
                </div>
              </>
            )
          }
        </div>
      </div>
    </div>
  );
}

export default MainPage;
