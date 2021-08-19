import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from './AppNavbar';
import SimpleWriteApi from './api/SimpleWriteApi';

class RoleEdit extends Component {

    emptyItem = {
        roleName: '',
        roleDescription: ''
    };

    constructor(props) {
        super(props);
        this.state = {
            item: this.emptyItem
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    async componentDidMount() {
        if (this.props.match.params.id !== 'new') {
            const role = await (await fetch(`/api/role/get/${this.props.match.params.roleid}`)).json();
            this.setState({item: role});
        }
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let item = {...this.state.item};
        item[name] = value;
        this.setState({item});
    }

    async handleSubmit(event) {
        event.preventDefault();
        const {item} = this.state;
    
        if(item.id){
            SimpleWriteApi.doFetch('/api/role/save/' + (item.id), 'PUT', item,"Role");
            this.props.history.push('/roles/'+(item.id));
        }else{
            SimpleWriteApi.doFetch('/api/role/create/', 'POST', item, "Role");
            this.props.history.push('/roles/new');
        }
    }

    render() {
        const {item} = this.state;
        const title = <h2>{item.id ? 'Edit Role' : 'Add Role'}</h2>;
    
        return <div>
            <AppNavbar/>
            <Container>
                {title}
                <Form onSubmit={this.handleSubmit}>
                    <FormGroup>
                        <Label for="roleName">Bezeichnung</Label>
                        <Input type="text" name="roleName" id="roleName" value={item.roleName || ''}
                               onChange={this.handleChange} />
                    </FormGroup>
                    <FormGroup>
                        <Label for="roleDescription">Beschreibung</Label>
                        <Input type="text" name="roleDescription" id="roleDescription" value={item.roleDescription || ''}
                               onChange={this.handleChange} />
                    </FormGroup>
                    <FormGroup>
                        <Button color="primary" type="submit">Save</Button>{' '}
                        <Button color="secondary" tag={Link} to="/roles">Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    }
}
export default withRouter(RoleEdit);