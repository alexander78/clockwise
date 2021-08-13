import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from './AppNavbar';

class ProjectEdit extends Component {

    emptyItem = {
        bezeichnung: '',
        fachid: ''
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
}
export default withRouter(ProjectEdit);