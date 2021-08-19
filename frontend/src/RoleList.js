import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link, withRouter } from 'react-router-dom'

class RoleList extends Component {

    constructor(props) {
        super(props);
        this.state = {roles: []};
    }

    componentDidMount() {
        fetch('/api/role/getAll')
            .then(response => response.json())
            .then(data => this.setState({roles: data}));
    }

    render() {
        const {roles, isLoading} = this.state;
    
        if (isLoading) {
            return <p>Loading...</p>;
        }
    
        const rolelist = roles.map(role => {
            return <tr key={role.id}>
                <td style={{whiteSpace: 'nowrap'}}>{role.roleName}</td>
                <td>{role.roleDescription}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"/roles/" + role.id}>Edit</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });
    
        return( 
            <div>
                <Container fluid>
                    <AppNavbar/>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/roles/new">Add Role</Button>
                    </div>
                    <h3>Roles</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="30%">Bezeichnung</th>
                            <th width="30%">Beschreibung</th>
                            <th width="40%">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {rolelist}
                        </tbody>
                    </Table>
                </Container>
            </div>)
                                                                                                                                                                              
    }
}

export default withRouter(RoleList);