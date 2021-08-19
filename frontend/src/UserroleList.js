import React, { Component } from 'react';
import { Button, Table, ButtonGroup, Container, Form, FormGroup } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom'
import RoleSelectionComponent from './RoleSelectionComponent';

class UserroleList extends Component {

    constructor(props) {
        super(props);
        this.state = {
                userroles: [], 
                selectOptions : [],
                id: "",
                name: ""
            };
            this.remove = this.remove.bind(this);
        }

    async componentDidMount() {
        fetch(`/api/role/getAllByUser/${this.props.match.params.userid}`)
            .then(response => response.json())
            .then(data => this.setState({userroles: data}));
            
        fetch(`/api/role/getAll`)
            .then(response => response.json())
            .then(data => this.setState({selectOptions: data}));
        
    }

    async remove(roleId) {
        await fetch(`/api/role/removeFromUser/${roleId}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedroles = [...this.state.userroles].filter(i => i.roleId !== roleId);
            this.setState({userroles: updatedroles});
        });
    }

    async handleSubmit(event) {
        event.preventDefault();
        const {item} = this.state;
    
        if(item.id){
        //     await SimpleWriteApi.doFetch('/api/user/addRole/'+(item.id), 'PUT', item, "User");
        //     this.props.history.push('/users/'+(item.id));
        // }else{
        //     await SimpleWriteApi.doFetch('/api/user/create', 'POST', item, "User");
        //     this.props.history.push('/users/new');
        }
    }

    async handleRoleSelectionChange(event){
        console.log(event.target.value)
    }

    render() {
        const {userroles, selectOptions, isLoading} = this.state;
        console.log(this.state.selectOptions);
        if (isLoading) {
            return <p>Loading...</p>;
        }
        const UserRoleList = userroles.map(role => {
            return <tr key={role.roleId}>
                <td style={{whiteSpace: 'nowrap'}}>{role.roleName}</td>
                <td>{role.roleDescription}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="danger" onClick={() => this.remove(role.roleId)}>Delete</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });

        return <div>
                <Container fluid>
                    <AppNavbar/>
                    <h3>Userroles</h3>
                    <Form onSubmit={this.handleSubmit}>
                        <div class="container-fluid">
                            <div class="row align-items-end">
                                <div class="col">
                                    <RoleSelectionComponent roles={selectOptions} onChangeRole={this.handleRoleSelectionChange}/>
                                </div>
                                <div class="col">
                                    <Button color="primary" type="submit">Add</Button>{' '}
                                </div>
                            </div>
                        </div>
                    </Form>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="20%">Rolle</th>
                            <th width="30%">Beschreibung</th>
                            <th width="50%">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {UserRoleList}
                        </tbody>
                    </Table>
                    <div className="float-right">
                        <Button color="secondary" tag={Link} to="/users">Cancel</Button>
                    </div>
                </Container>
            </div>
    }
}

export default UserroleList;