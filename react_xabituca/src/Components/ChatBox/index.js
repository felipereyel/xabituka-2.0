import React, { useState, useEffect } from 'react';
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

  useEffect(() => {
    async function fetchMessageList() {
      const res = await api.get(`/messages/${groupId}`)
      const data = await res.data
      console.log(data);
      setMessageList(data)
    }

    fetchMessageList()
  }, [groupId]);

  async function sendMessage(event) {
    event.preventDefault()
    setMessage('')

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
      const data = res.data
      console.log(data)
      setMessageList(data.message)
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
            <Message key={message.id} message={message} user={user} />
          ))
        }
      </div>
      <form
        className="main-input-wrapper"
        onSubmit={sendMessage}
      >
        <img
          onClick={unselectGroup}
          className="main-unicorn-icon-button"
          src={unicornicon}
          alt="Voltar"
        />
        <input
          className="main-input-message"
          defaultValue={message}
          onChange={(event) => console.log(message) || setMessage(event.target.value)}
        />
        <img
          className="main-send-button"
          src={send}
          alt="Enviar"
          onClick={sendMessage}
        />
      </form>
    </>
  )
}

export default ChatBox;

