import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom'

class UserList extends Component {

    constructor(props) {
        super(props);
        this.state = {users: []};
    }

    componentDidMount() {
        fetch('/api/user/getAll')
            .then(response => response.json())
            .then(data => this.setState({users: data}));
    }

    render() {
        const {users, isLoading} = this.state;
    
        if (isLoading) {
            return <p>Loading...</p>;
        }
    
        const userList = users.map(user => {
            return <tr key={user.id}>
                <td style={{whiteSpace: 'nowrap'}}>{user.firstname}</td>
                <td>{user.lastname}</td>
                <td>{user.email}</td>
                <td>{user.personalNr}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"users/" + user.id}>Edit</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });
    
        return <div>
                <Container fluid>
                    <AppNavbar/>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/users/new">Add User</Button>
                    < /div>
                    <h3>Users</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="30%">Vorname</th>
                            <th width="30%">Nachname</th>
                            <th width="30%">Email</th>
                            <th width="30%">PersonalNr</th>
                            <th width="40%">Actions</th>
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