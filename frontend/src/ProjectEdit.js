import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from './AppNavbar';
import SimpleWriteApi from './api/SimpleWriteApi';

class ProjectEdit extends Component {

    emptyItem = {
        bezeichnung: '',
        fachId: ''
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
            const project = await (await fetch(`/api/project/get/${this.props.match.params.id}`)).json();
            this.setState({item: project});
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
            await SimpleWriteApi.doFetch('/api/project/save/' + (item.id), 'PUT', item,"Project");
            this.props.history.push('/projects/'+(item.id));
        }else{
            await SimpleWriteApi.doFetch('/api/project/create/', 'POST', item, "Project");
            this.props.history.push('/projects/new');
        }
    }

    render() {
        const {item} = this.state;
        const title = <h2>{item.id ? 'Edit Project' : 'Add Project'}</h2>;
    
        return <div>
            <AppNavbar/>
            <Container>
                {title}
                <Form onSubmit={this.handleSubmit}>
                    <FormGroup>
                        <Label for="bezeichnung">Bezeichnung</Label>
                        <Input type="text" name="bezeichnung" id="bezeichnung" value={item.bezeichnung || ''}
                               onChange={this.handleChange} />
                    </FormGroup>
                    <FormGroup>
                        <Label for="fachid">Fachid</Label>
                        <Input type="text" name="fachId" id="fachId" value={item.fachId || ''}
                               onChange={this.handleChange} />
                    </FormGroup>
                    <FormGroup>
                        <Button color="primary" type="submit">Save</Button>{' '}
                        <Button color="secondary" tag={Link} to="/projects">Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    }
}
export default withRouter(ProjectEdit);