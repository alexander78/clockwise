import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom'

class UserList extends Component {

    constructor(props) {
        super(props);
        this.state = {users: [], filtered: []};

        this.getValueInput = this.getValueInput.bind(this);
    }

    componentDidMount() {
        fetch('/api/user/getAll')
            .then(response => response.json())
            .then(data => this.setState({users: data, filtered: data}));
    }

    getValueInput (evt) {
        const inputValue = evt.target.value;
        this.setState({ filtered: inputValue });
        this.filterNames(inputValue);
    }
      
    filterNames (inputValue) {
        // eslint-disable-next-line
        const { filtered, users } = this.state;
        this.setState({
          filtered: users.filter(item => 
             item.email.includes(inputValue)),
          });
    }

    render() {
        const {filtered, isLoading} = this.state;
    
        if (isLoading) {
            return <p>Loading...</p>;
        }
    
        const userList = filtered.map(user => {
            return <tr key={user.id}>
                <td style={{whiteSpace: 'nowrap'}}>{user.firstname}</td>
                <td>{user.lastname}</td>
                <td>{user.email}</td>
                <td>{user.personalNr}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"users/" + user.id}>Edit</Button>
                        <Button size="sm" color="primary" tag={Link} to={"workingtimemodels/" + user.id}>Workingtimemodel</Button>
                        <Button size="sm" color="primary" tag={Link} to={"userroles/" + user.id}>Userroles</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });
    
        return <div>
                <Container fluid>
                    <AppNavbar/>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/users/new">Add User</Button>
                    </div>
                    <h3>Users</h3>
                    <div className="float-right">
                        <input type="text" placeholder="Suche nach email..." onChange={ this.getValueInput }></input>
                    </div>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="15%">Vorname</th>
                            <th width="15%">Nachname</th>
                            <th width="25%">Email</th>
                            <th width="10%">PersonalNr</th>
                            <th width="35%">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {userList}
                        </tbody>
                    </Table>
                </Container>
            </div>
                                                                                                                                                                              
    }
}

export default UserList;