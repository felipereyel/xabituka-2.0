import React, { useState, useEffect } from 'react'
import { useHistory } from 'react-router-dom'
import unicorn from '../../Assets/unicorn.gif'
import './styles.css'
import logo from '../../Assets/logo.png'
import ChatGroup from '../../Components/ChatGroup'
import ChatBox from '../../Components/ChatBox'
import api from '../../Services/api'
import { notification, Modal, Cascader, Button } from 'antd'
import { UserAddOutlined, CloseOutlined, UsergroupAddOutlined } from '@ant-design/icons';


function MainPage() {
  const [selectedGroup, setSelectedGroup] = useState(null);
  const [chatList, setChatList] = useState([]);
  const [newUser, setNewUser] = useState({ isAdding: false, data: {} })
  const [allUsers, setAllUsers] = useState({ list: [], loading: true })

  useEffect(() => {
    async function loadAllUsers() {
      try {
        const res = await api.get('/users')
        const data = await res.data

        if (data.success) {
          console.log(data.users)
          await setAllUsers({
            list: data.users,
            loading: false
          })
        }
        else {
          const args = {
            message: 'Erro',
            description: 'Erro ao carregar lista de usuários.',
          }

          notification.open(args)
        }
      }
      catch (err) {
        const args = {
          message: 'Erro',
          description: 'Erro ao carregar lista de usuários.',
        }

        notification.open(args)
      }
    }

    loadAllUsers()
  }, [])
  // const [newUserIsAdmin, setNewUserIsAdmin] = useState(false)
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

  async function handleAddPersonToGroup() {
    console.log({
      selectedGroup,
      user: newUser.data
    })

    try {
      console.log(`/membership/${selectedGroup.id}/add/${newUser.data.id}`)
      const res = await api.get(`/membership/${selectedGroup.id}/add/${newUser.data.id}`, {
        headers: {
          Authorization: localStorage.getItem('token')
        }
      })
      const data = await res.data

      if (data.success === true) {
        const args = {
          message: 'Sucesso',
          description: `Usuário (${newUser.data.nickname}) adicionado.`,
        }

        notification.open(args)
      }
      else {
        console.log(data.reason)

        const args = {
          message: 'Erro',
          description: `Usuário (${newUser.data.nickname}) não foi adicionado.`,
        }

        notification.open(args)
      }
    }
    catch (err) {
      console.log(err)

      const args = {
        message: 'Erro',
        description: `Usuário (${newUser.data.nickname}) não foi adicionado.`,
      }

      notification.open(args)
    }
  }

  function filter(inputValue, path) {
    return path.some(option => option.label.toLowerCase().indexOf(inputValue.toLowerCase()) > -1);
  }

  return (
    <div className="main-wrapper">
      <Modal
        title="Adicionar usuário ao grupo"
        visible={newUser.isAdding}
        onCancel={() => setNewUser({ isAdding: false, data: {} })}
        width='80%'
        footer={[
          <Button key="confirm" onClick={() => handleAddPersonToGroup()}>
            Adicionar
            </Button>,
        ]}
      >
        <div>Escolha abaixo o usuário a ser adicionado</div>
        <Cascader
          style={{ width: '80%' }}
          options={allUsers.list.map(
            user => ({
              value: user,
              label: `${user.nickname} - ${user.fullName}`
            })
          )}
          onChange={(value) => setNewUser({ isAdding: true, data: value[0] })}
          placeholder="Please select"
          showSearch={{ filter }}
        />
      </Modal>

      <div className="white-box-main">
        <div className="main-chat-list-wrapper">
          <div className="main-chat-list">
            <div className="new-group">
              <div className="new-group-img-wrapper">
                <UsergroupAddOutlined className="new-group-img" />
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
                    <CloseOutlined className="exit-group" />
                  </div>
                  <div className="main-group-name">
                    {selectedGroup.name}
                  </div>
                  <div className="add-person-wrapper">
                    <UserAddOutlined
                      className="add-person"
                      onClick={() => setNewUser({ isAdding: true, data: {} })}
                    />
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
