import React, { Component } from 'react';
import { Button, Table, ButtonGroup, Container, Form } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link, withRouter } from 'react-router-dom'
import RoleSelectionComponent from './RoleSelectionComponent';
import SimpleWriteApi from './api/SimpleWriteApi';

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
            this.handleSubmit = this.handleSubmit.bind(this);
            this.handleRoleSelectionChange = this.handleRoleSelectionChange.bind(this);
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
        await fetch(`/api/role/removeFromUser/${this.props.match.params.userid}/${roleId}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedroles = [...this.state.userroles].filter(i => i.id !== roleId);
            this.setState({userroles: updatedroles});
        });
    }

    async handleSubmit(event) {
        event.preventDefault();
        const {id, name} = this.state;
    
        if(id){
            SimpleWriteApi.doFetch(`/api/role/addToUser/${this.props.match.params.userid}/`+(id), 'PUT', "", name);
            this.props.history.push(`/userroles/${this.props.match.params.userid}`);
        }
    }

    async handleRoleSelectionChange(event){
        const {selectOptions} = this.state;
        let filteredRole = [...selectOptions].filter(role => role.roleName === event.target.value)[0];
        console.log(filteredRole);
        this.setState({ id: filteredRole.id, name: filteredRole.roleName});
    }

    render() {
        const {userroles, selectOptions, isLoading} = this.state;
        console.log(this.state.selectOptions);
        if (isLoading) {
            return <p>Loading...</p>;
        }
        const UserRoleList = userroles.map(role => {
            return <tr key={role.id}>
                <td style={{whiteSpace: 'nowrap'}}>{role.roleName}</td>
                <td>{role.roleDescription}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="danger" onClick={() => this.remove(role.id)}>Delete</Button>
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

export default withRouter(UserroleList);