import React from 'react'
import { BrowserRouter, Route, Switch, Redirect } from 'react-router-dom'

// import isAuthenticated from '../src/auth'
// import LoginPage from './Pages/Signup'
import SignupPage from './Pages/Signup'
import LoginPage from './Pages/Login'
import MainPage from './Pages/Main'

function PrivateRoute({ component: Component, ...rest }) {
    const isLogged = true

    return (
      <Route
        {...rest}
        render={(props) => 
        isLogged
            ? (<Component {...props}/>)
            : (<Redirect to={{ pathname: "/", state: { from: props.location }}} />)
        }
      />
    );
  }

class Routes extends React.Component {
    render() {
        return(
            <BrowserRouter>
                <Switch>
                    {/* <PrivateRoute exact path='/grid' component={GridPage}/> */}
                    {/* <PrivateRoute exact path='/sign' component={OverviewPage}/> */}
                    <Route exact path='/signup' component={SignupPage}/>
                    <Route exact path='/login' component={LoginPage}/>
                    <Route exact path='/main' component={MainPage}/>
                    <Route path='/' component={LoginPage}/>
                </Switch>
            </BrowserRouter>
        );
    }
}

export default Routes;