import React, { useState, useEffect } from 'react'
import { useHistory } from 'react-router-dom'
import unicorn from '../../Assets/unicorn.gif'
import './styles.css'
import logo from '../../Assets/logo.png'
import ChatGroup from '../../Components/ChatGroup'
import ChatBox from '../../Components/ChatBox'
import api from '../../Services/api'
import { notification } from 'antd';


function MainPage() {
  const [selectedGroup, setSelectedGroup] = useState(null);
  const [chatList, setChatList] = useState([]);

  const history = useHistory()


  useEffect(() => {
    async function fetchChatList() {
      try {

        const res = await api.get("/groups", {
          headers: {
            Authorization: localStorage.getItem('token')
          }
        })
        const data = res.data
        console.log(data)

        if (data.success === true) {
          setChatList(data.groups)
        }

        else {
          const args = {
            message: 'Erro',
            description: 'Erro ao carregar grupos. Por favor, faça o login novamente.',
          }

          notification.open(args)
          history.push('/login')
          localStorage.clear()
        }

        // setChatList(data)
      }
      catch (err) {
        console.log(err)

        const args = {
          message: 'Erro',
          description: 'Erro no servidor.',
        }

        notification.open(args)
      }

    }

    fetchChatList()
  }, []);

  return (
    <div className="main-wrapper">
      <div className="white-box-main">
        <div className="main-chat-list-wrapper">
          <div className="main-chat-list">
            {
              chatList.map(group => (
                <ChatGroup key={group.id} group={group} setSelectedGroup={setSelectedGroup} />
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
