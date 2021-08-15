import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from './AppNavbar';

class AbrufEdit extends Component {

    emptyItem = {
        abrufNummer: '',
	    validFrom: '',
	    validTo: '',
        rahmenBmNummer: ''
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
        if (this.props.match.params.abrufid !== 'new') {
            const abruf = await (await fetch(`/api/abruf/get/${this.props.match.params.abrufid}`)).json();
            this.setState({item: abruf});
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
            await fetch('/api/abruf/save/' + (item.id), {
                method: 'PUT',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(item),
            });
            this.props.history.push('/abruf/'+(item.id)+'/'+(this.props.match.params.projectid));
        }else{
            await fetch('/api/abruf/create/'+(this.props.match.params.projectid), {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(item),
            });
            this.props.history.push('/abruf/new/'+(this.props.match.params.projectid));
        }
    }

    render() {
        const {item} = this.state;
        const title = <h2>{item.id ? 'Edit Abruf' : 'Add Abruf'}</h2>;
    
        return <div>
            <AppNavbar/>
            <Container>
                {title}
                <Form onSubmit={this.handleSubmit}>
                    <FormGroup>
                        <Label for="rahmenBmNummer">Rahmen-BM-Nr</Label>
                        <Input type="text" name="rahmenBmNummer" id="rahmenBmNummer" value={item.rahmenBmNummer || ''}
                               onChange={this.handleChange} />
                    </FormGroup>
                    <FormGroup>
                        <Label for="abrufNummer">Abrufnummer</Label>
                        <Input type="text" name="abrufNummer" id="abrufNummer" value={item.abrufNummer || ''}
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
                        <Button color="secondary" tag={Link} to={"/abrufe/" + this.props.match.params.projectid}>Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    }
}
export default withRouter(AbrufEdit);