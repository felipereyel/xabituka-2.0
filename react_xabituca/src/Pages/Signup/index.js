import React from 'react';
import './styles.css'
import logo from '../../Assets/logo.png'
import { useHistory } from 'react-router-dom'

function SignupPage() {
  const history = useHistory()

  function submitSignupForm(event) {
    event.preventDefault()
    history.push('/main')
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
              <input placeholder="Name" className="input" type="text" />
              <input placeholder="Login" className="input" type="text" />
              <input placeholder="Password" className="input" type="password" />
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
