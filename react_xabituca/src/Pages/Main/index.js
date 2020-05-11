import React, { useState, useEffect } from 'react'
import { useHistory } from 'react-router-dom'
import unicorn from '../../Assets/unicorn.gif'
import './styles.css'
import logo from '../../Assets/logo.png'
import ChatGroup from '../../Components/ChatGroup'
import ChatBox from '../../Components/ChatBox'
import api from '../../Services/api'
import { notification } from 'antd'
import {
  UserAddOutlined,
  CloseOutlined,
  UsergroupAddOutlined
} from '@ant-design/icons';


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
            <div className="new-group">
              <div className="new-group-img-wrapper">
                <UsergroupAddOutlined className="new-group-img"/>
              </div>
              <div className="chat-group-right-wrapper">
                <div className="chat-group-title">
                    Criar novo grupo
                </div>
                <div className="chat-group-last-message">
                    Venha se divertir no chat!
                </div>
              </div>
            </div>
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
                  <div className="exit-group-wrapper">
                    <CloseOutlined className="exit-group"/>
                  </div>
                  <div className="main-group-name">
                    {selectedGroup.name}
                  </div>
                  <div className="add-person-wrapper">
                    <UserAddOutlined className="add-person"/>
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
