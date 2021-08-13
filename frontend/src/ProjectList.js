import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom'

class ProjectList extends Component {

    constructor(props) {
        super(props);
        this.state = {projects: []};
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        fetch('/api/project/getAll')
            .then(response => response.json())
            .then(data => this.setState({projects: data}));
    }

    async remove(id) {
        await fetch(`/api/project/delete/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedprojects = [...this.state.projects].filter(i => i.id !== id);
            this.setState({projects: updatedprojects});
        });
    }

    render() {
        const {projects, isLoading} = this.state;
    
        if (isLoading) {
            return <p>Loading...</p>;
        }
    
        const projectList = projects.map(project => {
            return <tr key={project.id}>
                <td style={{whiteSpace: 'nowrap'}}>{project.bezeichnung}</td>
                <td>{project.fachId}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"projects/" + project.id}>Edit</Button>
                        <Button size="sm" color="danger" onClick={() => this.remove(project.id)}>Delete</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });
    
        return <div>
                <Container fluid>
                    <AppNavbar/>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/projects/new">Add Project</Button>
                    < /div>
                    <h3>Projects</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="30%">Bezeichnung</th>
                            <th width="30%">FachId</th>
                            <th width="40%">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {projectList}
                        </tbody>
                    </Table>
                </Container>
            </div>
                                                                                                                                                                              
    }
}

export default ProjectList;