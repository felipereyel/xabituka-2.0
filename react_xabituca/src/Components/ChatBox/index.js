import React, { useState, useEffect } from 'react';
import { useHistory } from 'react-router-dom'
import './styles.css'
import Message from '../../Components/Message'
import { notification } from 'antd'
import api from '../../Services/api'
import unicornicon from '../../Assets/unicorn-icon.png'
import send from '../../Assets/send.png'

function ChatBox({ groupId, unselectGroup }) {

  const user = "reyel"
  const [messageList, setMessageList] = useState([]);
  const [message, setMessage] = useState('')
  const history = useHistory()

  const username = localStorage.getItem('nickname')

  useEffect(() => {
    async function fetchMessageList() {
      try {
        const res = await api.get(`/messages/${groupId}`, {
          headers: {
            Authorization: localStorage.getItem('token')
          }
        })
        const data = await res.data
        console.log(data);

        if (data.success) {
          setMessageList(data.messages)
        }
        else {
          const args = {
            message: 'Erro',
            description: 'Erro ao carregar mensagens. Por favor, faça o login novamente.',
          }

          notification.open(args)
          history.push('/login')
          localStorage.clear()
        }
      }
      catch (err) {
        const args = {
          message: 'Erro',
          description: 'Erro no servidor. Tente novamente mais tarde',
        }

        notification.open(args)
      }
    }

    fetchMessageList()
  }, [groupId]);

  async function sendMessage(event) {
    event.preventDefault()
    await setMessage('')

    try {
      const res = await api.post(`/messages/${groupId}`,
        {
          content: message,
          userName: localStorage.getItem('nickname'),
        },
        {
          headers: {
            Authorization: localStorage.getItem('token')
          }
        }
      )
      const data = await res.data
      console.log(data)
      await setMessageList(data.messages)
    }
    catch (err) {
      console.log(err)

      const args = {
        message: 'Erro',
        description: 'Mensagem não enviada. Verifique sua conexão.',
      }

      notification.open(args)
    }
  }

  return (
    <>
      <div className="chat-box">
        {
          messageList.map((message) => (
            <Message key={message.id} message={message} username={username} />
          ))
        }
      </div>
      <form
        className="main-input-wrapper"
        onSubmit={(event) => { message.length > 0 && sendMessage(event) }}
      >
        <img
          onClick={unselectGroup}
          className="main-unicorn-icon-button"
          src={unicornicon}
          alt="Voltar"
        />
        <input
          className="main-input-message"
          type="text"
          placeholder="Digite uma mensagem"
          value={message}
          onInput={(event) => setMessage(event.target.value)}
        />
        <img
          className="main-send-button"
          src={send}
          alt="Enviar"
          onClick={(event) => { message.length > 0 && sendMessage(event) }}
        />
      </form>
    </>
  )
}

export default ChatBox;

