import React, { Component } from 'react';
import './App.css';
import Home from './Home';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import ProjectList from './ProjectList';
import UserList from './UserList';
import ProjectEdit from "./ProjectEdit";

class App extends Component {
  render() {
    return (
        <Router>
          <Switch>
            <Route path='/' exact={true} component={Home}/>
            <Route path='/projects' exact={true} component={ProjectList}/>
            <Route path='/projects/:id' component={ProjectEdit}/>
            <Route path='/users' exact={true} component={UserList}/>
          </Switch>
        </Router>
    )
  }
}

export default App;