import React, { useState } from 'react';
import './styles.css'
import logo from '../../Assets/logo.png'
import { useHistory } from 'react-router-dom'
import { notification } from 'antd'
import api from '../../Services/api'

function SignupPage() {
  const history = useHistory()

  const [name, setName] = useState('')
  const [login, setLogin] = useState('')
  const [password, setPassword] = useState('')

  async function submitSignupForm(event) {
    event.preventDefault()

    try {
      const res = await api.post('/sign-up', {
        nickname: login,
        full_name: name,
        psswd: password
      })

      const data = await res.data

      console.log(data)

      if (data.created === true) {
        const args = {
          message: 'Sucesso',
          description: 'Usuário criado com sucesso. Tente fazer o login para começar a usar o aplicativo =)',
        }

        notification.open(args)
        history.push('/login')
      }

      else {
        const args = {
          message: 'Erro',
          description: 'O nickname escolhido já está em uso. Por favor escolha outro.',
        }

        notification.open(args)
      }
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

  function changeToLoginScreen(event) {
    history.push('/login')
  }

  return (
    <div className="main-wrapper">
      <div className="white-box">
        <div className="right-align">
          <div className="button-login-wrapper">
            <button
              className="button-login"
              onClick={() => changeToLoginScreen()}
            >Login</button>
          </div>
          <div className="form-wrapper">
            <form>
              <h1>Sign up</h1>
              <input
                className="input"
                placeholder="Full Name"
                type="text"
                defaultValue={name}
                onInput={(e) => setName(e.target.value)}
              />
              <input
                className="input"
                placeholder="Nickname"
                type="text"
                defaultValue={login}
                onInput={(e) => setLogin(e.target.value)}
              />
              <input
                className="input"
                placeholder="Password"
                type="password"
                defaultValue={password}
                onInput={(event) => setPassword(event.target.value)}
              />
              <button
                className="submit-button"
                onClick={(event) => submitSignupForm(event)}
              >Submit
              </button>
            </form>
          </div>
        </div>
        <div className="left-align">
          <img className="logo" src={logo} alt="logo" />
        </div>
      </div>
    </div>
  );
}

export default SignupPage;
