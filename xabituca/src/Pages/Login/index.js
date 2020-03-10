import React from 'react';
import './styles.css'
import logo from '../../Assets/logo.png'
import { useHistory } from 'react-router-dom'

function LoginPage() {
  const history = useHistory()

  function submit(e) {
    e.preventDefault()
    history.push('/main')
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
              <input placeholder="Login" className="input" type="text" />
              <input placeholder="Password" className="input" type="password" />
              <button
                className="submit-button"
                onClick={(e) => submit(e)}
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
