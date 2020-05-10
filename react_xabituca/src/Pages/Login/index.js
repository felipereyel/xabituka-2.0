import React, { useState, useEffect } from 'react';
import './styles.css'
import logo from '../../Assets/logo.png'
import { useHistory } from 'react-router-dom'
import api from '../../Services/api'
import { notification } from 'antd';

function LoginPage() {
  const history = useHistory()

  const [login, setLogin] = useState('')
  const [password, setPassword] = useState('')


  async function handleLogin(e) {
    e.preventDefault()

    try {
      const res = await api.get('/users/login', {
        params: {
          nickname: login,
          psswd: password
        }
      })

      const data = await res.data
      console.log(data)

      if (data.success === true) {
        await localStorage.setItem('token', data.token)
        await localStorage.setItem('nickname', data.user.nickname)
        await localStorage.setItem('fullname', data.user.full_name)
        history.push('/main')
      }

      else {
        const args = {
          message: 'Erro',
          description: 'Usuário e/ou senha incorretos.',
        }

        notification.open(args)
      }

      console.log(res)
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

  function changeToSignupScreen(event) {
    history.push('/signup')
  }

  return (
    <div className="main-wrapper">
      <div className="white-box">
        <div className="right-align">
          <div className="button-login-wrapper">
            <button
              className="button-login"
              onClick={() => changeToSignupScreen()}
            >Signup</button>
          </div>
          <div className="form-wrapper">
            <form>
              <h1>Login</h1>
              {/* <input placeholder="Name" className="input" type="text" /> */}
              <input
                className="input"
                placeholder="Login"
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
                onClick={(e) => handleLogin(e)}
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

export default LoginPage;
