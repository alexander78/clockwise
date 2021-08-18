import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from './AppNavbar';
import SimpleWriteApi from './api/SimpleWriteApi';

class WorkingtimeModelEdit extends Component {

    emptyItem = {
        vacationDays: '',
        validFrom: '',
        validTo: ''
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
        if (this.props.match.params.workingtimeModelId !== 'new') {
            console.log(this.props.match.params.workingtimeModelId);
            const workingtimemodel = await (await fetch(`/api/workingtimemodel/get/${this.props.match.params.workingtimeModelId}`)).json();
            this.setState({item: workingtimemodel});
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
            await SimpleWriteApi.doFetch('/api/workingtimemodel/save/' + (item.id), 'PUT', item, "Workingtimemodel");
            this.props.history.push('/workingtimemodel/' + (item.id)+'/'+(this.props.match.params.userid));
        }else{
            await SimpleWriteApi.doFetch('/api/workingtimemodel/create/' + (this.props.match.params.userid), 'POST', item, "Workingtimemodel");
            this.props.history.push('/workingtimemodel/new/' + (this.props.match.params.userid));
        }
    }

    render() {
        const {item} = this.state;
        const title = <h2>{item.id ? 'Edit WorkingtimeModel' : 'Add WorkingtimeModel'}</h2>;
    
        return <div>
            <AppNavbar/>
            <Container>
                {title}
                <Form onSubmit={this.handleSubmit}>
                    <FormGroup>
                        <Label for="vacationDays">Urlaubstage</Label>
                        <Input type="text" name="vacationDays" id="vacationDays" value={item.vacationDays || ''}
                               onChange={this.handleChange} />
                    </FormGroup>
                    <FormGroup>
                        <Label for="validFrom">Gültig von</Label>
                        <Input type="date" name="validFrom" id="validFrom" value={item.validFrom || ''}
                               onChange={this.handleChange} />
                    </FormGroup>
                    <FormGroup>
                        <Label for="validTo">Gültig bis</Label>
                        <Input type="date" name="validTo" id="validTo" value={item.validTo || ''}
                               onChange={this.handleChange} />
                    </FormGroup>
                    <FormGroup>
                        <Button color="primary" type="submit">Save</Button>{' '}
                        <Button color="secondary" tag={Link} to={"/workingtimemodels/" + this.props.match.params.userid}>Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    }
}
export default withRouter(WorkingtimeModelEdit);